import java.util.PriorityQueue;

public class EventQueue {
	private PriorityQueue<Events> queue = new PriorityQueue<>();
	
	public void enQueue(Events event) {
	    queue.add(event);
	} 
	public boolean deQueue() {
			return queue.remove(0);
		}
	}


