<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 12.11.2023
  Time: 15:05
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
    <%@include file="/WEB-INF/views/header_footer/header.jsp" %>
</header>

<section class="login-page">
    <h2>Dodaj instytucję</h2>
    <form:form method="post" modelAttribute="institution" action="/admin/institutions/add" autocomplete="off">
        <div class="form-group">
            <form:input path="name" id="name" placeholder="Nazwa"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="description" id="description" placeholder="Opis"/>
            <form:errors path="description"/>
        </div>
        <div class="form-group form-group--buttons">

            <form:button class="btn" type="submit">Dodaj instytucję</form:button>
            <a href=${pageContext.request.contextPath}/admin class="btn">Wstecz</a>
        </div>
    </form:form>
</section>

<%@include file="/WEB-INF/views/header_footer/footer.jsp" %>
</body>
</html>

