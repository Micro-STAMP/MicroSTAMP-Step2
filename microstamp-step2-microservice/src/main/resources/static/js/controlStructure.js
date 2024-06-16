var controlStructureSelected;
var controlStructureToBeDeleted;

var actual_modal; // 0 -> addControlStructure 1 -> editControlStructure //control the restrictions return

$(window).ready(function () {
    var user_id = document.getElementById("user_id").innerText;
    $.ajax({
        "type": 'get',
        "url": '/controlstructures/user/' + user_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#csTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editControlStructureModal' onclick = loadEditControlStructure(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmControlStructureDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadControlStructureToBeDeleted(this.id)><span class='fa fa-trash'></span></button><button style='cursor: pointer; border-radius: 5px;' type='button' id=\"" + obj.id + "\" onclick=location.href=\"" + obj.id + "\"><span class='fa fa-search' aria-hidden='true'></span></button></td></tr>");
            });
            $("#csTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });
});

function addControlStructure() {
    var control_structure = {
        name: $("#control_structure-name").val(),
        user_id: document.getElementById("user_id").innerText,
    }

    $('#target').html('sending..');

    if($("#control_structure-name").val() == ""){
        $("#addControlStructureModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/controlstructures',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(control_structure)
        });
    }
}

function loadEditControlStructure(id){
    controlStructureSelected = id;
    $.ajax({
    url: '/controlstructures/'+ id,
    type: 'get',
    success: function (data) {
        $("#control_structure-edit-name").val(data.name);
    },
});
}

function editControlStructure() {
    var control_structure = {
        name: $("#control_structure-edit-name").val(),
    }

    $('#target').html('sending..');

    if($("#control_structure-edit-name").val() == ""){
        $("#editControlStructureModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/controlstructures/' + controlStructureSelected,
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(control_structure)
        });
    }
}

function loadControlStructureToBeDeleted(id){
    controlStructureToBeDeleted = id;
    $.ajax({
        url: '/controlstructures/'+ id,
        type: 'get',
        success: function (data) {
             $("#control_structure_delete_name").text(data.name);
        },
    });
}

function deleteControlStructure(){
    $.ajax({
        url: '/controlstructures/'+ controlStructureToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}

function returnNamelessRestriction(){
    $("#namelessRestrictionModal").modal("hide");
    if(actual_modal == 0)
        $("#addControlStructureModal").modal("show");
    else
        $("#editControlStructureModal").modal("show");
}