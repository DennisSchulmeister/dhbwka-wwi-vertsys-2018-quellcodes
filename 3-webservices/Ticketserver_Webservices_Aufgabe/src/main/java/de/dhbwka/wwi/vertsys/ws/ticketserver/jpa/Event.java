/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbwka.wwi.vertsys.ws.ticketserver.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Entity für eine Veranstaltung.
 */
@Entity
@Data // Lombok: @Data Generiert einen Konstruktur, sowie Getter und Setter
public class Event implements Serializable {

    @Id
    @GeneratedValue
    @TableGenerator(name = "event_ids", initialValue = 0, allocationSize = 5)
    private Long id;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Das Feld „Künstler” muss zwischein einem und 64 Zeichen lang sein.")
    @NotNull(message = "Das Feld „Künstler” darf nicht leer sein.")
    private String artist;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Das Feld „Veranstaltungsort” muss zwischein einem und 64 Zeichen lang sein.")
    @NotNull(message = "Das Feld „Veranstaltungsort” darf nicht leer sein.")
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Das Startdatum darf nicht leer sein.")
    private Date startDate;

    @Min(value = 1, message = "Es muss mindestens ein Ticket verkaufbar sein.")
    @NotNull(message = "Das Feld „Maximale Anzahl Tickets” darf nicht leer sein")
    private int maxTickets;

    private int remainingTickets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    List<Ticket> soldTickets = new ArrayList<>();
}
