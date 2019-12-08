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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Entity für ein gekauftes Ticket.
 */
@Entity
@Data // Lombok: @Data Generiert einen Konstruktur, sowie Getter und Setter
public class Ticket implements Serializable {
    
    @Id
    @GeneratedValue
    @TableGenerator(name = "ticket_ids", initialValue = 0, allocationSize = 5)
    private Long id;
    
    @ManyToOne
    Event event;
    
    @Column(length = 100)
    @Size(min = 1, max = 64, message = "Das Feld „Name des Ticketbesitzers” muss zwischein einem und 100 Zeichen lang sein.")
    @NotNull(message = "Das Feld „Name des Ticketbesitzers” darf nicht leer sein.")
    private String ticketHolderName;
    
    @Column(length = 100)
    @Size(min = 1, max = 64, message = "Das Feld „Adresse des Ticketbesitzers” muss zwischein einem und 100 Zeichen lang sein.")
    @NotNull(message = "Das Feld „Adresse des Ticketbesitzers” darf nicht leer sein.")
    private String ticketHolderAdress;
}
