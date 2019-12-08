<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Gästebuch MVC-Beispiel</title>
        <link rel="stylesheet" href="<c:url value="/static/style.css" />">
    </head>
    <body>
        <div class="container">
            <h1>Mein kleines Gästebuch</h1>

            <form:form modelAttribute="newEntry">
                <form:input path="name" type="text" placeholder="Dein Name"/>
                <button type="submit">Abschicken</button>
                
                <div class="error">
                    <form:errors path="*"/>
                </div>
            </form:form>

            <ul>
                <c:forEach items="${entries}" var="entry">
                    <li>
                        ${entry.visitDate}, ${entry.visitTime}: ${entry.name}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
