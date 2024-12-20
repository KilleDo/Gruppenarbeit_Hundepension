package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Hundepension extends JFrame{
    // <editor-fold desc="Definieren der GUI-Komponenten und Listen ">
    private JPanel hundregistrier_panel;
    private JLabel hundregistrieren_label;
    private JLabel name_label;
    private JLabel rasse_label;
    private JLabel groesse_label;
    private JLabel alter_label;
    private JLabel geschlecht_label;
    private JLabel kastriert_label;
    private JLabel info_label;
    private JLabel zimmertyp_label;
    private JLabel zimmerpartner_label;
    private JLabel probleme_label;
    private JLabel icon_label;
    private JScrollPane ausgabe_ScrollPane;
    private JTextArea ausgabe_textarea;
    private JTextArea weitereInformationen_textarea;
    private JTextField nameHund_textfield;
    private JTextField rasse_textfield;
    private JTextField probleme_textfield;
    private JTextField alter_textfield;
    private JComboBox rasse_combobox;
    private JComboBox groesse_combobox;
    private JComboBox geschlecht_combobox;
    private JComboBox kastriert_combobox;
    private JComboBox probleme_combobox;
    private JComboBox zimmerauswahl_combobox;
    private JButton speichern_button;
    private JButton reset_button;
    private JButton partnerZuweisen_button;
    protected ArrayList<Hund> suchtpartnerListe; //Erstellen der Liste zur Klasse Hunde, in welcher die Objekte gespeichert werden
    protected ArrayList<Hund> hundeimhotel; //Erstellen der Liste für alle Honde in der Hundepension
    // </editor-fold>

    // <editor-fold desc="GetterMethoden für die Listen">
    //Erstellen von gettermethoden um die Listen später in der anderen Klasse aufrufen zu können
    public ArrayList<Hund> getSuchtpartnerListe() {
        return suchtpartnerListe;
    }
    public ArrayList<Hund> getHundeimhotel() {
        return hundeimhotel;
    }
    // </editor-fold>

    // <editor-fold desc="Konstruktor">
    public Hundepension(){
        // <editor-fold desc="Erstellen des Fensters">
        setTitle("Hundepension "); // Titel des Fensters
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Sorgt dafür, dass das Fenster geschlossen wird, sobald der "Close" Knopf gedrückt wird
        setContentPane(hundregistrier_panel); //Platzieren des Panels, auf welchem alle GUI-Elemente platziert sind im Fenster
        setSize(1500,1500); //Größe des Fensters
        setVisible(true); //Sichtbarkeit des Fensters
        // </editor-fold desc>

        // <editor-fold desc="Verstecken bestimmter GUI-Elemente">
        // Die Rasse- und Problemeingabefelder, sollen nur sichtbar sein, wenn "andere:" ausgewählt wird, daher müssen sie Anfangs unsichtbar sein.
        rasse_textfield.setVisible(false);
        probleme_textfield.setVisible(false);
        // </editor-fold desc>

        // <editor-fold desc="Initialisieren der Listen">
        suchtpartnerListe = new ArrayList<>();
        hundeimhotel = new ArrayList<>();
        // </editor-fold desc>

        // <editor-fold desc="Aufrufen der initObjekte Methode">
        //Aufrufen der Methode initObjekte, sodass bereits zum Start des Programmes ein paar Objekte in der Liste hundeimhotel gespeichert sind
        initObjekte();
        // </editor-fold desc>

        // <editor-fold desc="Hundeingabe ActionListener">
        //Actionlistener für die Komboboxen
        rasse_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rassesichtbar = rasse_combobox.getSelectedItem().toString();
                //Sichtbarkeit des Rassetextfeldes
                if (rassesichtbar.equals("andere:")){ //falls "andere:" ausgewählt wird, soll das textfield sichtbar sein
                    rasse_textfield.setVisible(true);
                }else{ //sonst nicht
                    rasse_textfield.setVisible(false);
                }}
        });
        groesse_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        geschlecht_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        kastriert_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        probleme_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String problemsichtbar = probleme_combobox.getSelectedItem().toString();
                //Sichtbarkeit des Problemetextfeldes
                if (problemsichtbar.equals("andere:")){ //falls "andere:" ausgewählt wurde, soll das textfield sichtbar sein
                    probleme_textfield.setVisible(true);
                }else{ //sonst nicht
                    probleme_textfield.setVisible(false);
                }
            }
        });
        zimmerauswahl_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //ActionListener für den Knopf, der das eingegebene Objekt in der Liste speichert
        speichern_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // text entnehmen aus Comboboxen und Textfeldern und den variablen zuweisen  "zwischenspeichern"
                String hundename = nameHund_textfield.getText();
                String rasse = rasse_combobox.getSelectedItem().toString();
                if (rasse.equals("andere:")){ //falls "andere:" ausgewählt wurde, text aus dem Textfield stattdessen entnehmen
                    rasse = rasse_textfield.getText();} // und überschreiben/zwischenspeichern
                String groesse = groesse_combobox.getSelectedItem().toString();
                double alter; // initialisierung des double alter
                try{
                    alter = Double.parseDouble(alter_textfield.getText());
                }catch (NumberFormatException ex) { //falls es sich nicht in einen Double umwandeln lässt, weil z.B. Buchstaben enthalten:
                    JOptionPane.showMessageDialog(null, "Bitte als Altersangabe nur Zahlen eingeben."); // Fehlermeldung
                    return;} //unterbrechen des Vorgangs
                if (alter<1){ //falls der Hund unter einem Jahr alt ist: Fehlermeldung und unterbrechen des Speichervorgangs
                    JOptionPane.showMessageDialog(null,"Wir nehmen keine Hunde unter 1 Jahr.\nEntschuldigen Sie die Unannehmlichkeiten.");
                    return;}
                if(alter>20){ //falls der Hund über zwanzig Jahre alt ist: Fehlermeldung und unterbrechen des Speichervorgangs
                    JOptionPane.showMessageDialog(null,"Wir nehmen nur Hunde bis 20.\nEntschuldigen Sie die Unannehmlichkeiten");
                    return;}
                String geschlecht = geschlecht_combobox.getSelectedItem().toString();
                boolean kastriert; // initalisieren des boolean kastriert
                String ausgewaehlt = kastriert_combobox.getSelectedItem().toString();
                if (ausgewaehlt.isEmpty()){ // Falls in der Combobox nichts ausgewählt wurde, soll eine Fehlermeldung kommen
                    JOptionPane.showMessageDialog(null, "Bitte alles ausfüllen.");
                    return; //unterbrechen des Vorgangs
                }else if (ausgewaehlt.equals("Ja")){ // falls "Ja" ausgewählt wurde, wird kastriert als true zwischengespeichert
                    kastriert = true;
                }else{ //falls etwas anderes ausgewäjlt wurde (also "Nein"), wird kastriert als false zwischengespeichert
                    kastriert = false;}
                String probleme = probleme_combobox.getSelectedItem().toString();
                if (probleme.equals("andere:")){ // falls "andere:" ausgewählt wurde, wird der Text stattdessen dem textfield entnommen
                    probleme = probleme_textfield.getText();} // und überschrieben/zwischengespeichert
                String weitereInfos = weitereInformationen_textarea.getText();
                String zimmerversion = zimmerauswahl_combobox.getSelectedItem().toString();
                boolean einzelzimmer = false; // Initialisierung des booleans "einzelzimmer" als falsch
                if (zimmerversion.equals("Einzelzimmer")){
                    einzelzimmer = true; //falls einzelzimmer ausgewählt wurde würder als true überschrieben
                }
                String zimmerpartner = ""; //Der String Zimmerpartner wird grundsätzlich leer gesetzt

                //Fehler, falls etwas nicht ausgefüllt wurde:
                if (hundename.isEmpty() || rasse.isEmpty() || groesse.isEmpty() || geschlecht.isEmpty() || probleme.isEmpty() || zimmerversion.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bitte alles ausfüllen.");
                    return;
                }
                for (Hund hund: hundeimhotel){ //geht alle Objekte(hund) der Klasse Hunde, in der "hundeimhotel"-Liste durch
                    if (hund.getHundename().equals(hundename)){ // wenn der Hundename eines Objektes dem eingegebenen Namen entspricht:
                        JOptionPane.showMessageDialog(null, "Da es bereits einen " + hundename +" in der Pension gibt, " +
                                "\nbitten wir Sie, eine Namensergänzung hinzuzufügen " +
                                "\num Verwechslungen und Probleme im System zu vermeiden.\nz.B.:"+hundename+"_braunAuge."); //Fehlermeldung
                        return; //unterbrechen des Vorgangs
                    }
                }
                Hund hund = new Hund(hundename, rasse, groesse, alter, geschlecht, kastriert, probleme, weitereInfos, einzelzimmer, zimmerpartner);
                //Erstellen eines neuen Objekts der Klasse Hunde, mit den aus den textfield und Comboboxen entnommenen Eingaben
                String hundausgabe = hund.ausgeben(); //aufrufen der ausgeben Methode aus der Hunde Klasse am soeben erstellten Objekt und speichern in dem String "hundausgabe"
                hundeimhotel.add(hund); //hinzufügen des soeben erstelleten Hundes zur Liste "hundeimhotel"
                if (einzelzimmer == false){ // falls nicht einzelzimmer gewählt wurde (alsp Doppelzimmer)
                    suchtpartnerListe.add(hund); // wird er zur "suchtpartnerListe" hinzugefügt
                }
                ausgabe_textarea.setText(ausgabe_textarea.getText() + "\n" + hundausgabe); // hinzufügen des Objektes zur "ausgabe"-textarea
                }
        });
        //ActionListener für den Knopf, der die Eingabekomponenten zurücksetzt
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            nameHund_textfield.setText("");
            rasse_textfield.setText("");
            probleme_textfield.setText("");
            alter_textfield.setText("");
            weitereInformationen_textarea.setText("");
            rasse_combobox.setSelectedIndex(0);
            groesse_combobox.setSelectedIndex(0);
            geschlecht_combobox.setSelectedIndex(0);
            kastriert_combobox.setSelectedIndex(0);
            probleme_combobox.setSelectedIndex(0);
            }
        });
        // </editor-fold desc>

        // <editor-fold desc="Zimmerpartner ActionListener">
        partnerZuweisen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (suchtpartnerListe.isEmpty()){ //Fehler falls noch kein Hund der in ein Doppelzimmer will gespeichert wurde
                    JOptionPane.showMessageDialog(null, "Bitte einen Hund, welcher in ein Doppelzimmer soll eingeben."); //Fehlermeldung
                    return;
                    }
                    new Zimmerpartner(Hundepension.this); //öffnen des Zimmerpartner fensters und Übergabe des Objektes "Hundepension"
                    hundregistrier_panel.setVisible(false);
                }
        });
        // </editor-fold desc>
    }
    // </editor-fold desc>

    // <editor-fold desc="Methode zum Erzeugen mehrer Objekte und Speichern in der Liste">
    public void initObjekte(){ //Erschaffen von Objekten der Klasse Hunde
        Hund h1 = new Hund("Violet", "Yorkshire Terrier", "Kleiner Hund", 3, "Hündin", true,"nichts/niemanden", "Verträgt keine Eier. ",false, "");
        hundeimhotel.add(h1);
        Hund h2 = new Hund("Milo", "Labradoodle", "Mittlerer Hund", 4, "Rüde", true, "Hunden/Rüden", "Frisst gerne alles mögliche.",false, "");
        hundeimhotel.add(h2);
        Hund h3 = new Hund("Miko", "Beagle", "Kleiner Hund", 16, "Hündin", true, "Nichts", "", false, "");
        hundeimhotel.add(h3);
        Hund h4 = new Hund("Chewbacca", "Neufundländer", "Riesiger Hund", 4, "Rüde", true, "Eimerköpfen", "Guter Gefährte", false, "");
        hundeimhotel.add(h4);
        Hund h5 = new Hund("Hulk", "American Pit Bull Terrier", "Großer Hund", 8, "Rüde", false, "lauten Geräuschen", "Nicht erschrecken, sein Fell ist grün gefärbt.", false,"" );
        hundeimhotel.add(h5);
        Hund h6 = new Hund("Blacky", "Dalmatiner", "Großer Hund", 6, "Hündin", true,"Nichts", "", false, "");
        hundeimhotel.add(h6);
        Hund h7 = new Hund("Loki", "Zwergspitz", "Kleinhund", 11, "Rüde", true, "Nichts", "Ist ein bisschen Hinterlistig.", false, "");
        hundeimhotel.add(h7);
        Hund h8 = new Hund("Jabba", "Mops", "Kleiner Hund", 2, "Rüde", true, "Nichts", "", false, "");
        hundeimhotel.add(h8);}
    // </editor-fold desc>

    // <editor-fold desc="public static void Methode zum Aufrufen der Klasse">
        public static void main(String[] args) {
        new Hundepension();
    }
    // </editor-fold desc>
}