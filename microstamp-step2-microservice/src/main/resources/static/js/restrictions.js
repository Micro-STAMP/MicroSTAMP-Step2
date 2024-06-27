var previous;
var previous_edit;

$(window).on('load', function() {
    previous = $("#connection-source").val();
    $("#connection-target option[value="+previous+"]").wrap('<span/>');
    if($("#connection-source option[value="+previous+"]").text() == "Environment"){
        $("#connection-type-pi").css("display","");
        $("#connection-type-po").css("display","none");
        $("#connection-type").css("display","none");
    }
});

$("#connection-source").on('change',function(){
    conn_target_value = $("#connection-target").val();
    if($("#connection-source option[value="+this.value+"]").text() == "Environment"){
        $("#connection-type-pi").css("display","");
        $("#connection-type-po").css("display","none");
        $("#connection-type").css("display","none");
    }else if($("#connection-target option[value="+conn_target_value+"]").text() == "Environment"){
        $("#connection-type-po").css("display","");
        $("#connection-type-pi").css("display","none");
        $("#connection-type").css("display","none");
    }else{
        $("#connection-type-po").css("display","none");
        $("#connection-type-pi").css("display","none");
        $("#connection-type").css("display","");
        $("#connection-type").val("CONTROL_ACTION");
    }
});

$("#connection-edit-source").on('change',function(){
    conn_target_value = $("#connection-edit-target").val();
    if($("#connection-edit-source option[value="+this.value+"]").text() == "Environment"){
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
        $("#connection-edit-type").val("CONTROL_ACTION");
    }
});


$("#connection-target").on('change',function(){
    conn_source_value = $("#connection-source").val();
    if($("#connection-target option[value="+this.value+"]").text() == "Environment"){
        $("#connection-type-pi").css("display","none");
        $("#connection-type-po").css("display","");
        $("#connection-type").css("display","none");
    }else if($("#connection-source option[value="+conn_source_value+"]").text() == "Environment"){
        $("#connection-type-po").css("display","none");
        $("#connection-type-pi").css("display","");
        $("#connection-type").css("display","none");
    }else{
        $("#connection-type-po").css("display","none");
        $("#connection-type-pi").css("display","none");
        $("#connection-type").css("display","");
        $("#connection-type").val("CONTROL_ACTION");
    }
});

$("#connection-edit-target").on('change',function(){
    conn_source_value = $("#connection-edit-source").val();
    if($("#connection-edit-target option[value="+this.value+"]").text() == "Environment"){
        $("#connection-edit-type-pi").css("display","none");
        $("#connection-edit-type-po").css("display","");
        $("#connection-edit-type").css("display","none");
    }else if($("#connection-edit-source option[value="+conn_source_value+"]").text() == "Environment"){
        $("#connection-edit-type-po").css("display","none");
        $("#connection-edit-type-pi").css("display","");
        $("#connection-edit-type").css("display","none");
    }else{
        $("#connection-edit-type-po").css("display","none");
        $("#connection-edit-type-pi").css("display","none");
        $("#connection-edit-type").css("display","");
        $("#connection-edit-type").val("CONTROL_ACTION");
    }
});


$("#connection-source").on('focus', function () {
    previous = this.value;
}).change(function() {
    var selected = this.value;
    $("#connection-target option[value="+selected+"]").wrap('<span/>');
    $("#connection-target option[value="+previous+"]").unwrap();
    previous = this.value;

    conn_target_value = $("#connection-target").val();
    if($("#connection-source option[value="+this.value+"]").text() == "Environment"){
        $("#connection-type-pi").css("display","");
        $("#connection-type-po").css("display","none");
        $("#connection-type").css("display","none");
    }else if($("#connection-target option[value="+conn_target_value+"]").text() == "Environment"){
        $("#connection-type-po").css("display","");
        $("#connection-type-pi").css("display","none");
        $("#connection-type").css("display","none");
    }else{
        $("#connection-type-po").css("display","none");
        $("#connection-type-pi").css("display","none");
        $("#connection-type").css("display","");
        $("#connection-type").val("CONTROL_ACTION");
    }
});

$("#connection-edit-source").on('focus', function () {
    previous_edit = this.value;
}).change(function() {
    var selected = this.value;
    $("#connection-edit-target option[value="+selected+"]").wrap('<span/>');
    $("#connection-edit-target option[value="+previous_edit+"]").unwrap();
    previous_edit = this.value;

    conn_target_value = $("#connection-edit-target").val();
    if($("#connection-edit-source option[value="+this.value+"]").text() == "Environment"){
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
        $("#connection-edit-type").val("CONTROL_ACTION");
    }
});

$("#component-edit-father").on('focus', function () {
    $("#component-edit-father option[value="+componentSelected+"]").wrap('<span/>');
});

$("#component-edit-father").on('focusout', function () {
    $("#component-edit-father option[value="+componentSelected+"]").unwrap();
});

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
    var modalMessage = "An unexpected error occurred.";

    try {
        const errorMessage = JSON.parse(jqxhr.responseText);
        if (errorMessage.errors.length > 0) {
            modalMessage = errorMessage.errors
                .map(function(error) {
                     return error.message;
                }).join('<br>');
        }
        showErrorModal(modalMessage);
    } catch (e) {
        showErrorModal(modalMessage);
    }
});

function showErrorModal(message) {
    $(".modal").modal("hide");
    $("#errorModal .modal-body").html(message);
    $("#errorModal").modal("show");
}

