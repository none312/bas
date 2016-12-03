import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.PriorityQueue;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;

public class Scheduler extends JTextArea {
	public long waitingTime;
	private static int limitCycles;
	private Memory mem = new Memory();
	private boolean reachMaxCycle = false;
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public static PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	public static PriorityQueue<ECB> waitingQueue = new PriorityQueue<ECB>();
	private LineNumberReader br;

	String output;
	ExecutionQueue executionQueue = new ExecutionQueue();

	public static CPU cpu = new CPU();

	public Scheduler() {
System.out.println("ADA");
	}

	public void insertPCB(Process p) {
		p.pcb.setState("Ready");
		p.pcb.setArrivalTime(System.currentTimeMillis());
		readyQueue.add(p);
	}

	public void removePCB() {
		readyQueue.poll();
	}

	StringBuilder builder = new StringBuilder();
	StringBuilder stat = new StringBuilder();

	public String fcfs(PriorityQueue<Process> rdyQ, int cycles) {
		cpu = new CPU();
		builder = new StringBuilder();
		// PriorityQueue<Process> readyQueue = jobQueue;
		readyQueue = rdyQ;
		limitCycles = cycles;
		Process curExecutedProc = new Process();
		while (readyQueue.size() > 0 && !reachMaxCycle) {
			cpu.preempted = false;
			curExecutedProc = readyQueue.poll(); // executing first proc in
			System.out.println("REady queue every line " + readyQueue);
			curExecutedProc.pcb.setState("Run");
			executionQueue.enQueue(curExecutedProc);
			waitingTime = curExecutedProc.pcb.getArrivalTime();
			System.out.println("ARRI " + waitingTime);
			builder.append("\nExecuting " + curExecutedProc.pcb.getName() + "...........");
			try {
				int curOp = 0;

				if (StringUtils.isEmpty(curExecutedProc.pcb.getCurrentOperation())) {
					curOp = 2;
				} else {
					curOp = Integer.parseInt(curExecutedProc.pcb.getCurrentOperation());
				}
				br = new LineNumberReader(new FileReader(curExecutedProc.pcb.getName()));
				for (String line = null; (line = br.readLine()) != null;) {

					if (br.getLineNumber() > curOp) {
						if (cpu.preempted)
							break;
						builder.append("\n" + line);
						System.out.println(line);
						executeProg(line, curExecutedProc);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (cpu.detectPreemption()) {
				builder.append("\nProcess is preempted.");
			} // Done reading whole program file
			mem.memAvailable+=executionQueue.peek().pcb.getMemReq();
			executionQueue.deQueue();
			System.out.println("END OF FILE " + readyQueue);

		}
		System.out.println("WAIT  " + waitingTime);
		updateReadyQueue();
		return builder.toString();
	}

	public long calculateWaitingTime(Process p) {
		long waitTime = 0;
		waitTime = p.pcb.getArrivalTime() + p.pcb.getBurstTime();

		return waitTime;
	}

	private void executeProg(String task, Process pr) {

		if (task.startsWith("CALCULATE ")) {
			int burstTime = Integer.parseInt(task.substring(task.lastIndexOf(" ") + 1, task.length()));
			pr.pcb.setBurstTime(burstTime);
			while (burstTime > 0) {
				cpu.advanceClock();
				waitingTime++;
				burstTime--;
				if (cpu.getClock() >= limitCycles) {
					cpu.detectPreemption();
					Process currentProcessState = new Process();
					currentProcessState.pcb.setId(executionQueue.peek().pcb.getId());
					currentProcessState.pcb.setArrivalTime(System.currentTimeMillis());
					currentProcessState.pcb.setState("Ready");
					currentProcessState.pcb.setCurrentOperation(Integer.toString(br.getLineNumber() + 1));
					currentProcessState.pcb.setName(executionQueue.peek().pcb.getName());
					currentProcessState.pcb.setMemAddress(executionQueue.peek().pcb.getMemAddress());
					currentProcessState.pcb.setMemReq(executionQueue.peek().pcb.getMemReq());
					insertPCB(currentProcessState);
					reachMaxCycle=true;
					break;
				}
			}
			// System.out.println(cpu.getClock());
		}
		// TODO: can only work with 1 event in the queue
		else if (task.equals("IO")) {
			IOScheduler io = new IOScheduler();

			ECB ecb = new ECB(pr.pcb.getName(), System.currentTimeMillis());
			waitingQueue.add(ecb);
			builder.append("\n" + waitingQueue.toString());
			waitingTime += ecb.ioBurstTime;
			System.out.println(waitingQueue.toString());
			while (ecb.ioBurstTime > 0) {
				cpu.advanceClock();
				waitingTime++;
				ecb.ioBurstTime--;
				if (cpu.getClock() >= limitCycles) {
					cpu.detectPreemption();
					reachMaxCycle = true;
					break;
				}
			}
			// System.out.println(cpu.getClock());
		} else if (task.equals("YIELD")) {
			cpu.detectPreemption();
			Process currentProcessState = new Process();
			currentProcessState.pcb.setId(executionQueue.peek().pcb.getId());
			currentProcessState.pcb.setArrivalTime(System.currentTimeMillis());
			currentProcessState.pcb.setState("Ready");
			currentProcessState.pcb.setCurrentOperation(Integer.toString(br.getLineNumber() + 1));
			currentProcessState.pcb.setName(executionQueue.peek().pcb.getName());
			currentProcessState.pcb.setMemAddress(executionQueue.peek().pcb.getMemAddress());
			currentProcessState.pcb.setMemReq(executionQueue.peek().pcb.getMemReq());
			insertPCB(currentProcessState);

		} else if (task.equals("OUT")) {
			output = executionQueue.peek().pcb.toString();
		} else {
			output = "Error: Unknown command";
		}
	}

	public PriorityQueue<Process> updateReadyQueue() {

		 System.out.println("ASDA " + readyQueue);
		return readyQueue;
	}
	
	public Memory getMemory(){
		return mem;
	}

}
