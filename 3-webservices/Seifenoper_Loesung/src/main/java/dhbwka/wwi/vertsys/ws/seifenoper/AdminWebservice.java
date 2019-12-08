/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.seifenoper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Einfacher Webservice zum Anlegen von Testdaten.
 */
@Stateless
@WebService(serviceName = "seifenoper")
public class AdminWebservice {

    @EJB
    BeziehungBean beziehungBean;

    @EJB
    PersonBean personBean;

    @WebMethod
    @WebResult(name = "status")
    public String createExampleData() throws ParseException {
        // Alte Einträge komplett löschen
        for (Beziehung beziehung : this.beziehungBean.findAll()) {
            this.beziehungBean.delete(beziehung);
        }

        for (Person person : this.personBean.findAll()) {
            this.personBean.delete(person);
        }

        // Neue Personen anlegen
        Person joeCool = new Person("Joe Cool", 1983, Geschlecht.MAENNLICH, "Der Schwarm aller Frauen");
        this.personBean.saveNew(joeCool);

        Person gerdFleißig = new Person("Gerd Fleißig", 1971, Geschlecht.MAENNLICH, "Ist mit seiner Arbeit verheiratet");
        this.personBean.saveNew(gerdFleißig);

        Person timBader = new Person("Tim Bader", 1992, Geschlecht.MAENNLICH, "Schüchtern aber liebenswert");
        this.personBean.saveNew(timBader);

        Person alexKurz = new Person("Alex Kurz", 1991, Geschlecht.MAENNLICH, "Klassenkamerad von Tim");
        this.personBean.saveNew(alexKurz);

        Person sophiaBergmann = new Person("Sophia Bergmann", 1986, Geschlecht.WEIBLICH, "Ist einfach ein guter Kumpel");
        this.personBean.saveNew(sophiaBergmann);

        Person juliaHofer = new Person("Julia Hofer", 1982, Geschlecht.WEIBLICH, "War mal mit Gerd zusammen");
        this.personBean.saveNew(juliaHofer);

        Person susanneGraf = new Person("Susanne Graf", 1993, Geschlecht.WEIBLICH, "Ist auf der selben Schule wie Tim und Alex");
        this.personBean.saveNew(susanneGraf);

        Person klaraRoth = new Person("Klara Roth", 1987, Geschlecht.WEIBLICH, "Ist bei allens Jungs und Mädels beliebt");
        this.personBean.saveNew(klaraRoth);

        Person gerardDuPoint = new Person("Gerard Du Point", 1998, Geschlecht.UNBEKANNT, "Ein komischer Paradiesvogel!");
        this.personBean.saveNew(gerardDuPoint);

        // Neue Beziehungen anlegen
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        Beziehung beziehung;

        beziehung = new Beziehung(joeCool, juliaHofer, sdf.parse("2016-12-03"), false, "Haben sich auf dem Weihnachtsmarkt kennengelernt");
        this.beziehungBean.saveNew(beziehung);

        beziehung = new Beziehung(gerdFleißig, juliaHofer, sdf.parse("1996-04-21"), sdf.parse("2001-10-10"), false, "Haben zusammen studiert");
        this.beziehungBean.saveNew(beziehung);

        beziehung = new Beziehung(timBader, susanneGraf, sdf.parse("2010-05-11"), false, "Er half ihr, als sie in der Bahn angemacht wurde");
        this.beziehungBean.saveNew(beziehung);

        beziehung = new Beziehung(timBader, klaraRoth, sdf.parse("2017-09-04"), true, "Sie ist vor kurzem in dasselbe Haus gezogen");
        this.beziehungBean.saveNew(beziehung);

        beziehung = new Beziehung(alexKurz, klaraRoth, sdf.parse("2016-08-17"), false, "Waren zufällig im selben Ort in Urlaub");
        this.beziehungBean.saveNew(beziehung);

        // Status zurückgeben
        return "OK";
    }
}
