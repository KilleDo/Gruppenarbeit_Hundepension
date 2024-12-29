package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Zimmerpartner extends JFrame{
    // <editor-fold desc="Definieren der GUI-Komponenten, der Variablen und des Objektes hundepension">
    private JPanel zimmmerpartner_panel;
    private JLabel rassefilterinfo_label;
    private JTextArea filterausgabe_textarea;
    private JTextField rassefilter_textfield;
    private JTextField alterfilter_textfield;
    private JComboBox groessefilter_combobox;
    private JComboBox geschlechtfilter_combobox;
    private JComboBox alterfilter_combobox;
    private JComboBox kastriertfilter_combobox;
    private JComboBox eingabeHundeauswaehlen_combobox; // Combobox in welcher man einen, der zuvor in der Hundepension eingegeben Hunde auswaehlt
    private JComboBox zimmerpartnerwaehlen_combobox; //Combobox in welcher man einen Zimmerpartner für seinen Hund auswaehlt
    private JRadioButton alteregal_RadioButton;
    private JRadioButton egalrasse_RadioButton;
    private JRadioButton genauesFiltern_RadioButton;
    private JButton filtern_button;
    private JButton zuweisen_button;
    private JLabel informations_label;
    private JLabel rassefilter_label;
    private JLabel groessefilter_label;
    private JLabel alterfilter_label;
    private JLabel geschlechtfilter_label;
    private JLabel kastriertfilter_label;
    private JLabel zimmerpartnerzuweis_label;
    private JLabel ihrHund_label;
    private JLabel zimmerpartner_label;
    private JScrollPane filterausgabe_ScrollPane;
    private Hundepension hundepension; //hier wird ein Objekt der Hundepension erstellt, dieses wird immer aufgrufen zusammen mit GetterMethoden, um an die Listen der anderen Klasse zu gelangen
    private boolean sichtbaralter;
    private boolean sichtbarrasse;
    private boolean genauesfiltern = false;
    // </editor-fold>

    // <editor-fold desc="Konstruktor">
    public Zimmerpartner(Hundepension hundepension){
        this.hundepension = hundepension; //initialisieren des Objektes "hundepension" der Klasse "Hundepension"

        // <editor-fold desc="Erstellen des Fensters">
        setTitle(""); //Fenstertitel
        setDefaultCloseOperation(EXIT_ON_CLOSE); //bei Knopfdruck auf das rote x, soll das Fenster geschlossen werden
        setContentPane(zimmmerpartner_panel); //Platzieren des Panels, auf welchem alle GUI-Elemente platziert sind im Fenster
        setSize(1500,1500); //Größe des Fensters
        setVisible(true); //Sichtbarkeit des Fensters
        // </editor-fold desc>

        // <editor-fold desc="Einrichten der Hund-&Partnerauswahl Comboboxen">
        // Aufrufen der Methoden, mit welchen die Comboboxen eingrichtet werden, mit denen unser eingegebener Hund und der Partner ausgewählt wird
        setEingabeHundeauswaehlen();
        setZimmerpartnerwaehlencombobox();
        // </editor-fold desc>

        // <editor-fold desc="Anzeigen der Hunde in der Ausgabefläche">
        //Vom Beginn an alle Hunde in der Textarea anzeigen
        for (Hund hund: hundepension.getHundeimhotel()){ //geht alle Hunde der hundeimHotel-Liste durch
            String hundausgabe = hund.ausgeben(); //aufrufen der ausgeben Methode aus der Hunde Klasse am soeben erstellten Objekt und speichern in dem String "hundausgabe"
            filterausgabe_textarea.setText(filterausgabe_textarea.getText() + hundausgabe); // hinzufügen des Objektes zur textarea
        }
        // </editor-fold>

        // <editor-fold desc="Verstecken bestimmter GUI-Elemente ">
        // boolean "sichtbaralter" & "sichtbarrasse" werden erstellt und grundsätzlich auf false gesetzt (bestimmte textfields/comboboxen sollen erst durch drücken von radiobuttons sichtbar werden)
        sichtbaralter = false;
        alterfilter_textfield.setVisible(sichtbaralter);
        alterfilter_combobox.setVisible(sichtbaralter);
        sichtbarrasse = false;
        rassefilter_textfield.setVisible(sichtbarrasse);
        rassefilterinfo_label.setVisible(sichtbarrasse);
        // </editor-fold>

        // <editor-fold desc="Filter ActionListener ">
        //ActionListener für den Radiobutton, der bestimmt ob durch alle Hunde oder nur die Hunde, die einen Partner suchen gefiltert wird
        genauesFiltern_RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden/ radio button an: genauesfiltern = true/ radio button aus: genauesfiltern = false
                if (genauesfiltern == false){
                    genauesfiltern = true;
                }else{
                    genauesfiltern = false;
                }// genau gefiltert wird nur, wenn der radiobutton an ist/genauesfiltern = true ist
            }
        });
        //ActionListener für die Sichtbarkeitsradiobuttons
        egalrasse_RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden
                if (sichtbarrasse == false){
                    sichtbarrasse = true;
                }else{
                    sichtbarrasse = false;
                }
                // Das textfield und das dazugehörige label werden nur angezeigt, wenn der radiobutton aus ist/sichtbar = false ist
                rassefilter_textfield.setVisible(sichtbarrasse);
                rassefilterinfo_label.setVisible(sichtbarrasse);
            }
        });
        alteregal_RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden/ radio button an: sichtbar = false/ radio button aus: sichtbar = true
                if (sichtbaralter == false){
                    sichtbaralter = true;
                }else{
                    sichtbaralter = false;
                }
                // Das textfield und die combobox werden nur angezeigt, wenn der radiobutton aus ist/sichtbar = false ist
                alterfilter_textfield.setVisible(sichtbaralter);
                alterfilter_combobox.setVisible(sichtbaralter);
            }
        });
        //ActionListener für die Filter-Textfelder
        rassefilter_textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        alterfilter_textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //ActionListener für die Filter-Comboboxen
        groessefilter_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        alterfilter_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        geschlechtfilter_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        kastriertfilter_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //ActionListener für den Knopf, welcher das Filtern einleitet
        filtern_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genauesfiltern == false){
                    filtern(); // Wenn der genauesFilternradiobutton (also nur Hunde, die einen Zimmerpartner suchen) deaktiviert ist, wird die normale "filtern" Methode aufgerufen
                }else{ //sonst (also falls er aktiviert ist) wird die "partnerfiltern" Methode aufgerufen
                    partnerfiltern();
                }
            }
        });
        // </editor-fold>
        
        // <editor-fold desc="Partnerzuweisung ActionListener">
        //ActionListener für die Hund-&Partnerauswahl comboboxen
        eingabeHundeauswaehlen_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        zimmerpartnerwaehlen_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //ActionListener des Knopfes zum zuweisen der Hunde
        zuweisen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen_combobox.getSelectedItem().toString();
                String partnerhund = zimmerpartnerwaehlen_combobox.getSelectedItem().toString();
                if (meinhund.isEmpty() || partnerhund.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Bitte Ihren Hund und einen weiteren Hund für das Doppelzimmer auswählen");
                    return;}
                if (partnerhund.equals(meinhund)){ //falls 2x der gleiche Name ausgewählt wurde:
                    JOptionPane.showMessageDialog(null,"Sie können Ihren Hund nicht mit sich selbst in ein Zimmer stecken"); //Fehlermeldung
                    zimmerpartnerwaehlen_combobox.setSelectedIndex(0); // zimmerpartnercombobox wird zurück gesetzt
                    return;}
                for (Hund hund: hundepension.getHundeimhotel()) { //geht alle Hunde in der Pension durch
                    if(hund.getHundename().equals(meinhund)){ //wenn der Hund dem Hund aus der Combobox "eingabeHundeauswaehlen"(also dem String "meinhund") entspricht
                        hund.setZimmerpartner(partnerhund); //wird dem Hund, der in der anderen Combobox ausgewaehlte Hund (partnerhund) zugeordnet (die Methode setZimmerpartner aus der "Hunde" Klasse wird aufgerufen)
                        eingabeHundeauswaehlen_combobox.removeItem(meinhund); //Der hund wird aus der "eingabeHundeauswaehlen" combobox entfernt
                        eingabeHundeauswaehlen_combobox.setSelectedIndex(0); //Die Combobox wird wieder auf den erste Auswahl, also "" gesetzt
                        zimmerpartnerwaehlen_combobox.removeItem(meinhund); //der hund wird ebenso aus der zimmerpartnerwaehlencombobox entfernt, sodass es nicht dazu kommen kann, dass er doppelt zugewiesen wird
                        }
                }
                for (Hund hund: hundepension.getHundeimhotel()) { //geht erneut alle Hunde in der Pension durch
                    if (hund.getHundename().equals(partnerhund)) { //wenn der Hund dem Hund aus der Combobox "zimmerpartnerwaehlencombobox"(also dem String partnerhund) entspricht
                        hund.setZimmerpartner(meinhund); //wird dem partnerhund ebenso unser Hund als Partner zugewiesen
                        zimmerpartnerwaehlen_combobox.removeItem(partnerhund); //Der partnerhund wird aus der combobox entfernt
                        zimmerpartnerwaehlen_combobox.setSelectedIndex(0); //Die Combobox wird wieder auf die erste Auswahl (also "") gesetzt
                        eingabeHundeauswaehlen_combobox.removeItem(partnerhund); // der hund wird auch aus der eingabeHundeauswaehlen combobox entfernt, sodass es nicht dazu kommen kann, dass er doppelt zugewiesen wird
                                                                        // (nur notwendig falls wir 2 von uns eingegebenen Hunden gemeinsam in ein Zimmer stecken)

                    }
                }
                filterausgabe_textarea.setText(""); //die ausgabe textArea wird zurückgesetzt
                for (Hund hund: hundepension.getHundeimhotel()){ //geht alle Hunde durch
                    if (!hund.getZimmerpartner().isEmpty()){ //falls zimmerpartner nicht leer ist (der Hund also einen Zimmerpartner hat)
                        String hundausgabe = hund.ausgeben(); // aufrufen der "ausgeben" Methode aus der Klasse "Hunde" an dem "hund" Objekt und in dem String "hundausgabe" speichern
                        filterausgabe_textarea.setText(filterausgabe_textarea.getText() + hundausgabe); //hundausgabe zur Textarea hinzufügen
                    }
                }
                //Zurücksetzen der Filter
                rassefilter_textfield.setText("");
                groessefilter_combobox.setSelectedIndex(0);
                alterfilter_combobox.setSelectedIndex(0);
                alterfilter_textfield.setText("");
                geschlechtfilter_combobox.setSelectedIndex(0);
                kastriertfilter_combobox.setSelectedIndex(0);
                egalrasse_RadioButton.setSelected(true);
                sichtbarrasse = false;
                rassefilter_textfield.setVisible(sichtbarrasse);
                rassefilterinfo_label.setVisible(sichtbarrasse);
                alteregal_RadioButton.setSelected(true);
                sichtbaralter = false;
                alterfilter_textfield.setVisible(sichtbaralter);
                alterfilter_combobox.setVisible(sichtbaralter);
            }

        });
        // </editor-fold>
    }
    // </editor-fold desc>

    // <editor-fold desc="Filtermethoden">
    //Hauptmethoden zum filtern oder speziellen Filtern
    public void filtern(){
        //Genreller Filter, filtert durch alle Hunde in der Pension
        ArrayList<Hund> gefilterteHunde = new ArrayList<>(); //gefilterte Liste die Schlussendlich in der textarea ausgegeben wird
        String groesse = groessefilter_combobox.getSelectedItem().toString();
        String geschlecht = geschlechtfilter_combobox.getSelectedItem().toString();
        String kastriertstring = kastriertfilter_combobox.getSelectedItem().toString();

        //geht alle Hunde in der Pension durch und immer wenn eine der filtermethoden nicht zutrifft wird das Filtern am nächsten Objekt(nächsten hund) fortgesetzt
        for (Hund hund: hundepension.getHundeimhotel()){
            if (rassefiltern(hund) == false){
                continue;} //beendet den vorgang für das Objekt und springt zum nächsten in der Liste
            if (groessefiltern(hund, groesse) == false){
                continue;}
            if (geschlechtfiltern(hund, geschlecht) == false){
                continue;}
            if (kastriertfiltern(hund, kastriertstring) == false){
                continue;}
            if(alterfiltern(hund) == false){
                continue;}

            //Der Hund erfüllt alle Filterkriterien ->
            gefilterteHunde.add(hund); //wird zur Liste hinzugefügt
        }
        if (gefilterteHunde.isEmpty()){ //falls kein Hund durch die Filterkriterien gekommen ist -> Liste ist leer
            filterausgabe_textarea.setText("Keine Hunde entsprechen den ausgewählten Kriterien."); //ausgabe
        } else{ // falls Liste nicht leer
            filterausgabe_textarea.setText(""); //zurücksetzen der Textarea
            for (Hund hund: gefilterteHunde){ //geht alle Objekte "hund" die Liste durch
                String hundausgabe = hund.ausgeben(); //ruft die aufgeben Methode am objekt hund auf
                filterausgabe_textarea.setText(filterausgabe_textarea.getText() + hundausgabe); //fügt den Hund zur textarea hinzu
            }
        }gefilterteHunde.clear(); //die gefilterteHunde Liste wird zurückgesetzt
    }
    public void partnerfiltern(){
        //Quasi der gleiche ablauf wie bei der filtern Methode, nur mit dem Unterschied, dass noch danach gefiltert wird, dass der Hund in einem Doppelzimmer ist (Einzelzimmer = false) und er noch keinen Zimmerpartner hat.
        ArrayList<Hund> gefilterteHunde = new ArrayList<>(); //Die gefilterte Liste die am Ende in der textarea ausgegeben wird
        String groesse = groessefilter_combobox.getSelectedItem().toString();
        String geschlecht = geschlechtfilter_combobox.getSelectedItem().toString();
        String kastriertstring = kastriertfilter_combobox.getSelectedItem().toString();

        //geht alle Hunde in der Pension durch und immer wenn eine der filtermethoden nicht zutrifft wird das Filtern am nächsten Objekt(nächsten hund) fortgesetzt
        for (Hund hund: hundepension.getHundeimhotel()){
            if (hund.getEinzelzimmer()==true){
                continue;}
            if (!hund.getZimmerpartner().isEmpty()){ //Falls zimmerpartner NICHT leer ist
                continue;}
            if (rassefiltern(hund) == false){
                continue;}
            if (groessefiltern(hund, groesse) == false){
                continue;}
            if (geschlechtfiltern(hund, geschlecht) == false){
                continue;}
            if (kastriertfiltern(hund, kastriertstring) == false){
                continue;}
            if(alterfiltern(hund) == false){
                continue;}

            //Der Hund erfüllt alle Filterkriterien ->
            gefilterteHunde.add(hund);
        }
        if (gefilterteHunde.isEmpty()){
            filterausgabe_textarea.setText("Keine Hunde entsprechen den ausgewählten Kriterien.");
        } else{
            filterausgabe_textarea.setText("");
            for (Hund hund: gefilterteHunde){
                String hundausgabe = hund.ausgeben();
                filterausgabe_textarea.setText(filterausgabe_textarea.getText() + hundausgabe);
            }
        }gefilterteHunde.clear();
    }
    //Methode zum aufteilen des Strings des Rassetextfeldes
    public String[] splitrasseeingabe() {
        return rassefilter_textfield.getText().split(", "); //teilt den String auf immer wenn ein Komma und ein Leerzeichen eingetippt werden.
    }
    //Filtermthoden welche in den Hauptfiltermethoden aufgerufen werden
    private boolean rassefiltern(Hund hund){
        String[] rassefilterliste = splitrasseeingabe(); //erstellen eines Array, in welchem die einzelnen Bruchstücke/Rassen gespeichert werden
        if (sichtbarrasse == false) {
            return true;} //falls sichtbarrasse falsch ist, ist der egalrasseradiobutton aktiviert und somit ist jeder Hund "richtig"
        boolean rassepasst = false; //initialisieren des rassepasst boolean als falsch
        for (int i = 0; i < rassefilterliste.length && rassepasst == false; i++) { //Schleife, welche den Array durchgeht
            if(rassefilterliste[i].equals(hund.getRasse())){ //immer wenn die eingegebene Rasse der Rasse des hundes entspricht wird rassepasst auf "richtig" gesetzt
                rassepasst = true;
            }
        }
        return rassepasst; // rassepasst wird ausgegeben, falls filterkriterien zugetroffen haben: rassepasst == true sonst bleibt rassepasst false
    }
    private boolean groessefiltern(Hund hund, String groesse){
        return groesse.equals("Egal") || hund.getGroesse().equals(groesse); //Die Methode gibt den wert true aus, falls das gefilterte zum hund passt
        }
    private boolean geschlechtfiltern(Hund hund, String geschlecht){
        return geschlecht.equals("Egal") || hund.getGeschlecht().equals(geschlecht);//Die Methode gibt true aus, falls das gefilterte zum hund passt
    }
    private boolean kastriertfiltern(Hund hund, String kastriertstring) {
        boolean kastriert = !kastriertstring.equals("Nein"); //kastriert ist true, wenn nicht "Nein" (also "Ja" oder "Egal") ausgewählt wird, sonst false
        return kastriertstring.equals("Egal") || hund.istKastriert() == kastriert; //Die Methode gibt true aus, falls das gefilterte zum hund passt
    }
    private boolean alterfiltern(Hund hund) {
        if (sichtbaralter == false) {
            return true;} //Wenn der radio button "Egal" aktiviert ist, gibt die Methode direkt true aus
        boolean alterpasst = false;
        double alter;
        if (!alterfilter_textfield.getText().isEmpty()) { //falls das alter Textfeld nicht leer ist
            try {
                alter = Double.parseDouble(alterfilter_textfield.getText()); //umwandlung in double Wert
                if (hund.getAlter() == alter) { // Wenn das alter mit dem gefilterten Wert übereinstimmt
                    alterpasst = true; //Methode gibt true aus
                }
            } catch (NumberFormatException e) {
                //Fehleingaben werden einfach ignoriert
            }}
        String altersbereich = alterfilter_combobox.getSelectedItem().toString();
        if (!altersbereich.isEmpty()) { // alters combobox nicht leer, also ein wert "altersbereich" ausgewählt
            if (altersbereich.equals("1-5") && (hund.getAlter() >= 1 && hund.getAlter() <= 5)) {
                //falls in der combobox 1-5 ausgewählt wurde und das alter hundes größer/gleich 1 und kleiner/gleich 5 ist
                alterpasst = true;
            } else if (altersbereich.equals("6-10") && (hund.getAlter() >= 6 && hund.getAlter() <= 10)) {
                //falls in der combobox 6-10 ausgewählt wurde und das alter hundes nicht 6, 7, 8, 9 oder 10 ist
                alterpasst = true;
            } else if (altersbereich.equals("11-15") && (hund.getAlter() >= 11 && hund.getAlter() <= 15)) {
                //falls in der combobox 11-15 ausgewählt wurde und das alter hundes nicht 11, 12, 13, 14 oder 15 ist
                alterpasst = true;
            } else if (alterfilter_combobox.getSelectedItem().toString().equals("16-20") && (hund.getAlter() >= 16 && hund.getAlter() <= 20)) {
                //falls in der combobox 6-10 ausgewählt wurde und das alter hundes nicht 16, 17, 18, 19 oder 20 ist
                alterpasst = true;
            }
        }return alterpasst;
    }
    // </editor-fold desc>

    // <editor-fold desc="Methoden zum Füllen der Hund-&Partnerauswahl Comboboxen">
    private void setEingabeHundeauswaehlen(){
        eingabeHundeauswaehlen_combobox.addItem("");
        eingabeHundeauswaehlen_combobox.setSelectedIndex(0);
        for (Hund hund: hundepension.getSuchtpartnerListe()){
            eingabeHundeauswaehlen_combobox.addItem(hund.getHundename());
        }
    }
    private void setZimmerpartnerwaehlencombobox(){
        zimmerpartnerwaehlen_combobox.addItem("");
        zimmerpartnerwaehlen_combobox.setSelectedIndex(0);
        for (Hund hund: hundepension.getHundeimhotel()){
            if (hund.getZimmerpartner().isEmpty() && hund.getEinzelzimmer() == false){
                zimmerpartnerwaehlen_combobox.addItem(hund.getHundename());}
        }
    }
    // </editor-fold desc>
}