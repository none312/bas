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