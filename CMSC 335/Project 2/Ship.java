/*
 * File: Ship.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build ship object
 */

package project1;

import java.util.*;

public class Ship extends Thing{

    //Class variables    
    private double draft, length, weight, width;
    public HashMap<Integer, String> shipHash = new HashMap<>();
    //private PortTime arrivalTime, dockTime;
    
    //Constructor
    public Ship(Scanner sc1, Scanner sc2, Scanner sc3){
        super(sc1);
        int i = 0;
        while(sc2.hasNext()){
            if("pship".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                weight = sc2.nextDouble(); 
                length = sc2.nextDouble();
                width = sc2.nextDouble();
                draft = sc2.nextDouble();
                shipHash.put(i, getName());
                i++;
                shipHash.put(i, "" + getIndex());
                i++;
                shipHash.put(i, "" + getParent());
                i++;
                shipHash.put(i, "" + weight);
                i++;
                shipHash.put(i, "" + length);
                i++;
                shipHash.put(i, "" + width);
                i++;
                shipHash.put(i, "" + draft);
                i++;
                
                sc2.nextLine();
            }else{
                    sc2.nextLine();
            }
        }
        while(sc3.hasNext()){
            if("cship".equals(sc3.next())){
                    setName(sc3.next());
                    setIndex(sc3.nextInt());
                    setParent(sc3.nextInt());
                    weight = sc3.nextDouble(); 
                    length = sc3.nextDouble();
                    width = sc3.nextDouble();
                    draft = sc3.nextDouble();
                    shipHash.put(i, getName());
                    i++;
                    shipHash.put(i, "" + getIndex());
                    i++;
                    shipHash.put(i, "" + getParent());
                    i++;
                    shipHash.put(i, "" + weight);
                    i++;
                    shipHash.put(i, "" + length);
                    i++;
                    shipHash.put(i, "" + width);
                    i++;
                    shipHash.put(i, "" + draft);
                    i++;
                    sc3.nextLine();
            }else{
                    sc3.nextLine();
            }
        }
    }

    //Getters and Setters
    public double getDraft(){return draft;}
    public void setDraft(double d){draft = d;}
    public double getLength(){return length;}
    public void setLength(double l){length = l;}
    public double getWeight(){return weight;}
    public void setWeight(double w){weight = w;}
    public double getWidth(){return width;}
    public void setWidth(double w){width = w;}
    
    //Overriding method to sort names of ships in alphabetical order
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(shipHash.get(i) != null){
            names.add(shipHash.get(i));
            i = i+7;
        }
        
        return names.toString();
    }
   
    //Overriding toString method        
    @Override
    public String toString(){
        return "" + shipHash.toString();
    }
    
}