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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header>
    <%@include file="header_footer/header.jsp" %>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="userDTO" action="/register">

        <div class="form-group">
            <form:input path="firstName" id="firstName" placeholder="Imię"/>
            <form:errors path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" id="lastName" placeholder="Nazwisko"/>
            <form:errors path="lastName"/>
        </div>
        <div class="form-group">
            <form:input path="username" id="username" placeholder="Email"/>
            <form:errors path="username"/>
        </div>
        <div class="form-group">
            <form:password path="password" id="password" placeholder="Password"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:password path="passwordConfirm" id="passwordConfirm" placeholder="Confirm Password"/>
            <form:errors path="passwordConfirm"/>
        </div>
        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <form:button class="btn" type="submit">Załóż konto</form:button>
        </div>
    </form:form>
</section>

<%@include file="header_footer/footer.jsp" %>
</body>
</html>

