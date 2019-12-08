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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Von der Klasse SeriesResource des Servers abgeleitete Klasse, die im Prinzip
 * dieselben Methoden besitzt. Hier rufen wir jedoch den REST-Webservice des
 * Servers auf, anstelle direkt auf eine Datenbank zuzugreifen.
 */
public class SeriesResource {

    public static final String URL = "http://localhost:8080/REST_Serien_Server_Loesung/api/Series/";

    public String url = URL;
    public String username = "";
    public String password = "";

    Gson gson = new GsonBuilder().create();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public SeriesResource() {
    }

    public SeriesResource(String url) {
        this.url = url;
    }
    //</editor-fold>

    /**
     * Benutzername und Passwort für die Authentifizierung merken.
     *
     * @param username Benutzername
     * @param password Passwort
     */
    public void setAuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Serien suchen.
     *
     * @param query Suchbegriff
     * @return Gefundene Serien
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Series[] findSeries(String query) throws UnirestException, WebAppException {
        // Anfrage an den Server senden
        HttpResponse<String> httpResponse = Unirest.get(this.url)
                .queryString("query", query)
                .header("accept", "application/json")
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Series[].class);
    }

    /**
     * Neuen Song speichern.
     *
     * @param series Zu speichernde Serie
     * @return Gespeicherte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Series saveNewSeries(Series series) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.post(this.url)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(series))
                .asString();
        String res = httpResponse.getBody();
        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Series.class);
    }

    /**
     * Einzelne Serie auslesen.
     *
     * @param id Serien-ID
     * @return Gefundener Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Series getSeries(String id) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.get(this.url + id + "/")
                .header("accept", "application/json")
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Series.class);
    }

    /**
     * Anlegen oder Aktualisieren einer Serie.
     *
     * @param series Zu speichernde Serie
     * @return Gespeicherte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Series saveSeries(Series series) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.put(this.url + series.id + "/")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(series))
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Series.class);
    }

    /**
     * Serie löschen.
     *
     * @param id Serien-ID
     * @return Gelöschte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Series deleteSeries(String id) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.delete(this.url + id + "/")
                .header("accept", "application/json")
                .basicAuth(this.username, this.password)
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);
            
            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Series.class);
    }

}
