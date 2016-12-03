import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

public class cpuScheduler {
	public static PriorityQueue<Process> readyQueue;
	int cpu_clock =0;
	int current_time = 0;
	public cpuScheduler(PriorityQueue<Process>jobQueue){
		CPU cpu = new CPU();
		//load available jobs into ready queue
		readyQueue=jobQueue;
		//(int size, PriorityQueue<Process> pq) {
//		jobQueue = new PriorityQueue<Process>(size);
//		Process p1 = new Process("Process 1", 0, 1, 6);
//		Process p2 = new Process("Process 2", 4, 2, 2);
//		jobQueue.add(p1);
//		jobQueue.add(p2);
//		readyQueue = pq;
	}
	
	public void fcfs(PriorityQueue<Process>readyQueue)
	{
		int size = readyQueue.size();
		System.out.println("Ready queue " + readyQueue);
		for (int i = 0 ; i < size; i ++ )
		{
			System.out.println(readyQueue.peek().toString());
			System.out.println("Current time is " + current_time + " EXECUTING......");
			for (int t = 0; t <readyQueue.peek().getBurstTime(); t++)
			{
				cpu_clock++;
				current_time++;
			}
			readyQueue.poll();
			System.out.println("Current time " + current_time);
			System.out.println("CPU time " + cpu_clock);
			cpu_clock=0;
		}

	}
	
}
