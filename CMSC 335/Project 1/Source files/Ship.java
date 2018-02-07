/*
 * File: Ship.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build ship object
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing{

    //Class variables    
    //private PortTime arrivalTime, dockTime;
    private double draft, length, weight, width;
    //private ArrayList<Job> jobs;

    //Public arraylist class variable to be called from main class    
    public ArrayList<String> shipArray = new ArrayList<>();
    
    public Ship(Scanner sc1, Scanner sc2){
        super(sc1);
        //New scanner object for getting values for second loop
        Scanner sc3 = sc2;
        //Two loops used to add String objects to shipArray
        //First loop for when line is for Passenger Ships
        while(sc2.hasNext()){
            if("pship".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                weight = sc2.nextDouble(); 
                length = sc2.nextDouble();
                width = sc2.nextDouble();
                draft = sc2.nextDouble();
                shipArray.add("Name: " + getName() + " " + getIndex() + " " + getParent() + " " + weight + " " + length + " " + width + " " + draft);
                sc2.nextLine();
            }else{
                sc2.nextLine();
            }
        }
        
        //Second loop for when line is Cargo Ship
        //This doesn't seem to work, but I never needed to create a ship object in main class
        while(sc3.hasNext()){
            if("cship".equals(sc3.next())){
                setName(sc3.next());
                setIndex(sc3.nextInt()); 
                setParent(sc3.nextInt());
                weight = sc3.nextDouble(); 
                length = sc3.nextDouble();
                width = sc3.nextDouble();
                draft = sc3.nextDouble();
                shipArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nDock: " + getParent() + 
                        "\nWeight: " + weight + "\nLength: " + length + "\nWidth: " + width + "\nDraft: " + draft);
                sc3.nextLine();
            }else{
                sc3.nextLine();
            }
        }
    }
    
    //Getters and Setters
    public double getDraft(){return draft;}
    public void setDraft(double d){draft = d;}
    public double getLength(){return length;}
    public void setLength(double l){length = l;}
    public double getWeight(){return weight;}
    public void setWeight(double w){weight = w;}
    public double getWidth(){return width;}
    public void setWidth(double w){width = w;}
    
    //Overriding toString method        
    @Override
    public String toString(){
        return "Ship Array: " + shipArray.toString();
    }
    
}