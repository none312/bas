import java.util.PriorityQueue;

public class ExecutionQueue extends PriorityQueue<Process> {
	public ExecutionQueue()
	{
		
	}
	public void enQueue(Process p) {
		add(p);
	}

	public void deQueue() {
		poll();
	}
}
