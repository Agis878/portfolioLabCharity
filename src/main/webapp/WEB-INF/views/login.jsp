<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 05.11.2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header>
    <%@include file="header_footer/header.jsp" %>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <%--    <form:form method="post" action="login">--%>
    <%--        <div class="form-group">--%>
    <%--            <label for="username">Login</label>--%>
    <%--            <input type="text" required name="username" id="username" class="form-control" placeholder="Login"/>--%>
    <%--        </div>--%>
    <%--        <div class="form-group">--%>
    <%--            <label for="password">Password</label>--%>
    <%--            <input type="password" required name="password" id="password" class="form-control" placeholder="Password"/>--%>
    <%--        </div>--%>
    <%--    </form:form>--%>


    <form:form action="/login" method="post">

        <div class="form-group">
            <input type="text" required name="username" id="username" class="form-control" placeholder="Email"/>
        </div>
        <div class="form-group">
            <input type="password" required name="password" id="password" class="form-control" placeholder="Password"/>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form:form>
</section>

<%@include file="header_footer/footer.jsp" %>
</body>
</html>
