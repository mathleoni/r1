<%-- 
    Document   : login
    Created on : 25/05/2018, 12:04:13
    Author     : Rian Alves
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/bootstrap.jspf" %>
        <title>Login - Amigo Oculto</title>
    </head>
    <body>
        <div class="row">
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"><br/>
                <div class="box-parent-login">
                    <div class="well bg-white box-login">


                        <img src="icones/Logotipo.png" class="rounded mx-auto d-block" width="500" height="166" alt="Imagem">
                        <br/>
                        <form method="post">
                            <fieldset>

                                <div class="form-group ls-login-user">
                                    <label for="userLogin">E-mail</label>
                                    <input class="form-control ls-login-bg-user input-lg" id="userLogin" type="text" aria-label="Usuário" placeholder="Usuário" name="usuario">
                                </div>

                                <div class="form-group ls-login-password">
                                    <label for="userPassword">Senha</label>
                                    <input class="form-control ls-login-bg-password input-lg" id="userPassword" type="password" aria-label="Senha" placeholder="Senha" name="senha">
                                </div>

                                <center><a href="">Esqueci minha senha</a></center><br/>

                                <input type="submit" value="Entrar" class="btn btn-primary btn-lg btn-block" name="acessar" >

                                <center>Não possui cadastro? </center>
                                <center> <a href="registro.html">Cadastre-se agora</a></center>

                            </fieldset>
                        </form>
                    </div>
                    <c:if test="${erroLogin == true}">
                        <div class="alert alert-danger" role="alert">
                            <center>Erro ao realizar Login! Verifique a senha!</center>
                        </div>
                    </c:if>
                    <c:if test="${param.sucesso == true}">
                        <div class="alert alert-success" role="alert">
                            <center>Cadastro Realizado com sucesso!</center>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col"></div>
            <div class="col"></div>
        </div>

        <%@include file="/WEB-INF/jspf/rodape.jspf" %>