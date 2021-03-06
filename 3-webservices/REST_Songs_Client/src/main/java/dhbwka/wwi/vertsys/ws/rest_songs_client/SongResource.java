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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Von der Klasse SongResource des Servers abgeleitete Klasse, die im Prinzip
 * dieselben Methoden besitzt. Hier rufen wir jedoch den REST-Webservice des
 * Servers auf, anstelle direkt auf eine Datenbank zuzugreifen.
 */
public class SongResource {

    public static final String URL = "http://localhost:8080/REST_Songs_Server/api/Songs/";

    public String url = URL;
    public String username = "";
    public String password = "";

    Gson gson = new GsonBuilder().create();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public SongResource() {
    }

    public SongResource(String url) {
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
     * Songs suchen.
     * 
     * @param query Suchbegriff
     * @return Gefundene Songs
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Song[] findSongs(String query) throws UnirestException, WebAppException {
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
        
        return this.gson.fromJson(httpResponse.getBody(), Song[].class);
    }
    
    /**
     * Neuen Song speichern.
     * 
     * @param song Zu speichernder Song
     * @return Gespeicherter Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Song saveNewSong(Song song) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.post(this.url)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(song))
                .asString();
        
        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);
            
            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }
        
        return this.gson.fromJson(httpResponse.getBody(), Song.class);
    }
    
    /**
     * Einzelnen Song auslesen.
     * 
     * @param id Song-ID
     * @return Gefundener Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Song getSong(long id) throws UnirestException, WebAppException {
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
        
        return this.gson.fromJson(httpResponse.getBody(), Song.class);
    }
    
    /**
     * Aktualisieren eines Songs.
     * 
     * @param song Zu speichernder Song
     * @return Gespeicherter Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Song updateSong(Song song) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.post(this.url + song.id + "/")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(song))
                .asString();
        
        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);
            
            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }
        
        return this.gson.fromJson(httpResponse.getBody(), Song.class);
    }
    
    /**
     * Song löschen.
     * 
     * @param id Song-ID
     * @return Gelöschter Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Song deleteSong(long id) throws UnirestException, WebAppException {
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
        
        return this.gson.fromJson(httpResponse.getBody(), Song.class);
    }
    
}
