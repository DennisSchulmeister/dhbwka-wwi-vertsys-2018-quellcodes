/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.junit_beispiel.test;

import dhbwka.wwi.vertsys.javaee.junit_beispiel.GuestbookBean;
import dhbwka.wwi.vertsys.javaee.junit_beispiel.GuestbookEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit-Test für die GuestbookBean. Ab hier ist alles ganz normales JUnit 5,
 * auch wenn sich die meisten Goolge-Treffer und die Dokumentation von TomEE
 * noch auf JUnit 4 beziehen. Was soll man machen? NetBeans integriert beim
 * Anlegen eines neuen Projekts automatisch JUnit 5. Also nutzen wir eben das.
 * 
 * Die einzige Besonderheit hier ist, dass die Testklasse selbst auch eine
 * EJB ist, so dass wir hier die gewohnte Dependency Injection nutzen können,
 * um an die zu testenden EJBs ranzukommen.
 * 
 * Außerdem musste die delete()-Methode der GuestbookBean anders als normal
 * implementiert werden, damit sie im Unit Test keine Exception wirft. Könnte
 * ein Bug in der verwendeten TomEE-Version sein.
 */
@Stateless
public class TestGuestbookBean extends EJBTest {

    @EJB
    GuestbookBean guestbookBean;

    /**
     * Ein neu gespeicherter Datensatz muss eine ID zugewiesen bekommen.
     */
    @Test
    public void testSaveNew() {
        GuestbookEntity entry = new GuestbookEntity("Name", "Nachricht");
        entry = guestbookBean.saveNew(entry);

        assertNotNull(entry.getId(), "Der neu gespeicherte Datensatz hat keine ID!");
    }

    /**
     * Ein Eintrag kann anhand seiner ID gefunden werden.
     */
    @Test
    public void testFindById() {
        GuestbookEntity entry1 = new GuestbookEntity("Name", "Nachricht");
        entry1 = guestbookBean.saveNew(entry1);
        GuestbookEntity entry2 = guestbookBean.findById(entry1.getId());

        assertNotNull(entry2, "Der gespeicherte Eintrag wurde nicht gefunden!");
        assertEquals(entry1.getId(), entry2.getId(), "Die IDs stimmen nicht überein!");
    }

    /**
     * Änderungen an einem Eintrag werden gespeichert.
     */
    @Test
    public void testUpdate() {
        GuestbookEntity entry1 = new GuestbookEntity("Name", "Nachricht");
        entry1 = guestbookBean.saveNew(entry1);

        GuestbookEntity entry2 = guestbookBean.findById(entry1.getId());
        entry2.setName("NAME2");
        entry2.setMessage("NACHRICHT2");
        guestbookBean.update(entry2);

        GuestbookEntity entry3 = guestbookBean.findById(entry1.getId());

        assertEquals(entry2.getId(), entry3.getId(), "Die IDs stimmen nicht überein!");
        assertEquals(entry2.getName(), entry3.getName(), "Die Namen stimmen nicht überein!");
        assertEquals(entry2.getMessage(), entry3.getMessage(), "Die Nachrichten stimmen nicht überein!");
    }

    /**
     * Gelöschte Einträge werden wirklich gelöscht.
     */
    @Test
    public void testDelete() {
        GuestbookEntity entry1 = new GuestbookEntity("Name", "Nachricht");
        entry1 = guestbookBean.saveNew(entry1);
        guestbookBean.delete(entry1);
        GuestbookEntity entry2 = guestbookBean.findById(entry1.getId());
        
        assertNull(entry2, "Eintrag wurde nicht gelöscht!");
    }

}
