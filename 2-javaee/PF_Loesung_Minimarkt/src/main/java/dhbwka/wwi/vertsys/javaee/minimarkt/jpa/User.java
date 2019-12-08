/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.jpa;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Datenbankklasse für einen Benutzer.
 */
@Entity
@Table(name = "MINIMARKT_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @Id
    @Column(name = "USERNAME", length = 64)
    @Size(min = 5, max = 64, message = "Der Benutzername muss zwischen fünf und 64 Zeichen lang sein.")
    @NotNull(message = "Der Benutzername darf nicht leer sein.")
    private String username;
    
    public class Password {
        @Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
        public String password = "";
    }
    @Transient
    private final Password password = new Password();
    
    @Column(name = "PASSWORD_HASH", length = 64)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    private String passwordHash;

    @ElementCollection
    @CollectionTable(
            name = "MINIMARKT_USER_GROUP",
            joinColumns = @JoinColumn(name = "USERNAME")
    )
    @Column(name = "GROUPNAME")
    List<String> groups = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Offer> tasks = new ArrayList<>();
    
    @Size(min = 1, max = 64, message = "Der Name muss zwischen ein und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String realname;
    
    @Size(min = 1, max = 64, message = "Die Straße muss zwischen ein und 64 Zeichen lang sein.")
    @NotNull(message = "Die Straße darf nicht leer sein.")
    private String street;
    
    @Size(min = 1, max = 15, message = "Die Postleitzahl muss zwischen ein und 15 Zeichen lang sein.")
    @NotNull(message = "Die Postleitzahl darf nicht leer sein.")
    private String postcode;
    
    @Size(min = 1, max = 64, message = "Der Ort muss zwischen ein und 64 Zeichen lang sein.")
    @NotNull(message = "Der Ort darf nicht leer sein.")
    private String city;
    
    @Size(min = 1, max = 64, message = "Die Telefonnummer muss zwischen ein und 64 Zeichen lang sein.")
    @NotNull(message = "Die Telefonnummer darf nicht leer sein.")
    private String phone;
    
    @Size(min = 1, max = 64, message = "Die E-Mail muss zwischen ein und 64 Zeichen lang sein.")
    @NotNull(message = "Die E-Mailadresse darf nicht leer sein.")
    @Pattern(regexp = "^\\w+@\\w+\\..{2,3}(.{2,3})?$", message="Es muss eine gültige E-Mailadresse eingegeben werden.")
    private String email;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
    }
    
    public User(String username, String password, String realname, String street,
            String postcode, String city, String phone, String email) {
        this.username = username;
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
        this.realname = realname;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }

    public List<Offer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Offer> tasks) {
        this.tasks = tasks;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRealname() {
        return realname;
    }
    
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Passwort setzen und prüfen">
    /**
     * Berechnet der Hash-Wert zu einem Passwort.
     *
     * @param password Passwort
     * @return Hash-Wert
     */
    private String hashPassword(String password) {
        byte[] hash;

        if (password == null) {
            password = "";
        }
        
        // Hashwert zum Passwort berechnen
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            hash = "!".getBytes(StandardCharsets.UTF_8);
        }

        // Hashwert in einen Hex-String umwandeln
        // Vgl. https://stackoverflow.com/a/9855338
        char[] hashHex = new char[hash.length * 2];
        
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            hashHex[i * 2] = HEX_ARRAY[v >>> 4];
            hashHex[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        
        return new String(hashHex);
    }

    /**
     * Berechnet einen Hashwert aus dem übergebenen Passwort und legt ihn im
     * Feld passwordHash ab. Somit wird das Passwort niemals als Klartext
     * gespeichert.
     * 
     * Gleichzeitig wird das Passwort im nicht gespeicherten Feld password
     * abgelegt, um durch die Bean Validation Annotationen überprüft werden
     * zu können.
     *
     * @param password Neues Passwort
     */
    public void setPassword(String password) {
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
    }

    /**
     * Nur für die Validierung bei einer Passwortänderung!
     * @return Neues, beim Speichern gesetztes Passwort
     */
    public Password getPassword() {
        return this.password;
    }
    
    /**
     * Prüft, ob das übergebene Passwort korrekt ist.
     *
     * @param password Zu prüfendes Passwort
     * @return true wenn das Passwort stimmt sonst false
     */
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(this.hashPassword(password));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Zuordnung zu Benutzergruppen">
    /**
     * @return Eine unveränderliche Liste aller Benutzergruppen
     */
    public List<String> getGroups() {
        List<String> groupsCopy = new ArrayList<>();

        this.groups.forEach((groupname) -> {
            groupsCopy.add(groupname);
        });

        return groupsCopy;
    }

    /**
     * Fügt den Benutzer einer weiteren Benutzergruppe hinzu.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void addToGroup(String groupname) {
        if (!this.groups.contains(groupname)) {
            this.groups.add(groupname);
        }
    }

    /**
     * Entfernt den Benutzer aus der übergebenen Benutzergruppe.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void removeFromGroup(String groupname) {
        this.groups.remove(groupname);
    }
    //</editor-fold>

}
