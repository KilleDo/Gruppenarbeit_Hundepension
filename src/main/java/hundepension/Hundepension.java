package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// <editor-fold desc="">
public class Hundepension extends JFrame{
    private JLabel registration;
    private JTextField nameHundtextfield;
    private JComboBox rassecombobox;
    private JTextField rassetextfield;
    private JComboBox geschlechtcombobox;
    private JComboBox kastriertcombobox;
    private JComboBox groessecombobox;
    private JComboBox problemecombobox;
    private JTextField problemetextfield;
    private JTextField altertextfield;
    private JPanel hundregistrierpanel;
    private JLabel rasselabel;
    private JLabel groeßelabel;
    private JLabel alterlabel;
    private JLabel geschlechtlabel;
    private JLabel kastriertlabel;
    private JLabel problemelabel;
    private JLabel namelabel;
    private JButton speichern;
    private JButton resetButton;
    private JLabel weitereInformationenlabel;
    private JTextArea weitereInformationentextarea;
    private JTextArea ausgabe;
    private JComboBox zimmerauswahl;
    private JButton partnerZuweisenButton;
    private JScrollPane ausgabescrollbalken;
    // </editor-fold>
    // <editor-fold desc="Listen erstellen">
    protected ArrayList<Hunde> suchtpartnerListe; //Erstellen der Liste zur Klasse Hunde, in welcher die Objekte gespeichert werden
    protected ArrayList<Hunde> hundeimhotel; //später relevant für das Filtern der Zimmerpartner

