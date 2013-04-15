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
<title>Cadastro Cartao</title>
</head>
<body>
	<div id="boxcenter">
	<ul class="nav nav-tabs">
      <li><a href="/index">In�cio</a></li>
         <li class="active"><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
        <li><a href="<c:url value="/grud/transacao"/>">Cadastrar Transa��es</a></li>
        <li><a href="<c:url value="/grud/show/"/>">Exibir Transa��es</a></li>
        </ul>
        <p>
        </p>
		<div class="input-append">
			<c:url var="url" value="/grud/cadCartao" />
				<form:form action="${url}" method="POST" modelAttribute="cadCartao">
					<form:input cssStyle="width:250px" maxlength="30" path="nome" size="30" class="span2" id="appendedInputButton" type="text" placeholder="Tipo de Pagamento" />
					<button class="btn" type="submit" value="Criar Contato" >Criar!</button>
				</form:form> 
		</div>
	</div>
</body>
</html>