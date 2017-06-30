
$(document).ready(function() {
    var deleteLink = $("a:contains('Delete')");
    $(deleteLink).click(function(event) {
        $.ajax({
            url: $(event.target).attr("href"),
            type: "DELETE",
            success: function(data) {
                var rowToDelete = $(event.target).closest("tr");
                rowToDelete.remove();
            },
            error: function(ts) { alert(ts.responseText) }
        });
        event.preventDefault();
    });
    $('#btnSubmitComment').submit(function(event) {
        var mySrc = "http://" + $(location).attr('host') +""
        var name = $('#name').val();
            var comment = $('#comment').val();
            var json = {"name" : name, "comment" : comment };

            $.ajax({
                url: $('#btnSubmitComment').attr( "action"),
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',

                success: function(data) {
                    var respContent = "";
                    respContent+="<tr>";

                    respContent+="<td rowspan='2' width='65'>"+"<img src='/resources/img/user_img.png' width='64' height='64'>"+"</td>";
                    respContent+="<th colspan='2' >"+"<b>"+data.name+"</b> left a review  :"+"</th>";
                    respContent+="</tr>";
                    respContent+="<tr>";

                    respContent+="<th> <p>"+data.comment+"</th>";
                    respContent+="</tr>";
                    $('#table_grid').append(respContent);
                    $('#comment').val('');
                },
                error: function(ts) { alert(ts.responseText) }

            });
            event.preventDefault();
        });

    });
function addtoCart(id) {

    $.ajax({
        url : "http://" + $(location).attr('host') + "/lotAddtocart/"+id,
        type: 'POST',
        success: function (data) {
            $('#addToCart').hide();

        },
        error: function(ts) { alert(ts.responseText) }
    });
}
function uploadImage() {
    var isJpg = function(name) {
        return name.match(/jpg$/i)
    };

    var isPng = function(name) {
        return name.match(/png$/i)
    };
    var imgContainer = $('#imgContainer');
    var $input = $("#uploadimage")
    var filename = $.trim($input.val());

    if (!(isJpg(filename) || isPng(filename))) {
        alert('Please browse a JPG/PNG file to upload ...');
        return;
    };
    var fd = new FormData();
    fd.append('img', $input.prop('files')[0]);
    $.ajax({
        url: "http://" + $(location).attr('host') + "/updateImage",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false
    }).done(function(data) {
        imgContainer.html('');
        var img = '<img src="data:' + data.contenttype + ';base64,'
            + data.base64 + '"/>';

        imgContainer.append(img);
    });
}
function checkparam() {
    var $input = $("#textsearch").val();
    if ($input.length !=0){
        $("#submit").removeAttr('disabled');
    } else {
        $("#submit").attr('disabled', 'disabled');
    }
}
function changeStatus(id,enabled) {
    var json = {"enabled" : enabled}
    $.ajax({
        url : "http://" + $(location).attr('host') + "/editusers/update/"+id,
        type: 'PUT',
        contentType: 'application/json',
        data : JSON.stringify(json),
        success: function (data) {
            var respContent ='';
            respContent+="<a href='#' onclick='changeStatus("+data.id+","+data.enabled+")'>"+data.enabled+"</a>";
            $("#user_"+id).html(respContent);

        },
        error: function(ts) { alert(ts.responseText) }
    });
}
$(document).ready(function() {
    $('#category').submit(function(event) {
        var id = $("#id").val();
        var name = $("#categoryname").val();
        var json  = {"id" : id, "name" : name}

        $.ajax({
            url: $('#category').attr( "action"),
            contentType: 'application/json',
            type: 'POST',
            data : JSON.stringify(json),
            success: function (data) {
                $("#result").css({'background-color':'#00ff00', 'width':'20%'});

                $("#result").html(data);

            },
            error: function(ts) { alert(ts.responseText) }
        });
        event.preventDefault();
    });
});
function addToInput(id) {
    $("#id").val(id);

    $("#categorysubmit").show();

}
function deleteCategory(id) {
    $.ajax({
        url : "http://" + $(location).attr('host') + "/editcategory/"+id,
        type: 'DELETE',
        success: function (data) {
            $("#result").css({'background-color':'#ff0000', 'width':'20%'});

            $("#result").html(data);

        },
        error: function(ts) { alert(ts.responseText) }
    });
}
