/*
 * File: PassengerShip.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build passenger ship object
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class PassengerShip extends Ship{

    //Class variables    
    private int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;

    //Public arraylist class variable to be called from main class    
    public ArrayList<String> passengerShipArray = new ArrayList<>();
    
    //Constructor
    public PassengerShip(Scanner sc, Scanner sc1, Scanner sc2){
        super(sc, sc1);
        
        //Loop used to add String objects to passengerShipArray
        while(sc2.hasNext()){
            if("pship".equals(sc2.next())){ 
                setName(sc2.next());            
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                setWeight(sc2.nextDouble()); 
                setLength(sc2.nextDouble()); 
                setWidth(sc2.nextDouble()); 
                setDraft(sc2.nextDouble());
                numberOfPassengers = sc2.nextInt(); 
                numberOfRooms = sc2.nextInt(); 
                numberOfOccupiedRooms = sc2.nextInt();
                passengerShipArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nDock: " + 
                        getParent() + "\nWeight: " + getWeight() + " \nLength: " + getLength() + "\nWidth: " + 
                        getWidth() + "\nDraft: " + getDraft() + "\nNumber of Passengers: " + numberOfPassengers + 
                        "\nNumber of Rooms: " + numberOfRooms + "\nNumber of Occupied Rooms: " + numberOfOccupiedRooms); 
            }else{
                sc2.nextLine();
            }
        }
    }        
    
    //Getters
    public int getNOOR(){return numberOfOccupiedRooms;}
    public int getNOP(){return numberOfPassengers;}
    public int getNOR(){return numberOfRooms;}
    
    //Overriding toString method    
    @Override
    public String toString () {
        return passengerShipArray.toString();

    }
    
}