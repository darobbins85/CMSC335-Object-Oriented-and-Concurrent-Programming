/*
 * File: Dock.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build dock object
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Dock extends Thing{

    //Class variables    
    private Ship ship;
    private int dock;

    //Public arraylist class variable to be called from main class    
    public ArrayList<String> dockArray = new ArrayList<>(); 

    public Dock(Scanner sc1, Scanner sc2){
        super(sc1);
        while(sc2.hasNext()){
            if("dock".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                dock = sc2.nextInt();
                dockArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nPort: " + getParent() + "\nDock: " + dock);
                if(!sc2.hasNext()){
                }else{
                sc2.nextLine();               
                }
            }else{
                sc2.nextLine();
            }
        }
    }
    
    //Getters
    public Ship getShip(){return ship;}
    
    //Overriding toString method        
    @Override
    public String toString(){
        return dockArray.toString();
    }
    
}