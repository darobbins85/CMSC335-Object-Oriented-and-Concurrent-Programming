/*
 * File: Person.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build person object
 */

package project1;

import java.util.*;

public class Person extends Thing{

    //Class variables    
    private String skill, job;
    public HashMap<Integer, String> personHash = new HashMap<>();
    
    //Constructor
    public Person(Scanner sc1, Scanner sc2){
        super(sc1);       
        int i = 0;
        while(sc2.hasNext()){
            if("person".equals(sc2.next())){
                setName(sc2.next());
                setIndex(sc2.nextInt()); 
                setParent(sc2.nextInt());
                skill = sc2.next();
                personHash.put(i, getName());
                i++;
                personHash.put(i, "" + getIndex());
                i++;
                personHash.put(i, "" + getParent());
                i++;
                personHash.put(i, skill);
                i++;                            
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
    
    public String getSkillByName(String x, HashMap<Integer, String> hms){
        int i = 0;
            while(!hms.isEmpty()){
                if(x.matches(hms.get(i))){
                    job = hms.get(i+3);
                }                
                i = i+4;
                if(hms.get(i) == null){
                    break;
                }
            }
        return job;
    }
    
    //Overriding method to sort names of people in alphabetical order
    @Override
    public String getSortedNames(){
        SortedSet<String> names = new TreeSet<>();

        int i = 0;
        while(personHash.get(i) != null){
            names.add(personHash.get(i));
            i = i+4;
        }
        
        return names.toString();
    }
    
    //Overriding toString method        
    @Override
    public String toString(){
        return personHash.toString();
    }

}