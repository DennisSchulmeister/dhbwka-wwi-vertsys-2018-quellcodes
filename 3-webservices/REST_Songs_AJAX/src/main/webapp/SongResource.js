/* 
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
"use strict";

/**
 * Von der Klasse SongResource des Servers abgeleitete Klasse, die im Prinzip
 * dieselben Methoden besitzt. Hier rufen wir jedoch den REST-Webservice des
 * Servers auf, anstelle direkt auf eine Datenbank zuzugreifen.
 */
class SongResource {

    /**
     * Konstruktor.
     * @param {String} url Basis-URL des REST-Webservices (optional)
     */
    constructor(url) {
        this.url = url || "http://localhost:8080/REST_Songs_Server/api/Songs/";
        this.username = "";
        this.password = "";
    }

    /**
     * Benutzername und Passwort für die Authentifizierung merken.
     * @param {String} username Benutzername
     * @param {String} password Passwort
     */
    setAuthData(username, password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Songs suchen.
     * @param {String} query Suchparameter (optional)
     * @returns {Promise} Gefundene Songs
     */
    async findSongs(query) {
        let url = this.url;

        if (query !== undefined) {
            url += "?query=" + encodeURI(query);
        }

        let response = await fetch(url, {
            headers: {
                "accept": "application/json"
            }
        });

        return await response.json();
    }

    /**
     * Neuen Song speichern.
     * @param {Object} song Zu speichernder Song
     * @returns {Promise} Gespeicherter Song
     */
    async saveNewSong(song) {
        let response = await fetch(this.url, {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json",
                "authorization": "Basic " + btoa(this.username + ":" + this.password)
            },
            body: JSON.stringify(song)
        });

        return await response.json();
    }

    /**
     * Einzelnen Song auslesen.
     * @param {Number} id Song-ID
     * @returns {Promise} Gefundener Song
     */
    async getSong(id) {
        let response = await fetch(this.url + id + "/", {
            headers: {
                "accept": "application/json",
                "authorization": "Basic " + btoa(this.username + ":" + this.password)
            }
        });

        return await response.json();
    }

    /**
     * Aktualisieren eines Songs.
     * @param {Object} song Zu speichernder Song
     * @returns {Promise} Gespeicherter Song
     */
    async updateSong(song) {
        let response = await fetch(this.url + song.id + "/", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json",
                "authorization": "Basic " + btoa(this.username + ":" + this.password)
            },
            body: JSON.stringify(song)
        });

        return await response.json();
    }

    /**
     * Song löschen.
     * @param {Number} id Song ID
     * @returns {Promise} Gelöschter Song
     */
    async deleteSong(id) {
        let response = await fetch(this.url + id + "/", {
            method: "DELETE",
            headers: {
                "accept": "application/json",
                "authorization": "Basic " + btoa(this.username + ":" + this.password)
            }
        });

        return await response.json();
    }
}
