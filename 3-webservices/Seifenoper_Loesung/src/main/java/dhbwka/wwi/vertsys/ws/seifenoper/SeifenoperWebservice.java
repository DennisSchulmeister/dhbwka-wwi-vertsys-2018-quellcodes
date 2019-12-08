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

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Unser eigentlicher Webservice, die Java-Seifenoper.
 */
@Stateless
@WebService(serviceName = "seifenoper")
public class SeifenoperWebservice {

    @EJB
    BeziehungBean beziehungBean;

    @EJB
    PersonBean personBean;

    @WebMethod
    @WebResult(name = "person")
    public List<Person> findAllPersonen() {
        return this.personBean.findAll();
    }

    @WebMethod
    @WebResult(name = "beziehung")
    public List<Beziehung> findBeziehungByPerson(@WebParam(name = "personId") long personId) {
        Person person = this.personBean.findById(personId);
        return this.beziehungBean.findByPerson(person);
    }

    @WebMethod
    @WebResult(name = "beziehung")
    public Beziehung saveNewBeziehung(@WebParam(name = "beziehung") Beziehung beziehung) {
        return this.beziehungBean.saveNew(beziehung);
    }

    @WebMethod
    @WebResult(name = "beziehung")
    public Beziehung updateBeziehung(@WebParam(name = "beziehung") Beziehung beziehung) {
        return this.beziehungBean.update(beziehung);
    }

}
