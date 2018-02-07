/*
 * File: Thing.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build thing object
 */

package project1;

import java.util.*;

public class Thing implements Comparable<Thing>{
    
    //Class variables
    private String name, thing;    
    private int index, parent;
    public HashMap<Integer, String> thingHash = new HashMap<>();
    
    //Constructor
    public Thing(Scanner sc){
        int i = 0;        
        while(sc.hasNext()){
            while("//".equals(sc.next())){
                sc.nextLine();
            }
            if(sc.hasNext()){
                name = sc.next(); 
                index = sc.nextInt(); 
                parent = sc.nextInt();
                thingHash.put(i, "" + name);
                i++;
                thingHash.put(i, "" + index);
                i++;
                thingHash.put(i, "" + parent);
                i++;
                sc.nextLine();
            }   
        }
        
    }
    
    //Method to find a thing object by its index
    public String getThingByIndex(int x, HashMap<Integer, String> hms){
        int i = 1;
            while(!hms.isEmpty()){
                if(x == Integer.parseInt(hms.get(i))){
                    thing = hms.get(i-1);
                }                
                i = i+3;
                if(hms.get(i) == null){
                    break;
                }
            }
        return thing;
    }
    
    //Getters and Setters
    public String getName(){return name;}
    public void setName(String n){name = n;}
    public int getIndex(){return index;}
    public void setIndex(int i){index = i;}
    public int getParent(){return parent;}
    public void setParent(int p){parent = p;}
    
    //Defining compareTo method (did not use in main method)
    @Override
    public int compareTo(Thing other) {
        if (getName().compareTo(other.getName()) > 0){
            return 1;
        }
        else if(getName().compareTo(other.getName()) < 0){
            return -1;
        }else{
            return 0;
        }
    }
    
    //Overridden method used to sort names of all things in alphabetical order
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(thingHash.get(i) != null){
            names.add(thingHash.get(i));
            i = i+3;
        }
        
        return names.toString();
    }
    
    //Overriding toString method    
    @Override
    public String toString(){        
        return "" + thingHash;
    }

}