/*
 * File: Dock.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build dock object
 */

package project1;

import java.util.*;

public class Dock extends Thing{

    //Class variables    
    private Ship ship;
    private int dock;
    public HashMap<Integer, String> dockHash = new HashMap<>();
    
    //Constructor
    public Dock(Scanner sc1, Scanner sc2){
        super(sc1);
        int i = 0;
        while(sc2.hasNext()){
            if("dock".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                dock = sc2.nextInt();
                dockHash.put(i, getName());
                i++;
                dockHash.put(i, "" + getIndex());
                i++;
                dockHash.put(i, "" + getParent());
                i++;
                dockHash.put(i, "" + dock);
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
    
    //Getters
    public Ship getShip(){return ship;}
    
    //Overriding method to sort names of piers from first to last
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(dockHash.get(i) != null){
            names.add(dockHash.get(i));
            i = i+4;
        }
        
        return names.toString();
    }
    
    //Overriding toString method        
    @Override
    public String toString(){
        return dockHash.toString();
    }
    
}