<%-- 
    Copyright © 2019 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@tag pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="main" fragment="true"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <title>⛳️ Golf-Scorekarte | ${title}</title>
        <link rel="stylesheet" href="<c:url value="/style.css"/>" />
        <link rel="stylesheet" href="<c:url value="/form.css"/>" />

        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- Hauptinhalt der Seite -->
        <main>    
            <jsp:invoke fragment="main"/>
        </main>
    </body>
</html>