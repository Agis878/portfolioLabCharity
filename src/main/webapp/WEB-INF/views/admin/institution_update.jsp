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
    <h2>Edytuj instytucję</h2>
    <form:form method="post" modelAttribute="institutionToUpdate" action="/admin/institutions/update"
               autocomplete="off">

        <div class="form-group">

            <form:input path="name" id="name" placeholder="name"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="description" id="description" placeholder="description"/>
            <form:errors path="description"/>
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
