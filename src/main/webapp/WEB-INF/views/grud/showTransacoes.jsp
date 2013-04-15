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
      <li><a href="/index">In�cio</a></li>
         <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
        <li><a href="<c:url value="/grud/transacao"/>">Cadastrar Transa��es</a></li>
        <li class="active"><a href="<c:url value="/grud/show/"/>">Exibir Transa��es</a></li>
        </ul>
		<div>
			<table class="table table-hover" width="600px">
				<tr>
				<thead>
					<th>#</th>
					<th>Descri��o</th>
					<th>Data</th>
					<th>Valor</th>
					<th>Pagemento</th>
					<th>Tipo</th>
				</thead>
				</tr>
				<c:forEach items="${transacoes}" var="transacao">
					<c:if test="${transacao.tipo==1}">
						<tr class="success">
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
			</table>
		</div>
	</div>
</body>
</html>