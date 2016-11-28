import java.util.PriorityQueue;

public class ExecutionQueue {

    private PriorityQueue<Events> queue = new PriorityQueue<>();

    public void enQueue(Events event) {
    	
        queue.add(event);
    }

    boolean deQueue() {
        return queue.remove(0);
    }
}

 
//TODO: initiate ready queue, waiting queue and IO queue// or is this already done?

//initialize int (availableMemory = 256)
//PriorityQueue <Events> Ready queue = new PriorityQueue<>();
//PriorityQueue <Events> Waiting queue = new PriorityQueue<>();
//PriorityQueue <Events> IO queue = new PriorityQueue<>();

