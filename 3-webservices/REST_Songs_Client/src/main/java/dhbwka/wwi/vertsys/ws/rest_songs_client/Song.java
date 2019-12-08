/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_songs_client;

/**
 * Von der Song-Entity des Servers abgeleitete POJO-Klasse, die alle Daten zu
 * einem Song beinhaltet. Sie muss hier manuell angelegt (oder aus dem Server-
 * Projekt übernommen werden), da es keine Möglichkeit gibt, die benötigten
 * Klassen aus der WADL anzulegen bzw. die WADL ohnehin keine Informationen
 * hierüber enthält.
 */
public class Song {

    public final long id = 0L;
    public String name = "";
    public String artist = "";
    public String songwriters = "";
    public int releaseYear = 0;

}
