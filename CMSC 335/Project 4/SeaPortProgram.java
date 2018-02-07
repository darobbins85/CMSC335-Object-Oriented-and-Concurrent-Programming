/*
 * File: 
 * Author: David Robbins
 * Date: 2017.08.12
 * Purpose: Create Project 4 GUI
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;

public class SeaPortProgram extends JFrame{
    ArrayList <SeaPort> ports = new ArrayList <SeaPort>();
    ArrayList <Dock> docks = new ArrayList <Dock>(); 
    ArrayList <PassengerShip> pships = new ArrayList <PassengerShip>();
    ArrayList <CargoShip> cships = new ArrayList <CargoShip>();
    ArrayList <Person> persons = new ArrayList <Person>();
    ArrayList <Job> jobs = new ArrayList <Job>();
    
    final static int interval = 1000;
    
    String jfcString;
    HashMap<Integer, Thing> thingsHash = new HashMap<Integer, Thing>();
    
    JPanel jp;
    JTextArea jta = new JTextArea ();
    JTextField jtf;
    JobPanel jobpanelmaker;
    
    public SeaPortProgram(){
        
        setTitle ("Sea Port Program");
        setSize (600, 300);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible (true);        
        
        JScrollPane jsp = new JScrollPane (jta);
        add (jsp, BorderLayout.CENTER);
        
        JButton jbr = new JButton ("Read");
        JButton jbd = new JButton ("Display");
        JButton jbs = new JButton ("Search");

        
        JLabel jls = new JLabel ("Search by Index: ");      
        jtf = new JTextField (10);      
        jp = new JPanel ();
        jp.add (jbr);
        jp.add (jbd);
        jp.add (jls);
        jp.add (jtf);
        jp.add (jbs);        
        add (jp, BorderLayout.PAGE_START);
        
        jobpanelmaker = new JobPanel();
        jobpanelmaker.addPanel().add(new JLabel("Testing job panel for functionality..."));
        add(jobpanelmaker.addPanel(), BorderLayout.SOUTH);

        validate();

        
        jbr.addActionListener (new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choose SeaPort Data File");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(jfc.showOpenDialog(jbr) == JFileChooser.APPROVE_OPTION){}
            jfcString = jfc.getSelectedFile().getAbsolutePath();
            try {
                File fileone = new File(jfcString);
                readFile(new Scanner(fileone));
            } catch (FileNotFoundException ex) {
                System.out.println("No File");
            }
            }
        });
        
        jbd.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayPort();
            }
        });
        
        jbs.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                search (jtf.getText());
            }
        });
    }
    
    public void readFile (Scanner sf) {
        String inline = null;
        Scanner line = null;
        while (sf.hasNext()) {
            inline = sf.nextLine().trim();            
            if (inline.length() == 0) continue;
            line = new Scanner (inline);
            String arr[] = inline.split(" ", 2);
            switch (arr[0]){
                case "port"     : addPort     (line); break;
                case "dock"     : addDock     (line); break;
                case "pship"    : addPship    (line); break;
                case "cship"    : addCship    (line); break;
                case "person"   : addPerson   (line); break;
                case "job"      : addJob      (line); break;
            } // end switch                        
            
        } // end while reading data file

        EventQueue.invokeLater(new Runnable() { 
            public void run(){
                TreePanel portTree = new TreePanel("Data Tree", "SeaPort Info", thingsHash);
            }
        });
        
    } // end method readFile
   
    public void addPort(Scanner sc){
        SeaPort port = new SeaPort(sc);
        ports.add(port);
        thingsHash.put(port.index, port);
    }
    
    public void addDock(Scanner sc){
        Dock dock = new Dock(sc);
        docks.add(dock);
        thingsHash.put(dock.index, dock);
    }
    
    public void addPship(Scanner sc){
        PassengerShip pship = new PassengerShip(sc);
        pships.add(pship);
        thingsHash.put(pship.index, pship);
    }
    
    public void addCship(Scanner sc){
        CargoShip cship = new CargoShip(sc);
        cships.add(cship);
        thingsHash.put(cship.index, cship);
    }
    
    public void addPerson(Scanner sc){
        Person person = new Person(sc);
        persons.add(person);
        thingsHash.put(person.index, person);
    }
    
    public void addJob(Scanner sc){
        Job job = new Job(sc, jobpanelmaker, pships.get(0)); //Hardcoded in first passenger ship in data file 
        job.run();
        jobs.add(job);
        thingsHash.put(job.index, job);
    }

    public void displayPort () {
        Set<Integer> keys = thingsHash.keySet();
        Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
        Arrays.sort(keyArray);
            
        String st = "";
        int i = 0;
         
        while(keyArray.length != i){
            st += thingsHash.get(keyArray[i]).toString() + "\n";
            i++;
        }
        
        jta.append(st);
    } // end method readFile
    
    public void search (String target) {
        jta.setText(null);
        String st = "";
        st+= thingsHash.get(Integer.parseInt(target));
        jta.append(st);
    } // end method readFile
    
    public static void main (String [] args) {
        SeaPortProgram sc = new SeaPortProgram();
    } // end main
} // end class SeaPortProgram


class Thing{
    int index=0, parent=0;
    String name = "default", dump;
    
    public Thing(Scanner sc){
        dump = sc.next();        
        name = sc.next();
        index = sc.nextInt();
        parent = sc.nextInt();
    }
    

    public String toString(){
        return "Thing: " + name;
    }

} // end class Thing

class World extends Thing{
    ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
    
    public World(Scanner sc){
        super(sc);
    }
    
    public void addPort(SeaPort p){
        ports.add(p);
    }
    
    public String toString(){
        String st = "The World\n";
        return st;
    }
} // end class World

class SeaPort extends Thing{
    
    public SeaPort(Scanner sc){
        super(sc);
    }    
    
    public String toString(){
        String st = "\nSeaPort: " + name + "\nIndex: " + index + "\nParent: " + parent; 
        return st;
    }
}

class Dock extends Thing{
    Ship ship;
    
    public Dock(Scanner sc){
        super(sc);
    }
    
    public String toString(){
        return "\nDock: " + name + "\nIndex: " + index + "\nParent: " + parent;
    }

    void submitJob(Job aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Ship extends Thing{
    PortTime arrivalTime, dockTime;            
    double draft, length, weight, width;
    //ArrayList<Job> jobs = new ArrayList<Job>(); ------>I AM ASSUMING THIS IS THE PART OF MY CODE THAT NEEDS FIXING THE MOST SO THAT I CAN UN NOTE THE BLOCK OF CODE IN THE JOB CLASS THAT HANDLES THE THREADING
    boolean docked;                             
    
    public Ship(Scanner sc){
        super(sc);
        draft = sc.nextDouble();
        length = sc.nextDouble();
        weight = sc.nextDouble();
        width = sc.nextDouble();
        arrivalTime = new PortTime(1000);
        dockTime = new PortTime(1000);
        docked = true;
    }
    
    public String toString(){;
        return "\nShip: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nDraft: " + draft
                + "\nLength: " + length + "\nWeight: " + weight + "\nWidth: " + width;
    }

    
}

class PassengerShip extends Ship{
    int rooms, occupiedRooms, passengers;
    
    public PassengerShip(Scanner sc){
        super(sc);
        rooms = sc.nextInt();
        occupiedRooms = sc.nextInt();
        passengers = sc.nextInt();
    }
    
    public String toString(){
        return "\nShip: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nDraft: " + draft
                + "\nLength: " + length + "\nWeight: " + weight + "\nWidth: " + width + "\nRooms: " + rooms
                + "\nOccupied Rooms: " + occupiedRooms + "\nPassengers: " + passengers;
    }
}

class CargoShip extends Ship{
    double value, volume, weight;
    
    public CargoShip(Scanner sc){
        super(sc);
        value = sc.nextDouble();
        volume = sc.nextDouble();
        weight = sc.nextDouble();
    }
    
    public String toString(){
        return "\nShip: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nDraft: " + draft
                + "\nLength: " + length + "\nWeight: " + weight + "\nWidth: " + width + "\nCargo Value: "
                + value + "\nCargo Volume: " + volume + "\nCargo Weight: " + weight;
    }
}

class Person extends Thing{
    String skill;
    boolean busyFlag = false;
    
    public Person(Scanner sc){
        super(sc);
        skill = sc.next();
    }
    
    public String toString(){
        return "\nPerson: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nSkill: " + skill;
    }
    
}

class Job extends Thing implements Runnable {
 JobPanel jobPanel;
 Ship ship;
 World world;
 boolean isGo;
 double duration;
 ArrayList<String> requirements = new ArrayList<String>();
 public volatile boolean running = true;
 public volatile boolean paused = false;
 private final Object pauseLock = new Object();
 public volatile boolean isComplete = false;
 
 public Job(Scanner sc, JobPanel jobPanel, Ship ship) { //ENDED UP HARDCODING A SINGLE SHIP IN WHEN PASSING IN A VALUE TO THE SHIP PARAMETER
    super(sc);
    duration = sc.nextDouble();
    while(sc.hasNext()){
        requirements.add(sc.next());
    } 
    this.jobPanel = jobPanel;
    this.ship = ship;
    isGo = false;  
 }
 
 public String toString() {
  return "\nPerson: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nDuration: " + duration + "\nRequirements: " + requirements.toString();
 }
 
 public void run() {
  JPanel aPanel = new JPanel();
  aPanel.setLayout(new FlowLayout());
  JButton hold = new JButton("Pause");
  JButton restart = new JButton("Resume");
  JButton cancel = new JButton("Cancel");
  JProgressBar j = new JProgressBar();
  j.setString(name);
  j.setStringPainted(true);
  int max = Math.round((float)duration);
  j.setMaximum(max);
  aPanel.add(hold);
  aPanel.add(restart);
  aPanel.add(cancel);
  aPanel.add(j);
  jobPanel.addJob(aPanel);
  
  hold.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    pause();
   }
  });
  restart.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    resume();
   }
  });
  cancel.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    stop();
   }
  });
  
  /*int i = 0;
  while(running) {
   synchronized(pauseLock) {
    if(!running) {
     break;
    }
    if(paused) {
     try {
      pauseLock.wait();
     } catch (InterruptedException ex) {
      break;
     }
     if(!running) {
      break;
     }
    }
   }
   if (!ship.docked) { //IF I UNDERSTOOD HOW THE SHIP SHOULD OR SHOULDN'T BE DOCKED I CAN GET PAST THIS PART OF THE CODE WHICH IS CURRENTLY WHERE I AM GETTING AN ERROR
    try {
     Thread.sleep(100);
    } catch (InterruptedException e) {
     //e.printStackTrace();
    }
   }
   if (ship.docked) {
    j.setValue(i);
    jobPanel.revalidate();
    jobPanel.repaint();
    try {
     Thread.sleep(75);
    } catch (InterruptedException e) {
     //e.printStackTrace();
    }
    if (i >= duration) {
     alert();
    }
    i++;
   } 
  }*/ //--------> CHOSE TO NOTE OUT THIS BLOCK BECAUSE IT CAUSES THE PROGRAM TO FILL IN ALL BLACK FOR SOME UNKOWN REASON
 }
 
 public void stop() {
  running = false;
 }
 
 public void pause() {
  paused = true;
 }
 
 public void resume() {
  synchronized(pauseLock) {
   paused = false;
   pauseLock.notifyAll();
  }
 }
 
 public synchronized void alert() {
  jobPanel.incrementJobsCompleted(ship);
  stop();
 }    

}

