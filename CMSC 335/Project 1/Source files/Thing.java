/*
 * File: Thing.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build thing object
 */

package project1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Thing implements Comparable<Thing>{
    
    //Class variables
    private String name;    
    private int index, parent;
    
    public ArrayList<String> thingArray = new ArrayList<>();
    
    //Constructor
    public Thing(Scanner sc){
        
        //Loop used to add String objects to thingArray
        while(sc.hasNext()){
            while("//".equals(sc.next())){
                sc.nextLine();
            }
            if(sc.hasNext()){name = sc.next(); index = sc.nextInt(); parent = sc.nextInt();}            
            thingArray.add("Name: " + name + "\nIndex: " + index + "\nParent: " + parent);
            sc.nextLine();
        }
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
    
    //Overriding toString method    
    @Override
    public String toString(){        
        return thingArray.toString();
    }
  
}