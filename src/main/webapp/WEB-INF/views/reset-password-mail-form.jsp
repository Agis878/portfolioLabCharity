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
    <form method="post" action="/forgotPassword">
        <h2>Podaj adres email, na który zostanie wysłany link do zmiany hasła.</h2>
        <div class="form-group">
            <input name="username" id="username" placeholder="Email"/>
        </div>
        <input type="hidden" name="resetPasswordCode" value="${resetPasswordCode}"/>
        <div class="form-group form-group--buttons">
            <button type="submit" class="btn">Zatwierdź</button>
        </div>
    </form>
</section>
</body>
</html>

