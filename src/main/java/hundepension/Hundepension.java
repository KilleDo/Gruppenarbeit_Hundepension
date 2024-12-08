package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    protected ArrayList<Hunde> hundeeingabeListe; //Erstellen der Liste zur Klasse Hunde, in welcher die Objekte gespeichert werden
    protected ArrayList<Hunde> hundeimhotel; //später relevant für das Filtern der Zimmerpartner

    public ArrayList<Hunde> getHundeeingabeListe() {
        return hundeeingabeListe;
    }
    public ArrayList<Hunde> getHundeimhotel() {
        return hundeimhotel;
    }

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
        hundeeingabeListe = new ArrayList<>();
        hundeimhotel = new ArrayList<>();



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

                //Fehler, falls etwas nicht ausgefüllt wurde:

                if ( hundename.isEmpty() || rasse.isEmpty() || groesse.isEmpty() || geschlecht.isEmpty() || probleme.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bitte alles ausfüllen.");
                }else {
                    Hunde hund = new Hunde(hundename, rasse, groesse, alter, geschlecht, kastriert, probleme, weitereInfos);
                    String hundausgabe = hund.toString();
                    hundeeingabeListe.add(hund);
                    ausgabe.setText(ausgabe.getText() + "\n" + hundausgabe);
                }
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


        zimmerauswahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zimmer = zimmerauswahl.getSelectedItem().toString();
                if (zimmer.equals("Doppelzimmer")){
                    if (hundeeingabeListe.isEmpty()){ //Fehler falls noch kein Hund gespeichert wurde
                        JOptionPane.showMessageDialog(null, "Bitte erst ihren Hund eingeben.");
                        zimmerauswahl.setSelectedIndex(0); //sets Auswahl wieder auf "Einzelzimmer"
                        return;
                    }
                    new Zimmerpartner(Hundepension.this); //öffnen des Zimmerpartner fensters und Übergabe des Objektes
                }
            }
        });
    }

    public static void main(String[] args) {
        new Hundepension();
    }
}
