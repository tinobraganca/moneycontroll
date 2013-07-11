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
#box {
	height: 480px;
	left: 50%;
	margin: -240px 0 0 -320px;
	position: absolute;
	top: 50%;
	width: 640px
}
</style>
<title>Principal</title>
</head>
<body>
	<div id="box">
	<ul class="nav nav-tabs">
      <li class="active"><a href="/index">Início</a></li>
         <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
        <li><a href="<c:url value="/grud/transacao"/>">Cadastrar Transações</a></li>
        <li><a href="<c:url value="/grud/show/"/>">Exibir Transações</a></li>
        </ul>
		<div class="hero-unit">
			<h1>Money Control</h1>
			<p> <----------------------------------------------------------------------------------->
			     <----------------------------------------------------------------------------------->
			     <----------------------------------------------------------------------------------->
			</p>
			
		</div>
	</div>
</body>
</html>
