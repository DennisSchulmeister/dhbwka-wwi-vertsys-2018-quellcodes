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

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hauptklasse unseres kleinen Beispielprogramms. Sie implementiert ein kleines
 * Menüsystem zur Anzeige der vorhandenen Songs und zur Anlage eines neuen
 * Songs. Dabei greift sie auf die Klassen SongResource und Song zurück, die
 * passend zum Webservice angelegt wurden.
 */
public class Main {

    static BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    static SongResource songResource = new SongResource();

    /**
     * Hauptmenü des Programms. Zeigt alle vorhandenen Songs und fragt den
     * Anwender, ob er einen neuen Song anlegen oder das Programm beenden will.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean quit = false;

        while (!quit) {
            // Alle Songs vom Server abrufen und anzeigen
            System.out.println("================");
            System.out.println("Alle meine Songs");
            System.out.println("================");
            System.out.println();

            Song[] songs = songResource.findSongs("");

            if (songs != null) {
                for (Song song : songs) {
                    System.out.println("Name:       " + song.name);
                    System.out.println("Künstler:   " + song.artist);
                    System.out.println("Songwriter: " + song.songwriters);
                    System.out.println("Jahr:       " + song.releaseYear);
                    System.out.println();
                }
            }

            // Benutzer fragen, ob er einen neuen Song anlegen will
            System.out.println("[A] Neuen Song anlegen");
            System.out.println("[E] Ende");
            System.out.println();

            System.out.print("Deine Auswahl: ");
            String command = fromKeyboard.readLine();

            System.out.println();

            switch (command.toUpperCase()) {
                case "A":
                    createNewSong();
                    break;
                case "E":
                    System.out.println("Bye, bye!");
                    quit = true;
                    break;
            }
        }
    }

    /**
     * Menü zum Anlegen eines neuen Songs.
     *
     * @throws IOException
     * @throws UnirestException
     * @throws WebAppException
     */
    public static void createNewSong() throws IOException, UnirestException, WebAppException {
        System.out.println("==================");
        System.out.println("Neuen Song anlegen");
        System.out.println("==================");
        System.out.println();

        Song song = new Song();

        System.out.print("Name: ");
        song.name = fromKeyboard.readLine();

        System.out.print("Künstler: ");
        song.artist = fromKeyboard.readLine();

        System.out.print("Songwriter: ");
        song.songwriters = fromKeyboard.readLine();

        System.out.print("Jahr: ");
        song.releaseYear = Integer.parseInt(fromKeyboard.readLine());

        System.out.println();

        songResource.saveNewSong(song);
    }
}
