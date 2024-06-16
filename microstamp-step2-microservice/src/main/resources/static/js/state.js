function loadVariableToBeStated(id){
    variableToBeStated = id;
}

function addState(){
    var state = {
        name: $("#state-name").val(),
        variable_id: variableToBeStated,
    }

    $.ajax({
        url: '/states',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(state)
    });
}

function loadStateToBeEdited(id){
    stateToBeEdited = id;
    $.ajax({
        url: '/states/'+ id,
        type: 'get',
        success: function (data) {
            $("#state-edit-name").val(data.name);
        },
    });
}

function sendEditedState() {
    var state = {
        name: $("#state-edit-name").val(),
    }

    $.ajax({
        url: '/states/' + stateToBeEdited,
        type: 'put',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(state)
    });
}

function loadStateToBeDeleted(id){
    stateToBeDeleted = id;
    $.ajax({
            url: '/states/'+ id,
            type: 'get',
            success: function (data) {
                 $("#state_delete_name").text(data.name);
            },
        });
}

function deleteState(){
    $.ajax({
        url: '/states/'+ stateToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}