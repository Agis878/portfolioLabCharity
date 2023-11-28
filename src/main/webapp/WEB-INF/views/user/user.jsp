<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 11.11.2023
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Home</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li>Witaj ${loggedUser.firstName}</li>
        </ul>
        <ul>
            <li><a href="/user/donations" class="btn btn--small btn--without-border">Przekazane dary</a></li>
            <li><a href="/user/update" class="btn btn--small btn--without-border">Edytuj profil</a></li>
            <li><a href="/user/update/passwordChange" class="btn btn--small btn--without-border">Zmień hasło</a></li>
            <li><a href="/logout" class="btn btn--small btn--without-border">Wyloguj</a></li>

        </ul>
        <ul>
            <li><a href="/form" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/form" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>
<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${donationQty}</em>

            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em>${donationSum}</em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>

<section class="steps">
    <h2>Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>


</section>

<section class="about-us">
    <div class="about-us--text" id="about-us">
        <h2>O nas</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
            optio esse quisquam illo omnis.</p>
        <img src="<c:url value="/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section class="help" id="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
            Możesz sprawdzić czym się zajmują.</p>

        <ul class="help--slides-items">
            <c:forEach var="institution" items="${institutionList}" varStatus="loopStatus">
                <c:if test="${loopStatus.index % 2 == 0}">
                    <li>
                </c:if>

                <div class="col">
                    <div class="title">Fundacja "${institution.name}"</div>
                    <div class="subtitle">Cel i misja: ${institution.description}.</div>
                </div>

                <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
                    </li>
                </c:if>
            </c:forEach>


        </ul>
    </div>

</section>

<%@include file="../header_footer/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
