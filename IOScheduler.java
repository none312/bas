import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;
import java.util.Iterator;

public class IOScheduler {
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public IOScheduler() {
	//	jobQueue = new PriorityQueue<Process>(size);
		//Process p1 = new Process("Process 1", 0, 1, 6);
		//Process p2 = new Process("Process 2", 4, 2, 2);
		//jobQueue.add(p1);
		//jobQueue.add(p2);
	}
	
	public int randomIOburstTime()
	{
		Random r = new Random();
		int ioBurstTime = r.nextInt(25) + 25;
		return ioBurstTime;
	}
	public static void main (String args [] )
	{
	//	IOScheduler cpu = new IOScheduler(2);
//		Iterator<Process> it = jobQueue.iterator();
//		while(it.hasNext())
//		{
//			System.out.println(it.next().toString());
//		}
	//	cpu.fcfs(jobQueue);
	
	}

}
