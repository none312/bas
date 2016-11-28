import java.util.PriorityQueue;
import java.io.LineNumberReader;
import java.util.Comparator;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.PriorityQueue;

public class Scheduler {
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public static PriorityQueue<Process> rdyQ = new PriorityQueue<Process>();
	public static PriorityQueue<ECB> waitingQueue = new PriorityQueue<ECB>();

	ExecutionQueue executionQueue = new ExecutionQueue();

	public static CPU cpu = new CPU();

	public Scheduler() {
		// jobQueue = new PriorityQueue<Process>(size);
		// Process p1 = new Process("Process 1", 0, "new");
		// Process p2 = new Process("Process 2", 4, "new");
		// jobQueue.add(p1);
		// jobQueue.add(p2);
	}
	
	public PriorityQueue<Process> getReadyQ()
	{
		return rdyQ;
	}
	
	public PriorityQueue<Process> getJobQ()
	{
		return jobQueue;
	}
	
	public ExecutionQueue getExecutionQ()
	{
		return executionQueue;
	}
	
	public PriorityQueue<ECB> getWaitingQueue()
	{
		return waitingQueue;
	}

	public void insertPCB(Process p) {
		p.pcb.setState("Ready");
		p.pcb.setArrivalTime(System.currentTimeMillis());
		rdyQ.add(p);
	}

	public void removePCB() {
		rdyQ.poll();
	}

	public void fcfs(PriorityQueue<Process> readyQueue) {
		// PriorityQueue<Process> readyQueue = jobQueue;

		Process curExecutedProc = new Process();
		while (readyQueue.size() > 0) {
			curExecutedProc = readyQueue.poll(); // executing first proc in
													// raedyqueue
			curExecutedProc.pcb.setState("Run");
			executionQueue.enQueue(curExecutedProc);
			System.out.println("Executing " + curExecutedProc.pcb.getName() + "...........");
			try {
				LineNumberReader br = new LineNumberReader(new FileReader(curExecutedProc.pcb.getName()));
				for (String line = null; (line = br.readLine()) != null;) {
					if (br.getLineNumber() >= 2) {
						System.out.println("LINE  " + line);
						executeProg(line, curExecutedProc);

					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		//
		// int curBurstTime=0;
		// int real_time = 0;
		// int cpu_time = 0;
		// while (readyQueue.size() > 0) {
		// curExecutedProc = readyQueue.peek();
		// curBurstTime = curExecutedProc.pcb.getBurstTime();
		// readyQueue.poll();
		// executionQueue.enQueue(curExecutedProc);
		// System.out.println("Executing " + curExecutedProc.pcb.getName() +
		// "...........");
		// while(curBurstTime>0){
		// //System.out.println(readyQueue.poll());
		//// if(real_time ==3)
		//// {
		//// cpu.detectInterupt();
		//// waitingQueue.add(executionQueue.)
		//// }
		// real_time++;
		// cpu_time++;
		// curBurstTime--;
		// System.out.println("Real time: " + real_time + " cpu_time " +
		// cpu_time + " curBurstTime " + curBurstTime);
		// }

		// }
	}

	private static void executeProg(String task, Process pr) {
		if (task.startsWith("CALCULATE")) {
			int burstTime = Integer.parseInt(task.substring(task.lastIndexOf(" ") + 1, task.length()));
			pr.pcb.setBurstTime(burstTime);
			while (burstTime > 0) {
				cpu.advanceClock();
				burstTime--;
			}
			System.out.println("Cpu time " + cpu.clock.getClock());
			// scheduler.fcfs(readyQueue);
		}
		// TODO: can only work with 1 event in the queue
		else if (task.equals("IO")) {
			IO io = new IO();
			ECB ecb = new ECB(pr.pcb.getName(), "I/O request", io.generateIoBurstTime(), System.currentTimeMillis());
			waitingQueue.add(ecb);
			System.out.println("WAITING QUEUE " + waitingQueue.poll().toString());
			while (ecb.ioBurstTime > 0) {
				cpu.advanceClock();
				ecb.ioBurstTime--;
			}
			System.out.println("NUMBER OF CYCLES " + cpu.clock.getClock());
		}
		else if (task.equals("YIELD"))
		{
			
		}
		else if(task.equals("OUT"))
		{
			StringBuilder builder = new StringBuilder();
			builder.append("");
		}
		else
		{
			System.out.println("Error: Unknown command");
		}
	}

}
