package hundepension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Zimmerpartner extends JFrame{
    private JPanel zimmmerpartnerpanel;
    private JTextField rassefiltertextfield;
    private JList rassefilterlist;
    private JComboBox groessefiltercombobox;
    private JTextField alterfiltertextfield;
    private JComboBox geschlechtfiltercombobox;
    private JRadioButton alteregalRadioButton;
    private JComboBox alterfiltercombobox;
    private JComboBox kastriertfiltercombobox;
    private JTextArea filterausgabe;
    private JButton filternButton;
    private JLabel headline;
    private JScrollPane filterausgabescrollbar;
    private boolean sichtbar;
    private boolean genauesfiltern = false;
    private JComboBox eingabeHundeauswaehlen; // Combobox in welcher wir einen von uns zuvor in der Hundepension eingegeben Hund auswaehlen
    private JRadioButton nurHundeDieEinenRadioButton;
    private Hundepension hundepension; //hier wird ein Objekt der Hundepension erstellt, um die Listen zu erhalten

    //Konstruktor
    public Zimmerpartner(Hundepension hundepension){
        this.hundepension = hundepension; //initialisieren
        setTitle("Doppelzimmer ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(zimmmerpartnerpanel);
        setSize(1500,1500);
        setVisible(true);

        //Hier wird der Inhalt der Combobox, mit welcher unser eingegebener Hund ausgewählt wird, hinzugefügt
        eingabeHundeauswaehlen.addItem("");
        for (Hunde hund: hundepension.getSuchtpartnerListe()){
            eingabeHundeauswaehlen.addItem(hund.getHundename());}


        //sollen erst durch drücken des radiobuttons sichtbar werden
        sichtbar = false;
        alterfiltertextfield.setVisible(sichtbar);
        alterfiltercombobox.setVisible(sichtbar);

        ArrayList<Hunde> meinHund = new ArrayList<>(); //erstellen einer Liste in welcher immer nur der aktuelle Hund, für welchen ein Zimmerpartner gesucht wird, gespeichert ist

        eingabeHundeauswaehlen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                eingabeHundeauswaehlen.setEnabled(false);
                for (Hunde hund: hundepension.getSuchtpartnerListe()){
                    if (hund.getHundename().equals(meinhund)) {
                        hundepension.getSuchtpartnerListe().remove(hund);
                        meinHund.add(hund);
                    }
                }
            }
        });
        rassefilterlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        rassefiltertextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        groessefiltercombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alteregalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sichtbar == false){
                    sichtbar = true;
                }else{
                    sichtbar = false;
                }
                alterfiltertextfield.setVisible(sichtbar);
                alterfiltercombobox.setVisible(sichtbar);
            }
        });
        alterfiltertextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alterfiltercombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        geschlechtfiltercombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        kastriertfiltercombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        filternButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genauesfiltern == false){
                    filtern();
                }


            }
        });
        nurHundeDieEinenRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genauesfiltern == false){
                    genauesfiltern = true;
                }else{
                    genauesfiltern = false;
                }
            }
        });

    }

    public void filtern(){
        //Genreller Filter, filtert durch alle Hunde in der Pension
        ArrayList<Hunde> gefilterteHunde = new ArrayList<>(); //gefilterte Liste die Schlussendlich in der textarea ausgegeben wird
        String groesse = groessefiltercombobox.getSelectedItem().toString();
        String geschlecht = geschlechtfiltercombobox.getSelectedItem().toString();
        String kastriertstring = kastriertfiltercombobox.getSelectedItem().toString();
        double alter;
        boolean alterpasst = true; //initialisieren, Wert wird in Methode "alterfiltern" überschrieben

        for (Hunde hund: hundepension.getHundeimhotel()){
            if (groessefiltern(hund, groesse) == false){
                continue;}
            if (geschlechtfiltern(hund, geschlecht) == false){
                continue;}
            if (kastriertfiltern(hund, kastriertstring) == false){
                continue;}
            if(alterfiltern(hund)){
                continue;}

            //Der Hund erfüllt alle Filterkriterien ->
            gefilterteHunde.add(hund);
        }
        if (gefilterteHunde.isEmpty()){
            filterausgabe.setText("Keine Hunde entsprechen den ausgewählten Kriterien.");
        } else{
            filterausgabe.setText("");
            for (Hunde hund: gefilterteHunde){
                String hundausgabe = hund.ausgeben();
                filterausgabe.setText(filterausgabe.getText() + hundausgabe);
            }
        }
    }
    private boolean groessefiltern(Hunde hund, String groesse){
        return groesse.equals("Egal") || hund.getGroesse().equals(groesse); //Die Methode gibt den wert true aus, falls das gefilterte zum hund passt
        }
    private boolean geschlechtfiltern(Hunde hund, String geschlecht){
        return geschlecht.equals("Egal") || hund.getGeschlecht().equals(geschlecht);//Die Methode gibt true aus, falls das gefilterte zum hund passt
    }
    private boolean kastriertfiltern(Hunde hund, String kastriertstring) {
        boolean kastriert = !kastriertstring.equals("Nein"); //kastriert ist true, wenn nicht-nein (also ja oder egal) ausgewählt wird, sonst false
        return kastriertstring.equals("Egal") || hund.isKastriert() == kastriert; //Die Methode gibt true aus, falls das gefilterte zum hund passt
    }
    private boolean alterfiltern(Hunde hund) {
        if (sichtbar == false) {
            return true;
        } //Wenn der radio button "Egal" aktiviert ist, gibt die methode direkt true aus
        boolean alterpasst = false;
        double alter;
        if (!alterfiltertextfield.getText().isEmpty()) { //falls das alter textfeld nicht leer ist
            try {
                alter = Double.parseDouble(alterfiltertextfield.getText()); //umwandlung in double Wert
                if (hund.getAlter() == alter) { // Wenn das alter mit dem gefilterten Wert übereinstimmt
                    alterpasst = true; //Methode gibt true aus
                }
            } catch (NumberFormatException e) {
                //Fehleingaben werden ignoriert
            }}
        String altersbereich = alterfiltercombobox.getSelectedItem().toString();
        if (!altersbereich.isEmpty()) { // alters combobox nicht leer, also ein wert "altersbereich" ausgewählt
            if (altersbereich.equals("1-5") && (hund.getAlter() >= 1 && hund.getAlter() <= 5)) {
                //falls in der combobox 1-5 ausgewählt wurde und das alter hundes größer/gleich 1 und kleiner/gleich 5 ist
                alterpasst = true;
            } else if (altersbereich.equals("6-10") && (hund.getAlter() >= 6 && hund.getAlter() <= 10)) {
                //falls in der combobox 6-10 ausgewählt wurde und das alter hundes nicht 6, 7, 8, 9 oder 10 ist
                alterpasst = true;
            } else if (altersbereich.equals("11-15") && !(hund.getAlter() >= 11 && hund.getAlter() <= 15)) {
                //falls in der combobox 11-15 ausgewählt wurde und das alter hundes nicht 11, 12, 13, 14 oder 15 ist
                alterpasst = true;
            } else if (alterfiltercombobox.getSelectedItem().toString().equals("16-20") && !(hund.getAlter() >= 16 && hund.getAlter() <= 20)) {
                //falls in der combobox 6-10 ausgewählt wurde und das alter hundes nicht 16, 17, 18, 19 oder 20 ist
                alterpasst = true;
            }else{
                alterpasst = false;
            }
        }return alterpasst;
    }
}
    /*

            }

            }else if (!alterfiltertextfield.getText().isEmpty() && alter != hund.getAlter()){
                //falls das textfeld nicht leer ist und das alter in dem textfeld nicht dem des Hundes entspricht
                continue;*/
        /*public void zimmerpartnerfiltern(){
            for (Hunde hund: hundepension.getSuchtpartnerListe()){
                }
            }*/
