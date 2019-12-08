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

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hauptklasse unseres kleinen Beispielprogramms. Sie implementiert ein kleines
 * Menüsystem zur Anzeige der vorhandenen Serien und zur Anlage einer neuen
 * Serie. Dabei greift sie auf die Klassen SeriesCollection und Series zurück,
 * die passend zum Webservice angelegt wurden.
 */
public class Main {

    static BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    static SeriesResource seriesResource = new SeriesResource();

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
            // Alle Serien vom Server abrufen und anzeigen
            System.out.println("=================");
            System.out.println("Alle meine Serien");
            System.out.println("=================");
            System.out.println();

            Series[] seriesArray = seriesResource.findSeries("");

            if (seriesArray != null) {
                for (Series series : seriesArray) {
                    System.out.println("Id:       " + series.id);
                    System.out.println("Titel:    " + series.title);
                    System.out.println("Genre:    " + series.genre);
                    System.out.println("Land:     " + series.country);
                    System.out.println("Jahre:    " + series.fromYear + " bis " + series.toYear);
                    System.out.println("Staffeln: " + series.numberOfSeasons);
                    System.out.println("Episoden: " + series.numberOfEpisodes);
                    System.out.println();
                }
            }

            // Benutzer fragen, ob er einen neue Serie anlegen will
            System.out.println("[A] Neue Serie anlegen");
            System.out.println("[E] Ende");
            System.out.println();

            System.out.print("Deine Auswahl: ");
            String command = fromKeyboard.readLine();

            System.out.println();

            switch (command.toUpperCase()) {
                case "A":
                    createNewSeries();
                    break;
                case "E":
                    System.out.println("Bye, bye!");
                    quit = true;
                    break;
            }
        }
    }

    /**
     * Menü zum Anlegen einer neuen Serie.
     *
     * @throws IOException
     * @throws UnirestException
     * @throws WebAppException
     */
    public static void createNewSeries() throws IOException, UnirestException, WebAppException {
        System.out.println("==================");
        System.out.println("Neue Serie anlegen");
        System.out.println("==================");
        System.out.println();

        Series series = new Series();

        System.out.print("Id: ");
        series.id = fromKeyboard.readLine();

        System.out.print("Titel: ");
        series.title = fromKeyboard.readLine();

        System.out.print("Genre: ");
        series.genre = fromKeyboard.readLine();

        System.out.print("Land: ");
        series.country = fromKeyboard.readLine();

        System.out.print("Von Jahr: ");
        series.fromYear = Integer.parseInt(fromKeyboard.readLine());

        System.out.print("Bis Jahr: ");
        series.toYear = Integer.parseInt(fromKeyboard.readLine());

        System.out.print("Anz. Staffeln: ");
        series.numberOfSeasons = Integer.parseInt(fromKeyboard.readLine());

        System.out.print("Anz. Episoden: ");
        series.numberOfEpisodes = Integer.parseInt(fromKeyboard.readLine());

        System.out.println();

        seriesResource.saveNewSeries(series);
    }
}
