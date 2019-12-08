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
        Benutzerprofil bearbeiten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/user_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/offers/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>
                <h2>Passwort ändern</h2>

                <label for="user_username">
                    Benutzername:
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_username" value="${user_form.values["user_username"][0]}" readonly="readonly">
                </div>

                <label for="user_old_password">
                    Altes Passwort:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="password" name="user_old_password" value="${user_form.values["user_old_password"][0]}">
                </div>

                <label for="user_new_password1">
                    Neues Passwort:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="password" name="user_new_password1" value="${user_form.values["user_new_password1"][0]}">
                </div>

                <label for="user_new_password2">
                    Neues Passwort (wdh.):
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="password" name="user_new_password2" value="${user_form.values["user_new_password2"][0]}">
                </div>

                <h2>Anschrift</h2>

                <label for="user_realname">
                    Vor- und Nachname:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_realname" value="${user_form.values["user_realname"][0]}">
                </div>

                <label for="user_street">
                    Straße und Hausnummer:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_street" value="${user_form.values["user_street"][0]}">
                </div>

                <label for="user_street">
                    Postleitzahl und Ort:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_postcode" value="${user_form.values["user_postcode"][0]}">
                    <input type="text" name="user_city" value="${user_form.values["user_city"][0]}">
                </div>

                <h2>Kontaktdaten</h2>

                <label for="user_phone">
                    Telefon:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_phone" value="${user_form.values["user_phone"][0]}">
                </div>

                <label for="user_email">
                    E-Mail:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="user_email" value="${user_form.values["user_email"][0]}">
                </div>

                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <button class="icon-trash" type="submit" name="action" value="delete">
                        Löschen
                    </button>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty user_form.errors}">
                <ul class="errors">
                    <c:forEach items="${user_form.errors}" var="error">
                        <li>${error}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>