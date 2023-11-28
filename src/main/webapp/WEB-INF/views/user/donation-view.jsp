<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 25.11.2023
  Time: 09:17
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
    <title>Lista darów</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <style>
        body {
            text-align: center;
        }

        .help--slides-items {
            width: 80%;
            margin: 0 auto;
        }

        .form-group--buttons {
            margin-top: 20px;
        }

        .btn {
            margin-right: 10px;
        }
    </style>
</head>
<body>

<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li>Witaj ${loggedUser.firstName}</li>
        </ul>
    </nav>
</header>
<section class="help">
    <h2>Przekazane dary</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>Lista przekazanych przez Ciebie darów.</p>
        <div class="steps--container">
            <table class="help--slides-items">
                <thead>
                <tr class="table-info">
                    <th><h3>Instytucja</h3></th>
                    <th>Ilość</th>
                    <th>Data odbioru</th>
                    <th>Godzina odbioru</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="donation" items="${donationList}" varStatus="loopStatus">
                    <tr>
                        <td>${donation.institution.name}</td>
                        <td>${donation.quantity}</td>
                        <td>${donation.pickUpDate}</td>
                        <td>${donation.pickUpTime}</td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <hr/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="form-group form-group--buttons" margin-top: 20px>
            <a href="/form" class="btn">Przekarz dary</a>
            <button type="button" class="btn btn--without-border prev-step" onclick="goBack()">Wstecz</button>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
