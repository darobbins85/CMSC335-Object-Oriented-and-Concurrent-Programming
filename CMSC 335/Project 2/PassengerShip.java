/*
 * File: PassengerShip.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build passenger ship object
 */

package project1;

import java.util.*;

public class PassengerShip extends Ship{

    //Class variables    
    private int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;
    private String passengers;
    public HashMap<Integer, String> pshipHash = new HashMap<>();
    
    //Constructor
    public PassengerShip(Scanner sc, Scanner sc1, Scanner sc3, Scanner sc2){
        super(sc, sc1, sc3);
        int i = 0;
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
                pshipHash.put(i, getName());
                i++;
                pshipHash.put(i, "" + getIndex());
                i++;
                pshipHash.put(i, "" + getParent());
                i++;
                pshipHash.put(i, "" + getWeight());
                i++;
                pshipHash.put(i, "" + getLength());
                i++;
                pshipHash.put(i, "" + getWidth());
                i++;
                pshipHash.put(i, "" + getDraft());
                i++;
                pshipHash.put(i, "" + numberOfPassengers);
                i++;
                pshipHash.put(i, "" + numberOfRooms);
                i++;
                pshipHash.put(i, "" + numberOfOccupiedRooms);
                i++;
            }else{
                sc2.nextLine();
            }
        }
    }        
    
    //Getters
    public int getNOOR(){return numberOfOccupiedRooms;}
    public int getNOP(){return numberOfPassengers;}
    public int getNOR(){return numberOfRooms;}
    
    //Method to find the number of passengers based on the name of the ship
    public String getNumPassengersByName(String x, HashMap<Integer, String> hms){
        int i = 0;
            while(!hms.isEmpty()){
                if(x.matches(hms.get(i))){
                    passengers = hms.get(i+7);
                }                
                i = i+10;
                if(hms.get(i) == null){
                    break;
                }
            }
        return passengers;
    }
    
    //Overriding method to sort names of ships in alphabetical order
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(pshipHash.get(i) != null){
            names.add(pshipHash.get(i));
            i = i+10;
        }
        
        return names.toString();
    }
    
    //Overriding toString method    
    @Override
    public String toString () {
        return pshipHash.toString();
    }
  
}