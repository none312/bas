import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout; //import default layout managing(ordering)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame; // basic windows feature(title bar, minimize/maximize)
import javax.swing.JLabel; // outputs text+images on screen
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
	private JPanel output = new JPanel();
	public JTable jt;
	public JPanel input = new JPanel(new GridLayout(1, 2));

	public JLabel txl = new JLabel("output");
	public JTextField txtfd = new JTextField(20);
	public JTextPane display = new JTextPane();

	// text from text area and set it to the output
	private void setInputPanel() {
		txtfd.setBackground(Color.black);
		txtfd.setForeground(Color.white);
		txtfd.setCaretColor(Color.white);
		window.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				txtfd.requestFocus();
			}
		});
		txtfd.setBorder(null);
		
		JPanel output = new JPanel();
		output.setBorder(new EmptyBorder(10,10,10,10));

		input.add(txtfd);

		JScrollPane scp = new JScrollPane();
		display.setBackground(Color.black);
		display.setForeground(Color.white);
		display.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(display);
		display.add(scp);
		
		input.add(scrollPane);

	}

	public JTable pcbTable;
	int count = 0;
	DefaultTableModel model = new DefaultTableModel();
	Extract helper = new Extract();
	String[] info;

	public void updatePcbTable(PriorityQueue<Process> queue) {
		String column[] = { "ID", "PROGRAM", "MEM REQ", "STATE", "BURST TIME" };
		String row[] = new String[7];
		model.setColumnIdentifiers(column);
		pcbTable = new JTable(model);
		// pcbTable.getColumnModel().getColumn(0).setMinWidth(0);
		//
		// pcbTable.getColumnModel().getColumn(0).setWidth(0);
		// pcbTable.getColumnModel().getColumn(0).setMaxWidth(0);
		System.out.println(model.getRowCount());
		if (queue.size() <= 0)
		{
			model.setRowCount(0);
			return;
		}
			
		info = helper.extractPcbInfo(queue.peek());
	
		if (model.getRowCount() > 0) {

			
			for (int r = 0; r < model.getRowCount(); r++) {
				if (queue.peek().pcb.getId().equals(pcbTable.getValueAt(r, 0))) {
					System.out.println(queue.poll().pcb.getId());
					model.removeRow(r);
				
				}
			}
		}
		for (int columnIndex = 0; columnIndex < pcbTable.getColumnCount(); columnIndex++) {
			row[columnIndex] = info[columnIndex];

		}
		model.addRow(row);

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

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1600, 700);
		window.setVisible(true);
		updatePcbTable(new PriorityQueue<Process>());
		JScrollPane sp = new JScrollPane(pcbTable);
		pcb.add(sp);
		window.add(pcb);
		setInputPanel();
		window.add(input); // adding item to the window
		StringBuilder builder = new StringBuilder();
		
		
	
		txtfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				executeCmd(txtfd.getText());
				txtfd.setText("");
			}
		});
		window.setLayout(new GridLayout(2, 1)); // default layout

	}

	public void executeCmd(String cmd) {
		if (cmd.equals("proc"))
			proc();
		else if (cmd.startsWith("load ")) {
			load(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		} else if (cmd.equals("mem"))
			mem();
		// if(parse.getcmd().equals("mem"))
		// mem();
		else if (cmd.startsWith("exe ")) {
			exe(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		} else if (cmd.equals("exit"))
			exit();
		else if (cmd.equals("reset")) {
			reset();
		} else {
			display.setText("Invalid Command");
		}
		// if(parse.getcmd().equals("reset"))
		// reset();
		// if(parse.getcmd().equals("exit"))
		// exit();
		// if(parse.getcmd().equals("load"))
		// if(parse.getval()!= null)
		// load();
	}

	JTable procTable;
	DefaultTableModel model3 = new DefaultTableModel();

	private void proc() {
		String column[] = { "PROGRAM", "MEM REQUIRE", "STATE", "TOTAL CYCLES", "I/O REQUEST" };
		String row[] = new String[6];
		model3.setColumnIdentifiers(column);
		procTable = new JTable(model3);
		PriorityQueue<Process> temp = new PriorityQueue<Process>();

		// prompt the user to enter their name

		temp = readyQueue;

		while (temp.size() > 0) {
			Process pr = temp.poll();

			Extract helper = new Extract();
			try {
				helper.extractProgInfo(pr.pcb.name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			row[0] = pr.pcb.getName();
			row[1] = Integer.toString(pr.pcb.getMemReq());
			row[2] = pr.pcb.getState();
			row[3] = Integer.toString(helper.totalCycles);
			row[4] = Integer.toString(helper.IOcount);
			// System.out.println(row[0] + " " + row[1]);
		}
		model3.addRow(row);
		JOptionPane.showMessageDialog(null, new JScrollPane(procTable));

	}

	JTable memTable;
	DefaultTableModel model2 = new DefaultTableModel();

	private String mem() {
		String column[] = { "PROGRAM", "MEM LOCATION", "MEMORY REQUIREMENT" };
		String row[] = new String[3];
		model2.setColumnIdentifiers(column);
		memTable = new JTable(model2);
		PriorityQueue<Process> temp = new PriorityQueue<Process>();

		JFrame frame = new JFrame("Memory Table");

		// prompt the user to enter their name

		temp = readyQueue;
		while (temp.size() > 0) {
			Process pr = temp.poll();

			row[0] = pr.pcb.getName();
			row[1] = Integer.toString(pr.pcb.getMemAddress());
			row[2] = Integer.toString(pr.pcb.getMemReq());
			// System.out.println(row[0] + " " + row[1]);
		}
		model2.addRow(row);
		JOptionPane.showMessageDialog(null, new JScrollPane(memTable));
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

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			// while(br.readLine()!=null)
			Random r = new Random();
			String name = br.readLine();
			String memReq = br.readLine();
			int memUsed = Integer.parseInt(memReq.substring(memReq.lastIndexOf(" ") + 1, memReq.length()));
			memory.addMemUsed(memUsed);
			System.out.println(memory.getMemoryUsed());
			if (memory.getAvailableMemory() > 0) {
				// Create new Process with name, arrivalTime, State, memory req,
				// memory location
				p = new Process(Integer.toString(id), name, curTime, "New", memUsed, r.nextInt(100));

				jobQueue.add(p);
				updatePcbTable(jobQueue);
				id++;
				builder.append(jobQueue.toString());
				display.setText(builder.toString());
				// System.out.println(readyQueue);

			} else {
				display.setText("Loading process unsuccesful. Reached maximum memory");
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

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return builder.toString();

	}

	private void exe(String limitCycles) {
		if (readyQueue.size() <= 0) {
			display.setText("Error: No jobs availabled for executing. Need to LOAD a program before EXE");
		}
		display.setText(scheduler.fcfs(readyQueue, Integer.parseInt(limitCycles)));
		readyQueue=scheduler.updateReadyQueue();
		updatePcbTable(readyQueue);
		memory=scheduler.getMemory();
	}

	private void reset() {
		readyQueue = new PriorityQueue<Process>();
		model.setRowCount(0);
		memory = new Memory();

	}

	private void exit() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

	}

}
