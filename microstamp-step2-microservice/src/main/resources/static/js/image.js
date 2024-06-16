function uploadImage(){

    image = $("#image").val();
    var control_structure_id = $("#control_structure_id").val();
    var myFile = $('#image').prop('files');

    var data = new FormData();
    data.append('image',myFile[0]);

    $.ajax({
        url: '/images/' + control_structure_id,
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            location.reload();
        },
        data:data
    });
}