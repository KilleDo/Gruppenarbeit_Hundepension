package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// <editor-fold desc="">
public class Zimmerpartner extends JFrame {
    private JPanel zimmmerpartnerpanel;
    private JTextField rassefiltertextfield;
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
    private boolean sichtbaralter;
    private boolean sichtbarrasse;
    private boolean genauesfiltern = false;
    private JComboBox eingabeHundeauswaehlen; // Combobox in welcher wir einen von uns zuvor in der Hundepension eingegeben Hund auswaehlen
    private JRadioButton genauesFilternradiobutton;
    private JComboBox zimmerpartnerwaehlencombobox;
    private JButton zuweisenbutton;
    private JRadioButton egalrasseRadioButton;
    private JLabel rassefilterinfolabel;
    private Hundepension hundepension; //hier wird ein Objekt der Hundepension erstellt, um die Listen zu erhalten

    //private ArrayList<Hunde> meinHundListe;
    //private ArrayList<Hunde> zugewiesenListe;
    // </editor-fold>
    //Konstruktor
    public Zimmerpartner(Hundepension hundepension) {
        this.hundepension = hundepension; //initialisieren
        setTitle("Doppelzimmer ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(zimmmerpartnerpanel);
        setSize(1500, 1500);
        setVisible(true);

        //Aufrufen der Methoden, mit welchen die Comboboxen eingreichtet werden, mit denen unser eingegebener Hund und der Partner ausgewählt wird
        setEingabeHundeauswaehlen();
        setZimmerpartnerwaehlencombobox();

        //Vom Beginn an alle Hunde in der Textarea anzeigen
        for (Hunde hund : hundepension.getHundeimhotel()) { //geht alle Hunde der hundeimHotel-Liste durch
            String hundausgabe = hund.ausgeben(); //aufrufen der ausgeben Methode aus der Hunde Klasse am soeben erstellten Objekt und speichern in dem String "hundausgabe"
            filterausgabe.setText(filterausgabe.getText() + hundausgabe); // hinzufügen des Objektes zur textarea
        }

        //boolean "sichtbaralter" & "sichtbarrasse" werden erstellt und grundsätzlich auf false gesetzt (bestimmte textfields/comboboxen sollen erst durch drücken von radiobuttons sichtbar werden)
        sichtbaralter = false;
        alterfiltertextfield.setVisible(sichtbaralter);
        alterfiltercombobox.setVisible(sichtbaralter);
        sichtbarrasse = false;
        rassefiltertextfield.setVisible(sichtbarrasse);
        rassefilterinfolabel.setVisible(sichtbarrasse);


        eingabeHundeauswaehlen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                if (meinhund.isEmpty()) {
                    return;
                }
                //eingabeHundeauswaehlen.setEnabled(false);
                /*for (Hunde hund: hundepension.getSuchtpartnerListe()){
                    if (hund.getHundename().equals(meinhund)) {
                        hundepension.getSuchtpartnerListe().remove(hund);
                        meinHundListe.add(hund);
                    }
                }*/
            }
        });
        egalrasseRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden
                if (sichtbarrasse == false) {
                    sichtbarrasse = true;
                } else {
                    sichtbarrasse = false;
                }
                // Das textfield und das dazugehörige label werden nur angezeigt, wenn der radiobutton aus ist/sichtbar = false ist
                rassefiltertextfield.setVisible(sichtbarrasse);
                rassefilterinfolabel.setVisible(sichtbarrasse);
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
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden/ radio button an: sichtbar = false/ radio button aus: sichtbar = true
                if (sichtbaralter == false) {
                    sichtbaralter = true;
                } else {
                    sichtbaralter = false;
                }
                // Das textfield und die combobox werden nur angezeigt, wenn der radiobutton aus ist/sichtbar = false ist
                alterfiltertextfield.setVisible(sichtbaralter);
                alterfiltercombobox.setVisible(sichtbaralter);
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
                if (genauesfiltern == false) {
                    filtern(); // Wenn der genauesFilternradiobutton (also nur Hunde, die einen Zimmerpartner suchen) deaktiviert ist, wird die normale "filtern" Methode aufgerufen
                } else { //sonst (also falls er aktiviert ist) wird die "partnerfiltern" Methode aufgerufen
                    partnerfiltern();
                }
            }
        });
        genauesFilternradiobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Radiobutton gedrückt wird, soll der boolean gewechselt werden/ radio button an: genauesfiltern = true/ radio button aus: genauesfiltern = false
                if (genauesfiltern == false) {
                    genauesfiltern = true;
                } else {
                    genauesfiltern = false;
                }// genau gefiltert wird nur, wenn der radiobutton an ist/genauesfiltern = true ist
            }
        });
        zimmerpartnerwaehlencombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString(); //entwerten der Combobox
                String partnerhund = zimmerpartnerwaehlencombobox.getSelectedItem().toString(); //entwerten der Partnercombobox
                if (partnerhund.equals(meinhund)) { //falls 2x der gleiche Name ausgewählt wurde:
                    JOptionPane.showMessageDialog(null, "Sie können Ihren Hund nicht mit sich selbst in ein Zimmer stecken"); //Fehlermeldung
                    zimmerpartnerwaehlencombobox.setSelectedIndex(0); // zimmerpartnercombobox wird zurück gesetzt
                }
            }
        });
        zuweisenbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                String partnerhund = zimmerpartnerwaehlencombobox.getSelectedItem().toString();
                if (meinhund.isEmpty() || partnerhund.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bitte Ihren Hund und einen weiteren Hund für das Doppelzimmer auswählen");
                    return;
                }
                for (Hunde hund : hundepension.getHundeimhotel()) {
                    if (hund.getHundename().equals(partnerhund)) {
                        hundepension.getSuchtpartnerListe().remove(hund);
                        hund.setZimmerpartner(meinhund);
                    }
                }
                for (Hunde hund : hundepension.getHundeimhotel()) {
                    if (hund.getHundename().equals(meinhund)) {
                        hund.setZimmerpartner(partnerhund);
                    }
                }
                for (Hunde hund : hundepension.getHundeimhotel()) {
                    if (!hund.getZimmerpartner().isEmpty()) {
                        filterausgabe.setText("");
                        String hundausgabe = hund.ausgeben();
                        filterausgabe.setText(filterausgabe.getText() + hundausgabe);
                    }
                }
                setEingabeHundeauswaehlen();
                setZimmerpartnerwaehlencombobox();
            }

        });
    }

    public void filtern() {
        //Genreller Filter, filtert durch alle Hunde in der Pension
        ArrayList<Hunde> gefilterteHunde = new ArrayList<>(); //gefilterte Liste die Schlussendlich in der textarea ausgegeben wird
        String groesse = groessefiltercombobox.getSelectedItem().toString();
        String geschlecht = geschlechtfiltercombobox.getSelectedItem().toString();
        String kastriertstring = kastriertfiltercombobox.getSelectedItem().toString();
        double alter;
        boolean alterpasst = true; //initialisieren, Wert wird in Methode "alterfiltern" überschrieben

        for (Hunde hund : hundepension.getHundeimhotel()) {
            if (rassefiltern(hund) == false) {
                continue;
            }
            if (groessefiltern(hund, groesse) == false) {
                continue;
            }
            if (geschlechtfiltern(hund, geschlecht) == false) {
                continue;
            }
            if (kastriertfiltern(hund, kastriertstring) == false) {
                continue;
            }
            if (alterfiltern(hund) == false) {
                continue;
            }

            //Der Hund erfüllt alle Filterkriterien ->
            gefilterteHunde.add(hund); //wird zur Liste hinzugefügt
        }
        if (gefilterteHunde.isEmpty()) { //falls kein Hund durch die Filterkriterien gekommen ist -> Liste ist leer
            filterausgabe.setText("Keine Hunde entsprechen den ausgewählten Kriterien."); //ausgabe
        } else { // falls Liste nicht leer
            filterausgabe.setText(""); //zurücksetzen der Textarea
            for (Hunde hund : gefilterteHunde) { //geht alle Objekte "hund" die Liste durch
                String hundausgabe = hund.ausgeben(); //ruft die aufgeben Methode am objekt hund auf
                filterausgabe.setText(filterausgabe.getText() + hundausgabe); //fügt den Hund zur textarea hinzu
            }
        }
        gefilterteHunde.clear(); //die gefilterteHunde Liste wird zurükgesetzt
    }

    public void partnerfiltern() {
        //Quasi der gleiche ablauf wie bei der filtern Methode, nur mit dem Unterschied, dass noch danach gefiltert wird, dass der Hund in einem Doppelzimmer ist (Einzelzimmer = false) und er noch keinen Zimmerpartner hat.
        ArrayList<Hunde> gefilterteHunde = new ArrayList<>(); //Die gefilterte Liste die am Ende in der textarea ausgegeben wird
        String groesse = groessefiltercombobox.getSelectedItem().toString();
        String geschlecht = geschlechtfiltercombobox.getSelectedItem().toString();
        String kastriertstring = kastriertfiltercombobox.getSelectedItem().toString();

        for (Hunde hund : hundepension.getHundeimhotel()) {
            if (hund.getEinzelzimmer() == true) {
                continue;
            }
            if (!hund.getZimmerpartner().isEmpty()) {
                continue;
            }
            if (rassefiltern(hund) == false) {
                continue;
            }
            if (groessefiltern(hund, groesse) == false) {
                continue;
            }
            if (geschlechtfiltern(hund, geschlecht) == false) {
                continue;
            }
            if (kastriertfiltern(hund, kastriertstring) == false) {
                continue;
            }
            if (alterfiltern(hund) == false) {
                continue;
            }

            //Der Hund erfüllt alle Filterkriterien ->
            gefilterteHunde.add(hund);
        }
        if (gefilterteHunde.isEmpty()) {
            filterausgabe.setText("Keine Hunde entsprechen den ausgewählten Kriterien.");
        } else {
            filterausgabe.setText("");
            for (Hunde hund : gefilterteHunde) {
                String hundausgabe = hund.ausgeben();
                filterausgabe.setText(filterausgabe.getText() + hundausgabe);
            }
        }
        gefilterteHunde.clear();
    }

    public String[] splitrasseeingabe() {
        return rassefiltertextfield.getText().split(", "); //teilt den String auf immer wenn ein Komma und ein Leerzeichen eingetippt werden.
    }

    private boolean rassefiltern(Hunde hund) {
        String[] rassefilterliste = splitrasseeingabe(); //erstellen eines Array, in welchem die einzelnen Bruchstücke/Rassen gespeihert werden
        if (sichtbarrasse == false) {
            return true;
        } //falls sichtbarrasse falsch ist, ist der egalrasseradiobutton aktiviert und somit ist jeder Hund "richtig"
        boolean rassepasst = false; //initialisieren des rassepasst boolean als falsch
        for (int i = 0; i < rassefilterliste.length && rassepasst == false; i++) { //Schleife, welche den Array durchgeht
            if (rassefilterliste[i].equals(hund.getRasse())) { //immer wenn die eingegebene Rasse der Rasse des hundes entspricht wird rassepasst auf "richtig" gesetzt
                rassepasst = true;
            }
        }
        return rassepasst; // rassepasst wird ausgegeben, falls filterkriterien zugetroffen haben: rassepasst == true sonst bleibt rassepasst false
    }

    private boolean groessefiltern(Hunde hund, String groesse) {
        return groesse.equals("Egal") || hund.getGroesse().equals(groesse); //Die Methode gibt den wert true aus, falls das gefilterte zum hund passt
    }

    private boolean geschlechtfiltern(Hunde hund, String geschlecht) {
        return geschlecht.equals("Egal") || hund.getGeschlecht().equals(geschlecht);//Die Methode gibt true aus, falls das gefilterte zum hund passt
    }

    private boolean kastriertfiltern(Hunde hund, String kastriertstring) {
        boolean kastriert = !kastriertstring.equals("Nein"); //kastriert ist true, wenn nicht-nein (also ja oder egal) ausgewählt wird, sonst false
        return kastriertstring.equals("Egal") || hund.istKastriert() == kastriert; //Die Methode gibt true aus, falls das gefilterte zum hund passt
    }

    private boolean alterfiltern(Hunde hund) {
        if (sichtbaralter == false) {
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
            }
        }
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
            } else {
                alterpasst = false;
            }
        }
        return alterpasst;
    }

    private void setEingabeHundeauswaehlen() {
        eingabeHundeauswaehlen.removeAllItems(); //Combobox wird zurück gesetzt
        eingabeHundeauswaehlen.addItem("");
        eingabeHundeauswaehlen.setSelectedIndex(0);
        for (Hunde hund : hundepension.getSuchtpartnerListe()) {
            eingabeHundeauswaehlen.addItem(hund.getHundename());
        }
        eingabeHundeauswaehlen.revalidate(); // GUI-Update erzwingen
        eingabeHundeauswaehlen.repaint();    // GUI neu rendern
    }

    private void setZimmerpartnerwaehlencombobox() {
        zimmerpartnerwaehlencombobox.removeAllItems();
        zimmerpartnerwaehlencombobox.addItem("");
        zimmerpartnerwaehlencombobox.setSelectedIndex(0);
        for (Hunde hund : hundepension.getHundeimhotel()) {
            if (hund.getZimmerpartner().isEmpty() && hund.getEinzelzimmer() == false) {
                zimmerpartnerwaehlencombobox.addItem(hund.getHundename());
            }
        }
        //zimmerpartnerwaehlencombobox.revalidate(); // GUI-Update erzwingen
        //zimmerpartnerwaehlencombobox.repaint();    // GUI neu rendern
    }

}
