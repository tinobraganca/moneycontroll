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
label.valid {
    background-color: #DFF0D8;
    border-color: #D6E9C6;
    color: #468847;
    border-radius: 4px 4px 4px 4px;
    margin-bottom: 20px;
    padding: 8px 35px 8px 14px;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}

label.errors {
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
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.bootpag.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
<title>Cadastrar</title>
</head>
<body>
    <%@ include file="abrastractSomaTotal.jsp"%>
    <div id="boxcenter">
        <ul class="nav nav-tabs">
            <li><a href="/index">In&iacute;cio</a></li>
            <li><a href="<c:url value="/grud/cad"/>">Cadastro De Pagementos</a></li>
            <li><a href="<c:url value="/grud/transacao"/>">Cadastrar Transa&ccedil;&otilde;es</a></li>
            <li class="active"><a href="<c:url value="/grud/show/"/>">Exibir Transa&ccedil;&otilde;es</a></li>
        </ul>

        <table id="tab" class="table table-hover" width="600px">
        </table>

        <div class="boxcenter" id="page-selection">
            <!--  Pagination goes here-->
        </div>

        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Altera&ccedil;ao de Transa&ccedil;ao</h3>
            </div>

            <form:form action="/grud/show/alteraTransacao/" method="POST" modelAttribute="alteraTransacao">
                <div class="modal-body">
                    <div>
                        <label for="data">Data:</label>
                        <form:input id="data" cssStyle="width:250px" maxlength="30" path="data" size="20" class="datepicker" />
                    </div>

                    <div>
                        <label for="valor">valor:<form:errors path="valor" cssClass="errors" /><br /></label>
                        <form:input id="valor" cssStyle="width:250px" maxlength="30" path="valor" size="20" type="number" />
                    </div>
                    <form:input type="hidden" id="id" path="id" />
                    <label for="descricao">Descri&ccedil;ao:<form:errors path="descricao" cssClass="errors" /><br /></label>
                    <form:select path="tipo">
                        <form:option value="0">Escolha uma op&ccedil;ao</form:option>
                        <form:option value="1">Receita</form:option>
                        <form:option value="2">Despesas</form:option>
                    </form:select>

                    <form:select id="cartao" path="cartao.id">
                        <form:option value="0">Escolha uma op&ccedil;ao</form:option>
                        <form:options items="${cartoes}" itemValue="id" itemLabel="nome" />
                    </form:select>
                    <div>
                        <form:textarea rows="3" id="descricao" cssStyle="width:250px" maxlength="30" path="descricao" type=""
                            placeholder="Digite aqui" size="30" />
                    </div>
                    <p></p>
                </div>

                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
                    <button class="btn btn-primary">Salvar mudan&ccedil;as</button>
                </div>
            </form:form>
        </div>

    </div>
    <script type="text/javascript">
					var page = 1;
					$(document)
							.ready(
									function() {
										$('.datepicker').datepicker({
											format : "dd/mm/yy"
										})
										myfunction(page);
										numeroMaxPag();
										$.validator
												.addMethod(
														"validaDataTransacao",
														function(value, element) {
															// put your own logic here, this is just a (crappy) example
															return value
																	.match(/^\d\d?\/\d\d?\/\d\d$/);
														},
														"Voce deve inserir uma data no formato dd/mm/yy.");

										jQuery.validator
												.addMethod(
														'validaSelectTransacao',
														function(value) {
															return (value != '0');
														},
														"Voce deve selecionar uma das op&ccedil;&otilde;s");

										$("#alteraTransacao")
												.validate(
														{
															rules : {
																valor : {
																	required : true
																},
																data : {
																	validaDataTransacao : true,
																	required : true
																},
																descricao : {
																	required : true,
																	minlength : 4,
																},
																tipo : {
																	validaSelectTransacao : true,
																	required : true
																},
																"cartao.id" : {
																	validaSelectTransacao : true,
																	required : true
																}

															},
															messages : {
																descricao : {
																	required : "Digite o seu nome",
																	minlength : "O seu nome deve conter, no minimo, 4 caracteres",
																},
																valor : {
																	required : "Deve inserir um valor a transa&ccedil;&atilde;o",

																},
																data : {
																	required : "Voce deve inserir uma data"
																},
															}
														});
									});

					function chamaModal(id) {
						var jsonUrl = "/grud/show/updateControl/?id=" + id;
						var html = '';
						$.ajax({
							'type' : "GET",
							'url' : jsonUrl,
							'dataType' : "json",
							'success' : function(retorno) {
								console.log(retorno)
								$('#myModal').modal();
								var tipo = retorno['tipo'];

								$('#valor').val(retorno['valor']);
								$('#descricao').val(retorno['descricao']);
								$('#data').val(retorno['dataFormatada']);
								$('#id').val(retorno['id']);
								$.each(retorno, function(index) {
									var item = retorno[index];
									var opcoes = retorno['cartao'].id;
									var $dd = $(retorno['cartao'].nome);
									var $options = $('option', $dd);

									$options.each(function() {
										if ($(this).val() == opcoes) {
											$dd.val($(this).val());
										}
									});
								});
								var $dd = $('#tipo');
								var $options = $('option', $dd);
								$options.each(function() {
									if ($(this).val() == tipo) {
										$dd.val($(this).val());
									}
								});
							}
						});
					}
					var myfunction = function(page) {
						var jsonUrl = "/grud/show/lista/?page=" + page;
						var html = '';
						$
								.ajax({
									'type' : "GET",
									'url' : jsonUrl,
									'dataType' : "json",
									'success' : function(retorno) {
										html += '<tr><th>#</th><th>Descri&ccedil;ao</th><th>Data</th><th>Valor</th><th>Pagemento</th><th>Tipo</th></tr>';
										$
												.each(
														retorno,
														function(index) {
															console
																	.log(retorno[index])
															var item = retorno[index];
															var tipo = item['tipo'];
															if (tipo == 1) {
																html += '<tr class="success" onclick="chamaModal('
																		+ item['id']
																		+ ')">';
																html += '<td>'
																		+ item['id']
																		+ '</a></td>';
																html += '<td>'
																		+ item['descricao']
																		+ '</td>';
																html += '<td>'
																		+ item['dataFormatada']
																		+ '</td>';
																html += '<td>'
																		+ item['valor']
																		+ '</td>';
																html += '<td>'
																		+ item['cartao'].nome
																		+ '</td>';
																html += '<td> Receita</td>';
																html += '</tr>'
															}
															if (tipo == 2) {
																html += '<tr class="error"  onclick="chamaModal('
																		+ item['id']
																		+ ')">';
																html += '<td>'
																		+ item['id']
																		+ '</td>';
																html += '<td>'
																		+ item['descricao']
																		+ '</td>';
																html += '<td>'
																		+ item['dataFormatada']
																		+ '</td>';
																html += '<td>'
																		+ item['valor']
																		+ '</td>';
																html += '<td>'
																		+ item['cartao'].nome
																		+ '</td>';
																html += '<td> Despesas</td>';
																html += '</tr>'
															}
														});
										$('#tab').html($(html));
									}
								});
					}

					var numeroMaxPag = function() {
						var jsonUrl = "/grud/show/pagMax";
						var html = '';
						boxcenter
						$.ajax({
							'type' : "GET",
							'url' : jsonUrl,
							'dataType' : "json",
							'success' : function(retorno) {
								console.log(retorno);
								$('#page-selection').bootpag({
									total : retorno,
									page : 1,
									maxVisible : 5,
								}).on('page', function(event, num) {
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