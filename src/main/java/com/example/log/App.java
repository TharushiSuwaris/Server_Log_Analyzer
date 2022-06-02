package com.example.log;

import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class App implements ActionListener
{
    private JLabel l1;
    private JComboBox<String> box;
    private JTextArea ta1;
    private JLabel l2;
    private JLabel l3;
    private JTextArea ta2;
    private JButton b1;
    private JLabel title;
    private JScrollPane sp1;
    private JScrollPane sp2;
    private JPanel panel = new JPanel();
    JFrame frame = new JFrame ("Server Log Analyser");

    public App() {
        //construct preComponents
        String[] boxItems = {"Nawagala", "Dola"};

        //construct components
        l1 = new JLabel ("Server  :");
        box = new JComboBox<String> (boxItems);
        ta1 = new JTextArea (100, 100);
        sp1 = new JScrollPane(ta1);
        l2 = new JLabel ("Errors :");
        l3 = new JLabel ("Warnings :");
        ta2 = new JTextArea (100, 100);
        sp2 = new JScrollPane(ta2);
        b1 = new JButton ("Analyse");
        title = new JLabel ("Server Log Analyzer");
        
        //adjust size and set layout
        panel.setPreferredSize (new Dimension (810, 513));
        panel.setLayout (null);

        //add components
        panel.add (l1);
        panel.add (box);
        //panel.add (ta1);
        panel.add(sp1);
        panel.add (l2);
        panel.add (l3);
        panel.add (sp2);
        panel.add (b1);
        panel.add (title);

        //set component bounds (only needed by Absolute Positioning)
        l1.setBounds (120, 95, 100, 25);
        box.setBounds (185, 95, 100, 25);
        ta1.setBounds (120, 180, 550, 110);
        sp1.setBounds(120, 180, 550, 110);
        l2.setBounds (120, 145, 100, 25);
        l3.setBounds (120, 300, 100, 25);
        ta2.setBounds (120, 335, 550, 110);
        sp2.setBounds (120, 335, 550, 110);
        b1.setBounds (420, 100, 105, 25);
        title.setBounds (260, 20, 320, 50);
        title.setFont(new Font("Serif", Font.BOLD, 24));

        b1.addActionListener(this);

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (panel);
        frame.pack();
        frame.setVisible (true);
    }

    public static void main(String[] args) throws IOException{
    
        new App();
        
    }

    public void actionPerformed(ActionEvent e) {
        PreProcess process = new PreProcess();
        try {
            process.processLines("mz.0");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        process.readJsonObject("errors.json");
        ta1.setText(process.errors);
        process.readJsonObject("warnings.json");
        ta2.setText(process.warnings);
    }

}
