function addConnection() {
    var connType;

    if($("#connection-type-pi").css("display") == "none" && $("#connection-type-po").css("display") == "none"){
        connType = $("#connection-type").val();
    }else if($("#connection-type-pi").css("display") == "none" && $("#connection-type").css("display") == "none"){
        connType = $("#connection-type-po").val();
    }else{
        connType = $("#connection-type-pi").val();
    }

    var connection = {
        sourceId: $("#connection-source").val(),
        targetId: $("#connection-target").val(),
        connectionType: connType,
        style: $("#connection-style").val(),
        controlStructureId: $("#control_structure_id").val(),
    }

    $.ajax({
        url: '/connections',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(connection)
    });
}

function editConnection(id){
    connectionSelected = id;

     $.ajax({
        url: '/connections/'+ id,
        type: 'get',
        success: function (data) {

            if(data.connectionType == "PROCESS_INPUT" || data.connectionType == "DISTURBANCE"){
                $("#connection-edit-type-pi").val(data.connectionType);
            }else if(data.connectionType == "PROCESS_OUTPUT"){
                $("#connection-edit-type-po").val(data.connectionType);
            }else{
                $("#connection-edit-type").val(data.connectionType);
            }

            $("#connection-edit-style").val(data.style);
            $("#connection-edit-source").val(data.source.id);
            $("#connection-edit-target").val(data.target.id);
            previous_edit = data.source.id;
            wrapFirst(previous_edit);

            var conn_target_value = data.target.id;
            var conn_source_value = data.source.id;

            if($("#connection-edit-source option[value="+conn_source_value+"]").text() == "Environment"){
                $("#connection-edit-type-pi").css("display","");
                $("#connection-edit-type-po").css("display","none");
                $("#connection-edit-type").css("display","none");
            }else if($("#connection-edit-target option[value="+conn_target_value+"]").text() == "Environment"){
                $("#connection-edit-type-po").css("display","");
                $("#connection-edit-type-pi").css("display","none");
                $("#connection-edit-type").css("display","none");
            }else{
                $("#connection-edit-type-po").css("display","none");
                $("#connection-edit-type-pi").css("display","none");
                $("#connection-edit-type").css("display","");
            }
        },
    });
}

function sendEditedConnection(){

    var connType;
    if($("#connection-edit-type-pi").css("display") == "none" && $("#connection-edit-type-po").css("display") == "none"){
        connType = $("#connection-edit-type").val();
    }else if($("#connection-edit-type-pi").css("display") == "none" && $("#connection-edit-type").css("display") == "none"){
        connType = $("#connection-edit-type-po").val();
    }else{
        connType = $("#connection-edit-type-pi").val();
    }

    var connection = {
        sourceId: $("#connection-edit-source").val(),
        targetId: $("#connection-edit-target").val(),
        connectionType: connType,
        style: $("#connection-edit-style").val(),
        controlStructureId: $("#control_structure_id").val(),
    }

    $.ajax({
        url: '/connections/' + connectionSelected,
        type: 'put',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(connection)
    });
}

function wrapFirst(val){
    $("#connection-edit-target option[value="+val+"]").wrap('<span/>');
}

function unwrapLast(){
     $("#connection-edit-target option[value="+previous_edit+"]").unwrap();
}

function loadConnectionToBeDeleted(id){
    connectionToBeDeleted = id;
    $.ajax({
        url: '/connections/'+ id,
        type: 'get',
        success: function (data) {
             $("#connectionToBeDeletedType").text(data.connectionType);
             $("#connectionToBeDeletedSourceName").text(data.source.name);
             $("#connectionToBeDeletedTargetName").text(data.target.name);
        },
    });
    $("#labelsToBeDeleted").empty();
    $.ajax({
        url: '/labels/connection/'+ id,
        type: 'get',
        success: function (data) {
             $.each(data, function (idx, obj) {
                $("#labelsToBeDeleted").append("<li>" + obj.label + "</li>");
            });
        },
    });
}

function deleteConnection(){
    $.ajax({
        url: '/connections/'+ connectionToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}