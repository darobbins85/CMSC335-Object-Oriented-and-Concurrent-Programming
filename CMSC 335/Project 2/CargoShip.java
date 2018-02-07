/*
 * File: CargoShip.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build cargo ship object
 */

package project1;

import java.util.*;

public class CargoShip extends Ship{

    //Class variables    
    private double cargoValue, cargoVolume, cargoWeight;
    private String cWeight;
    public HashMap<Integer, String> cshipHash = new HashMap<>();
    
    //Constructor
    public CargoShip(Scanner sc, Scanner sc1, Scanner sc3, Scanner sc2){
        super(sc, sc1, sc3);
        int i = 0;
        while(sc2.hasNext()){
            if("cship".equals(sc2.next())){ 
                setName(sc2.next());            
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                setWeight(sc2.nextDouble()); 
                setLength(sc2.nextDouble()); 
                setWidth(sc2.nextDouble()); 
                setDraft(sc2.nextDouble());
                cargoValue = sc2.nextDouble(); 
                cargoVolume = sc2.nextDouble();
                cargoWeight = sc2.nextDouble();
                //Add values to cshipHash
                cshipHash.put(i, getName());
                i++;
                cshipHash.put(i, "" + getIndex());
                i++;
                cshipHash.put(i, "" + getParent());
                i++;
                cshipHash.put(i, "" + getWeight());
                i++;
                cshipHash.put(i, "" + getLength());
                i++;
                cshipHash.put(i, "" + getWidth());
                i++;
                cshipHash.put(i, "" + getDraft());
                i++;
                cshipHash.put(i, "" + cargoValue);
                i++;
                cshipHash.put(i, "" + cargoVolume);
                i++;
                cshipHash.put(i, "" + cargoWeight);
                i++;
            }else{
                sc2.nextLine();
            }
        }
    } 
    
    //Getters
    public double getCargoValue(){return cargoValue;}
    public double getCargoVolume(){return cargoVolume;}
    public double getCargoWeight(){return cargoWeight;}
    
    //Method to find Cargo Weight by name
    public String getCargoWeightByName(String x, HashMap<Integer, String> hms){
        int i = 0;
            while(!hms.isEmpty()){
                if(x.matches(hms.get(i))){
                    cWeight = hms.get(i+9);
                }                
                i = i+10;
                if(hms.get(i) == null){
                    break;
                }
            }
        return cWeight;
    }
    
    //Overriding method to sort names of ships in alphabetical order
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(cshipHash.get(i) != null){
            names.add(cshipHash.get(i));
            i = i+10;
        }
        
        return names.toString();
    }
    
    //Overriding toString method    
    @Override
    public String toString () {
        return cshipHash.toString();
    }

}