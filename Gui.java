import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout; //import default layout managing(ordering)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame; // basic windows feature(title bar, minimize/maximize)
import javax.swing.JLabel; // outputs text+images on screen
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Gui {
	private static final long Memory = 0;
	private static JFrame window = new JFrame();

	public static void main(String[] args) {
		Gui example = new Gui();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1600, 700);
		window.setVisible(true);
		window.getContentPane().setBackground(Color.WHITE);
	}
	

	private JPanel pcb = new JPanel(new BorderLayout());
	private JPanel gantt = new JPanel();
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

		txtfd.setBackground(Color.RED);
		txtfd.setForeground(Color.BLACK);
		txtfd.setCaretColor(Color.BLACK);
		input.add(txtfd);

		display.setText("OUTPUT");
		display.setBackground(Color.GREEN);
		display.setForeground(Color.BLACK);
		input.add(display);

	}

	public JTable pcbTable;
	DefaultTableModel model = new DefaultTableModel();

	// SPLIT multiple process, then split each attribute
	public void createPcbTable(StringBuilder sb) {
		String[] singlePcb, pcbInfo = null;
		String column[] = { "PROGRAM", "MEM LOCATION", "STATE", "BURST TIME", "ARRIVAL TIME" };
		System.out.println(sb.toString());
		// model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		pcbTable = new JTable(model);
		// pcbTable.setModel(model);
		Object row[] = new Object[6];
		if (!sb.toString().equals("")) {
			if (sb.toString().contains("\n")) {
				singlePcb = sb.toString().split("\n");
				for (int i = 0; i < singlePcb.length; i++) {
					pcbInfo = singlePcb[i].split(",");
				}
			}
			pcbInfo = sb.toString().split(",");
			for (int i = 0; i < pcbInfo.length; i++) {
				row[i] = pcbInfo[i];
			}
		}
		// model = (DefaultTableModel) jt.getModel();
		model.addRow(row);
	}

	public int row_count = 0;

	public void createProcTable(StringBuilder sb) {
		// String data[][] = { { "Web Browser", "100Kb","RUN", "1000", "2000",
		// "2" }, { "Media Player", "40Kb","WAIT", "100", "200","4" }};
		String column[] = { "PROGRAM", "MEM USAGE", "STATE", "CYCLES COMPLETE", "CYCLES LEFT", "I/O PERFORMED" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);

		// String data[][] = new String[100][100];
		jt = new JTable(model);

		jt.setModel(model);
		String row[] = new String[7];
		if (!sb.toString().equals("")) {
			String[] procList = sb.toString().split(",");
			for (int i = 0; i < procList.length; i++) {
				row[i] = procList[i];
			}
		}
		model = (DefaultTableModel) jt.getModel();
		model.insertRow(row_count, row);
		row_count++;
		model.fireTableDataChanged();
		JScrollPane sp = new JScrollPane(jt);
		pcb.add(sp);
		window.add(pcb);
	}

	public JLabel lab = new JLabel("pcb");
	public JLabel gt = new JLabel("gantt");
	public JLabel st = new JLabel("stats");
	int limitCycles = 0;
	StringBuilder procString = new StringBuilder();
	PriorityQueue<Process> jobQueue;
	PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	final int TOTAL_MEMORY = 256;
	Scheduler scheduler = new Scheduler();
	
	GanttChart gc = new GanttChart();

	public Gui() {

		// super("The title bar"); // title for the window
		// CommandLineInterface cmd = new CommandLineInterface();

		setInputPanel();
		window.add(input); // adding item to the window
		StringBuilder builder = new StringBuilder();
		// builder.append("web,10kb,run,20,10,1");
		// createProcTable(builder);
		builder.append("web" + ",0,Ready,100,0");
		createPcbTable(builder);

		JScrollPane sp = new JScrollPane(pcbTable);
		pcb.add(sp);
		window.add(pcb);
		stats.add(st);

		// window.add(pcb);
		window.add(gc);
		
		window.add(stats);

		txtfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				display.setText(executeCmd(txtfd.getText()));
			}
		});
		window.setLayout(new GridLayout(2, 1)); // default layout

	}

	public String executeCmd(String cmd) {
		if (cmd.equals("proc"))
			return proc();
		else if (cmd.startsWith("load ")) {
			return load(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		} else if (cmd.equals("mem"))
			return mem();
		// if(parse.getcmd().equals("mem"))
		// mem();
		else if (cmd.startsWith("exe ")) {
			return exe(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		}else if(cmd.equals("reset"))
			return reset();
		else if (cmd.equals("exit")) {
			return exit();
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

	private String proc() {
		
   JFrame frame = new JFrame();
   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   String[] col = {"Process State", "Program Name", "Memory Usage", "Cycles Complete", "Cycles Left", "I/O Requests Performed"};
   PCB pcb = new PCB();
   Clock cloc = new Clock();
   Object rowData[][] = { { pcb.getState(), "" , "","" } };
		JTable table = new JTable(rowData, col);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 150);
		frame.setVisible(true);
		
//		StringBuilder builder = new StringBuilder();
//
//		System.out.println("READY QUEUE " + procString.toString());
//
//		createProcTable(procString);
//
//		// System.out.println("WAITING QUEUE " + scheduler.getWaitingQueue());
		return "test";
	}

	private String mem() {
		createPcbTable(procString);
		return "mem";
//		
////		Runtime rt = Runtime.getRuntime();
////		Memory = rt.totalMemory() - rt.freeMemory();
//		String num = Long.toString(Memory);
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		String[] col = {"Memory Used"};
//		
//		Object rowData[][] = { { num} };
//		  
//		JTable table = new JTable(rowData, col);
//		JScrollPane scrollPane = new JScrollPane(table);
//		frame.add(scrollPane, BorderLayout.CENTER);
//		frame.setSize(300, 150);
//		frame.setVisible(true);
		
	}

	private String load(String fileName) {
		jobQueue = new PriorityQueue<Process>();
		StringBuilder builder = new StringBuilder();
		long curTime = System.currentTimeMillis();
		Process p = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			// while(br.readLine()!=null)
			Random r = new Random();
			String name = br.readLine();
			String memReq = br.readLine();
			p = new Process(name, curTime, "New", Integer.parseInt(memReq.substring(memReq.lastIndexOf(" ") + 1, memReq.length())), r.nextInt(100));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jobQueue.add(p);
		int memory = 0;
		builder.append("LOADING " + jobQueue.peek().pcb.getName() + "...........");
		while (jobQueue.size() > 0 && memory < TOTAL_MEMORY) {
			Process pr = jobQueue.poll();
			pr.pcb.setArrivalTime(System.currentTimeMillis());
			pr.pcb.setState("Ready");
			int totalCycles = totalCycles(pr.pcb.getName());
			readyQueue.add(pr);
			procString.append(pr.pcb.getName() + "," + pr.pcb.getMemAddress() + "," + pr.pcb.getState() + ","
					+ totalCycles + "," + pr.pcb.getArrivalTime() + "\n");

		}
		builder.append("\n" + readyQueue);
		return builder.toString();
	}

	private int totalCycles(String fileName) {
		LineNumberReader br;
		int totalCycles = 0;
		try {
			br = new LineNumberReader(new FileReader(fileName));
			for (String line = null; (line = br.readLine()) != null;) {
				if (br.getLineNumber() >= 2) {
					// executeProcess(line,pr);
					if (line.startsWith("CALCULATE")) {
						totalCycles += Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.length()));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalCycles;
	}

	private String exe(String limitCycles) {
		StringBuilder builder = new StringBuilder();
		int memory = 0;
		if (readyQueue.size() == 0) {
			System.out.println("Error: No jobs availabled for executing");
			return null;
		}
		int cycles = 0;
		while (readyQueue.size() > 0) {
			scheduler.fcfs(readyQueue, Integer.parseInt(limitCycles));
			
		}
		builder.append("exe");
		// int burstTime = Integer.parseInt(delimiter(pr.pcb.getName()));
		// pr.pcb.setBurstTime(burstTime);
		// scheduler.insertPCB(jobQueue.poll());
		// scheduler.fcfs();
		// System.out.println("BUILDER " + builder.toString());
		return builder.toString();
	}

	private String reset() {
		Clock clock = new Clock();
		clock.reset();
		window.setVisible(false);
		window.repaint();
		window.setVisible(true);
		return null;
	}

	private String exit() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		return null;
	}

}
