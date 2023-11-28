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
            <li>Witaj ${loggedUser.firstName}</li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Zmiana hasła</h2>

    <form:form method="post" modelAttribute="passwordUpdateDTO" action="/user/update/passwordChange"
               autocomplete="on">

    <div class="form-group form-group--inline">
        <label class="form-group--inline label input">Nowe hasło</label>
        <div>
            <form:input path="password" id="password" required="required"/>
            <form:errors path="password"/>
        </div>
    </div>
    <div class="form-group form-group--inline">
        <label class="form-group--inline label input">Powtórz nowe hasło</label>
        <div>
            <form:input path="passwordConfirm" id="passwordConfirm" required="required"/>
            <form:errors path="passwordConfirm"/>
        </div>
    </div>
    <div class="form-group form-group--buttons">
        <button type="button" class="btn prev-step" onclick="goBack()">Wstecz</button>
        <form:button class="btn" type="submit">Zapisz zmiany</form:button>
    </div>
    </form:form>

    <script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>
</html>
