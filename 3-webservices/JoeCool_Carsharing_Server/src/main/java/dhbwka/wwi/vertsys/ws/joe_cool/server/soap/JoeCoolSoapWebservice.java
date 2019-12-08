/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.joe_cool.server.soap;

import dhbwka.wwi.vertsys.ws.joe_cool.server.ejb.AutomobileBean;
import dhbwka.wwi.vertsys.ws.joe_cool.server.ejb.CustomerBean;
import dhbwka.wwi.vertsys.ws.joe_cool.server.ejb.LoanContractBean;
import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Automobile;
import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.Customer;
import dhbwka.wwi.vertsys.ws.joe_cool.server.jpa.LoanContract;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Unsere eigentliche Webservice-Klasse.
 */
@Stateless
@WebService
public class JoeCoolSoapWebservice {
    
    @EJB
    CustomerBean customerBean;
    
    @EJB
    AutomobileBean automobileBean;
    
    @EJB
    LoanContractBean loanContractBean;
    
    @WebMethod
    @WebResult(name = "customer")
    public Customer saveNewCustomer(@WebParam(name = "customer") Customer customer) {
        return this.customerBean.saveNew(customer);
    }
    
    @WebMethod
    @WebResult(name = "automobile")
    public Automobile saveNewAutomobile(@WebParam(name = "automobile") Automobile automobile) {
        return this.automobileBean.saveNew(automobile);
    }
    
    @WebMethod
    @WebResult(name = "loanContract")
    public LoanContract saveNewLoanContract(@WebParam(name = "loanContract") LoanContract loanContract)
            throws LoanContractBean.CarUnavailableException {
        
        return this.loanContractBean.saveIfNoConflict(loanContract);
    }
    
    @WebMethod
    @WebResult(name = "automobile")
    public List<Automobile> findAllAutomobiles() {
        return this.automobileBean.findAll();
    }
    
    @WebMethod
    @WebResult(name = "loanContract")
    public List<LoanContract> findLoanContractsByCustomer(@WebParam(name = "customerId") long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
       
        return this.loanContractBean.findByCustomer(customer);
    }
}
