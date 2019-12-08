/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_server.rest;

import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.SeasonBean;
import dhbwka.wwi.vertsys.ws.rest_serien_server.ejb.SeriesBean;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Episode;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Season;
import dhbwka.wwi.vertsys.ws.rest_serien_server.jpa.Series;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Minimaler REST-Webservice, der eine Reihe von Demodaten für uns anlegt.
 */
@Path("CreateDemoData")
@Consumes({"application/json", "text/xml"})
@Produces({"application/json", "text/xml"})
public class CreateDemoData {

    @EJB
    private SeriesBean seriesBean;

    @EJB
    private SeasonBean seasonBean;

    @GET
    public StatusResponse createDemoData() {
        for (Series series : this.seriesBean.findAll()) {
            this.seriesBean.delete(series);
        }
        
        for (Season season : this.seasonBean.findAll()) {
            this.seasonBean.delete(season);
        }

        Series series;

        // Serie: Akte X
        series = new Series("X-Files", "Akte X", "Mystery", "USA / Canada", 1993, 2018, 11, 218);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 1993, new String[]{"Gezeichnet", "Die Warnung", "Das Nest", "Signale", "Der Teufel von Jersey", "Schatten", "Die Maschine", "Eis", "Besessen", "Gefallener Engel", "Eve", "Feuer", "Die Botschaft", "Verlockungen", "Lazarus", "Ewige Jugend", "Täuschungsmanöver", "Der Wunderheiler", "Verwandlungen", "Der Kokon", "Ein neues Nest", "Wiedergeboren", "Roland", "Das Labor"});
        this.saveSeason(series, 1994, new String[]{"Kontakt", "Der Parsit", "Blut", "Schlaflos", "Unter Kontrolle - Teil 1", "Seilbahn zu den Sternen - Teil 2", "Drei", "An der Grenze", "Der Vulkan", "Rotes Museum", "Excelsis Dei", "Böse geboren", "Todestrieb", "Satan", "Frische Knochen", "Die Kolonie - Teil 1", "Die Kolonie - Teil 2", "Sophie", "Totenstille", "Der Zirkus", "Heilige Asche", "Verseucht", "Das Experiment", "Unsere kleine Stadt", "Anasazi - Teil 1"});
        this.saveSeason(series, 1995, new String[]{"Das Ritual – Teil 2", "Verschwörung des Schweigens – Teil 3", "Blitzschlag", "Der Hellseher", "Die Liste", "Fett", "Der zweite Körper", "Parallele", "Die Autopsie – Teil 1", "Der Zug – Teil 2", "Offenbarung", "Krieg der Koprophagen", "Energie", "Groteske", "Der Feind – Teil 1", "Der Feind – Teil 2", "Mein Wille sei dein Wille", "Der Fluch", "Höllengeld", "Andere Wahrheiten", "Heimsuchung", "Der See", "Ferngesteuert", "Der Tag steht schon fest – Teil 1"});
        this.saveSeason(series, 1996, new String[]{"Herrenvolk – Teil 2", "Blutschande", "Teliko", "Unruhe", "Rückkehr der Seelen", "Hexensabbat", "Gedanken des geheimnisvollen Rauchers", "Tunguska – Teil 1", "Tunguska – Teil 2", "Die Sammlung", "Der Chupacabra", "Leonard Betts", "Mutterkorn", "Memento Mori", "Der Golem", "Unsichtbar", "Tempus Fugit – Teil 1", "Tempus Fugit – Teil 2", "Rückkehr aus der Zukunft", "Ein unbedeutender Niemand", "Der Pakt mit dem Teufel", "Todes-Omen", "Dämonen", "Gethsemane"});
        this.saveSeason(series, 1997, new String[]{"Redux – Teil 1", "Redux – Teil 2", "Die unüblichen Verdächtigen", "Vom Erdboden verschluckt", "Der große Mutato", "Emily – Teil 1", "Emily – Teil 2", "Kitsunegari", "Die Wurzeln des Bösen", "Ein Spiel", "Kill Switch", "Böses Blut", "Cassandra – Teil 1", "Cassandra – Teil 2", "Gute Patrioten", "Das innere Auge", "Alle Seelen", "Die Pine-Bluff-Variante", "Folie a Deux", "Das Ende"});
        this.saveSeason(series, 1998, new String[]{"Der Anfang", "Die Fahrt", "Im Bermuda-Dreieck", "Dreamland – Teil 1", "Dreamland – Teil 2", "Die Geister, die ich rief", "Zeit der Zärtlichkeit", "Der Regenmacher", "S.R. 819", "Tithonus", "Zwei Väter – Teil 1", "Ein Sohn – Teil 2", "Aqua Mala", "Montag", "Arkadien", "Alpha", "Trevor", "Milagro", "Ex", "Suzanne", "Sporen", "Artefakte"});
        this.saveSeason(series, 1999, new String[]{"Böse Zeichen – Teil 1", "Tausend Stimmen – Teil 2", "Hunger", "Millennium", "Masse mal Beschleunigung", "Das Glück des Henry Weems", "Tor zur Hölle", "Der unglaubliche Maleeni", "Schlangen", "Alte Seelen – Teil 1", "Sternenlicht – Teil 2", "Vollmond", "Game Over", "Zauberstaub", "Cobra", "Zerbrochene Spiegel", "Augenblicke", "Nikotin", "Hollywood", "27.000.000 : 1", "Drei Wünsche", "Alles beginnt in Oregon – Teil 1"});
        this.saveSeason(series, 2000, new String[]{"Verschwunden – Teil 2", "Gibson Praise – Teil 3", "Klauen und Zähne", "Gesteinigt", "Billy", "Rückwärts", "Via Negativa", "Fenster der Seele", "Schlaues Metall", "Hier und nicht hier", "Der Seelenesser", "Underground", "Frucht des Leibes", "Es ist zu spät – Teil 1", "Lebendig tot – Teil 2", "Drei Worte", "Feuer und Asche", "Sie kommen", "Augenlicht", "Einer von vielen – Teil 1", "William – Teil 2"});
        this.saveSeason(series, 2001, new String[]{" Unter Wasser – Teil 1", "Adam und Eva – Teil 2", "Daemonicus", "4-D", "Der Herr der Fliegen", "Schattenmann", "Desaparecido", "Häutungen", "Drohungen – Teil 1", "Die Prophezeiung – Teil 2", "Audrey", "Der Mörder in mir", "Sechs und neun", "Unter dem Bett", "Helden", "Zum Wohle des Kindes", "Erlösung", "Die perfekte Familie", "Die Wahrheit – Teil 1", "Die Wahrheit – Teil 2"});
        this.saveSeason(series, 2017, new String[]{"Der Kampf", "Gründer-Mutation", "Mulder und Scully gegen das Wer-Monster", "Heimat", "Babylon", "Der Kampf II"});
        this.saveSeason(series, 2018, new String[]{"Der Kampf III", "Dieses Leben, jenes Leben", "Galgenmännchen", "Der Mandela-Effekt", "Ghouli", "Kätzchen", "Rm9sbG93ZXJz", "Das Tor zur Hölle", "Nichts währt ewig", "Der Kampf IV"});

