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

        Person juliaHofer = new Person("Julia Hofer", 1982, Geschlecht.WEIBLICH, "War mal mit Gerd zusammen");
        this.personBean.saveNew(juliaHofer);

        //
        // TODO: Weitere Personen anlegen
        //

        // Neue Beziehungen anlegen
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        Beziehung beziehung;

        beziehung = new Beziehung(joeCool, juliaHofer, sdf.parse("2016-12-03"), false, "Haben sich auf dem Weihnachtsmarkt kennengelernt");
        this.beziehungBean.saveNew(beziehung);

        //
        // TODO: Weitere Beziehungen anlegen
        //

        // Status zurückgeben
        return "OK";
    }
}
