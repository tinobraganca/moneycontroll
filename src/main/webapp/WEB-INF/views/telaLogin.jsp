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
h1 {
    height: 100px;
    left: 70%;
    margin: -240px 0 0 -320px;
    position: absolute;
    top: 70%;
    width: 380px
}
</style>
<title>Principal</title>
</head>
<body>
    <div id="box">
        <div class="hero-unit">
            <form class="form-horizontal">
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Email</label>
                    <div class="controls">
                        <input type="text" id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputPassword">Senha</label>
                    <div class="controls">
                        <input type="password" id="inputPassword" placeholder="Senha">
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <label class="checkbox"> <input type="checkbox"> Lembre-se de mim
                        </label>
                        <button type="submit" class="btn">Entrar</button>
                        <a class="btn btn-primary" type="submit" class="btn">Cadastre-se</a>
                    </div>
                </div>
        </form>
        </div>
    </div>
</body>
</html>
