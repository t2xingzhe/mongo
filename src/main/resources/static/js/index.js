$(function() {
    $('#sub').click(function() {
        var link = $('#link').val();
        var sort = $('#sort').val();
        $.post("input2",
            {
                url:link,
                sort:sort
            },
            function(data,status){
                $('#list').html(data);
                //alert("Data: " + data + "\nStatus: " + status);
            });

    });
});
