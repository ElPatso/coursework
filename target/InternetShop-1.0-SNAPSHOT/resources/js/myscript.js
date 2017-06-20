
$(document).ready(function() {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    var deleteLink = $("a:contains('Delete')");
    $(deleteLink).click(function(event) {

        $.ajax({
            url: $(event.target).attr("href"),
            type: "DELETE",
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(data) {
                var rowToDelete = $(event.target).closest("tr");

                rowToDelete.remove();

            },
            error: function(ts) { alert(ts.responseText) }
        });

        event.preventDefault();
    });
    $('#btnSubmitComment').submit(function(event) {

            var name = $('#name').val();
            var comment = $('#comment').val();
            var json = {"name" : name, "comment" : comment };

            $.ajax({
                url: $('#btnSubmitComment').attr( "action"),
                data: JSON.stringify(json),
                type: "POST",
                contentType: 'application/json',
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function(data) {
                    var respContent = "";
                    respContent+="<tr>";

                    respContent+="<td>"+"<b>"+data.name+"</b> Залишив відгук :"+"</td>";
                    respContent+="</tr>";
                    respContent+="<tr>";

                    respContent+="<td> <p>"+data.comment+"</td>";
                    respContent+="</tr>";
                    $('#table_grid').last().append(respContent);
                    $('#comment').val('');
                },
                error: function(ts) { alert(ts.responseText) }

            });
            event.preventDefault();
        });

    });
function addtoCart(id) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    $.ajax({
        url : "http://" + $(location).attr('host') + "/lot/"+id,
        type: 'POST',

        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            $('#addToCart').hide();

        },
        error: function(ts) { alert(ts.responseText) }
    });
}