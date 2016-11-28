import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

public class IOScheduler {
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public IOScheduler(int size) {
		jobQueue = new PriorityQueue<Process>(size);
		Process p1 = new Process("Process 1", 0, 1, 6);
		Process p2 = new Process("Process 2", 4, 2, 2);
		jobQueue.add(p1);
		jobQueue.add(p2);
	}
	
	public void fcfs(PriorityQueue<Process> jobQueue)
	{
		PriorityQueue<Process> readyQueue = jobQueue;
		for (int i = 0; i <= jobQueue.size(); i++)
		{
			System.out.println(readyQueue.poll());
//			System.out.println("TEST " + readyQueue.poll());
		}
	}
	
	public static void main (String args [] )
	{
		IOScheduler cpu = new IOScheduler(2);
//		Iterator<Process> it = jobQueue.iterator();
//		while(it.hasNext())
//		{
//			System.out.println(it.next().toString());
//		}
		cpu.fcfs(jobQueue);
	
	}

}
