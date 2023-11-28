<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 26.11.2023
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Account Activation</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>

<div>
    <%-- Sprawdź, czy aktywacja zakończyła się sukcesem --%>
    <c:if test="${activationSuccess}">
        <h1>Aktywacja zakończona sukcesem</h1>
        <p>Aktywacja twojego konta zakończyła się sukcesem.
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </c:if>

    <%-- Sprawdź, czy wystąpił błąd w trakcie aktywacji --%>
    <c:if test="${activationError}">
        <h1>Aktywacja zakończona niepowodzeniem.</h1>
        <p>Wystąpił błąd podczas aktywacji konta.Sprawdź poprawność kodu aktywacyjnego.</p>
    </c:if>
</div>
</body>
</html>