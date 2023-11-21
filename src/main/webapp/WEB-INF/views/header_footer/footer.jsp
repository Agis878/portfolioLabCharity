<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 05.11.2023
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<footer>
    <div id="contact" class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>

        <form:form class="form--contact" action="/send-email-form" method="post" modelAttribute="feedback">

            <div class="form-group form-group--50">
                <form:input path="name" type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <!-- Warunkowe wyświetlanie pola email -->
                <c:choose>
                    <c:when test="${not empty loggedUser}">
                        <!-- Użyj unikalnego identyfikatora, gdy użytkownik jest zalogowany -->
                        <form:input path="email" id="emailForLoggedInUser" type="text" name="email" placeholder="Email"
                                    value="${loggedUser.username}" readonly="true"/>
                    </c:when>
                    <c:otherwise>
                        <!-- Użyj innego unikalnego identyfikatora, gdy użytkownik nie jest zalogowany -->
                        <form:input path="email" id="emailForNonLoggedInUser" type="text" name="email"
                                    placeholder="Email"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <
            <div class="form-group">
                <form:textarea path="message" name="message" placeholder="Wiadomość" rows="1"/>
            </div>
            <input type="hidden" id="to" name="to" value="${emailAddress}"/>
            <form:button type="submit">Wyślij</form:button>
        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="/images/icon-facebook.svg"/></a> <a href="#"
                                                                                             class="btn btn--small"><img
                src="/images/icon-instagram.svg"/></a>
        </div>
    </div>
</footer>


