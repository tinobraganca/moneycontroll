<style type="text/css">
#boxright {
    left: 85%;
    margin: -240px 0 0 -320px;
    position: absolute;
    top: 50%;
    height: 180px;
    width: 340px;
}
</style>
<div id="boxright">
    <table id="tab2" class="table table-bordered" width="300px">
    </table>
</div>
<script type="text/javascript">
$(document).ready(function(){
    var jsonUrl = "/grud/abrastractSomaTotal";
    $.ajax({
        'type': "GET",
        'url': jsonUrl,
        'dataType': "json",
        'success': function(retorno) {
        	var html = '<tr class="success"><td>Total Geral de receita = '+retorno['receitas']+' </td></tr>';  
            html += '<tr class="error"><td>Total geral de custo = '+retorno['despesas']+' </td></tr>';  
            html += '<tr><td>Total Geral = '+retorno['total']+'</td></tr>';  
            $('#tab2').html(html);
        }
    });
});
</script>