        // Serie: Star Trek – The Original Series
        series = new Series("ST-TOS", "Star Trek – The Original Series", "Sci-Fi", "USA", 1966, 1969, 3, 79);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 1966, new String[]{"Das Letzte seiner Art", "Der Fall Charlie", "Die Spitze des Eisberges", "Implosion in der Spirale", "Kirk : 2 = ?", "Die Frauen des Mr Mudd", "Der alte Traum", "Miri, ein Kleinling", "Der Zentralnervensystemmanipulator", "Pokerspiele", "Talos IV – Tabu – Teil 1", "Talos IV – Tabu – Teil 2", "Kodos, der Henker", "Spock unter Verdacht", "Landeurlaub", "Notlandung auf Galileo 7", "Tödliche Spiele auf Gothos", "Ganz neue Dimensionen", "Morgen ist gestern", "Kirk unter Anklage", "Landru und die Ewigkeit", "Der schlafende Tiger", "Krieg der Computer", "Falsche Paradiese", "Horta rettet ihre Kinder", "Kampf um Organia", "Auf Messers Schneide", "Griff in die Geschichte", "Spock außer Kontrolle"});
        this.saveSeason(series, 1967, new String[]{"Weltraumfieber", "Der Tempel des Apoll", "Ich heiße Nomad", "Ein Parallel-Universum", "Die Stunde der Erkenntnis", "Planeten-Killer", "Das Spukschloss im Weltall", "Der dressierte Herrscher", "Metamorphose", "Reise nach Babel", "Im Namen des jungen Tiru", "Wie schnell die Zeit vergeht", "Tödliche Wolken", "Der Wolf im Schafspelz", "Kennen Sie Tribbles?", "Meister der Sklaven", "Epigonen", "Das Loch im Weltraum", "Der erste Krieg", "Geist sucht Körper", "Schablonen der Gewalt", "Stein und Staub", "Das Jahr des roten Vogels", "Computer M5", "Brot und Spiele", "Ein Planet, genannt Erde"});
        this.saveSeason(series, 1968, new String[]{"Spocks Gehirn", "Die unsichtbare Falle", "Der Obelisk", "Kurs auf Markus 12", "Die fremde Materie", "Wildwest im Weltraum", "Das Gleichgewicht der Kräfte", "Der verirrte Planet", "Das Spinnennetz", "Platons Stiefkinder", "Was summt denn da?", "Der Plan der Vianer", "Brautschiff Enterprise", "Wen die Götter zerstören", "Bele jagt Lokai", "Fast unsterblich", "Gefährliche Planetengirls", "Strahlen greifen an", "Planet der Unsterblichen", "Die Reise nach Eden", "Die Wolkenstadt", "Seit es Menschen gibt", "Portal in die Vergangenheit", "Gefährlicher Tausch"});

        // Serie: Star Trek – The Next Generation
        series = new Series("ST-TNG", "Star Trek – The Next Generation", "Sci-Fi", "USA", 1987, 1994, 7, 178);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 1987, new String[]{"Der Mächtige (1)", "Mission Farpoint (2)", "Gedankengift", "Der Ehrenkodex", "Der Wächter", "Der Reisende", "Die geheimnisvolle Kraft", "Das Gesetz der Edo", "Die Schlacht von Maxia", "Rikers Versuchung", "Die Frau seiner Träume", "Der große Abschied", "Das Duplikat", "Planet Angel One", "11001001", "Die Entscheidung des Admirals", "Die Sorge der Aldeaner", "Ein Planet wehrt sich", "Prüfungen", "Worfs Brüder", "Die Waffenhändler", "Die Seuche", "Die schwarze Seele", "Begegnung mit der Vergangenheit", "Die Verschwörung", "Die Neutrale Zone"});
        this.saveSeason(series, 1988, new String[]{"Das Kind", "Illusion oder Wirklichkeit?", "Sherlock Data Holmes", "Der unmögliche Captain Okona", "Der stumme Vermittler", "Das fremde Gedächtnis", "Die jungen Greise", "Der Austauschoffizier", "Wem gehört Data?", "Die Thronfolgerin", "Die Iconia-Sonden", "Hotel Royale", "Die Zukunft schweigt", "Rikers Vater", "Brieffreunde", "Zeitsprung mit Q", "Das Herz eines Captains", "Der Planet der Klone", "Andere Sterne, andere Sitten", "Klingonenbegegnung", "Galavorstellung", "Kraft der Träume"});
        this.saveSeason(series, 1989, new String[]{"Die Macht der Naniten", "Die Macht der Paragraphen", "Die Überlebenden auf Rana-Vier", "Der Gott der Mintakaner", "Mutterliebe", "Die Energiefalle", "Auf schmalem Grat", "Der Barzanhandel", "Yuta, die Letzte ihres Clans", "Der Überläufer", "Die Verfemten", "Terror auf Rutia-Vier", "Noch einmal Q", "Riker unter Verdacht", "Die alte Enterprise", "Datas Nachkomme", "Die Sünden des Vaters", "Versuchskaninchen", "Picard macht Urlaub", "Der Telepath", "Der schüchterne Reginald", "Der Sammler", "Botschafter Sarek", "Die Damen Troi", "Wer ist John?", "In den Händen der Borg"});
        this.saveSeason(series, 1990, new String[]{"Angriffsziel Erde", "Familienbegegnung", "Die ungleichen Brüder", "Endars Sohn", "Das Experiment", "Die Rettungsoperation", "Tödliche Nachfolge", "Gedächtnisverlust", "Die letzte Mission", "Das kosmische Band", "Datas Tag", "Der Rachefeldzug", "Der Pakt mit dem Teufel", "Beweise", "Erster Kontakt", "Die Begegnung im Weltraum", "Augen in der Dunkelheit", "Der unbekannte Schatten", "Die Reise ins Ungewisse", "Gefangen in der Vergangenheit", "Das Standgericht", "Die Auflösung", "Odan, der Sonderbotschafter", "Verräterische Signale", "Datas erste Liebe", "Der Kampf um das Klingonische Reich – Teil 1"});
        this.saveSeason(series, 1991, new String[]{"Der Kampf um das Klingonische Reich – Teil 2", "Darmok", "Fähnrich Ro", "Das Recht auf Leben", "Katastrophe auf der Enterprise", "Gefährliche Spielsucht", "Wiedervereinigung? Teil 1", "Wiedervereinigung? Teil 2", "Der zeitreisende Historiker", "Die Soliton-Welle", "Der einzige Überlebende", "Geistige Gewalt", "Das künstliche Paradies", "Mission ohne Gedächtnis", "Ungebetene Gäste", "Die Operation", "Verbotene Liebe", "Déjà vu", "Ein missglücktes Manöver", "Hochzeit mit Hindernissen", "Eine hoffnungslose Romanze", "Die imaginäre Freundin", "Ich bin Hugh", "So nah und doch so fern", "Das zweite Leben", "Gefahr aus dem 19. Jahrhundert – Teil 1"});
        this.saveSeason(series, 1992, new String[]{"Gefahr aus dem 19. Jahrhundert – Teil 2", "Todesangst beim Beamen", "Der unmoralische Friedensvermittler", "Besuch von der alten Enterprise", "In den Subraum entführt", "Eine echte „Q“", "Erwachsene Kinder", "Eine Handvoll Datas", "Datas Hypothese", "Geheime Mission auf Celtris Drei – Teil 1", "Geheime Mission auf Celtris Drei – Teil 2", "Das Schiff in der Flasche", "Aquiel", "Das Gesicht des Feindes", "Willkommen im Leben nach dem Tode", "Der Moment der Erkenntnis – Teil 1", "Der Moment der Erkenntnis – Teil 2", "In der Hand von Terroristen", "Der Feuersturm", "Das fehlende Fragment", "Phantasie oder Wahrheit?", "Verdächtigungen", "Der rechtmäßige Erbe", "Riker : 2 = ?", "Gefangen in einem temporären Fragment", "Angriff der Borg – Teil 1"});
        this.saveSeason(series, 1993, new String[]{"Angriff der Borg – Teil 2", "Indiskretionen", "Das Interface", "Der Schachzug – Teil 1", "Der Schachzug – Teil 2", "Traumanalyse", "Ort der Finsternis", "Kontakte", "Die Raumkatastrophe", "Soongs Vermächtnis", "Parallelen", "Das Pegasus-Projekt", "Die oberste Direktive", "Ronin", "Beförderung", "Radioaktiv", "Der Komet", "Der Fall „Utopia Planitia“", "Genesis", "Am Ende der Reise", "Ritus des Aufsteigens", "Boks Vergeltung", "Neue Intelligenz", "Die Rückkehr von Ro Laren", "Gestern, heute, morgen – Teil 1", "Gestern, heute, morgen – Teil 2"});

        // Serie: Star Trek – Deep Space Nine
        series = new Series("ST-DS9", "Star Trek – Deep Space Nine", "Sci-Fi", "USA", 1993, 1999, 7, 176);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 1993, new String[]{"Der Abgesandte", "Die Khon-Ma", "Unter Verdacht", "Babel", "Tosk, der Gejagte", "Q - unerwünscht", "Der Fall „Dax“", "Der Parasit", "Chula - das Spiel", "Die Nachfolge", "Der Steinwandler", "Die Prophezeiung", "Die Legende von Dal'Rok", "Mulliboks Mond", "Macht der Phantasie", "Persönlichkeiten", "Meuterei", "Der undurchschaubare Marritza", "Blasphemie"});
        this.saveSeason(series, 1994, new String[]{"Die Heimkehr (1)", "Der Kreis (2)", "Die Belagerung (3)", "Der Symbiont", "Die Konspiration", "Das „Melora“-Problem", "Profit oder Partner!", "Die Ermittlung", "Rätselhafte Fenna", "Auge des Universums", "Rivalen", "Metamorphosen", "Das Harvester-Desaster", "O'Briens Identität", "Das Paradiesexperiment", "Die Illusion", "Der Trill-Kandidat", "Profit und Verlust", "Der Blutschwur", "Der Maquis – Teil 1", "Der Maquis – Teil 2", "Das Implantat", "Die andere Seite", "Die Wahl des Kai", "Das Tribunal", "Der Plan des Dominion"});
        this.saveSeason(series, 1995, new String[]{"Die Suche – Teil 1", "Die Suche – Teil 2", "Das Haus des Quark", "Das Equilibrium", "Die zweite Haut", "Der Ausgesetzte", "In der Falle", "Meridian", "Defiant", "Das Festival", "Gefangen in der Vergangenheit – Teil 1", "Gefangen in der Vergangenheit – Teil 2", "Der Funke des Lebens", "Herz aus Stein", "Trekors Prophezeiung", "Das Motiv der Propheten", "Der Visionär", "Ferne Stimmen", "Durch den Spiegel", "Der geheimnisvolle Garak – Teil 1", "Der geheimnisvolle Garak – Teil 2", "Die Erforscher", "Familienangelegenheiten", "Shakaar", "Facetten", "Der Widersacher"});
        this.saveSeason(series, 1996, new String[]{"Der Weg des Kriegers", "Der Besuch", "Der hippokratische Eid", "Indiskretion", "Wiedervereinigt", "Das Wagnis", "Kleine grüne Männchen", "Das Schwert des Kahless", "Unser Mann Bashir", "Die Front (1)", "Das verlorene Paradies (2)", "Emotionen", "Zu neuer Würde", "Die Söhne von Mogh", "Der Streik", "Die Übernahme", "Das Gefecht", "Strafzyklen", "Der zerbrochene Spiegel", "Die Muse", "In eigener Sache", "Die Abtrünnigen", "Hoffnung", "Quarks Schicksal", "Das Urteil"});
        this.saveSeason(series, 1997, new String[]{"Die Apokalypse droht", "Das Schiff", "Gefährliche Liebschaften", "Die Schlacht um Ajilon Prime", "Die Erpressung", "Immer die Last mit den Tribbles", "Die Reise nach Risa", "Die Schuld", "Der Aufstieg", "Heilige Visionen", "Dunkelheit und Licht", "Das Baby", "Für die Uniform", "Die Schatten der Hölle (1)", "Im Lichte des Infernos (2)", "Dr. Bashirs Geheimnis", "Der Datenkristall", "Kriegsgeschäfte", "Die Überwindung", "Liebe und Profit", "Martoks Ehre", "Kinder der Zeit", "Glanz des Ruhms", "Empok Nor", "Die Karte", "Zu den Waffen!"});
        this.saveSeason(series, 1998, new String[]{"Zeit des Widerstands", "Entscheidungen", "Söhne und Töchter", "Hinter der Linie", "Ein kühner Plan (1)", "Sieg oder Niederlage? (2)", "Klingonische Tradition", "Erkenntnis", "Statistische Wahrscheinlichkeiten", "Der glorreiche Ferengi", "Das Gute und das Böse", "Wer trauert um Morn?", "Jenseits der Sterne", "Das winzige Raumschiff", "Ehre unter Dieben", "Wandel des Herzens", "Tiefes Unrecht", "Inquisition", "In fahlem Mondlicht", "Auf seine Art", "Zeit der Abrechnung", "Valiant", "Die Beraterin", "Das Zeitportal", "Der Klang ihrer Stimme", "Tränen der Propheten"});
        this.saveSeason(series, 1999, new String[]{"Das Gesicht im Sand (1)", "Schatten und Symbole (2)", "Nachempfindung", "Wettkampf in der Holosuite", "Sarina", "Verrat, Glaube und gewaltiger Fluss", "Der Dahar-Meister", "Die Belagerung von AR-558", "Entscheidung auf Empok Nor", "Leben in der Holosuite", "Die verlorene Tochter", "Die Tarnvorrichtung", "Freies Schussfeld", "Hirngespinst", "Badda-Bing, Badda-Bang", "Unter den Waffen schweigen die Gesetze", "Im Ungewissen", "Bis dass der Tod uns scheide", "Eine sonderbare Kombination (1)", "Im Angesicht des Bösen (2)", "Ein Unglück kommt selten allein", "Kampf mit allen Mitteln", "Extreme Maßnahmen", "In den Wirren des Krieges", "Das, was du zurücklässt"});

        // Serie: Star Trek – Voyager
        series = new Series("ST-VOY", "Star Trek – Voyager", "Sci-Fi", "USA", 1995, 2001, 7, 172);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 1995, new String[]{"Der Fürsorger", "Die Parallaxe", "Subraumspalten", "Transplantationen", "Der mysteriöse Nebel", "Das Nadelöhr", "Die Augen des Toten", "Das Unvorstellbare", "Das oberste Gesetz", "Der Verrat", "Helden und Dämonen", "Bewusstseinsverlust", "Von Angesicht zu Angesicht", "Dr. Jetrels Experiment", "Erfahrungswerte"});
        this.saveSeason(series, 1996, new String[]{"Die 37er", "Der Namenlose", "Das Holo-Syndrom", "Elogium", "Der Zeitstrom", "Die Raumverzerrung", "Der Höllenplanet", "Rätselhafte Visionen", "Tattoo", "Suspiria", "Das Signal", "Die Resistance", "Prototyp", "Allianzen", "Die Schwelle", "Gewalt", "Der Flugkörper", "Todessehnsucht", "Lebensanzeichen", "Der Verräter", "Die Verdoppelung", "Unschuld", "Das Ultimatum", "Tuvix", "Entscheidungen", "Der Kampf ums Dasein – Teil 1"});
        this.saveSeason(series, 1997, new String[]{"Der Kampf ums Dasein – Teil 2", "Tuvoks Flashback", "Das Hochsicherheitsgefängnis", "Der Schwarm", "Das Wurmloch", "Das Erinnern", "Das Ritual", "Vor dem Ende der Zukunft – Teil 1", "Vor dem Ende der Zukunft – Teil 2", "Der Kriegsherr", "Die \"Q\"-Krise", "Makrokosmos", "Das Wagnis", "Das andere Ego", "Der Wille", "Pon Farr", "Die Kooperative", "Charakterelemente", "Die Asteroiden", "Die neue Identität", "Temporale Sprünge", "Das wirkliche Leben", "Herkunft aus der Ferne", "Translokalisation", "Rebellion Alpha", "Skorpion – Teil 1"});
        this.saveSeason(series, 1998, new String[]{"Skorpion – Teil 2", "Die Gabe", "Tag der Ehre", "Nemesis", "Der Isomorph", "Der schwarze Vogel", "Verwerfliche Experimente", "Ein Jahr Hölle – Teil 1", "Ein Jahr Hölle – Teil 2", "Gewalttätige Gedanken", "Apropos Fliegen", "Leben nach dem Tod", "Wache Momente", "Flaschenpost", "Jäger", "Die Beute", "Im Rückblick", "Das Tötungsspiel", "Vis à vis", "Die Omega-Direktive", "Unvergessen", "Der Zeitzeuge", "Dämon", "Eine", "In Furcht und Hoffnung"});
        this.saveSeason(series, 1999, new String[]{"Nacht", "Die Drohne", "Extreme Risiken", "In Fleisch und Blut", "Es war einmal", "Temporale Paradoxie", "Das Vinculum", "Inhumane Praktiken", "Dreißig Tage", "Kontrapunkt", "Verborgene Bilder", "Chaoticas Braut", "Schwere", "Euphorie", "Das ungewisse Dunkel", "Das Generationenschiff", "Endstation: Vergessenheit", "Der Fight", "Die Denkfabrik", "Verheerende Gewalt", "Liebe inmitten der Sterne", "23:59", "Zeitschiff Relativity", "Geheimnisvolle Intelligenz", "Equinox – Teil 1"});
        this.saveSeason(series, 2000, new String[]{"Equinox – Teil 2", "Überlebensinstinkt", "Die Barke der Toten", "Dame, Doktor, Ass, Spion", "Alice", "Rätsel", "Die Zähne des Drachen", "Ein kleiner Schritt", "Die Voyager-Konspiration", "Das Pfadfinder-Projekt", "Fair Haven", "Es geschah in einem Augenblick", "Der Virtuose", "Das Mahnmal", "Tsunkatse", "Kollektiv", "Das Geistervolk", "Asche zu Asche", "Icheb", "Der gute Hirte", "Lebe flott und in Frieden", "Die Muse", "Voller Wut", "Rettungsanker", "Der Spuk auf Deck Zwölf", "Unimatrix Zero – Teil 1"});
        this.saveSeason(series, 2001, new String[]{"Unimatrix Zero – Teil 2", "Unvollkommenheit", "Das Rennen", "Verdrängung", "Kritische Versorgung", "Eingeschleust", "Körper und Seele", "Nightingale", "Fleisch und Blut – Teil 1", "Fleisch und Blut – Teil 2", "Zersplittert", "Abstammung", "Reue", "Die Prophezeiung", "Die Leere", "Arbeiterschaft – Teil 1", "Arbeiterschaft – Teil 2", "Menschliche Fehler", "Q2", "Die Veröffentlichung", "Friendship One", "Ein natürliches Gesetz", "Eine Heimstätte", "Renaissance Mensch", "Endspiel – Teil 1", "Endspiel – Teil 2"});

        // Serie: Star Trek – Enterprise
        series = new Series("ST-ENT", "Star Trek – Enterprise", "Sci-Fi", "USA", 2001, 2005, 4, 98);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 2001, new String[]{"Aufbruch ins Unbekannte", "Freund oder Feind", "Geistergeschichten", "In guter Hoffnung", "Terra Nova", "Doppeltes Spiel", "Das Eis bricht", "Die Saat", "Familienbande", "Kalter Krieg", "Lautloser Feind", "Lieber Doktor", "Schlafende Hunde", "Im Schatten von P’Jem", "Allein", "Die Verschmelzung", "Gesetze der Jagd", "Raumpiraten", "Schiff der Geister", "In sicherem Gewahrsam", "Vox Sola", "Gefallene Heldin", "Durch die Wüste", "Zwei Tage auf Risa", "Die Schockwelle"});
        this.saveSeason(series, 2002, new String[]{"Die Schockwelle, Teil 2", "Carbon Creek", "Das Minenfeld", "Todesstation", "Eine Nacht Krankenstation", "Marodeure", "Der Siebente", "Der Kommunikator", "Eigenarten", "Vermisst", "Kostbare Fracht", "Der Laufsteg", "Morgengrauen", "Stigma", "Waffenstillstand", "Die Zukunft", "Canamar", "Übergang", "Das Urteil", "Horizon", "Böses Blut", "Cogenitor", "Regeneration", "Erstflug", "Kopfgeld", "Die Ausdehnung"});
        this.saveSeason(series, 2003, new String[]{"Die Xindi", "Anomalie", "Transformation", "Rajiin", "Impulsiv", "Exil", "Die Ladung", "Dämmerung", "Faustrecht", "Ebenbild", "Carpenter Street", "Das auserwählte Reich", "Testgebiet", "Kriegslist", "Der Vorbote", "Auf Ärztliche Anweisung", "Brutstätte", "Azati Prime", "Beschädigungen", "Die Vergessenen", "E²", "Der Rat", "Countdown", "Stunde Null"});
        this.saveSeason(series, 2004, new String[]{"Sturmfront", "Sturmfront, Teil 2", "Zuhause", "Borderland", "Cold Station 12", "Die Augments", "Der Anschlag", "Zeit des Erwachens", "Kir’Shara", "Daedalus", "Beobachtungseffekt", "Babel", "Vereinigt", "Die Aenar", "Die Heimsuchung", "Die Abweichung", "Die Verbindung", "Die dunkle Seite des Spiegels", "Die dunkle Seite des Spiegels, Teil 2", "Dämonen", "Terra Prime", "Dies sind die Abenteuer"});

        // Serie: Star Trek – Discovery (ab Ende Season 2 unvollständig)
        series = new Series("ST-DSC", "Star Trek – Discovery", "Sci-Fi", "USA", 2017, -1, 2, 19);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 2017, new String[]{"Leuchtfeuer", "Das Urteil", "Lakaien und Könige", "Sprung", "Wähle deinen Schmerz", "Lethe", "T=Mudd²", "Si Vis Pacem, Para Bellum", "Algorithmus", "Nur wegen dir", "Der Wolf im Inneren", "Blindes Verlangen", "Auftakt zur Vergangenheit", "Flucht nach vorn", "Nimm meine Hand"});
        this.saveSeason(series, 2018, new String[]{"Bruder", "New Eden", "Lichtpunkt", "Der Charonspfennig", "Die Heiligen der Unvollkommenheit", "Donnergrollen", "Licht und Schatten", "Gedächtniskraft", "(Project Daedalus)", "(The Red Angel)", "(Perpetual Infinity)"});

        // Serie: Stranger Things (ab Season 3 unvollständig)
        series = new Series("StrangerThings", "Stranger Things", "Sci-Fi", "USA", 2016, -1, 3, 17);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 2016, new String[]{"Kapitel eins: Das Verschwinden des Will Byers", "Kapitel zwei: Die Verrückte auf der Maple Street", "Kapitel drei: Grausame Nacht", "Kapitel vier: Die Leiche", "Kapitel fünf: Der Floh und der Akrobat", "Kapitel sechs: Das Monster", "Kapitel sieben: Die Badewanne", "Kapitel acht: Die andere Seite"});
        this.saveSeason(series, 2017, new String[]{"Kapitel eins: MAD MAX", "Kapitel zwei: Süßes oder Saures, Freak", "Kapitel drei: Die Kaulquappe", "Kapitel vier: Will, der Weise", "Kapitel fünf: Dig Dug", "Kapitel sechs: Der Spion", "Kapitel sieben: Die verlorene Schwester", "Kapitel acht: Der Gedankenschinder", "Kapitel neun: Das Tor"});
        this.saveSeason(series, 2018, new String[]{"Suzie, hörst du mich?", "Ratten", "Die verschwundene Rettungsschwimmerin", "Der Sauna-Test", "Der Ursprung", "Der Geburtstag", "Der Biss", "Die Schlacht von Starcourt"});

        // Serie: The Muppets
        series = new Series("TheMuppets", "The Muppets", "Sitcom", "USA", 2015, 2016, 1, 16);
        this.seriesBean.saveNew(series);

        this.saveSeason(series, 2015, new String[]{"Der verhasste Gast", "Neue Besen kehren mies", "Der Bär ist los", "Spaß mit Piggy", "Zickenterror", "Der Ex-Faktor", "Bloß keinen Stress!", "Verknallt in Chelsea", "Der große Gonzo", "Das Mindy-Problem", "Nicht unser Ding, das Marketing?", "Zeigt her eure Schwänzchen!", "Schwein sucht Freund für’s Leben", "Lügen haben grüne Beine", "Die Sau ist raus!", "Alles nur aus Liebe"});

        // Statusmeldung zurückgeben
        StatusResponse response = new StatusResponse();
        response.status = "OK";
        response.message = "Demodaten wurden angelegt.";
        return response;
    }

    private void saveSeason(Series series, int year, String[] episodeTitles) {
        int seasonNumber = this.seasonBean.findBySeriesId(series.getId()).size() + 1;
        Season season = new Season(series, seasonNumber, year);

        List<Episode> episodes = new ArrayList<>();
        int nr = 0;

        for (String episodeTitle : episodeTitles) {
            Episode episode = new Episode(++nr, episodeTitle);
            episodes.add(episode);
        }

        season.setEpisodes(episodes);
        season.setNumberOfEpisodes(nr);

        this.seasonBean.saveNew(season);
    }

}