class JobPanel {
    JPanel jp;
    
    JobPanel(){
        jp = new JPanel();
    }
    
    public JPanel addPanel(){
        return jp;
    }
    
    void addJob(JPanel p) {
        jp.add(p);
    }

    void revalidate() {
        jp.validate();
    }

    void repaint() {
        jp.repaint();
    }

    void incrementJobsCompleted(Ship ship) { //-----> SUSPECT THAT I DO NOT HAVE THE METHOD DEFINED CORRECTLY
        jp.removeAll();
    }

}

class PortTime{ //DONT GET WHERE I AM TO FIND THE VALUE OF TIME FOR EACH PORT
    int time;
    
    public PortTime(int t){
        this.time = t;
    }
    
    public String toString(){
        String st = "" + time;
        return st;
    }
}

class TreePanel extends JFrame
{
    private JTree tree;
    
    public TreePanel(String windowName, String rootName, HashMap<Integer, Thing> hash)
    {
        //this.hash = hash;
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootName);
        //create the child nodes
        DefaultMutableTreeNode node = null;
        Set<Integer> keys = hash.keySet();
        Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
        Arrays.sort(keyArray);
        
        int i = 0;
        while(keyArray.length != i){
            if(node != root){
                node = new DefaultMutableTreeNode(hash.get(keyArray[i]).name);
                DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Index: " + hash.get(keyArray[i]).index);
                DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Parent: " + hash.get(keyArray[i]).parent);
                if(hash.get(i) == null){
                    root.add(node);
                    node.add(node1);
                    node.add(node2);
                    i++;
                }else{
                    break;
                }            
            }                              
        }
        
        JPanel panel = new JPanel();
        tree = new JTree(root);
        panel.add(tree);
        JScrollPane x = new JScrollPane(panel);
        add(x);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(windowName);       
        this.pack();        
        this.setVisible(true);
    }     

}