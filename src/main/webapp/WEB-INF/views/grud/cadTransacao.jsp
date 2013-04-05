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
		<c:url var="url" value="" />
		<form:form action="${url}" method="POST" modelAttribute="transacao">

			<div>
				<label for="data">Data:</label>
				<form:input cssStyle="width:250px" maxlength="30" path="data" size="20" />
			</div>

			<div>
				<label for="valor">valor:</label>
				<form:input cssStyle="width:250px" maxlength="30" path="valor"
					size="20" />
			</div>
			<div>
				<label for="descricao">Descricao:</label>
				<form:input cssStyle="width:250px" maxlength="30" path="descricao"
					size="30" />
			</div>
			<select name="cartaoId">
				<c:forEach items="${cartoes}" var="car">
					<option value="${cartoes.id}">${cartoes.name}</option>
				</c:forEach>
			</select>
			<div class="submit">
				<form:input type="submit" value="Criar Contato" />
			</div>
			
		</form:form>
	</div>
</body>
</html>