/*
 * File: 
 * Author: David Robbins
 * Date: 2017.07.29
 * Purpose: Create Project 3 GUI
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;

public class SeaPortProgram extends JFrame{
    ArrayList <SeaPort> ports = new ArrayList <SeaPort>(); // the ports
    ArrayList <Dock> docks = new ArrayList <Dock>(); // the ports
    ArrayList <PassengerShip> pships = new ArrayList <PassengerShip>(); // the ports
    ArrayList <CargoShip> cships = new ArrayList <CargoShip>(); // the ports
    ArrayList <Person> persons = new ArrayList <Person>(); // the ports
    ArrayList <Job> jobs = new ArrayList <Job>(); // the ports
    
    final static int interval = 1000;
    int i;
    Timer t1,t2,t3;
    
    String jfcString;
    Scanner scan;
    HashMap<Integer, Thing> thingsHash = new HashMap<Integer, Thing>();
    
    JPanel jp;
    JTextArea jta = new JTextArea ();
    JTextField jtf;
    
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
        
        JButton job1 = new JButton ("Job 1");
        JButton job2 = new JButton ("Job 2");
        JButton job3 = new JButton ("Job 3");
        JProgressBar j1 = new JProgressBar(0, 5);
        j1.setValue(0);
        j1.setStringPainted(true);
        JProgressBar j2 = new JProgressBar(0, 10);
        j2.setValue(0);
        j2.setStringPainted(true);
        JProgressBar j3 = new JProgressBar(0, 15);
        j3.setValue(0);
        j3.setStringPainted(true);
        
        JLabel jls = new JLabel ("Search by Index: ");
        
        jtf = new JTextField (10);
        
        jp = new JPanel ();
        jp.add (jbr);
        jp.add (jbd);
        jp.add (jls);
        jp.add (jtf);
        jp.add (jbs);
        
        add (jp, BorderLayout.PAGE_START);
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(3,2));
        southPanel.add(j1);
        southPanel.add(job1);
        southPanel.add(j2);
        southPanel.add(job2);
        southPanel.add(j3);
        southPanel.add(job3);
        add(southPanel, BorderLayout.SOUTH);
        
        validate ();
        
        job1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                i = 0;
                t1.start();
                job1.setEnabled(false);
            }
            
        });
        
        job2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                i = 0;
                t2.start();
                job2.setEnabled(false);
            }
            
        });
        
        job3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                i = 0;
                t3.start();
                job3.setEnabled(false);
            }
            
        });
        
        t1 = new Timer(interval, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(i==20){
                    t1.stop();
                    job1.setEnabled(true);
                }else{
                    i++;
                    j1.setValue(i);
                }
            }
            
        });
        
        t2 = new Timer(interval, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(i==20){
                    t2.stop();
                    job2.setEnabled(true);
                }else{
                    i++;
                    j2.setValue(i);
                }
            }
            
        });
        
        t3 = new Timer(interval, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(i==20){
                    t3.stop();
                    job3.setEnabled(true);
                }else{
                    i++;
                    j3.setValue(i);
                }
            }
            
        });
        
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
            } // end switch
            
        } // end while reading data file
        addJobs();
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
    
    public void addJobs(){
        Job job = new Job(thingsHash, jp);
        jobs.add(job);
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
} // end class SorcerersCave


class Thing{
    int index=0, parent=0;
    String name = "default";
    
    public String toString(){
        return "Thing: " + name;
    }
} // end class Thing

class World extends Thing{
    ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
    
    public String toString(){
        String st = "The World\n";
        return st;
    }
} // end class World

class SeaPort extends Thing{
    ArrayList<Dock> docks = new ArrayList<Dock>();
    ArrayList<Ship> que = new ArrayList<Ship>();
    ArrayList<Ship> ships = new ArrayList<Ship>();
    ArrayList<Person> persons = new ArrayList<Person>();
    
    public SeaPort(Scanner sc){
        sc.next();        
        name = sc.next();
        index = sc.nextInt();
        parent = sc.nextInt();
    }
    
    public void addDock (Dock d){
        docks.add(d);
    }
    public void addShipToQue(Ship s){
        que.add(s);
    }
    public void addShip(Ship s){
        ships.add(s);
    }
    public void addPeople(Person p){
        persons.add(p);
    }
    
    public String toString(){
        String st = "\nSeaPort: " + name + "\nIndex: " + index + "\nParent: " + parent; 
        return st;
    }
}

class Dock extends Thing{
    Ship ship;
    
    public Dock(Scanner sc){
        sc.next();
        name = sc.next();
        index = sc.nextInt();
        parent = sc.nextInt();
    }
    
    public String toString(){
        return "\nDock: " + name + "\nIndex: " + index + "\nParent: " + parent;
    }
}

class Ship extends Thing{
    PortTime arrivalTime, dockTime;
    double draft, length, weight, width;
    ArrayList<Job> jobs = new ArrayList<Job>();
    
    public Ship(Scanner sc){
        sc.next();
        name = sc.next();
        index = sc.nextInt();
        parent = sc.nextInt();
        draft = sc.nextDouble();
        length = sc.nextDouble();
        weight = sc.nextDouble();
        width = sc.nextDouble();
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
        sc.next();
        name = sc.next();
        index = sc.nextInt();
        parent = sc.nextInt();
        skill = sc.next();
    }
    
    public String toString(){
        return "\nPerson: " + name + "\nIndex: " + index + "\nParent: " + parent + "\nSkill: " + skill;
    }
    
}

//USING THE CAVE EXAMPLE, THIS WAS MY ATTEMPT TO GET THE PROGRESS BARS WORKING
class Job extends Thing implements Runnable {
    double duration;
    ArrayList<String> skills = new ArrayList<String>();
    
    static Random rn = new Random();
    JPanel parent;
    Person person = null;
    int jobIndex;
    int jobTime;
    String jobName = "";
    JProgressBar pm = new JProgressBar();
    boolean goFlag = true;
    boolean noKillFlag = true;
    JButton jbGo = new JButton("Stop");
    JButton jbKill = new JButton("Cancel");
    Status status = Status.SUSPENDED;
    
    enum Status {RUNNING, SUSPENDED, WAITING, DONE};
    
    public Job(HashMap<Integer, Thing> thingsHash, JPanel cv){
        parent = cv;
        //sc.next(); 
        //sc.next();
        //System.out.println(thingsHash.toString());
        //System.out.println(thingsHash.keySet());
        
        Set<Integer> keys = thingsHash.keySet();
        Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
        Arrays.sort(keyArray);
        
        String st = "";
        int i = 0;
         
        // THIS IS WHERE I WAS TRYING TO ATTACH AN INDEX TO A JOB TO TROUBLESHOOT THIS PART OF THE PROJECT
        while(keyArray.length != i){ 
            st += thingsHash.get(keyArray[i]).toString() + "\n";
            i++;
        }
        
        System.out.println(st);
        
        //jobIndex = sc.nextInt();sc.nextInt();
        //jobName = sc.next();
        //int target = jobIndex;
        //person = (Person)thingHash.get(target);
        //jobTime = sc.nextInt();
        
        pm = new JProgressBar();
        pm.setStringPainted(true);
        parent.add(pm);
        //parent.add(new JLabel(person.name, SwingConstants.CENTER));
        //parent.add(new JLabel(jobName, SwingConstants.CENTER));
        
        parent.add(jbGo);
        parent.add(jbKill);
        
        jbGo.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               toggleGoFlag();
           } 
        });
        
        jbKill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setKillFlag();
            }
        });
        
        new Thread (this).start();
    }
    
    public void toggleGoFlag(){
        goFlag = !goFlag;
    }
    
    public void setKillFlag(){
        noKillFlag = false;
        jbKill.setBackground(Color.red);
    }
    
    void showStatus(Status st){
        status = st;
        switch(status){
            case RUNNING:
                jbGo.setBackground(Color.green);
                jbGo.setText("Running");
                break;
            case SUSPENDED:
                jbGo.setBackground(Color.yellow);
                jbGo.setText("Suspended");
                break;
            case WAITING:
                jbGo.setBackground(Color.orange);
                jbGo.setText("Waiting");
                break;
            case DONE:
                jbGo.setBackground(Color.red);
                jbGo.setText("Done");
                break;
        }
    }
    
    public void run(){
        long time = System.currentTimeMillis();
        long startTime = time;
        long stopTime = time + 1000 * jobTime;
        double duration = stopTime - time;
        
        /*synchronized (person){
            while(person.busyFlag){
                showStatus(Status.WAITING);
                try{
                    person.wait();
                }catch(InterruptedException e){
                    System.out.println("ERROR");
                }
            }
            person.busyFlag = true;
        }*/
        
        /*while(time < stopTime && noKillFlag){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                System.out.println("ERROR");
            }
            if(goFlag){
                showStatus(Status.RUNNING);
                time += 100;
                pm.setValue((int)(((time - startTime) / duration) * 100));                
            }else{
                showStatus(Status.SUSPENDED);
            }
        }*/
        
        pm.setValue(100);
        showStatus(Status.DONE);
        /*synchronized(person){
            person.busyFlag = false;
            person.notifyAll();
        }*/
    }
    //I GAVE UP ON THIS DUE TO TIME CONSTRAINTS BUT WILL ADD A RANDOM PROGRESS BAR TO MY JFRAME TO SHOW I UNDERSTAND ITS BASIC USAGE
    
    
    public String toString(){
        String st = " ";
        return st;
    }

}

class PortTime{
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