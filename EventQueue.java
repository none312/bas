import java.util.PriorityQueue;

public class EventQueue {
	PriorityQueue<ECB> eventQueue = new PriorityQueue<ECB>();

	public void enQueue(ECB e) {
		eventQueue.add(e);
	}
	
	public void deQueue()
	{
		eventQueue.poll();
	}
}
