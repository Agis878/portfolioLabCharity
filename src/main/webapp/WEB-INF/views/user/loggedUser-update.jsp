<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 22.11.2023
  Time: 11:24
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
    <title>Edycja użytkownika</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li>Witaj ${user.firstName}</li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Edytuj swój profil</h2>
    <%--    @elvariable id="${user}" type=""--%>
    <form:form method="post" id="userDTO" modelAttribute="updateDTO" action="/user/update" autocomplete="on">
        <div class="form-group form-group--inline">
            <label class="form-group--inline label input">Email</label>
            <div>
                <form:input path="username" id="username" value="${user.username}"/>
                <form:errors path="username"/>
            </div>
        </div>
        <div class="form-group form-group--inline">
            <label class="form-group--inline label input">Imię</label>
            <div>
                <form:input path="firstName" id="firstName" value="${user.firstName}"/>
                <form:errors path="firstName"/>
            </div>
        </div>
        <div class="form-group form-group--inline">
            <label class="form-group--inline label input">Nazwisko</label>
            <div>
                <form:input path="lastName" id="lastName" value="${user.lastName}"/>
                <form:errors path="lastName"/>
            </div>
        </div>
        <div class="form-group form-group--buttons">
            <button type="button" class="btn prev-step" onclick="goBack()">Wstecz</button>
            <form:button class="btn" type="submit">Zapisz zmiany</form:button>
        </div>
    </form:form>
</section>
<script src="${pageContext.request.contextPath}/js/app.js"></script>


</body>
</html>
