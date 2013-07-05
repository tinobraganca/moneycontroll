<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/resources/css/datepicker.css" rel="stylesheet">
<style type="text/css">
label.valid{
	background-color: #DFF0D8;
    border-color: #D6E9C6;
    color: #468847;
    border-radius: 4px 4px 4px 4px;
    margin-bottom: 20px;
    padding: 8px 35px 8px 14px;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}
label.errors{
    background-color: #F2DEDE;
    border-color: #EED3D7;
    color: #B94A48;
    border-radius: 4px 4px 4px 4px;
    margin-bottom: 20px;
    padding: 8px 35px 8px 14px;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}
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
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
   
    <%@ include file="abrastractSomaTotal.jsp"%>
	<div id="boxcenter">
		<ul class="nav nav-tabs">
	        <li><a href="/index">In&iacute;cio</a></li>
	        <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
	        <li class="active"><a href="<c:url value="/grud/transacao"/>">Cadastrar Transa&ccedil;&otilde;es</a></li>
	        <li><a href="<c:url value="/grud/show/"/>">Exibir Transa&ccedil;&otilde;es</a></li>
	    </ul>
			
		<c:url var="url" value="" />
		<form:form action="/grud/transacao/" method="POST" modelAttribute="transacao" class="form-horizontal">
			<div>
			<%if (("ok").equals(request.getParameter("status"))){ %>
                   <label class="alert alert-success">Sua transa&ccedil;&atilde;o Foi incluida</label>
             <%}if(("fail").equals(request.getParameter("status"))){ %>
                  <label class="alert alert-success">Erro na inclusao da transa&ccedil;&atilde;o</label> 
                  <%  } %>    
            </div>
			<div class="control-group">
				<label class="control-label" for="data">Data:<form:errors path="data" cssClass="errors" /><br /></label>
			    <div class="controls">
					<form:input cssStyle="width:250px" maxlength="30" path="data" size="20" class="datepicker" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="valor">Valor:<form:errors path="valor" cssClass="errors" /><br /></label>
				<div class="controls">
					<form:input cssStyle="width:250px" maxlength="30" path="valor" size="20" type="number"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="tipo">Tipo da Transa&ccedil;&atilde;o</label>
				<div class="controls">
					<form:select path="tipo">
						<form:option value="0">Escolha uma op&ccedil;&atilde;o</form:option>
						<form:option value="1">Receita</form:option>
						<form:option value="2">Despesas</form:option>
					</form:select>
				</div>
			</div>
			<div class="control-group">
			<label class="control-label" for="cartao.id">Tipo do Pagamento</label>
				<div class="controls">
					<form:select path="cartao.id">
						<form:option value="0">Nenhuma das op&ccedil;&otilde;es</form:option>
						<form:options items="${cartoes}" itemValue="id" itemLabel="nome" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
			<label class="control-label" for="descricao">Descri&ccedil;&atilde;o:<form:errors path="descricao" cssClass="errors" /><br /></label>
				<div class="controls">
					<form:input rows="3" cssStyle="width:250px" maxlength="30" path="descricao" type="" placeholder="Digite aqui" size="30" />
				</div>
			</div>
            <p></p>
			<div class="submit">
				<div class="controls">
					<input class="btn btn-primary" type="submit" value="Criar Trans&ccedil;&atilde;o" />
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript"> 
        $(document).ready(function() {
	        $.validator.addMethod(
	        	    "validaDataTransacao",
	        	    function(value, element) {
	        	        // put your own logic here, this is just a (crappy) example
	        	        return value.match(/^\d\d?\/\d\d?\/\d\d$/);
	        	    },
	        	    "Voce deve inserir uma data no formato dd/mm/yy."
	        	);
	        
	        jQuery.validator.addMethod('validaSelectTransacao', function (value) {
	            return (value != '0');
	        }, "Voce deve selecionar uma das op&ccedil;&otilde;s");

        	$('.datepicker').datepicker({format:"dd/mm/yy"});
	        
        	$("#transacao").validate({
	        	rules:{
	        		valor:{
	        			required:true
	        		},
	        		data:{
						validaDataTransacao:true,
	        			required:true
	        		},
	        		descricao:{
	        			required:true,minlength: 4,
	        		},
	        		tipo:{
	        			validaSelectTransacao:true,
	        			required:true
	        		},
	        		"cartao.id":{
	        			validaSelectTransacao:true,
	        			required:true
	        		}
	        		
	        	},
	        	messages:{
	        		descricao:{
	        			required: "Digite o seu nome",
	        			minlength: "O seu nome deve conter, no mínimo, 4 caracteres",
	        		},
	        		valor:{
	        			required:"Deve inserir um valor a transa&ccedil;&atilde;o",
	        			
	        		},
	        		data:{
	        			required:"Voce deve inserir uma data"
	        		},
	        	}
	        });
    });
    </script>
</body>
</html>