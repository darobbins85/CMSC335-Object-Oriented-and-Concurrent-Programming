/*
 * File: 
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build SeaPort object
 */

package project1;

import java.util.*;

public class SeaPort extends Thing{
    
    //Class variables
    private Dock docks;
    private Ship que;
    private Ship ships;
    private Person people;
    public HashMap<Integer, String> seaportHash = new HashMap<>();
    
    //Main Constructor
    public SeaPort(Scanner sc1, Scanner sc2){
        super(sc1);
        int i = 0;
        while(sc2.hasNext()){
            if("port".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                //seaportArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nPort: " + getParent());
                seaportHash.put(i, getName());
                i++;
                seaportHash.put(i, "" + getIndex());
                i++;
                seaportHash.put(i, "" + getParent());
                i++;
                
                if(!sc2.hasNext()){
                }else{
                sc2.nextLine();               
                }
            }else{
                sc2.nextLine();
            }
        }
    }
    
    //Secondary Constructor
    public SeaPort(Scanner sc, Dock docks, Ship que, Ship ships, Person people){
        super(sc);
        this.docks = docks;
        this.que = que;
        this.ships = ships;
        this.people = people;
    }
    
    //Getters
    public Dock getDocks(){return docks;}
    public Ship getQue(){return que;}
    public Ship getShips(){return ships;}
    public Person getPeople(){return people;}
    
    //Overriding method to sort names of seaports in alphabetical order
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(seaportHash.get(i) != null){
            names.add(seaportHash.get(i));
            i = i+3;
        }
        
        return names.toString();
    }
    
    //Overriding toString method    
    @Override      
    public String toString () {
        return seaportHash.toString();
        }
    
}