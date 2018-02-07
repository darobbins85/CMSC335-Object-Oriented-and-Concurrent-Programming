/*
 * File: Person.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build person object
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Person extends Thing{

    //Class variables    
    private String skill;
    
    //Public arraylist class variable to be called from main class
    public ArrayList<String> personArray = new ArrayList<>(); 
    
    //Constructor
    public Person(Scanner sc1, Scanner sc2){
        super(sc1);

        //Loop used to add String objects to personArray        
        while(sc2.hasNext()){
            if("person".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                skill = sc2.next();
                personArray.add("Name: " + getName() + "\nIndex: " + getIndex() + "\nDock: " + getParent() + "\nSkill: " + skill);
                if(!sc2.hasNext()){
                    //sc2.close();
                }else{
                sc2.nextLine();               
                }
            }else{
                sc2.nextLine();
            }
        }
    }
    
    //Getters
    public String getSkill(){return skill;}
    
    //Overriding toString method        
    @Override
    public String toString(){
        return personArray.toString();
    }

}