import java.util.*;
import java.io.*;

public class Parsing {

    private String readFile;
    private String cmd, val;
    private PriorityQueue<String> queue = new PriorityQueue<String>();
    private Scanner input;
    
    public Parsing() {
    }
    public void parseFile(String filename){
        this.readFile = "executions\\"+filename;
       System.out.println(readFile);
       parseFile();
    }
    public void parseLine(String command) {
     command = command.toLowerCase();
     input = new Scanner(command);
     this.cmd = input.next();
     val = null;
     if(input.hasNext())
    	 val = input.next();
     input.close();
    }

    private void parseFile() {
        try {
        	File sampleFile = new File(readFile);
            input = new Scanner(sampleFile);
            while(input.hasNext()) {
                queue.add(input.next());
            }
        } catch (Exception e) { System.out.println("FILE NOT FOUND EXCEPTION"); }
        input.close();
    }

    public PriorityQueue<String> getQueue() {
      return queue;
    }
    
    public String getcmd() {
    	return cmd;
    }
     public String getval(){
    	 return val;
        }
    }
