<%-- 
    Copyright © 2019 Dennis Schulmeister-Zimolong

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
    <jsp:attribute name="title">Übersicht</jsp:attribute>
        
    <jsp:attribute name="main">
        <%-- Button zum Anlegen einer neuen Scorekarte --%>
        <form method="GET" action="<c:url value="/new/"/>">
            <button type="submit">
                Neue Scorekarte anlegen
            </button>
        </form>
            
        <%-- Liste der bereits vorhandenen Scorekarten --%>
        <table class="container">
            <c:if test="${not empty scorecards}">
                <thead>
                    <tr>
                        <th>
                            Datum
                        </th>
                        <th>
                            Uhrzeit
                        </th>
                        <th>
                            Golfplatz
                        </th>
                        <th>
                            Par
                        </th>
                        <th>
                            Schläge
                        </th>
                        <th>
                            Stableford
                        </th>
                    </tr>
                </thead>
            </c:if>
            <c:forEach items="${scorecards}" var="scorecard">
                <tr>
                    <td>
                        <a href="<c:url value="/show/${scorecard.id}"/>">
                            <c:out value="${scorecard.gameDate}"/>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/show/${scorecard.id}"/>">
                            <c:out value="${scorecard.gameTime}"/>
                        </a>
                    </td>
                    <td>
                        <c:out value="${scorecard.course}"/>
                    </td>
                    <td>
                        <c:out value="${scorecard.par}"/>
                    </td>
                    <td>
                        <c:out value="${scorecard.sumStrokes}"/>
                    </td>
                    <td>
                        <c:out value="${scorecard.stableford}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:attribute>
</template:base>