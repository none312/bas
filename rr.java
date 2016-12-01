import java.util.PriorityQueue;

public class rr {
	// TODO: wait until the process arrived then add it to the readyQ instead of
	// assuming all jobQ is added to ReadyQ at once
	public  rr() {
		Clock clock = new Clock();
		PriorityQueue<Process> readyQueue = null;
		// PriorityQueue<Process> executionQueue = new PriorityQueue<Process>();
		PriorityQueue<Process> waitingQueue = new PriorityQueue<Process>();
		int quantum = 2;
		int real_time = 0;
		int cpu_time = 0;
		int curBurstTime;
		CPU cpu = new CPU();
		ExecutionQueue executionQueue = new ExecutionQueue();
		Process curExecutedProc = new Process();
		int size = readyQueue.size();
		// for (int i = 0; i <=readyQueue.size(); i++) //loop through all
		// processes in readyQ
		while (readyQueue.size() > 0) {
			System.out.println("ReadyQ size " + readyQueue.size());
			curExecutedProc = readyQueue.peek();
			curBurstTime = curExecutedProc.pcb.getBurstTime();
			readyQueue.poll();
			executionQueue.enQueue(curExecutedProc);
			System.out.println("Executing " + curExecutedProc.pcb.getName() + "...........");
			clock.execute(real_time); // execute clock
			// TODO: if real_time + cpu_time = arrivalTime, readyQ.add(Process)

			// What if process is finish before quantum?
			for (int q = 1; q <= quantum; q++) {
				if (real_time == 2) {
					cpu.detectInterupt();

				}

				curBurstTime--;
				// System.out.println("Current Time " + real_time);
				System.out.println("Quantum " + q);
				System.out.println("Burst time " + curBurstTime);
				if (curBurstTime <= 0) {
					// executionQueue.deQueue()
					// readyQueue.peek().pcb.setState("Terminated");
					// readyQueue.poll();
					// terminated, set state, remove from executionQ, remove
					// from readyQ
					System.out.println(curExecutedProc.pcb.getName() + " finished executing");
					executionQueue.deQueue();
					q = quantum + 1;
				}
				real_time++;
				clock.cpuTime++;
			}
			// if still have remaining bursttime, deQ from exeQ, poll() from
			// redyQ, then put back on readyQ
			// need to put everything under update current queue state method
			if (curBurstTime > 0) {
				executionQueue.executionQ.peek().pcb.setBurstTime(curBurstTime);
				executionQueue.executionQ.peek().pcb.setState("waiting");
				readyQueue.add(executionQueue.executionQ.poll());
			}
		}
	}

	public static void main(String args[]) {
		// Scheduler cpu = new Scheduler(2);
		// // Iterator<Process> it = jobQueue.iterator();
		// // while(it.hasNext())
		// // {
		// // System.out.println(it.next().toString());
		// // }
		// //cpu.fcfs(jobQueue);
		// System.out.println(jobQueue);
		//// cpu.rr();
		// cpu=new Scheduler(2);
		// cpu.fcfs();
	}


}
