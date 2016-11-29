import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CommandLineInterface extends JFrame  
	{
	private static final Parsing parse = new Parsing();
	
//	public static boolean name(String input){
//	parse.parseLine(input);
//	boolean valid = valid ();
//	return valid;
//		}
public static String executeCmd(String cmd)	{ 
	if(cmd.equals("proc"))

		return proc();
	else
		return null;
//	if(parse.getcmd().equals("mem"))
//		mem();
//	if(parse.getcmd().equals("exe"))
//		exe();
//	if(parse.getcmd().equals("reset"))
//		reset();
//	if(parse.getcmd().equals("exit"))
//		exit();
//	if(parse.getcmd().equals("load"))
//		if(parse.getval()!= null)
//		load();
	}
	    public void proc() {
	    	//return "test";
	    	JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String[] col = {"Process State", "Program Name", "Memory Usage", "Cycles Complete", "Cycles Left",
					"CPU Time Needed","CPU Time Used", "I/O Requests Performed"};
			PCB pcb = new PCB();
			Clock cloc = new Clock();
			Object rowData[][] = { { pcb.getState(), "" , "","" } };
			    
			
			JTable table = new JTable(rowData, col);
			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane, BorderLayout.CENTER);
			frame.setSize(300, 150);
			frame.setVisible(true);
	    	
	    }
	    	public void mem(){
	    		Runtime runtime = Runtime.getRuntime();
	    		long Memory = runtime.totalMemory() - runtime.freeMemory();
	    		String num = Long.toString(Memory);
	    		JFrame frame = new JFrame();
	    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		String[] col = {"Memory Used"};
	    		
	    		
	    		Object rowData[][] = { { num} };
	    		    
	    		
	    		JTable table = new JTable(rowData, col);
	    		JScrollPane scrollPane = new JScrollPane(table);
	    		frame.add(scrollPane, BorderLayout.CENTER);
	    		frame.setSize(300, 150);
	    		frame.setVisible(true);
	    }

	    private static void load() {	      
	    
	    }
	    private static void exe() {
	    
	    }

	    private static void reset() {

	    }
	    
	    private static void exit() {
	    	window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			//or
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//or
			System.exit(0);
	    }

	    private static void promptUser(){

	    }
	}
//CommandLineInterface enables the user to type commands in a terminal or console window to interact with the OS.

//COMMANDS

/*proc()
  mem()
  load()
  exe()
  reset()
  promptUser()
  */

