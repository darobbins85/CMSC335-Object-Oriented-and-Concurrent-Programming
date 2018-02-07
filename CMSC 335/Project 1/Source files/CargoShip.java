/*
 * File: CargoShip.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build cargo ship object
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class CargoShip extends Ship{

    //Class variables    
    private double cargoValue, cargoVolume, cargoWeight;
    
    //Public arraylist class variable to be called from main class    
    public ArrayList<String> cargoShipArray = new ArrayList<>();
    
    //Constructor
    public CargoShip(Scanner sc, Scanner sc1, Scanner sc2){
        super(sc, sc1);
        
        //Loop used to add String objects to cargoShipArray
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
                cargoShipArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nDock: " + 
                        getParent() + "\nWeight: " + getWeight() + " \nLength: " + getLength() + "\nWidth: " + 
                        getWidth() + "\nDraft: " + getDraft() + "\nCargo Value: " + cargoValue + "\nCargo Volume: " + 
                        cargoVolume + "CargoWeight: " + cargoWeight);
            }else{
                sc2.nextLine();
            }
        }
    } 
    
    //Getters
    public double getCargoValue(){return cargoValue;}
    public double getCargoVolume(){return cargoVolume;}
    public double getCargoWeight(){return cargoWeight;}
    
    //Overriding toString method    
    @Override
    public String toString () {
        return cargoShipArray.toString();

    }

}