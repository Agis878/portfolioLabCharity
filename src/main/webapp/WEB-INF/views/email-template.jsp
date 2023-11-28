<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 22.11.2023
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Email Template</title>
</head>
<body>
<p>Adres Email: <span th:text="${email}"></span></p>
<p>Nazwa użytkownika: <span th:text="${name}"></span></p>
<p>Wiadomość: <span th:text="${message}"></span></p>
</body>
</html>
