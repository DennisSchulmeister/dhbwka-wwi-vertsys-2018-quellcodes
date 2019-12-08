/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_client;

/**
 * Von der Series-Entity des Servers abgeleitete POJO-Klasse, die alle Daten zu
 * einer Serie beinhaltet. Sie muss hier manuell angelegt (oder aus dem Server-
 * Projekt übernommen werden), da es keine Möglichkeit gibt, die benötigten
 * Klassen aus der WADL anzulegen bzw. die WADL hierfür nicht genügend
 * Informationen beinhaltet.
 */
public class Series {
    public String id = "";
    public String title = "";
    public String genre = "";
    public String country = "";
    public int fromYear = -1;
    public int toYear = -1;
    public int numberOfSeasons = -1;
    public int numberOfEpisodes = -1;
}
