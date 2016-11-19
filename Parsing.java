import java.util.*;
import java.io.*;

public class Parsing {

    public String inputfile = "input.txt";
    public PriorityQueue<String> queue = new PriorityQueue<String>();
    public File file = new File(inputfile);
    public Scanner input;
    public String command, value;

    public Parsing() {
    }
    public void parseFile(String filename){
        this.inputfile = filename;
        parse();
    }
    public void parseLine(String command) {
      command.toLowerCase();
      input = new Scanner(command);
      command = input.next();
      value = null;
      if(input.hasNext())
        value = input.next();
      input.close();
    }

    private void parse() {
        try {
            input = new Scanner(file);
            while(input.hasNext()) {
                queue.add(input.next());
            }
        } catch (Exception e) { System.out.println("__________"); }
        input.close();
    }

    public PriorityQueue<String> getStuff() {
      return queue;
    }
    
    public void testprint() {

        while(queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }
}