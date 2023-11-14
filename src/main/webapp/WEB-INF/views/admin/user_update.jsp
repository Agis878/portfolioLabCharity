<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 12.11.2023
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
    <%@include file="/WEB-INF/views/header_footer/header.jsp" %>
</header>

<section class="login-page">
    <h2>Edytuj użytkownika</h2>
    <form:form method="post" modelAttribute="user" action="/admin/update" autocomplete="off">

        <div class="form-group">

            <form:input path="username" id="username" placeholder="Email"/>
            <form:errors path="username"/>
        </div>
        <div class="form-group">
            <form:input path="firstName" id="firstName" placeholder="Imię"/>
            <form:errors path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" id="lastName" placeholder="Nazwisko"/>
            <form:errors path="lastName"/>
        </div>

        <div class="form-group">

            <form:select path="role" id="role">
                <form:option value="" label="--Select Role --"/>
                <form:option value="ROLE_USER">USER</form:option>
                <form:option value="ROLE_ADMIN">ADMIN</form:option>
            </form:select>
            <form:errors path="role"/>
        </div>
        <div class="form-group">

            <form:select path="active" id="active">
                <form:option value="" label="--Select is Active --"/>
                <form:option value="true">True</form:option>
                <form:option value="false">False</form:option>
            </form:select>
            <form:errors path="active"/>
        </div>
        <div class="form-group">
            <form:hidden path="password" id="password"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">

            <form:hidden path="passwordConfirm" id="passwordConfirm"/>
            <form:errors path="passwordConfirm"/>
        </div>
        <div class="form-group form-group--buttons">
            <form:hidden path="id" id="id"/>
            <form:button class="btn" type="submit">Zapisz zmiany</form:button>
            <a href=${pageContext.request.contextPath}/admin class="btn">Wstecz</a>
        </div>
    </form:form>
</section>
</section>

<%@include file="/WEB-INF/views/header_footer/footer.jsp" %>
</body>
</html>
