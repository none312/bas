import java.awt.BorderLayout;
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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.color.*;

public class Gui {
	private static JFrame window = new JFrame();
	private static Memory memory = new Memory();

	public static void main(String[] args) {
		Gui example = new Gui();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1600, 700);
		window.setVisible(true);
	}

	private JPanel pcb = new JPanel(new BorderLayout());
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

	public JTable pcbTable;
	int count = 0;
	DefaultTableModel model = new DefaultTableModel();
	Extract helper = new Extract();
	String[] info;

	public void updatePcbTable(PriorityQueue<Process> queue) {
		String column[] = { "ID", "PROGRAM", "MEM LOCATION", "STATE", "BURST TIME", "ARRIVAL TIME" };
		String row[] = new String[7];
		model.setColumnIdentifiers(column);
		pcbTable = new JTable(model);
		pcbTable.getColumnModel().getColumn(0).setMinWidth(0);

		pcbTable.getColumnModel().getColumn(0).setWidth(0);
		pcbTable.getColumnModel().getColumn(0).setMaxWidth(0);

		if (queue.size() <= 0)
			return;
		info = helper.extractPcbInfo(queue.peek());
		System.out.println(queue);
		if (model.getRowCount() > 0) {
			for (int r = 0; r < model.getRowCount(); r++) {
				
				if (queue.peek().pcb.getId().equals(pcbTable.getValueAt(r, 0))) {
					model.removeRow(r);
				}
			}
		}
		for (int columnIndex = 0; columnIndex < pcbTable.getColumnCount(); columnIndex++) {
			row[columnIndex] = info[columnIndex];
			
		}
		model.addRow(row);

	}

	// SPLIT multiple process, then split each attribute
	public void createPcbTable(StringBuilder sb) {
		String[] singlePcb, pcbInfo = null;
		String column[] = { "ID", "PROGRAM", "MEM LOCATION", "STATE", "BURST TIME", "ARRIVAL TIME" };
		System.out.println(sb.toString());
		// model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		pcbTable = new JTable(model);
		pcbTable.setModel(model);
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
		JScrollPane sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pcb.add(sp);
		window.add(pcb);
	}

	public JLabel lab = new JLabel("pcb");
	public JLabel gt = new JLabel("gnatt");
	public JLabel st = new JLabel("stats");
	int limitCycles = 0;
	StringBuilder procString = new StringBuilder();
	PriorityQueue<Process> jobQueue;
	PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	final int TOTAL_MEMORY = 256;
	Scheduler scheduler = new Scheduler();

	public Gui() {

		// super("The title bar"); // title for the window
		// CommandLineInterface cmd = new CommandLineInterface();

		setInputPanel();
		window.add(input); // adding item to the window
		StringBuilder builder = new StringBuilder();
		// builder.append("web,10kb,run,20,10,1");
		// // createProcTable(builder);
		// builder.append("web" + ",0,Ready,100,0");
		// createPcbTable(builder);
		updatePcbTable(new PriorityQueue<Process>());
		JScrollPane sp = new JScrollPane(pcbTable);
		pcb.add(sp);
		window.add(pcb);
		gnatt.add(gt);
		stats.add(st);

		// window.add(pcb);
		window.add(gnatt);
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
		} else
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
		StringBuilder builder = new StringBuilder();

		System.out.println("READY QUEUE " + procString.toString());

		createProcTable(procString);

		// System.out.println("WAITING QUEUE " + scheduler.getWaitingQueue());
		return "test";
	}

	private String mem() {
		createPcbTable(procString);
		return "mem";
	}


	/*
	 * Ex) Load web - JobQueue add new Process [web, arrivalTime, mem] - Loop
	 * through job - check memory before adding to jobQueue
	 */
	int id = 0;
	private String load(String fileName) {
		jobQueue = new PriorityQueue<Process>();
		StringBuilder builder = new StringBuilder();
		long curTime = System.currentTimeMillis();
		
		Process p = null;
		if (memory.getAvailableMemory() > 0) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				// while(br.readLine()!=null)
				Random r = new Random();
				String name = br.readLine();
				String memReq = br.readLine();
				// Create new Process with name, arrivalTime, State, memory req,
				// memory location
				p = new Process(Integer.toString(id), name, curTime, "New",
						Integer.parseInt(memReq.substring(memReq.lastIndexOf(" ") + 1, memReq.length())),
						r.nextInt(100));

				jobQueue.add(p);
				updatePcbTable(jobQueue);
				id++;
				//System.out.println(readyQueue);
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PriorityQueue<Process> tempQ = new PriorityQueue<Process>();
			while (jobQueue.size() > 0) {
				Process pr = jobQueue.poll();
				pr.pcb.setState("Ready");
				pr.pcb.setArrivalTime(System.currentTimeMillis());
				readyQueue.add(pr);
				tempQ.add(pr);
				updatePcbTable(tempQ);
			}

		}
		else
		{
			return "Reached maximum memory";
		}
		//
		// int memUsed = memory.addMemUsed(jobQueue.peek().pcb.getMemReq());
		// System.out.println(memUsed);
		// if (memory.getAvailableMemory() < memory.getTotalMemory())
		// jobQueue.add(p);
		// else
		// System.out.println("Maximum memory reached!");
		// builder.append("LOADING " + jobQueue.peek().pcb.getName() +
		// "...........");
		// while (jobQueue.size() > 0 && memory.getAvailableMemory() <
		// TOTAL_MEMORY) {
		// Process pr = jobQueue.poll();
		// pr.pcb.setArrivalTime(System.currentTimeMillis());
		// pr.pcb.setState("Ready");
		// int totalCycles = totalCycles(pr.pcb.getName());
		// readyQueue.add(pr);
		// procString.append(pr.pcb.getName() + "," + pr.pcb.getMemAddress() +
		// "," + pr.pcb.getState() + ","
		// + totalCycles + "," + pr.pcb.getArrivalTime() + "\n");
		//
		// }
		// builder.append("\n" + readyQueue);
		return builder.toString();

	}

	private String exe(String limitCycles) {
		StringBuilder builder = new StringBuilder();
		int memory = 0;
		if (readyQueue.size() == 0) {
			System.out.println("Error: No jobs availabled for executing. Need to LOAD a program before EXE");
			return null;
		}
		scheduler.fcfs(readyQueue);
		// int burstTime = Integer.parseInt(delimiter(pr.pcb.getName()));
		// pr.pcb.setBurstTime(burstTime);
		// scheduler.insertPCB(jobQueue.poll());
		// scheduler.fcfs();
		// System.out.println("BUILDER " + builder.toString());
		return builder.toString();

	}

	private void reset() {

	}

	private void exit() {

	}

}
