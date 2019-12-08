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

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test für eine generische Entity Bean</title>
    </head>
    <body>
        <h1>Test für eine generische Entity Bean</h1>
        
        <h2>Ausgewählte Filme (Jahr <c:out value="${jahr}"/>)</h2>
        <ul>
            <c:forEach items="${filme}" var="film">
                <li>
                    <c:out value="${film.name}"/>, <c:out value="${film.jahr}"/>, <c:out value="${film.genre}"/>
                </li>
            </c:forEach>
        </ul>
        
        <h2>Filme aus Jahr</h2>
        <form method="GET">
            <input type="number" placeholder="Jahr" name="jahr">
            <br>
            <input type="submit">
        </form>
        
        <h2>Film anlegen</h2>
        <form method="POST">
            <input type="text" placeholder="Name" name="name">
            <br>
            <input type="number" placeholder="Jahr" name="jahr">
            <br>
            <input type="text" placeholder="Genre" name="genre">
            <br>
            <input type="submit">
        </form>
    </body>
</html>
