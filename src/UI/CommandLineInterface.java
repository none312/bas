import javax.swing.JFrame;

public class CommandLineInterface extends JFrame  
	{
// 	//Executes the commands to the OS and simulators
	
// 	long mem;
// 	int valueOfMem;
// 	int counter = 0;
// 	String line = null;
// 	String programName = null;
// 	String num;
// 	int numberOfCycles;
// 	PriorityQueue<String> newProcess = new PriorityQueue<String>();
	
// 	private JFrame mainFrame;
// 	private JPanel controlPanel;
	
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
// 		DefaultTableModel  tableModel = new DefaultTableModel(col, 0);
// 		JTable table = new JTable(tableModel);
// 		JScrollPane tableContainer = new JScrollPane(table);
// 		tableContainer.getViewport().setViewPosition(new Point(5,5));
// 		mainFrame.add(tableContainer, BorderLayout.CENTER);
// 		mainFrame.setSize(300, 150);
// 		//controlPanel.setLayout(new BorderLayout());
// 		//controlPanel.add(tableContainer, BorderLayout.CENTER);
// 		//controlPanel.add(tableContainer,  BorderLayout.PAGE_START);
// 		//mainFrame.getContentPane().add(controlPanel);
// 		mainFrame.pack();
// 		mainFrame.setVisible(true)
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

