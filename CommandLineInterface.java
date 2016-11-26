public class CommandLineInterface extends Gui 
	{
	private static final Parsing parse = new Parsing();
	
	public static boolean name(String input){
	parse.parseLine(input);
	boolean valid = valid();
	
private static boolean valid()	{
	if(parse.getcmd().equals("proc"))
		return true;
	if(parse.getcmd().equals("mem"))
		return true;
	if(parse.getcmd().equals("exe"))
		return true;
	if(parse.getcmd().equals("reset"))
		return true;
	if(parse.getcmd().equals("exit"))
		return true;
	if(parse.getcmd().equals("load"))
		if(parse.getval()!= null)
			return true;
	return false;
	}
private static void choosecmd(String cmd){
	switch(cmd){
	
	case "proc": proc(); 
	break;
	
	case "mem": mem(); 
	break;
	
	case "exe": exe();
	break;
	
	case "reset": reset();
	break;
	
	case "exit": exit();
	break;
	
	default: break;
	}
}
	    private static void proc() {

	    }

	    private static void mem() {

	    }

	    private static void load() {	      
	    
	    }
	    private static void exe() {
	    
	    }

	    private static void reset() {

	    }
	    
	    private static void exit() {
	      
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

