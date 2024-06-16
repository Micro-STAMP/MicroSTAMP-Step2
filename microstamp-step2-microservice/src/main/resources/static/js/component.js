function addComponent(){
    if(document.getElementById('isVisible').checked) {
        var checkedValue = new Boolean(1);
    }else{
        var checkedValue = new Boolean(0);
    }

    var component = {
        name: $("#component-name").val(),
        fatherId: $("#component-father").val(),
        border: $("#component-border").val(),
        isVisible: checkedValue,
        controlStructureId: $("#control_structure_id").val(),
    }

    if($("#component-type").val() == "controlledProcess")
        var componentType = $("#component-type").val() + "es";
    else var componentType = $("#component-type").val() + "s";

    if($("#component-name").val() == "Environment"){
        $("#addComponentModal").modal("hide");
        actual_modal = 0;
        $("#environmentRestrictionModal").modal("show");
    }else if($("#component-name").val() == ""){
        $("#addComponentModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/'+ componentType,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(component)
        });
    }
}

function editComponent(id){
    componentSelected = id;
    $.ajax({
        url: '/components/'+ id,
        type: 'get',
        success: function (data) {
            $("#component-edit-name").val(data.name);
            switch(data.type){
                case "Controller":
                    $("#component-edit-type").val("controller");
                    break;
                case "Actuator":
                    $("#component-edit-type").val("actuator");
                    break;
                case "Sensor":
                    $("#component-edit-type").val("sensor");
                    break;
                case "ControlledProcess":
                    $("#component-edit-type").val("controlledProcess");
                    break;
            }
            $("#component-edit-border").val(data.border);
            if(data.father == null)
                $("#component-edit-father").val("null");
            else
                $("#component-edit-father").val(data.father.id);
            if(data.isVisible == true)
                $("#editIsVisible").prop("checked", true);
            else
                $("#editIsNotVisible").prop("checked", true);

            componentSelectedOriginalType = $("#component-edit-type").val();
        },
    });
}

function sendEditedComponent(){
    if(document.getElementById('editIsVisible').checked) {
        var checkedValue = new Boolean(1);
    }else{
        var checkedValue = new Boolean(0);
    }

    var type = $("#component-edit-type").val();
    type = type.charAt(0).toUpperCase() + type.slice(1);

    var component = {
        name: $("#component-edit-name").val(),
        fatherId: $("#component-edit-father").val(),
        border: $("#component-edit-border").val(),
        isVisible: checkedValue,
        controlStructureId: $("#control_structure_id").val(),
        type: type,
    }

    if(componentSelectedOriginalType == "controlledProcess")
        var componentType = componentSelectedOriginalType + "es";
    else var componentType = componentSelectedOriginalType + "s";

    var father_id = $("#component-edit-father").val();

    $.when(checkOrphanRestriction(componentSelected, father_id)).done(function(a1){
        if($("#component-edit-name").val() == "Environment"){
            $("#editComponentModal").modal("hide");
            actual_modal = 1;
            $("#environmentRestrictionModal").modal("show");
        }else if($("#component-edit-name").val() == ""){
            $("#editComponentModal").modal("hide");
            actual_modal = 1;
            $("#namelessRestrictionModal").modal("show");
        }else if(orphan){
            $("#editComponentModal").modal("hide");
            actual_modal = 1;
            $("#orphanRestrictionModal").modal("show");
            orphan = false;
        }else{
            console.log(component);
            $.ajax({
                url: '/'+ componentType + '/' + componentSelected,
                type: 'put',
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    location.reload();
                },
                data: JSON.stringify(component)
            });
        }
    });
}

function loadComponentsAndConnectionsToBeDeleted(id){
    componentToBeDeleted = id;
    $("#itemsToBeDeleted").css("display","grid");
    $("#itemsToBeDeletedSpan").css("display","flex");
    $("#componentsToBeDeleted").css("display","grid");
    $("#connectionsToBeDeleted").css("display","grid");
    $("#componentsToBeDeletedLi").css("display","");
    $("#connectionsToBeDeletedLi").css("display","");

    $.ajax({
        url: '/components/'+ id,
        type: 'get',
        success: function (data) {
             $("#componentToBeDeletedName").text(data.name);
        },
    });

    $("#componentsToBeDeleted").empty();
    $("#connectionsToBeDeleted").empty();

    $.ajax({
        url: '/components/listComponentsAndConnectionsToBeDeleted/'+ id,
        type: 'get',
        success: function (data) {
            var numComp = 0;
            var numConn = 0;
            $.each(data, function (idx, obj) {
                if(obj.name != null){
                    $("#componentsToBeDeleted").append("<li>" + obj.name + "</li>");
                    numComp++;
                }else{
                    $("#connectionsToBeDeleted").append("<li>" + obj.source.name + " ---> " + obj.target.name + "</li>");
                    numConn++;
                }
            });
            if((numComp == 0) && (numConn == 0)){
                $("#itemsToBeDeleted").css("display","none");
                $("#itemsToBeDeletedSpan").css("display","none");
            }else if(numComp == 0){
                $("#componentsToBeDeleted").css("display","none");
                $("#componentsToBeDeletedLi").css("display","none");
            }else if(numConn == 0){
                $("#connectionsToBeDeleted").css("display","none");
                $("#connectionsToBeDeletedLi").css("display","none");
            }
        },
    });
}

function deleteComponent(){
    $.ajax({
        url: '/components/'+ componentToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}