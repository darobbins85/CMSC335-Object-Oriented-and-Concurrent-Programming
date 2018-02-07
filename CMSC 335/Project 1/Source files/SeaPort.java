/*
 * File: 
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: 
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class SeaPort extends Thing{
    
    //Class variables
    private ArrayList<Dock> docks;
    private ArrayList<Ship> que;
    private ArrayList<Ship> ships;
    private ArrayList<Person> people;

    //Public arraylist class variable to be called from main class
    public ArrayList<String> seaportArray = new ArrayList<>();
    
    public SeaPort(Scanner sc1, Scanner sc2){
        super(sc1);
        while(sc2.hasNext()){
            if("port".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                seaportArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nPort: " + getParent());
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
    public ArrayList<Dock> getDocks(){return docks;}
    public ArrayList<Ship> getQue(){return que;}
    public ArrayList<Ship> getShips(){return ships;}
    public ArrayList<Person> getPeople(){return people;}
    
    //Overriding toString method    
    @Override      
    public String toString () {
        return seaportArray.toString();
        }
    
}