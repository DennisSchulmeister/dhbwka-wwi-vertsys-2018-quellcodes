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
        Übersicht
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/offer_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/offer/new/"/>">Angebot anlegen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/categories/"/>">Kategorien bearbeiten</a>
        </div>
        
        <div class="menuitem">
            <a href="<c:url value="/app/user/"/>">Benutzer bearbeiten</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="search_text" value="${param.search_text}" placeholder="Beschreibung"/>

            <select name="search_category">
                <option value="">Alle Kategorien</option>

                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${param.search_category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <select name="search_offer_type">
                <option value="">Alle Angebotsarten</option>

                <c:forEach items="${offerTypes}" var="offerType">
                    <option value="${offerType}" ${param.search_offer_type == offerType ? 'selected' : ''}>
                        <c:out value="${offerType.label}"/>
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Angebote --%>
        <c:choose>
            <c:when test="${empty offers}">
                <p>
                    Es wurden keine Angebote gefunden. 🐈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="dhbwka.wwi.vertsys.javaee.minimarkt.web.WebUtils"/>
                
                <table>
                    <thead>
                        <tr>
                            <th>Bezeichnung</th>
                            <th>Kategorie</th>
                            <th>Benutzer</th>
                            <th>Angebotstyp</th>
                            <th class="right">Preis</th>
                            <th>Preistyp</th>
                            <th>Datum</th>
                        </tr>
                    </thead>
                    <c:forEach items="${offers}" var="offer">
                        <tr>
                            <td>
                                <a href="<c:url value="/app/offer/${offer.id}/"/>">
                                    <c:out value="${offer.shortText}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${offer.category.name}"/>
                            </td>
                            <td>
                                <c:out value="${offer.owner.realname}"/>
                            </td>
                            <td>
                                <c:out value="${offer.offerType.label}"/>
                            </td>
                            <td class="right">
                                <c:out value="${utils.formatDouble(offer.price)}"/>
                            </td>
                            <td>
                                <c:out value="${offer.priceType.label}"/>
                            </td>
                            <td>
                                <c:out value="${utils.formatDate(offer.createDate)}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>