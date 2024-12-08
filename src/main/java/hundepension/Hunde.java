package hundepension;

//Klasse mit sämtlichen Attributen für einen Hund (= Objekt)
public class Hunde {
    private String hundename;
    private String rasse;
    private String groesse;
    private double alter;
    private String geschlecht;
    private boolean kastriert;
    private String probleme;
    private String weitereInfos;

    //Konstruktor (Dieser wird verwendet um Objekte der Klasse Hunde zu erstellen und deren Atrribute zu initialisieren)
    public Hunde(String hundename, String rasse, String groesse, double alter, String geschlecht, boolean kastriert, String probleme, String weitereInfos) {
        this.hundename = hundename;
        this.rasse = rasse;
        this.groesse = groesse;
        this.alter = alter;
        this.geschlecht = geschlecht;
        this.kastriert = kastriert;
        this.probleme = probleme;
        this.weitereInfos = weitereInfos;

    }

    //Filter:
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
    public boolean isKastriert() {
        return kastriert;
    }

    //überschreibt ___.toString und erzeugt eine andere Textdarstellung des Objektes (\n = Zeilenumbruch)
    public String toString(){
        return " \n" + hundename + " \n Rasse: " + rasse + "\n Größe: " + groesse + "\n Alter: " + alter + "\n Geschlecht: " + geschlecht
               + "\n Kastriert: " + kastriert + "\n Hat Probleme mit " + probleme + "\n " + weitereInfos + "\n\n------------------------------";
    }
}
