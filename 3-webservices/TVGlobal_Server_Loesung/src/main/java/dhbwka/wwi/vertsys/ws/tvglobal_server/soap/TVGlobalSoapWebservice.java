/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.tvglobal_server.soap;

import dhbwka.wwi.vertsys.ws.tvglobal_server.jpa.Program;
import dhbwka.wwi.vertsys.ws.tvglobal_server.jpa.Station;
import dhbwka.wwi.vertsys.ws.tvglobal_server.ejb.ProgramBean;
import dhbwka.wwi.vertsys.ws.tvglobal_server.ejb.StationBean;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Einfacher SOAP-Webservice zum Anlegen und Suchen von Fernsehsendern und
 * Sendungen.
 */
@WebService(serviceName = "TVGlobal")
public class TVGlobalSoapWebservice {

    @EJB
    StationBean stationBean;

    @EJB
    ProgramBean programBean;

    @WebMethod
    @WebResult(name = "station")
    public Station createNewStation(@WebParam(name = "station") Station station) {
        return stationBean.saveNew(station);
    }

    @WebMethod
    @WebResult(name = "program")
    public Program createNewProgram(@WebParam(name = "program") Program program) {
        return programBean.saveNew(program);
    }

    @WebMethod
    @WebResult(name = "station")
    public List<Station> getAllStations() {
        return stationBean.findAll();
    }

    @WebMethod
    @WebResult(name = "program")
    public List<Program> findProgramByStartBetween(
            @WebParam(name = "startTimeFrom") Date startTimeFrom,
            @WebParam(name = "startTimeTo") Date startTimeTo) {

        return programBean.findByStartBetween(startTimeFrom, startTimeTo);
    }

}
