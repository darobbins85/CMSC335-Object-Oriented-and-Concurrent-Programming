/*
 * File: SeaPortProgram.java
 * Author: David Robbins
 * Date: 2017.07.16
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
    private Ship ship;
    private Thing thing;
    
    public SeaPortProgram(String title){
        super(title);
        setFrame(100, 100);
        setSize(820,575);
        setLocationRelativeTo(null);
        setVisible(true);
        
        //Panels
        JPanel mainPanel = new JPanel();
        JPanel Panel1 = new JPanel();
        JPanel Panel2 = new JPanel();
        JPanel Panel3 = new JPanel();
        
        //Buttons
        JButton openButton = new JButton("Choose File");
        JButton portButton = new JButton("Ports");
        JButton dockButton = new JButton("Docks");
        JButton pshipButton = new JButton("Passenger Ships");
        JButton cshipButton = new JButton("Cargo Ships");
        JButton peopleButton = new JButton("People");
        JButton portButtonSort = new JButton("Ports");
        JButton dockButtonSort = new JButton("Docks");
        JButton pshipButtonSort = new JButton("Passenger Ships");
        JButton cshipButtonSort = new JButton("Cargo Ships");
        JButton peopleButtonSort = new JButton("People");
        JButton findJobButton = new JButton("Find");
        JButton findShipButton = new JButton("Find");
        JButton findWeightButton = new JButton("Find");
        JButton findPassengersButton = new JButton("Find");
        
        //Labels
        JLabel findShipLabel = new JLabel("Find Ship by Index");       
        JLabel findJobLabel = new JLabel("Find Job by Passenger Name");   
        JLabel findWeightByNameLabel = new JLabel("Find Cargo Weight by Ship Name");      
        JLabel findPassengersLabel = new JLabel("Find Number of Passengers by Ship Name");
        JLabel mainButtons = new JLabel("All Information: ");
        JLabel sortButtons = new JLabel("Sorted Names: ");
        
        //Text Fields
        JTextField findShipField = new JTextField(10);
        JTextField findJobField = new JTextField(10);
        JTextField findWeightField = new JTextField(10);
        JTextField findPassengersField = new JTextField(10);
        
        //Other JFrame Objects
        JTextArea dataArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(dataArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);      
        
        //buttonPanel1 - added objects
        Panel1.add(openButton);
        
        //buttonPanel2 - added objects and layout
        Panel2.setLayout(new GridLayout(2, 6));
        Panel2.add(mainButtons);
        Panel2.add(portButton);
        Panel2.add(dockButton);
        Panel2.add(pshipButton);
        Panel2.add(cshipButton);
        Panel2.add(peopleButton);
        Panel2.add(sortButtons);
        Panel2.add(portButtonSort);
        Panel2.add(dockButtonSort);
        Panel2.add(pshipButtonSort);
        Panel2.add(cshipButtonSort);
        Panel2.add(peopleButtonSort);
        
        //buttonPanel3 - added objects and layout
        Panel3.setLayout(new GridLayout(4, 3));
        Panel3.add(findShipLabel);
        Panel3.add(findShipField);
        Panel3.add(findShipButton);
        Panel3.add(findJobLabel);
        Panel3.add(findJobField);
        Panel3.add(findJobButton);
        Panel3.add(findWeightByNameLabel);
        Panel3.add(findWeightField);
        Panel3.add(findWeightButton);
        Panel3.add(findPassengersLabel);
        Panel3.add(findPassengersField);
        Panel3.add(findPassengersButton);
        
        //mainPanel - add panels and set layout
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(Panel1, BorderLayout.NORTH);
        mainPanel.add(Panel2, BorderLayout.CENTER);
        mainPanel.add(Panel3, BorderLayout.SOUTH);
        
        //Add mainPanel and scrollPane to GUI and set layout
        add(scrollPane, BorderLayout.SOUTH);             
        add(mainPanel, BorderLayout.NORTH);
        
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
                    pship = new PassengerShip(tester1, tester2, tester2, tester3);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    tester3 = new Scanner(new File(jfcString));
                    cship = new CargoShip(tester1, tester2, tester2, tester3);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    person = new Person(tester1, tester2);
                    
                    tester1 = new Scanner(new File(jfcString));
                    tester2 = new Scanner(new File(jfcString));
                    tester3 = new Scanner(new File(jfcString));
                    ship = new Ship(tester1, tester2, tester3);
                    
                    tester1 = new Scanner(new File(jfcString));
                    thing = new Thing(tester1);

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
            dataArea.setText("---All Port Information---\n");
            int i = 0;
                while(!port.seaportHash.isEmpty()){
                    dataArea.append("\n" + port.seaportHash.get(i));
                    i++;
                    if(port.seaportHash.get(i) == null){
                        break;
                    }
                }
            
        });
        
        //Action for when the Port button for sorting is pressed
        portButtonSort.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---Sorted Port Names---\n");
            
            dataArea.append("\n" + port.getSortedNames());
 
        });
        
        //Action for when the Dock button is pressed
        dockButton.addActionListener((ActionEvent c) -> {

            dataArea.setText(null);
            dataArea.setText("---All Dock Information---\n");
            int i = 0;
            while(!dock.dockHash.isEmpty()){
                    dataArea.append("\n" + dock.dockHash.get(i));
                    i++;
                    if(dock.dockHash.get(i) == null){
                        break;
                    }
                }

        });
        
        //Action for when the Dock button for sorting is pressed
        dockButtonSort.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---Sorted Dock Names---\n");
            
            dataArea.append("\n" + dock.getSortedNames());
 
        });
        
        //Action for when the Passenger Ship button is pressed
        pshipButton.addActionListener((ActionEvent d) -> {
            
            dataArea.setText(null);
            dataArea.append("---All Passenger Ship Information---\n");
            int i = 0;
            while(!pship.pshipHash.isEmpty()){
                dataArea.append("\n" + pship.pshipHash.get(i));
                i++;
                if(pship.pshipHash.get(i) == null){
                    break;
                }
            }                   
        });
        
        //Action for when the Passenger Ship button for sorting is pressed
        pshipButtonSort.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---Sorted Passenger Ship Names---\n");
            
            dataArea.append("\n" + pship.getSortedNames());
 
        });
        
        //Action for when the Cargo Ship button is pressed
        cshipButton.addActionListener((ActionEvent e) -> {

            dataArea.setText(null);
            dataArea.append("---All Cargo Ship Information---\n");
            int i = 0;
            while(!cship.cshipHash.isEmpty()){
                dataArea.append("\n" + cship.cshipHash.get(i));
                i++;
                if(cship.cshipHash.get(i) == null){
                    break;
                }
            }
        });
        
        //Action for when the Cargo Ship button for sorting is pressed
        cshipButtonSort.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---Sorted Cargo Ship Names---\n");
            
            dataArea.append("\n" + cship.getSortedNames());
 
        });
        
        //Action for when the People button is pressed
        peopleButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---All Information About People---\n");
            int i = 0;
            while(!person.personHash.isEmpty()){
                    dataArea.append("\n" + person.personHash.get(i));
                    i++;
                    if(person.personHash.get(i) == null){
                        break;
                    }
                }
        });
        
        //Action for when the People button for sorting is pressed
        peopleButtonSort.addActionListener((ActionEvent b) -> {
            
            dataArea.setText(null);
            dataArea.setText("---Sorted Persons Names---\n");
            
            dataArea.append("\n" + person.getSortedNames());
 
        });
        
        //Action for when the Find button for finding a ship by index is pressed
        findShipButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---Name of Ship according to Index---\n\n");            
            dataArea.append(thing.getThingByIndex(Integer.parseInt(findShipField.getText()), thing.thingHash));
        });
        
        //Action for when the Find button for finding a job by passenger name is pressed
        findJobButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---Job according to Name entered---\n\n");            
            dataArea.append(person.getSkillByName(findJobField.getText(), person.personHash));
        });
        
        //Action for when the Find button for finding cargo weight by ship name is pressed
        findWeightButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---Cargo Weight of Ship Name entered---\n\n");            
            dataArea.append(cship.getCargoWeightByName(findWeightField.getText(), cship.cshipHash));
        });
        
        //Action for when the Find button for finding number of passengers by ship name is pressed
        findPassengersButton.addActionListener((ActionEvent f) -> {

            dataArea.setText(null);
            dataArea.setText("---Number of Passengers according to name entered---\n\n");            
            dataArea.append(pship.getNumPassengersByName(findPassengersField.getText(), pship.pshipHash));
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