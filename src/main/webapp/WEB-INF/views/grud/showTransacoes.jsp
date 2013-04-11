<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<div>
			<table width="600px">
				<tr>
				<thead>
					<th>Id</th>
					<th>Nome</th>
				</thead>
				</tr>
				<c:forEach items="${transacoes}" var="transacao">
					<tr>
						<td>${transacao.id}</td>
						<td>${transacao.descricao}</td>
						<td>${transacao.dataFormatada}</td>
						<td>${transacao.valor}</td>
						<td>${transacao.cartao.nome}</td>
						<c:if test="${transacao.tipo==1}">
						<td>Receita</td>
						</c:if>
						<c:if test="${transacao.tipo==2}">
						<td>Despesas</td>
						</c:if>
					</tr>
				</c:forEach>
				</table>
			</div>	
		</div>
</body>
</html>