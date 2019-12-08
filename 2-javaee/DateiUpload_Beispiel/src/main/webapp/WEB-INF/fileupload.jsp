<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>📎 Beispiel für einen Dateiupload mit Java</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Beispiel für einen Dateiupload mit Java</h1>

        <h2>Neue Datei hochladen</h2>

        <form method="POST" enctype="multipart/form-data">
            <input type="file" name="myFile" />
            <button type="submit">Hochladen</Button>
        </form>

        <h2>Bereits vorhandene Dateien</h2>

        <c:choose>
            <c:when test="${empty allFiles}">
                <p class="message">
                    Es wurden noch keine Dateien hochgeladen.
                </p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Vorschau</th>
                            <th>ID</th>
                            <th>Dateiname</th>
                            <th>Größe</th>
                        </tr>
                    </thead>

                    <c:forEach items="${allFiles}" var="file">
                        <tr>
                            <td>
                                <a href="<c:url value="/file?id=${file.id}"/>">
                                    <img src="<c:url value="/file?id=${file.id}"/>" alt="${file.filename}" class="preview"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${file.id}" />
                            </td>
                            <td>
                                <c:out value="${file.filename}" />
                            </td>
                            <td>
                                <fmt:formatNumber var="size" value="${fn:length(file.filecontent) / 1024 / 1024}" maxFractionDigits="2" />
                                <c:out value="${size} MiB" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
