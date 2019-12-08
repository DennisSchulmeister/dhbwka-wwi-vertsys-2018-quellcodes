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
    <jsp:attribute name="title">Details</jsp:attribute>

    <jsp:attribute name="main">
        <%-- Button zur Navigation zur Übersicht --%>
        <form method="GET" action="<c:url value="/"/>">
            <button type="submit">
                Zurück zur Übersicht
            </button>
        </form>

        <%-- Inhalte der Scorekarte --%>
        <div class="container">
            <form method="POST">
                <input name="amountStrokes" type="hidden" value="18"/>
                
                <div class="formline">
                    <div>
                        <label for="gameDate">Datum</label>
                        <br/>
                        <input name="gameDate" type="date" value="${scorecard.gameDate}" />
                    </div>
                    <div>
                        <label for="gameTime">Uhrzeit</label>
                        <br/>
                        <input name="gameTime" type="time" value="${scorecard.gameTime}" />
                    </div>

                    <div class="grow">
                        <label for="course">Golfplatz</label>
                        <br/>
                        <input name="course" type="text" value="${scorecard.course}" />
                    </div>

                    <div>
                        <label for="par">Par</label>
                        <br/>
                        <input name="par" type="number" value="${scorecard.par}" />
                    </div>
                </div>

                <div class="formline">
                    <div class="grow">
                        <label for="strokes[0]">Loch 1</label>
                        <br/>
                        <input name="strokes[0]" type="number" value="${strokes[0].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[1]">Loch 2</label>
                        <br/>
                        <input name="strokes[1]" type="number" value="${strokes[1].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[2]">Loch 3</label>
                        <br/>
                        <input name="strokes[2]" type="number" value="${strokes[2].amount}" />
                    </div>

                    <div class="grow">
                        <label for="strokes[3]">Loch 4</label>
                        <br/>
                        <input name="strokes[3]" type="number" value="${strokes[3].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[4]">Loch 5</label>
                        <br/>
                        <input name="strokes[4]" type="number" value="${strokes[4].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[5]">Loch 6</label>
                        <br/>
                        <input name="strokes[5]" type="number" value="${strokes[5].amount}" />
                    </div>
                </div>
                    
                <div class="formline">
                    <div class="grow">
                        <label for="strokes[6]">Loch 7</label>
                        <br/>
                        <input name="strokes[6]" type="number" value="${strokes[6].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[7]">Loch 8</label>
                        <br/>
                        <input name="strokes[7]" type="number" value="${strokes[7].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[8]">Loch 9</label>
                        <br/>
                        <input name="strokes[8]" type="number" value="${strokes[8].amount}" />
                    </div>

                    <div class="grow">
                        <label for="strokes[9]">Loch 10</label>
                        <br/>
                        <input name="strokes[9]" type="number" value="${strokes[9].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[10]">Loch 11</label>
                        <br/>
                        <input name="strokes[10]" type="number" value="${strokes[10].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[11]">Loch 12</label>
                        <br/>
                        <input name="strokes[11]" type="number" value="${strokes[11].amount}" />
                    </div>
                </div>
                    
                <div class="formline">
                    <div class="grow">
                        <label for="strokes[12]">Loch 13</label>
                        <br/>
                        <input name="strokes[12]" type="number" value="${strokes[12].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[13]">Loch 14</label>
                        <br/>
                        <input name="strokes[13]" type="number" value="${strokes[13].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[14]">Loch 15</label>
                        <br/>
                        <input name="strokes[14]" type="number" value="${strokes[14].amount}" />
                    </div>

                    <div class="grow">
                        <label for="strokes[15]">Loch 16</label>
                        <br/>
                        <input name="strokes[15]" type="number" value="${strokes[15].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[16]">Loch 17</label>
                        <br/>
                        <input name="strokes[16]" type="number" value="${strokes[16].amount}" />
                    </div>
                    
                    <div class="grow">
                        <label for="strokes[17]">Loch 18</label>
                        <br/>
                        <input name="strokes[17]" type="number" value="${strokes[17].amount}" />
                    </div>
                </div>
                    
                <div class="formline">
                    <button type="submit">Scorekarte sichern</button>
                    <div>
                        <label class="total">Summe:</label>
                        <c:out value="${scorecard.sumStrokes}" />
                    </div>
                    <div>
                        <label class="total">Stableford:</label>
                        <c:out value="${scorecard.stableford}" />
                    </div>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>