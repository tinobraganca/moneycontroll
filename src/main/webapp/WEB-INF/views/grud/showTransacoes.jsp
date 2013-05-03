<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<li><a href="/index">Início</a></li>
			<li><a href="<c:url value="/grud/cad"/>">Cadastro De
					Pagementos</a></li>
			<li><a href="<c:url value="/grud/transacao"/>">Cadastrar
					Transações</a></li>
			<li class="active"><a href="<c:url value="/grud/show/"/>">Exibir
					Transações</a></li>
		</ul>
		<div>
			<table class="table table-hover" width="600px">
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

				<form:form action="/grud/show/" method="GET" modelAttribute="page.page">
					<c:forEach items="${transacoes}" var="transacao">
						<c:if test="${transacao.tipo==1}">
							<tr class="success" onclick="editar(${transacao.id})">
								<td>${transacao.id}</td>
								<td>${transacao.descricao}</td>
								<td>${transacao.dataFormatada}</td>
								<td>${transacao.valor}</td>
								<td>${transacao.cartao.nome}</td>

								<td><c:if test="${transacao.tipo==1}">Receita</c:if> <c:if
										test="${transacao.tipo==2}">Despesas</c:if></td>
							</tr>
						</c:if>
						<c:if test="${transacao.tipo==2}">
							<tr class="error">
								<td>${transacao.id}</td>
								<td>${transacao.descricao}</td>
								<td>${transacao.dataFormatada}</td>
								<td>${transacao.valor}</td>
								<td>${transacao.cartao.nome}</td>
								<td><c:if test="${transacao.tipo==1}">Receita</c:if> <c:if
										test="${transacao.tipo==2}">Despesas</c:if></td>
							</tr>
						</c:if>
					</c:forEach>
				</form:form>
			</table>
			<c:out value="${maxPages}" />
			<form:form action="/grud/show/" method="GET" modelAttribute="page">
					<div class="pagination">
						<ul>
<%-- 							<li><a href="<form:input path="page"/>">${maxPages}</a></li> --%>
						</ul>
					</div>
			</form:form>

			<!-- Modal -->
			<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">Modal header</h3>
				</div>
				<div class="modal-body">
					<p>Um corpo fino …</p>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
					<button class="btn btn-primary">Salvar mudanças</button>
				</div>
			</div>

		</div>
		<script type="text/javascript" src="/resources/js/jquery.js"></script>
		<script type="text/javascript" src="/resources/js/bootstrap.min.js">
	
	</script>
		<script type="text/javascript">
	  
	var editar = function(id) {
		   $.ajax({
	           url :'/updateControl/',
	           type :'post',
	           data :{'id':id},
	           success: function(retorno){
	        	      $('#myModal').html(retorno);
	        	    },
               error: function(retorno){
                  alert("Erro: "+retorno);
               }
	       })
	   };
	   
	$('#myModal').modal(data-backdrop="'show'");
	   
	</script>
</body>
</html>