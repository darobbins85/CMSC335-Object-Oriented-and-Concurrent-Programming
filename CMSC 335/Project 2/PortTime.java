/*
 * File: PortTime.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build PortTime object -- Unused in Project 2
 */

package project1;

public class PortTime {

    //Class variables    
    private int time;
    
    public PortTime(int time){
        this.time = time;
    }
    
    public int getTime(){return time;}
    
    @Override
    public String toString(){
        return "Time: " + getTime();
    }
}