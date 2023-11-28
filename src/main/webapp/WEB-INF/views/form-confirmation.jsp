<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 05.11.2023
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Form confirmation</title>

</head>
<body>
<div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>
<section class="steps">
    <c:choose>
        <c:when test="${not empty loggedUser}">
            <a href="/user" class="btn btn--small">Powrót do strony głównej</a>
        </c:when>
        <c:otherwise>
            <a href="/" class="btn btn--small">Powrót do strony głównej</a>
        </c:otherwise>
    </c:choose>
</section>
</body>
</html>

