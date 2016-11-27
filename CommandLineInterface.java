import javax.swing.JFrame;

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
	    private static String proc() {
	    	return "test";
	    }

	    private static void mem() {

	    }

	    private static void load() {	      
	    
	    }
	    private static void exe() {
	    
	    }

	    private static void reset() {
		//mainFrame = the frame name you initially set
		//mainFrame.repaint();
		//mainFrame.setVisible(true);
	    }
	    
	    private static void exit() {
	    // yourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //or
	    //mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
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

