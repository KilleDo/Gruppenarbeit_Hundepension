package hundepension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    boolean problemsichtbar = false;

    public Hundepension(){
        setTitle("Hundepension ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(hundregistrierpanel);
        setSize(1500,400); //größe des Fensters
        setVisible(true); //Fenster sichtbar machen

        rassetextfield.setVisible(false);
        problemetextfield.setVisible(false);


        rassecombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rasse = rassecombobox.getSelectedItem().toString();

                if (rasse.equals("andere:")){
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
                String probleme = problemecombobox.getSelectedItem().toString();

                if (probleme.equals("andere:")){
                    problemetextfield.setVisible(true);
                }else{
                    problemetextfield.setVisible(false);
                }
            }
        });
        speichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            nameHundtextfield.setText("");
            rassetextfield.setText("");
            problemetextfield.setText("");

            }
        });
    }

    public static void main(String[] args) {
        new Hundepension();
    }
}
