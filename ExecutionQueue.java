import java.util.PriorityQueue;

public class ExecutionQueue {
	PriorityQueue<Process> executionQ = new PriorityQueue<Process>();

	public ExecutionQueue()
	{
		
	}
	public void enQueue(Process p) {
		executionQ.add(p);
	}

	public void deQueue() {
		executionQ.poll();
	}
}
