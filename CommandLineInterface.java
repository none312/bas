import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class CommandLineInterface {
	private static final Parsing parse = new Parsing();
	public static int limitCycles =0;
	public static StringBuilder procString = new StringBuilder();
	public static PriorityQueue<Process> jobQueue;
	public static PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	private static final int TOTAL_MEMORY = 256;
	private static Scheduler scheduler = new Scheduler();

	// public static boolean name(String input){
	// parse.parseLine(input);
	// boolean valid = valid ();
	// return valid;
	// }
	public static String executeCmd(String cmd) {
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

	private static String proc() {
		StringBuilder builder = new StringBuilder();
		
		System.out.println("READY QUEUE " + readyQueue);
		
		Gui gui = new Gui();
		gui.createProcTable(procString);
		
		//System.out.println("WAITING QUEUE " + scheduler.getWaitingQueue());
		return "test";
	}

	private static void mem() {

	}

	private static String load(String fileName) {
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
			procString.append(pr.pcb.getName()+",10kb,"+pr.pcb.getState()+",CYCLE COMPLETE,"+",CYCLES LEFT,"+"IO");
			
		}
		builder.append("\n"+readyQueue);
		return builder.toString();

	}

	private static String exe() {
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
	private static String exe(String limitCycles) {
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

	private static void reset() {

	}

	private static void exit() {

	}

	private static void promptUser() {

	}

	private static void executeProcess(String task, PriorityQueue<Process> readyQueue) {
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
}
// CommandLineInterface enables the user to type commands in a terminal or
// console window to interact with the OS.

// COMMANDS

/*
 * proc() mem() load() exe() reset() promptUser()
 */
