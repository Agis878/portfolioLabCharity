<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 22.11.2023
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="slogan container container--90">
    <h2>${error}</h2>
</div>
<section class="steps">
    <a href="/" class="btn btn--small">Powrót do strony głównej</a>
</section>
</body>
</html>
