package hundepension;
//Klasse mit sämtlichen Attributen für einen Hund (= Objekt)
public class Hund {
    // <editor-fold desc="Definiren der Variablen">
    //erstellen der Attribute für die Listen, welche wir in den anderen Klassen erstellen
    private String hundename;
    private String rasse;
    private String groesse;
    private double alter;
    private String geschlecht;
    private boolean kastriert;
    private String probleme;
    private String weitereInfos;
    private boolean einzelzimmer;
    private String zimmerpartner;
    // </editor-fold>
    // <editor-fold desc="Konstruktor">
    //Konstruktor (Dieser wird verwendet um Objekte der Klasse Hunde zu erstellen und deren Atrribute zu initialisieren)
    public Hund(String hundename, String rasse, String groesse, double alter, String geschlecht, boolean kastriert, String probleme, String weitereInfos, boolean einzelzimmer, String zimmerpartner) {
        this.hundename = hundename;
        this.rasse = rasse;
        this.groesse = groesse;
        this.alter = alter;
        this.geschlecht = geschlecht;
        this.kastriert = kastriert;
        this.probleme = probleme;
        this.weitereInfos = weitereInfos;
        this.einzelzimmer = einzelzimmer;
        this.zimmerpartner = zimmerpartner;
    }
    // </editor-fold desc>
    // <editor-fold desc="Gettermethoden">
    public String getHundename() {
        return hundename;
    }
    public String getRasse() {
        return rasse;
    }
    public String getGroesse() {
        return groesse;
    }
    public double getAlter() {
        return alter;
    }
    public String getGeschlecht() {
        return geschlecht;
    }
    public boolean istKastriert() {
        return kastriert;
    }
    public boolean getEinzelzimmer(){
        return einzelzimmer;
    }
    public String getZimmerpartner(){
        return zimmerpartner;
    }
    // </editor-fold>
    // <editor-fold desc="Methode zum hinzufügen des Zimmerpartners">
    public void setZimmerpartner(String zimmerpartner) { //überschreibt den zimmerpartner String
        this.zimmerpartner = "Zimmerpartner: " + zimmerpartner;
    }
    // </editor-fold desc">
    // <editor-fold desc="Methode zur übersichtlichen Ausgabe eines Objektes">
    // erzeugt eine andere Textdarstellung des Objektes (\n = Zeilenumbruch)
    public String ausgeben(){
        return " \n\n" + hundename + " \n Rasse: " + rasse + "\n Größe: " + groesse + "\n Alter: " + alter + "\n Geschlecht: " + geschlecht
               + "\n Kastriert: " + kastriert + "\n Hat Probleme mit " + probleme + "\n " + weitereInfos + "\n" + zimmerpartner +"\n\n------------------------------";
    }
    // </editor-fold desc>
}