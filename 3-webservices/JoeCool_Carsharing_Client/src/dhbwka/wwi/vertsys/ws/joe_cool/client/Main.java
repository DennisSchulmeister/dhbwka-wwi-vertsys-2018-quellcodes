/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbwka.wwi.vertsys.ws.joe_cool.client;

import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.Automobile;
import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.CarUnavailableException_Exception;
import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.Customer;
import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.JoeCoolSoapWebservice;
import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.JoeCoolSoapWebserviceService;
import dhbwka.wwi.vertsys.ws.joe_cool.server.soap.LoanContract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

/**
 * Hauptklasse unseres kleinen Prgrämmchens.
 */
public class Main {

    private final JoeCoolSoapWebservice ws;
    private final BufferedReader fromKeyboard;

    /**
     * Hier fängt alles an …
     *
     * @param args Kommandozeilenparameter
     * @throws java.io.IOException
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    public static void main(String[] args) throws IOException, DatatypeConfigurationException {
        // Damit wir nicht so viele static-Methoden haben, erzeugen wir
        // hier erstmal ein Objekt und übergeben ihm die Kontrolle.
        Main main = new Main();
        main.runMainMenu();
    }

    /**
     * Konstruktor. Hier wird der Webservice-Stub erzeugt und in this.ws
     * abgelegt. Ebenso wird ein Datenstrom zum Einlesen der Tastatureingaben
     * erzeugt und in this.fromKeyboard abgelegt.
     */
    public Main() {
        // Webservice-Stub erzeugen
        //System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        //System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        //System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        //System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        JoeCoolSoapWebserviceService joeCool = new JoeCoolSoapWebserviceService();
        this.ws = joeCool.getJoeCoolSoapWebservicePort();

        // Datenstrom zum Einlesen von Tastatureingaben erzeugen
        this.fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Hauptschleife mit dem Hauptmenü. Das Menü wird so oft angezeigt, bis sich
     * der Anwender zum Beenden des Programms entschließt.
     *
     * @throws java.io.IOException
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    private void runMainMenu() throws IOException, DatatypeConfigurationException {
        System.out.println("         .----.");
        System.out.println("      _.'__    `.");
        System.out.println("  .--(#)(##)---/#\\");
        System.out.println(".' @          /###\\");
        System.out.println(":         ,   #####");
        System.out.println(" `-..__.-' _.-\\###/");
        System.out.println("        `;_:    `\"'");
        System.out.println("     .'\"\"\"\"`.");
        System.out.println("    /,  JOE  ,\\");
        System.out.println("   //  COOL!  \\\\");
        System.out.println("   -Car Sharing-");
        System.out.println("   `-._______.-'");
        System.out.println("   ___`. | .'___");
        System.out.println("  (______|______)");
        System.out.println();
        System.out.println();

        boolean quit = false;

        while (!quit) {
            System.out.println("=========");
            System.out.println("Hauptmenü");
            System.out.println("=========");
            System.out.println();
            System.out.println("  [K] Kunde anlegen");
            System.out.println("  [F] Fahrzeug anlegen");
            System.out.println("  [A] Fahrzeug ausleihen");
            System.out.println("  [L] Leihverträge auflisten");
            System.out.println("  [E] Ende");
            System.out.println();

            System.out.print("Deine Auswahl: ");
            String command = this.fromKeyboard.readLine();
            System.out.println();

            switch (command.toUpperCase()) {
                case "K":
                    this.saveNewCustomer();
                    System.out.println();
                    break;
                case "F":
                    this.saveNewAutomobile();
                    System.out.println();
                    break;
                case "A":
                    this.saveNewLoanContract();
                    System.out.println();
                    break;
                case "L":
                    this.findLoanContractsByCustomer();
                    System.out.println();
                    break;
                case "E":
                    quit = true;
                    break;
                default:
                    System.out.println("Sorry, ich habe dich nicht verstanden …");
                    System.out.println();
            }
        }
    }

    private void saveNewCustomer() throws IOException {
        System.out.println("=============");
        System.out.println("Kunde anlegen");
        System.out.println("=============");
        System.out.println();

        // Felder vom Benutzer eingeben lassen
        System.out.print("Vorname:      ");
        String firstName = this.fromKeyboard.readLine();

        System.out.print("Nachname:     ");
        String lastName = this.fromKeyboard.readLine();

        System.out.print("Straße:       ");
        String street = this.fromKeyboard.readLine();

        System.out.print("Hausnummer:   ");
        String housenumber = this.fromKeyboard.readLine();

        System.out.print("Postleitzahl: ");
        String zip = this.fromKeyboard.readLine();

        System.out.print("Ort:          ");
        String city = this.fromKeyboard.readLine();

        System.out.print("Land:         ");
        String country = this.fromKeyboard.readLine();

        System.out.println();

        // Neuen Kunden anlegen
        Customer customer = new Customer();
        Holder<Customer> hCustomer = new Holder<>(customer);

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setStreet(street);
        customer.setHousenumber(housenumber);
        customer.setZip(zip);
        customer.setCity(city);
        customer.setCountry(country);

        this.ws.saveNewCustomer(hCustomer);

        System.out.println("Kundennummer " + hCustomer.value.getId() + " wurde angelegt.");
    }

    private void saveNewAutomobile() throws IOException {
        System.out.println("================");
        System.out.println("Fahrzeug anlegen");
        System.out.println("================");
        System.out.println();

        // Felder vom Benutzer eingeben lassen
        System.out.print("Hersteller: ");
        String manufacturer = this.fromKeyboard.readLine();

        System.out.print("Modell:     ");
        String model = this.fromKeyboard.readLine();

        System.out.print("Baujahr:    ");
        int buildYear = Integer.parseInt(this.fromKeyboard.readLine());

        System.out.println();

        // Neues Fahrzeug speichern
        Automobile automobile = new Automobile();
        Holder<Automobile> hAutomobile = new Holder<>(automobile);

        automobile.setManufacturer(manufacturer);
        automobile.setModel(model);
        automobile.setBuildYear(buildYear);

        this.ws.saveNewAutomobile(hAutomobile);

        System.out.println("Fahrzeug " + hAutomobile.value.getId() + " wurde angelegt.");
    }

    private void saveNewLoanContract() throws IOException, DatatypeConfigurationException {
        System.out.println("==================");
        System.out.println("Fahrzeug ausleihen");
        System.out.println("==================");
        System.out.println();

        // Verfügbare Fahrzeuge anzeigen
        List<Automobile> automobiles = this.ws.findAllAutomobiles();

        if (automobiles.isEmpty()) {
            System.out.println("Es stehen leider keine Fahrzeuge zur Verfügung.");
            return;
        }

        System.out.println("Folgende Fahrzeuge stehen zur Verfügung:");
        System.out.println();

        for (Automobile automobile : automobiles) {
            String line = automobile.getManufacturer()
                    + " "
                    + automobile.getModel()
                    + ", Baujahr "
                    + automobile.getBuildYear()
                    + ", ID "
                    + automobile.getId();

            System.out.println("  " + line);
        }

        System.out.println();

        // Felder vom Benutzer eingeben lassen
        System.out.print("Kundennummer:              ");
        long customerId = Long.parseLong(this.fromKeyboard.readLine());

        System.out.print("Fahrzeug-ID:               ");
        long automobileId = Long.parseLong(this.fromKeyboard.readLine());

        System.out.print("Abholung am (yyyy-mm-dd):  ");
        String beginDateStr = this.fromKeyboard.readLine();

        System.out.print("Rückgabe am (yyyy-mm-dd):  ");
        String endDateStr = this.fromKeyboard.readLine();

        System.out.println();

        // Neuen Leihvertrag speichern        
        DatatypeFactory dtf = DatatypeFactory.newInstance();
        XMLGregorianCalendar beginDate = dtf.newXMLGregorianCalendar(beginDateStr);
        XMLGregorianCalendar endDate = dtf.newXMLGregorianCalendar(endDateStr);
        
        Customer customer = new Customer();
        customer.setId(customerId);

        Automobile automobile = new Automobile();
        automobile.setId(automobileId);

        LoanContract loanContract = new LoanContract();
        loanContract.setCustomer(customer);
        loanContract.setAutomobile(automobile);
        loanContract.setBeginDate(beginDate);
        loanContract.setEndDate(endDate);

        try {
            Holder<LoanContract> hLoanContract = new Holder<>(loanContract);
            this.ws.saveNewLoanContract(hLoanContract);
            System.out.println("Alles Klar! Leihvertrag " + hLoanContract.value.getId() + " wurde angelegt.");
        } catch (CarUnavailableException_Exception ex) {
            System.out.println("FEHLER: " + ex.getMessage());
        }
    }

    private void findLoanContractsByCustomer() throws IOException {
        System.out.println("=====================");
        System.out.println("Leihverträge anzeigen");
        System.out.println("=====================");
        System.out.println();

        // Kundennummer abfragen
        System.out.print("Kundennummer: ");
        long customerId = Long.parseLong(this.fromKeyboard.readLine());

        System.out.println();

        // Verträge auflisten
        List<LoanContract> contracts = this.ws.findLoanContractsByCustomer(customerId);
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");

        for (LoanContract contract : contracts) {
            Date beginDate = contract.getBeginDate().toGregorianCalendar().getTime();
            Date endDate = contract.getEndDate().toGregorianCalendar().getTime();

            String automobile = contract.getAutomobile().getManufacturer()
                    + " "
                    + contract.getAutomobile().getModel()
                    + ", Baujahr "
                    + contract.getAutomobile().getBuildYear();

            System.out.println("  Fahrzeug: " + automobile);
            System.out.println("  Abholung am: " + fmt.format(beginDate));
            System.out.println("  Rückgabe am: " + fmt.format(endDate));
            System.out.println();
        }

        if (contracts.isEmpty()) {
            System.out.println("Es wurden keine Leihverträge gefunden.");
        }
    }

}
