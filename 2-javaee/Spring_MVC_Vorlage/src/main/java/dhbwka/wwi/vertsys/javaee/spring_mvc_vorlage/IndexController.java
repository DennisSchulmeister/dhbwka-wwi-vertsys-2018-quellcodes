/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.spring_mvc_vorlage;

import javax.ejb.EJB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Vorlage für ein Spring MVC Webprojekt mit Maven.
 */
@Controller
public class IndexController {
    
    @EJB(mappedName = "java:global/Spring_MVC_Vorlage/GreetingBean")
    GreetingBean greeting;
    
    @GetMapping(value="/")
    public String showStartPage(ModelMap model) {
        model.addAttribute("message", greeting.getGreeting());
        return "index";
    }
}
