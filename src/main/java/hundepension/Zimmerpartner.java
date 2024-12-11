package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// <editor-fold desc="">
public class Zimmerpartner extends JFrame{
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
    private JRadioButton nurDoppelzimmerradiobutton;
    private JComboBox zimmerpartnerwaehlencombobox;
    private JButton zuweisenbutton;
    private JRadioButton egalrasseRadioButton;
    private JLabel rassefilterinfolabel;
    private Hundepension hundepension; //hier wird ein Objekt der Hundepension erstellt, um die Listen zu erhalten
    // </editor-fold>
    //Konstruktor
    public Zimmerpartner(Hundepension hundepension){
        this.hundepension = hundepension; //initialisieren
        setTitle("Doppelzimmer ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(zimmmerpartnerpanel);
        setSize(1500,1500);
        setVisible(true);

        //Hier wird der Inhalt der Combobox, mit welcher unser eingegebener Hund ausgewählt wird und die Combobox, mit welcher der Partner ausgewählt wird, hinzugefügt
       /* eingabeHundeauswaehlen.addItem("");
        for (Hunde hund: hundepension.getSuchtpartnerListe()){
            eingabeHundeauswaehlen.addItem(hund.getHundename());}
        zimmerpartnerwaehlencombobox.addItem("");
        for (Hunde hund: hundepension.getHundeimhotel()){
            if (hund.getZimmerpartner().isEmpty()){
                zimmerpartnerwaehlencombobox.addItem(hund.getHundename());
            }
        }*/
        setEingabeHundeauswaehlen();
        setZimmerpartnerwaehlencombobox();


        //sollen erst durch drücken des radiobuttons sichtbar werden
        sichtbaralter = false;
        alterfiltertextfield.setVisible(sichtbaralter);
        alterfiltercombobox.setVisible(sichtbaralter);
        sichtbarrasse = false;
        rassefiltertextfield.setVisible(sichtbarrasse);
        rassefilterinfolabel.setVisible(sichtbarrasse);


        ArrayList<Hunde> meinHundListe = new ArrayList<>(); //erstellen einer Liste in welcher immer nur der aktuelle Hund, für welchen ein Zimmerpartner gesucht wird, gespeichert ist
        ArrayList<Hunde> zugewiesenListe = new ArrayList<>();

        eingabeHundeauswaehlen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                if (meinhund.isEmpty()){
                    return;
                }
                eingabeHundeauswaehlen.setEnabled(false);
                for (Hunde hund: hundepension.getSuchtpartnerListe()){
                    if (hund.getHundename().equals(meinhund)) {
                        hundepension.getSuchtpartnerListe().remove(hund);
                        meinHundListe.add(hund);
                    }
                }
            }
        });
        egalrasseRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sichtbarrasse == false){
                    sichtbarrasse = true;
                }else{
                    sichtbarrasse = false;
                }
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
                if (sichtbaralter == false){
                    sichtbaralter = true;
                }else{
                    sichtbaralter = false;
                }
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
                String filterkastriert = kastriertfiltercombobox.getSelectedItem().toString();

            }
        });
        filternButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genauesfiltern == false){
                    filtern();
                }else{
                    partnerfiltern();
                }
            }
        });
        nurDoppelzimmerradiobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genauesfiltern == false){
                    genauesfiltern = true;
                }else{
                    genauesfiltern = false;
                }
            }
        });
        zimmerpartnerwaehlencombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                String partnerhund = zimmerpartnerwaehlencombobox.getSelectedItem().toString();
                if (partnerhund.equals(meinhund)){
                    JOptionPane.showMessageDialog(null,"Sie können Ihren Hund nicht mit sich selbst in ein Zimmer stecken");
                    zimmerpartnerwaehlencombobox.setSelectedIndex(0);
                }
            }
        });
        zuweisenbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eingabeHundeauswaehlen.setEnabled(true);
                String meinhund = eingabeHundeauswaehlen.getSelectedItem().toString();
                String partnerhund = zimmerpartnerwaehlencombobox.getSelectedItem().toString();
                if (meinhund.isEmpty() || partnerhund.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Bitte Ihren Hund und einen weiteren Hund für das Doppelzimmer auswählen");
                    return;}
                for (Hunde hund: hundepension.getSuchtpartnerListe()){
                    if(hund.getHundename().equals(partnerhund)){
                        hundepension.getSuchtpartnerListe().remove(hund);
                        zugewiesenListe.add(hund);
                    }}
                String hundename = meinhund;
                String rasse = meinHundListe.getFirst().getRasse();
                String groesse = meinHundListe.getFirst().getGroesse();
                double alter = meinHundListe.getFirst().getAlter();
                String geschlecht = meinHundListe.getFirst().getGeschlecht();
                boolean kastriert = meinHundListe.getFirst().isKastriert();
                String probleme = meinHundListe.getFirst().getProbleme();
                String weitereInfos = meinHundListe.getFirst().getWeitereInfos();
                boolean einzelzimmer = meinHundListe.getFirst().getEinzelzimmer();
                String zimmerpartner = " Zimmerpartner: "+partnerhund;
                Hunde upadtedhund = new Hunde(hundename, rasse, groesse, alter, geschlecht, kastriert, probleme, weitereInfos, einzelzimmer, zimmerpartner);
                for (Hunde oldhund : hundepension.getHundeimhotel()){
                    if(oldhund.getHundename().equals(meinhund)){
                        hundepension.getHundeimhotel().remove(oldhund);
                        meinHundListe.remove(oldhund);
                        hundepension.getHundeimhotel().add(upadtedhund);
                    }}
                hundename = partnerhund;
                rasse = zugewiesenListe.getFirst().getRasse();
                groesse = zugewiesenListe.getFirst().getGroesse();
                alter = zugewiesenListe.getFirst().getAlter();
                geschlecht = zugewiesenListe.getFirst().getGeschlecht();
                kastriert = zugewiesenListe.getFirst().isKastriert();
                probleme = zugewiesenListe.getFirst().getProbleme();
                weitereInfos = zugewiesenListe.getFirst().getWeitereInfos();
                einzelzimmer = zugewiesenListe.getFirst().getEinzelzimmer();
                zimmerpartner = " Zimmerpartner: "+eingabeHundeauswaehlen.getSelectedItem().toString(); //funktioniert aus irgendeinem Grund noch nicht
                Hunde updatedhundpartner = new Hunde(hundename, rasse, groesse, alter, geschlecht, kastriert, probleme, weitereInfos, einzelzimmer, zimmerpartner);
                for (Hunde oldhundpartner : hundepension.getHundeimhotel()){
                    if (oldhundpartner.getHundename().equals(partnerhund)) {
                        hundepension.getHundeimhotel().remove(oldhundpartner);
                        zugewiesenListe.remove(oldhundpartner);
                        hundepension.getHundeimhotel().add(updatedhundpartner);
                    }}
                if (!hundepension.getSuchtpartnerListe().isEmpty()){
                    setEingabeHundeauswaehlen();
                    /*eingabeHundeauswaehlen.addItem("");
                    for (Hunde hund: hundepension.getSuchtpartnerListe()){
                        eingabeHundeauswaehlen.addItem(hund.getHundename());}*/
                    setZimmerpartnerwaehlencombobox();
                    /*zimmerpartnerwaehlencombobox.addItem("");
                    for (Hunde hund: hundepension.getHundeimhotel()){
                        if (hund.getZimmerpartner().isEmpty()){
                            zimmerpartnerwaehlencombobox.addItem(hund.getHundename());}
                    }*/
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
            filterausgabe.setText("Keine Hunde entsprechen den ausgewählten Kriterien.");
        } else{
            filterausgabe.setText("");
            for (Hunde hund: gefilterteHunde){
                String hundausgabe = hund.ausgeben();
                filterausgabe.setText(filterausgabe.getText() + hundausgabe);
            }
        }gefilterteHunde.clear();
    }
    public void partnerfiltern(){
        //Spezieller Filter, filtert durch alle Hunde in der Pension die noch keinen Zimmerpartner haben und in ein Doppelzimmer wollen.
        ArrayList<Hunde> gefilterteHunde = new ArrayList<>(); //Die gefilterte Liste die am Ende in der textarea ausgegeben wird
        String groesse = groessefiltercombobox.getSelectedItem().toString();
        String geschlecht = geschlechtfiltercombobox.getSelectedItem().toString();
        String kastriertstring = kastriertfiltercombobox.getSelectedItem().toString();
        double alter;
        boolean alterpasst = true; //initialisieren, Wert wird in Methode "alterfiltern" überschrieben

        for (Hunde hund: hundepension.getHundeimhotel()){
            if (hund.getEinzelzimmer()==true){
                continue;}
            if (!hund.getZimmerpartner().isEmpty()){
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
            filterausgabe.setText("Keine Hunde entsprechen den ausgewählten Kriterien.");
        } else{
            filterausgabe.setText("");
            for (Hunde hund: gefilterteHunde){
                String hundausgabe = hund.ausgeben();
                filterausgabe.setText(filterausgabe.getText() + hundausgabe);
            }
        }gefilterteHunde.clear();
    }
    public String[] splitrasseeingabe() {
        return rassefiltertextfield.getText().split(", ");
    }
    private boolean rassefiltern(Hunde hund){
        String[] rassefilterliste = splitrasseeingabe();
        if (sichtbarrasse == false) {
            return true;}
        boolean rassepasst = false;
        for (int i = 0; i < rassefilterliste.length && rassepasst == false; i++) {
            if(rassefilterliste[i].equals(hund.getRasse())){
                rassepasst = true;
            }
        }
        return rassepasst;
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
        if (sichtbaralter == false) {
            return true;} //Wenn der radio button "Egal" aktiviert ist, gibt die methode direkt true aus
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

    private void setEingabeHundeauswaehlen(){
        eingabeHundeauswaehlen.addItem("");
        for (Hunde hund: hundepension.getSuchtpartnerListe()){
            eingabeHundeauswaehlen.addItem(hund.getHundename());
        }
    }
    private void setZimmerpartnerwaehlencombobox(){
        zimmerpartnerwaehlencombobox.addItem("");
        for (Hunde hund: hundepension.getHundeimhotel()){
            if (hund.getZimmerpartner().isEmpty()){
                zimmerpartnerwaehlencombobox.addItem(hund.getHundename());}
        }
    }
}