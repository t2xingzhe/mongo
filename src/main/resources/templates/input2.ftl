<script>
    $(function() {
        $('.img').click(function() {
            //alert($(this).attr("fan"));
            //var magnet = $(this).load("/json?id="+$(this).attr("fan"));
            var magnet = "";
            $.get("/json?id="+$(this).attr("fan"),function(data,status){
                var no = $('.magnet').val();
                if(no.length == 0) {
                    $('.magnet').val(data + "\r\n");
                }else{
                    $('.magnet').val(no + "\n\r" + data + "\r\n");
                }
            });
            //$('.magnet').append(magnet + "<br>");
        });
    });
</script>
<#if itemList??>
    <#list itemList as item>
    <div>
        <img class="img" src="${item.img!}" fan="${item.id!}"/>

        <div>${item.title!} --- ${item.id!} <a href = "/act?name=${item.actUrl!}&id=${item.sId!}">${item.act}</a></div>
    </div>
    </#list>
</#if>