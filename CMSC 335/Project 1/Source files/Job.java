/*
 * File: Job.java
 * Author: David Robbins
 * Date: 2017.06.26
 * Purpose: Build job object -- Unused in Project 1
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Job extends Thing{

    //Class variables    
    private double duration;
    private ArrayList<String> requirements;
    
    public Job(Scanner sc){
        super(sc);  
    }
    
    public double getDuration(){return duration;}
    public ArrayList getRequirements(){return requirements;}
    
    @Override
    public String toString(){
        return super.toString() + "Duration: " + getDuration() + "Requirements: " + getRequirements(); 
    }
}