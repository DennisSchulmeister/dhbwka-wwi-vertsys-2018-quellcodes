/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.guestbook_springmvc;

import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * SpringMVC-Controller, der das Servlet im anderen Gästebuch-Beispiel ersetzt.
 * Er hat genau dieselbe Aufgabe, SpringMVC nimmt uns aber sehr viel Arbeit ab.
 */
@Controller
public class GuestbookController {
    
    // Spring braucht den vollen JNDI-Namen der Bean
    // Vgl. https://access.redhat.com/documentation/en-US/JBoss_Web_Framework_Kit/1.2/html/Spring_Developer_Guide/ch07s05s02.html
    @EJB(mappedName = "java:global/Gaestebuch_SpringMVC_Beispiel/GuestbookBean")
    GuestbookBean guestbookBean;
    
    @GetMapping(value="/")
    public String showStartPage(ModelMap model) {
        // Anstelle des Request Context und des Session Context nutzt SpringMVC
        // eine ModelMap, in der Daten an die JSP übergeben werden können.
        List<GuestbookEntry> entries = this.guestbookBean.findAllEntries();
        model.addAttribute("entries", entries);

        // Zusätzlich ein leeres Objekt für das Anlageformular
        if (!model.containsAttribute("newEntry")) {
            GuestbookEntry newEntry = new GuestbookEntry();
            model.addAttribute("newEntry", newEntry);
        }

        // Weiter zu guestbook.jsp
        return "guestbook";
    }
    
    @PostMapping(value="/")
    public String saveNewEntry(
            @Valid @ModelAttribute("newEntry") GuestbookEntry newEntry,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        // Wie praktisch: SpringMVC prüft das Formular automatisch auf Fehler!
        // Hierfür mussten wir in der GuestbookEntry-Klasse nur ein paar weitere
        // Annotationen hinzufügen, damit Spring weiß, was es prüfen soll.
        if (result.hasErrors()) {
            // Damit die Fehlermeldung nicht beim Redirect verloren geht.
            // Sonst dürfte man hier keinen Redirect machen.
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newEntry", result);
            redirectAttributes.addFlashAttribute("newEntry", newEntry);
            
            return "redirect:/";
        }
        
        this.guestbookBean.createNewEntry(newEntry.getName());
        
        // Hier rufen wir die JSP nicht direkt auf, sondern senden einen Redirect
        // an den Browser, damit er die Seite neulädt. Haben wir im Servlet auch
        // so gemacht, war dort nur komplizierter.
        return "redirect:/";
    }
    
}
