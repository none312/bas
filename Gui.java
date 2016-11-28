import java.awt.Color;
import java.awt.GridLayout; //import default layout managing(ordering)
import java.awt.List;

import javax.swing.JFrame; // basic windows feature(title bar, minimize/maximize)
import javax.swing.JLabel; // outputs text+images on screen
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.awt.event.ActionEvent;
import java.awt.color.*;

public class Gui {
	private static JFrame window = new JFrame();

	public static void main(String[] args) {
		Gui example = new Gui();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1600, 700);
		window.setVisible(true);
	}

	private JPanel pcb = new JPanel();
	private JPanel gnatt = new JPanel();
	private JPanel stats = new JPanel();
	private JPanel output = new JPanel();
	public JTable jt;
	public JPanel input = new JPanel(new GridLayout(2, 0));
	public JLabel txl = new JLabel("output");
	public JTextField txtfd = new JTextField();
	public JTextPane display = new JTextPane();

	// text from text area and set it to the output
	private void setInputPanel() {
		// txtfd.addKeyListener(new java.awt.event.KeyAdapter() {
		// public void keyPressed(java.awt.event.KeyEvent evt) {
		// txtfd(evt);
		// }
		//
		// private void txtfd(KeyEvent evt) {
		// int keyCode = evt.getKeyCode();
		// if (keyCode == 10) {
		// txl.setText(cmd.executeCmd(txtfd.getText())); // user input
		// // and set
		// // result to
		// // output
		// // label }
		// }
		// }
		// });

		txtfd.setBackground(Color.black);
		txtfd.setForeground(Color.white);
		txtfd.setCaretColor(Color.white);
		input.add(txtfd);

		display.setText("OUTPUT");
		display.setBackground(Color.black);
		display.setForeground(Color.white);
		input.add(display);

	}

	public void createProcTable(StringBuilder sb) {
		// String data[][] = { { "Web Browser", "100Kb","RUN", "1000", "2000",
		// "2" }, { "Media Player", "40Kb","WAIT", "100", "200","4" }};
		String column[] = { "PROGRAM", "MEM USAGE", "STATE", "CYCLES COMPLETE", "CYCLES LEFT", "I/O PERFORMED" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		
	//	String data[][] = new String[100][100];
		jt = new JTable(model);
		
		jt.setModel(model);
		String[] row = new String[7];
		if(!sb.toString().equals(""))
		{
			String[] procList = sb.toString().split(",");
			for(int i = 0; i<procList.length; i++)
			{
				row[i]=procList[i];
				}
		}
		model = (DefaultTableModel)jt.getModel();
		model.addRow(row);
		JScrollPane sp = new JScrollPane(jt);
		pcb.add(sp);
		window.add(pcb);


		// jt.setBounds(30, 40, 200, 300);
//		StringBuilder builder = new StringBuilder();
//		builder.append("web,10kb,run,20,10,1");
//		int index = 0;
//		if (!sb.toString().equals("")) {
//			String[] procList = sb.toString().split(",");
//			for (int i = 0; i < 2; i++) {
//				for (int j = 0; j < 6; j++) {
//					data[i][j] = procList[j];
//					// builder.delete(0, builder.indexOf(","));
//					// System.out.println("BUILDER " +
//					// builder.substring(0,builder.indexOf(",")));
//
//				}
//			}
//		}
	}
	public JLabel lab = new JLabel("pcb");
	public JLabel gt = new JLabel("gnatt");
	public JLabel st = new JLabel("stats");
	int limitCycles =0;
	StringBuilder procString = new StringBuilder();
	 PriorityQueue<Process> jobQueue;
	 PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	 final int TOTAL_MEMORY = 256;
	Scheduler scheduler = new Scheduler();
	
	public Gui() {
		
		
		// super("The title bar"); // title for the window
		//CommandLineInterface cmd = new CommandLineInterface();

		
		setInputPanel();
		window.add(input); // adding item to the window
		StringBuilder builder = new StringBuilder();
	//	builder.append("web,10kb,run,20,10,1");
		createProcTable(builder);
		

		
		gnatt.add(gt);
		stats.add(st);

		// window.add(pcb);
		window.add(gnatt);
		window.add(stats);

		txtfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("TXTFD " + txtfd.getText());
				display.setText(executeCmd(txtfd.getText()));
			}
		});
		window.setLayout(new GridLayout(2, 1)); // default layout

	}
	
	public  String executeCmd(String cmd) {
		if (cmd.equals("proc"))
			return proc();
		else if (cmd.startsWith("load ")) {
			return load(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		}
		// if(parse.getcmd().equals("mem"))
		// mem();
		else if (cmd.equals("exe"))
		{
			return exe();
		}
		else if (cmd.startsWith("exe "))
		{
			return exe(cmd.substring(cmd.lastIndexOf(" ")+1,cmd.length()));
		}
		else
			return "Invalid command";
		// if(parse.getcmd().equals("reset"))
		// reset();
		// if(parse.getcmd().equals("exit"))
		// exit();
		// if(parse.getcmd().equals("load"))
		// if(parse.getval()!= null)
		// load();
	}
	
	private  void executeProcess(String task, PriorityQueue<Process> readyQueue) {
		if (task.startsWith("CALCULATE"))
		{
			//int burstTime = Integer.parseInt(task.substring(task.lastIndexOf(" ") + 1, task.length()));
		//	pr.pcb.setBurstTime(burstTime);
			scheduler.fcfs(readyQueue);
		}
		else if(task.equals("IO"))
		{
			IO io = new IO();
			io.generateIoBurstTime();
		}
	}
	
	private  String proc() {
		StringBuilder builder = new StringBuilder();
		
		System.out.println("READY QUEUE " + procString.toString());
		
		createProcTable(procString);
		
		//System.out.println("WAITING QUEUE " + scheduler.getWaitingQueue());
		return "test";
	}

	private  void mem() {

	}

	private  String load(String fileName) {
		jobQueue = new PriorityQueue<Process>();
		StringBuilder builder = new StringBuilder();
		long curTime = System.currentTimeMillis();

		Process p = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			// while(br.readLine()!=null)
			p = new Process(br.readLine(), curTime, "New");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jobQueue.add(p);
		int memory=0;
		builder.append("LOADING " + jobQueue.peek().pcb.getName() + "...........");
		while (jobQueue.size() > 0 && memory < TOTAL_MEMORY) {
			Process pr = jobQueue.poll();
			pr.pcb.setArrivalTime(System.currentTimeMillis());
			pr.pcb.setState("Ready");
			readyQueue.add(pr);
			procString.append(pr.pcb.getName()+",10kb,"+pr.pcb.getState()+",CYCLE COMPLETE,"+"CYCLES LEFT,"+"IO");
			
		}
		builder.append("\n"+readyQueue);
		return builder.toString();

	}

	private  String exe() {
		StringBuilder builder = new StringBuilder();
		int memory = 0;
		if (jobQueue.size() == 0) {
			System.out.println("Error: No jobs availabled for executing");
			return null;
		}
		Process pr =null;
		System.out.println("SHOULD GO HERE " + readyQueue);
		scheduler.fcfs(readyQueue);
//		}
//		while(readyQueue.size()>0){
//			try {
//				LineNumberReader br = new LineNumberReader(new FileReader(pr.pcb.getName()));
//				for (String line = null; (line = br.readLine()) != null;) {
//					if (br.getLineNumber() >= 2) {
//					//	executeProcess(line,pr);
//						builder.append(line).append("\n");
//					}
//				}
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		//}

		// int burstTime = Integer.parseInt(delimiter(pr.pcb.getName()));
		// pr.pcb.setBurstTime(burstTime);
		// scheduler.insertPCB(jobQueue.poll());
		// scheduler.fcfs();
		//System.out.println("BUILDER " + builder.toString());
		return builder.toString();
	}
	private  String exe(String limitCycles) {
		StringBuilder builder = new StringBuilder();
		int memory = 0;
		if (jobQueue.size() == 0) {
			System.out.println("Error: No jobs availabled for executing");
			return null;
		}
		Process pr =null;
		while (jobQueue.size() > 0 && memory < TOTAL_MEMORY) {
			pr = jobQueue.poll();
			pr.pcb.setArrivalTime(System.currentTimeMillis());
			pr.pcb.setState("Ready");
			readyQueue.add(pr);
		}
		int cycles =0;
		while(readyQueue.size()>0 && cycles<Integer.parseInt(limitCycles)){
			try {
				LineNumberReader br = new LineNumberReader(new FileReader(pr.pcb.getName()));
				for (String line = null; (line = br.readLine()) != null;) {
					if (br.getLineNumber() >= 2) {
						//executeProcess(line,pr);
						builder.append(line).append("\n");
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// int burstTime = Integer.parseInt(delimiter(pr.pcb.getName()));
		// pr.pcb.setBurstTime(burstTime);
		// scheduler.insertPCB(jobQueue.poll());
		// scheduler.fcfs();
		//System.out.println("BUILDER " + builder.toString());
		return builder.toString();
	}

	private  void reset() {

	}

	private  void exit() {

	}


}
