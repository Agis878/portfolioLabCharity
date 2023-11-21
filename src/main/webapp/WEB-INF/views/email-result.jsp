<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 17.11.2023
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Result</title>
</head>
<body>
<h1>Email Result</h1>

<div th:if="${successMessage}">
    <p style="color: green;">${successMessage}</p>
</div>

<div th:if="${errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</div>

<a href="/">Back to Home</a>
</body>
</html>
