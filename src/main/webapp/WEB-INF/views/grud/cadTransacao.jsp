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
<link href="/resources/css/datepicker.css" rel="stylesheet">
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
        <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
        <li class="active"><a href="<c:url value="/grud/transacao"/>">Cadastrar Transações</a></li>
        <li><a href="<c:url value="/grud/show/"/>">Exibir Transações</a></li>
    </ul>
		<c:url var="url" value="" />
		<form:form action="/grud/transacao/" method="POST"
			modelAttribute="transacao">

			<div>
				<label for="data">Data:</label>
				<form:input cssStyle="width:250px" maxlength="30" path="data" size="20" class="datepicker" />
			</div>

			<div>
				<label for="valor">valor:</label>
				<form:input cssStyle="width:250px" maxlength="30" path="valor"
					size="20" />
			</div>
				<label for="descricao">Descricao:</label>
			<form:select path="tipo">
				<form:option value="0">Escolha uma opção</form:option>
				<form:option value="1">Receita</form:option>
				<form:option value="2">Despesas</form:option>
			</form:select>

			<form:select path="cartao.id">
				<form:option value="-1">Nenhuma das opçoes</form:option>
				<form:options items="${cartoes}" itemValue="id" itemLabel="nome" />
			</form:select>
			<div>
				<form:textarea rows="3" cssStyle="width:250px" maxlength="30" path="descricao" type="" placeholder="Digite aqui" size="30" />
			</div>
            <p></p>
			<div class="submit">
				<input class="btn btn-primary" type="submit" value="Criar Transação" />
			</div>
		</form:form>
	</div>
	
	<script type="text/javascript" src="/resources/js/jquery.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap-datepicker.js"></script>
	
	<script type="text/javascript"> 
       $('.datepicker').datepicker({format:"dd/mm/yy"})
    </script>
    
	
</body>
</html>