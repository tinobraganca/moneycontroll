<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">
<style type="text/css">
#boxcenter {
    height: 480px;
    left: 50%;
    margin: -240px 0 0 -320px;
    position: absolute;
    top: 50%;
    width: 640px
}
</style>
<title>Cadastrar</title>
</head>
<body>
    <div id="boxcenter">
        <ul class="nav nav-tabs">
	        <li><a href="/index">In&iacute;cio</a></li>
	        <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
	        <li><a href="<c:url value="/grud/transacao"/>">Cadastrar Transa&ccedil;&otilde;es</a></li>
	        <li class="active"><a href="<c:url value="/grud/show/"/>">Exibir Transa&ccedil;&otilde;es</a></li>
        </ul>
            <table id="tab" class="table table-hover" width="600px">
                <tr>
                <thead>
                    <th>#</th>
                    <th>Descrição</th>
                    <th>Data</th>
                    <th>Valor</th>
                    <th>Pagemento</th>
                    <th>Tipo</th>
                </thead>
                </tr>
            </table>
            <div  class="boxcenter" id="page-selection"><!--  Pagination goes here--></div>

<!--            <!-- Modal --> 
<!--            <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" -->
<!--                aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!--                <div class="modal-header"> -->
<!--                    <button type="button" class="close" data-dismiss="modal" -->
<!--                        aria-hidden="true">×</button> -->
<!--                    <h3 id="myModalLabel">Modal header</h3> -->
<!--                </div> -->
<!--                <div class="modal-body"> -->
<!--                    <p>Um corpo fino …</p> -->
<!--                </div> -->
<!--                <div class="modal-footer"> -->
<!--                    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button> -->
<!--                    <button class="btn btn-primary">Salvar mudanças</button> -->
<!--                </div> -->
<!--            </div> -->

        </div>
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.bootpag.js"></script>
<script type="text/javascript">
var page = 1;
$(document).ready(function(){
    myfunction(page);
    numeroMaxPag();
});

var myfunction = function(page){
     var jsonUrl = "/grud/show/lista/?page="+page;
     var html ='';
        $.ajax({
            'type': "GET",
            'url' :jsonUrl,
            'dataType':"json",
            'success': function (retorno) {
                $.each(retorno, function(index) {
                    var item = retorno[index];
                    var tipo = item['tipo'];
                    if (tipo == 1) {
                        html += '<tr class="success">';
                        html += '<td>' + item['id'] + '</td>';
                        html += '<td>' + item['descricao'] +'</td>';
                        html += '<td>' + item['dataFormatada'] +'</td>';
                        html += '<td>' + item['valor'] +'</td>';
                        html += '<td>' + item['Transacoes'] +'</td>';
                        html += '<td> Receita</td>';
                        html += '</tr>'
                    } if (tipo == 2) {
                        html += '<tr class="error">';
                        html += '<td>' + item['id'] + '</td>';
                        html += '<td>' + item['descricao'] +'</td>';
                        html += '<td>' + item['dataFormatada'] +'</td>';
                        html += '<td>' + item['valor'] +'</td>';
                        html += '<td>' + item['cartao.nome'] +'</td>';
                        html += '<td> Despesas</td>';
                        html += '</tr>'
                    }
                });
             $('#tab').html($(html));
            }
        });
}

var numeroMaxPag = function (){
         var jsonUrl = "/grud/show/pagMax";
         var html ='';boxcenter
            $.ajax({
                'type': "GET",
                'url' :jsonUrl,
                'dataType':"json",
                'success': function (retorno) {
                       console.log(retorno);
                       $('#page-selection').bootpag({   
                       total: retorno,
                       page: 1,
                       maxVisible: 5, 
                    }).on('page', function(event, num){
                        console.log(event);
                        console.log(num);
                        myfunction(num);
                    });
             }
      });
}

</script>
</body>
</html>