    public ArrayList<Hunde> getSuchtpartnerListe() {
        return suchtpartnerListe;
    }
    public ArrayList<Hunde> getHundeimhotel() {
        return hundeimhotel;
    }
    // </editor-fold>
    //Konstruktor
    public Hundepension(){
        setTitle("Hundepension "); // Titel des Fensters
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Sorgt dafür, dass das Fenster geschlossen wird, sobald der "Close" Knopf gedrückt wird
        setContentPane(hundregistrierpanel);
        setSize(1500,1500); //Größe des Fensters
        setVisible(true); //Sichtbarkeit des Fensters

        // Die Rasse- und Problemeingabefelder, sollen nur sichtbar sein, wenn "andere:" ausgewählt wird, daher müssen sie Anfangs unsichtbar sein.
        rassetextfield.setVisible(false);
        problemetextfield.setVisible(false);

        //Initialisieren der Liste "hundeeingabeListe" in welcher die Hunde gespeichert werden sollen.
        suchtpartnerListe = new ArrayList<>();
        hundeimhotel = new ArrayList<>();

        //Hunde aus initObjekt Methode in Hundeimhotelliste aufnehmen
        initObjekte();

        //Actionlistener für die Komboboxen
        rassecombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rassesichtbar = rassecombobox.getSelectedItem().toString();
                //Sichtbarkeit des Rassetextfeldes
                if (rassesichtbar.equals("andere:")){
                    rassetextfield.setVisible(true);
                }else{
                    rassetextfield.setVisible(false);
                }}
        });
        groessecombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        geschlechtcombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        kastriertcombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        problemecombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String problemsichtbar = problemecombobox.getSelectedItem().toString();
                //Sichtbarkeit des Problemetextfeldes
                if (problemsichtbar.equals("andere:")){
                    problemetextfield.setVisible(true);
                }else{
                    problemetextfield.setVisible(false);
                }
            }
        });
        zimmerauswahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        speichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // text entnehmen aus Comboboxen und Textfeldern und den variablen zuweisen "zwischenspeichern"
                String hundename = nameHundtextfield.getText();
                String rasse = rassecombobox.getSelectedItem().toString();
                if (rasse.equals("andere:")){ //falls "andere:" ausgewählt wurde, text aus dem Textfield stattdessen entnehmen
                    rasse = rassetextfield.getText();} // und überschreiben/zwischenspeichern
                String groesse = groessecombobox.getSelectedItem().toString();
                double alter; // initialisierung des double alter
                try{
                    alter = Double.parseDouble(altertextfield.getText());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Bitte als Altersangabe nur Zahlen eingeben.");
                    return;} //unterbrechen des Vorgangs
                if (alter<1){
                    JOptionPane.showMessageDialog(null,"Wir nehmen keine Hunde unter 1 Jahr.\nEntschuldigen Sie die Unannehmlichkeiten.");
                    return;
                }else if(alter>20){
                    JOptionPane.showMessageDialog(null,"Wir nehmen nur Hunde unter 20.\nEntschuldigen Sie die Unannehmlichkeiten");
                    return;
                }
                String geschlecht = geschlechtcombobox.getSelectedItem().toString();
                boolean kastriert; // initalisieren des boolean kastriert
                String ausgewaehlt = kastriertcombobox.getSelectedItem().toString(); // kastriert Combobox entwerten
                if (ausgewaehlt.isEmpty()){ // Falls in der Combobox nichts ausgewählt wurde, soll eine Fehlermeldung kommen
                    JOptionPane.showMessageDialog(null, "Bitte alles ausfüllen.");
                    return; //unterbrechen des Vorgangs
                }else if (ausgewaehlt.equals("Ja")){ // falls "Ja" ausgewählt wurde, wird kastriert als richtig zwischengespeichert
                    kastriert = true;
                }else{ //falls etwas anderes ausgewäjlt wurde (also "Nein"), wird kastriert als falsch zwischengespeichert
                    kastriert = false;}
                String probleme = problemecombobox.getSelectedItem().toString();
                if (probleme.equals("andere:")){ // falls "andere:" ausgewählt wurde, wird der Text stattdessen dem textfield entnommen
                    probleme = problemetextfield.getText();} // und überschrieben/zwischengespeichert
                String weitereInfos = weitereInformationentextarea.getText();
                String zimmerversion = zimmerauswahl.getSelectedItem().toString();
                boolean einzelzimmer = false;
                if (zimmerversion.equals("Einzelzimmer")){
                    einzelzimmer = true;
                }
                String zimmerpartner = "";

                //Fehler, falls etwas nicht ausgefüllt wurde:
                if (hundename.isEmpty() || rasse.isEmpty() || groesse.isEmpty() || geschlecht.isEmpty() || probleme.isEmpty() || zimmerversion.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bitte alles ausfüllen.");
                    return;
                }
                for (Hunde hund: hundeimhotel){
                    if (hund.getHundename().equals(hundename)){
                        JOptionPane.showMessageDialog(null, "Da es bereits einen " + hundename +" in der Pension gibt, " +
                                "\nbitten wir Sie, eine Namensergänzung hinzuzufügen " +
                                "\num Verwechslungen und Probleme im System zu vermeiden.\nz.B.:"+hundename+"_braunAuge.");
                        return;
                    }
                }
                Hunde hund = new Hunde(hundename, rasse, groesse, alter, geschlecht, kastriert, probleme, weitereInfos, einzelzimmer, zimmerpartner);
                String hundausgabe = hund.ausgeben();
                hundeimhotel.add(hund);
                if (einzelzimmer == false){
                    suchtpartnerListe.add(hund);
                }
                ausgabe.setText(ausgabe.getText() + "\n" + hundausgabe);
                }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            nameHundtextfield.setText("");
            rassetextfield.setText("");
            problemetextfield.setText("");
            altertextfield.setText("");
            weitereInformationentextarea.setText("");
            rassecombobox.setSelectedIndex(0);
            groessecombobox.setSelectedIndex(0);
            geschlechtcombobox.setSelectedIndex(0);
            kastriertcombobox.setSelectedIndex(0);
            problemecombobox.setSelectedIndex(0);
            }
        });

        partnerZuweisenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zimmer = zimmerauswahl.getSelectedItem().toString();
                if (zimmer.equals("Doppelzimmer")){
                    if (suchtpartnerListe.isEmpty()){ //Fehler falls noch kein Hund gespeichert wurde
                        JOptionPane.showMessageDialog(null, "Bitte erst ihren Hund eingeben.");
                        zimmerauswahl.setSelectedIndex(0); //sets Auswahl wieder auf "Einzelzimmer"
                        return;
                    }
                    new Zimmerpartner(Hundepension.this); //öffnen des Zimmerpartner fensters und Übergabe des Objektes
                }
            }
        });
    }
    public void initObjekte(){
        Hunde h1 = new Hunde("Violet", "Yorkshire Terrier", "Kleiner Hund", 3, "Hündin", true,"nichts/niemanden", "Verträgt keine Eier. ",false, "");
        hundeimhotel.add(h1);
        Hunde h2 = new Hunde("Milo", "Labradoodle", "Mittlerer Hund", 4, "Rüde", true, "Hunden/Rüden", "Frisst gerne alles mögliche.",false, "");
        hundeimhotel.add(h2);
        Hunde h3 = new Hunde("Miko", "Beagle", "Kleiner Hund", 6, "Hündin", true, "Nichts", "", false, "");
        hundeimhotel.add(h3);
        Hunde h4 = new Hunde("Chewbacca", "Neufundländer", "Riesiger Hund", 4, "Rüde", true, "Eimerköpfen", "Guter Gefährte", false, "");
        hundeimhotel.add(h4);
        Hunde h5 = new Hunde("Hulk", "American Pit Bull Terrier", "Großer Hund", 8, "Rüde", false, "lauten Geräuschen", "Nicht erschrecken, sein Fell ist grün gefärbt.", false,"" );
        hundeimhotel.add(h5);
        Hunde h6 = new Hunde("Blacky", "Dalmatiner", "Großer Hund", 6, "Hündin", true,"Nichts", "", false, "");
        hundeimhotel.add(h6);
        Hunde h7 = new Hunde("Loki", "Zwergspitz", "Kleinhund", 5, "Rüde", true, "Nichts", "Ist ein bisschen Hinterlistig.", false, "");
        hundeimhotel.add(h7);
        Hunde h8 = new Hunde("Jabba", "Mops", "Kleiner Hund", 2, "Rüde", true, "Nichts", "", false, "");
        hundeimhotel.add(h8);}

        public static void main(String[] args) {
        new Hundepension();

    }
}