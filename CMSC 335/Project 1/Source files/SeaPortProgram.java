/*
 * File: SeaPortProgram.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Creates the GUI for this  program
 */

package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;

public class SeaPortProgram extends JFrame{
    
    //Class variables
    private Scanner tester1;
    private Scanner tester2;
    private Scanner tester3;
    private String seaPortString, dockString, pshipString, cshipString, personString, jfcString;
    private SeaPort port;
    private Dock dock;
    private PassengerShip pship;
    private CargoShip cship;
    private Person person;
    
    public SeaPortProgram(String title){
        super(title);
        setFrame(100, 100);
        setSize(600,500);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // GUI Panels and Objects
        JPanel top = new JPanel();
        JPanel buttonPanelTop = new JPanel();
        JPanel buttonPanelBot = new JPanel();
        //JPanel mid = new JPanel();
        JButton openButton = new JButton("Choose File");
        JButton portButton = new JButton("Ports");
        JButton dockButton = new JButton("Docks");
        JButton pshipButton = new JButton("Passenger Ships");
        JButton cshipButton = new JButton("Cargo Ships");
        JButton peopleButton = new JButton("People");
        JTextArea dataArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(dataArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        /*JLabel nameLabel = new JLabel("Search Name:");
        JLabel indexLabel = new JLabel("Search Index:");
        JLabel parentLabel = new JLabel("Search Parent:");
        JLabel skillLabel = new JLabel("Search Skill:");        
        JTextField name = new JTextField();
        JTextField index = new JTextField();
        JTextField parent = new JTextField();
        JTextField skill = new JTextField();*/
        
        //Add objects to panels
        buttonPanelTop.add(openButton);
        buttonPanelBot.add(portButton);
        buttonPanelBot.add(dockButton);
        buttonPanelBot.add(pshipButton);
        buttonPanelBot.add(cshipButton);
        buttonPanelBot.add(peopleButton);
        top.setLayout(new GridLayout(2,1));
        top.add(buttonPanelTop);
        top.add(buttonPanelBot);
        /*mid.setLayout(new GridLayout(2,4));
        mid.add(nameLabel);
        mid.add(name);
        mid.add(indexLabel);
        mid.add(index);
        mid.add(parentLabel);
        mid.add(parent);
        mid.add(skillLabel);
        mid.add(skill);*/
        
        //Add panels to GUI
        add(scrollPane, BorderLayout.SOUTH);             
        add(top, BorderLayout.NORTH);
        //add(mid, BorderLayout.CENTER);
        
        //Creat button action listeners for various buttons
        openButton.addActionListener((ActionEvent a) -> {
            //Create File Chooser for user to choose their file
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choose SeaPort Data File");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(jfc.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION){}
            jfcString = jfc.getSelectedFile().getAbsolutePath();
                
                //Using the chosen file, create main objects used in program
                try{
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    port = new SeaPort(tester1, tester2);

                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    dock = new Dock(tester1, tester2);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    tester3 = new Scanner(new File(jfcString));
                    pship = new PassengerShip(tester1, tester2, tester3);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    tester3 = new Scanner(new File(jfcString));
                    cship = new CargoShip(tester1, tester2, tester3);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    person = new Person(tester1, tester2);
                    
                    //Display message indicating data structure is created and have user press next buttosn as  needed
                    String message = "Data structure Created\n\nPress labelled buttons to find information as needed";
                    JOptionPane.showMessageDialog(null, message);
                    
                }
                catch(Exception e){
                    String message = "File not found or error in file";
                    JOptionPane.showMessageDialog(null, message);
                }
        });
        
        //Action for when the Port button is pressed
        portButton.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---All Ports---\n\n");
            int i = 0;
            while(port.seaportArray.size() > i){            
                seaPortString = port.seaportArray.get(i);
                i++;
                dataArea.append(seaPortString);
                dataArea.append("\n\n");
            }
            
        });
        
        //Action for when the Dock button is pressed
        dockButton.addActionListener((ActionEvent c) -> {

            dataArea.setText(null);
            dataArea.setText("---All Docks---\n\n");
            int i = 0;
            while(dock.dockArray.size() > i){            
                dockString = dock.dockArray.get(i);
                i++;                   
                dataArea.append(dockString);
                dataArea.append("\n\n");
            }
        });
        
        //Action for when the Passenger Ship button is pressed
        pshipButton.addActionListener((ActionEvent d) -> {
            
            dataArea.setText(null);
            dataArea.append("---All Passenger Ships---\n\n");
            int i = 0;
            while(pship.passengerShipArray.size() > i){            
                pshipString = pship.passengerShipArray.get(i);
                i++;
                dataArea.append(pshipString);
                dataArea.append("\n\n");
            }                      
        });
        
        //Action for when the Cargo Ship button is pressed
        cshipButton.addActionListener((ActionEvent e) -> {

            dataArea.setText(null);
            dataArea.append("---All Cargo Ships---\n\n");
            int i = 0;
            while(cship.cargoShipArray.size() > i){            
                cshipString = cship.cargoShipArray.get(i);
                i++;
                dataArea.append(cshipString);
                dataArea.append("\n\n");
            }
        });
        
        //Action for when the People button is pressed
        peopleButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---All People---\n\n");
            int i = 0;
            while(person.personArray.size() > i){            
                personString = person.personArray.get(i);
                i++;         
                dataArea.append(personString);
                dataArea.append("\n\n");
            }
        });
                             
    }
    
    //Method to set the frame to visible
    public void display(){
        setVisible(true);
    }
    
    //Method to set the size of the frame
    private void setFrame(int width, int height){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Main method
    public static void main(String[] args) {
        
        //creat the Seaport porgram and display
        SeaPortProgram seaport = new SeaPortProgram("SeaPort Program");
        seaport.display();
        
    }
}