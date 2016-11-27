import java.util.PriorityQueue;

// Event queue is used by the interrupt system
// EventQueue = priority queue (Binary Heap Structure)

public class EventQueue {
	PriorityQueue<ECB> eventQueue = new PriorityQueue<ECB>();

	public void enQueue(ECB e) {
		eventQueue.add(e);
	}
	
	public void deQueue(ECB e)
	{
		eventQueue.remove(e);
	}
}
