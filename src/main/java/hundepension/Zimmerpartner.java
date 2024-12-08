package hundepension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox eingabeHundeauswaehlen; // Combobox in welcher wir einen von uns zuvor in der Hundepension eingegeben Hund auswaehlen
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
        for (Hunde hund: hundepension.getHundeeingabeListe()){
            eingabeHundeauswaehlen.addItem(hund.getHundename());}


        //sollen erst durch drücken des radiobuttons sichtbar werden
        sichtbar = false;
        alterfiltertextfield.setVisible(sichtbar);
        alterfiltercombobox.setVisible(sichtbar);

        eingabeHundeauswaehlen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

            }
        });
    }
}
