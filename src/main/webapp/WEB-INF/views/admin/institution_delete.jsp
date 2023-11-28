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
<h2>Czy napewno chcesz usunąć instytucję "${institutionToDelete.name}"?</h2>
<form method="post" action="/admin/institutions/delete/${institutionToDelete.id}">
    <div class="button-group">
        <button type="submit">Yes</button>
        |
        <a href="/admin/institutions">No</a>
    </div>
    <input type="hidden" name="id" value="${institutionToDelete.id}"/>
</form>
<div>
    <a href="${pageContext.request.contextPath}/admin" class="btn">Wstecz</a>
</div>

</body>
</html>

