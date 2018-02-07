/*
 * File: World.java
 * Author: David Robbins
 * Date: 2017.07.16
 * Purpose: Build world object -- Unused in Project 2
 */

package project1;

import java.util.ArrayList;
import java.util.Scanner;

public class World extends Thing{

    //Class variables    
    private ArrayList<SeaPort> ports;
    private PortTime time;
    
    public World(Scanner sc, ArrayList<SeaPort> ports, PortTime time){
        super(sc);
        this.ports = ports;
        this.time = time;
    }
         
    @Override
    public String toString(){
        return super.toString() + "SeaPorts: " + ports + "Time: " + time;
    }
    
}