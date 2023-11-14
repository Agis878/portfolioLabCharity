<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 21.09.2023
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<body>
<h2>Do you like to remove user "${userToDelete.username}"?</h2>
<%--<form method="post" th:action="@{'/admin/delete/' + ${userToDelete.id}}">--%>
<form method="post" action="/admin/delete/${userToDelete.id}">
    <div class="button-group">
        <button type="submit">Yes</button>
        |
        <a href="/admin/${userRole eq 'ROLE_ADMIN' ? 'admins' : 'users'}">No</a>

        <%--        <a th:href="@{'/admin/' + (${userRole eq 'ROLE_ADMIN' ? 'admins' : 'users'})}">No</a>--%>
    </div>
    <input type="hidden" name="id" value="${userToDelete.id}"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/admin" class="btn">Wstecz</a>
</div>

</body>
</html>
<%--<head>--%>
<%--    <title>Delete user</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2>Do you like to remove user "${userToDelete.username}"?</h2>--%>
<%--<form method="post">--%>
<%--    <div class="button-group">--%>
<%--        <button type="submit">Yes</button>|<a href="/admin/admins">No</a>--%>
<%--    </div>--%>
<%--    <input type="hidden" name="id" value="${userToDelete.id}"/>--%>
<%--</form>--%>
<%--<div>--%>
<%--    <a href=${pageContext.request.contextPath}/admin class="btn">Wstecz</a>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
