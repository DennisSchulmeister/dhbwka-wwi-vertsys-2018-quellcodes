<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${readonly}">
                Angebot anzeigen
            </c:when>
            <c:when test="${edit}">
                Angebot bearbeiten
            </c:when>
            <c:otherwise>
                Angebot anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/offer_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/offers/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column margin">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>
                <label for="offer_category">Kategorie:</label>
                <div class="side-by-side">
                    <select name="offer_category" ${readonly ? 'readonly="readonly"' : ''}>
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${offer_form.values["offer_category"][0] == category.id ? 'selected' : ''}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="offer_offer_type">
                    Art des Angebots:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side margin">
                    <select name="offer_offer_type" ${readonly ? 'readonly="readonly"' : ''}>
                        <c:forEach items="${offerTypes}" var="offerType">
                            <option value="${offerType}" ${offer_form.values["offer_offer_type"][0] == offerType ? 'selected' : ''}>
                                <c:out value="${offerType.label}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="offer_short_text">
                    Bezeichnung:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="offer_short_text" value="${offer_form.values["offer_short_text"][0]}" ${readonly ? 'readonly="readonly"' : ''}>
                </div>

                <label for="offer_long_text">
                    Beschreibung:
                </label>
                <div class="side-by-side">
                    <textarea name="offer_long_text" ${readonly ? 'readonly="readonly"' : ''}><c:out value="${offer_form.values['offer_long_text'][0]}"/></textarea>
                </div>

                <label for="offer_long_text">
                    Preis:
                </label>
                <div class="side-by-side">
                    <select name="offer_price_type" ${readonly ? 'readonly="readonly"' : ''}>
                        <c:forEach items="${priceTypes}" var="priceType">
                            <option value="${priceType}" ${offer_form.values["offer_price_type"][0] == priceType ? 'selected' : ''}>
                                <c:out value="${priceType.label}"/>
                            </option>
                        </c:forEach>
                    </select>

                    <input type="text" name="offer_price" value="${offer_form.values["offer_price"][0]}" ${readonly ? 'readonly="readonly"' : ''}>
                </div>

                <%-- Button zum Abschicken --%>
                <c:if test="${!readonly}">
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit" name="action" value="save">
                            Sichern
                        </button>

                        <c:if test="${edit}">
                            <button class="icon-trash" type="submit" name="action" value="delete">
                                Löschen
                            </button>
                        </c:if>
                    </div>
                </c:if>
            </div>

            <%-- Anzeigefelder --%>
            <label for="offer_owner">Angelegt am:</label>
            <div class="side-by-side margin">
                <c:out value="${offer_form.values['offer_create_date'][0]}" />
                <c:out value="${offer_form.values['offer_create_time'][0]}" />
            </div>

            <label for="offer_owner">Anbieter:</label>
            <div class="side-by-side">
                <c:out value="${offer_form.values['owner_realname'][0]}" />
                <br/>
                <c:out value="${offer_form.values['owner_street'][0]}" />
                <br/>
                <c:out value="${offer_form.values['owner_postcode'][0]}" />
                <c:out value="${offer_form.values['owner_city'][0]}" />
                <br/>
                <c:out value="${offer_form.values['owner_phone'][0]}" />
                <br/>
                <c:out value="${offer_form.values['owner_email'][0]}" />
                <br/>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty offer_form.errors}">
                <ul class="errors">
                    <c:forEach items="${offer_form.errors}" var="error">
                        <li>${error}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>