import java.util.PriorityQueue;

public class OS {
int current_time = 0;
int cpu_time =0;
public OS(){

}

public static void main (String args []){
	Process p1 = new Process("Process 1", 0, 1, 6);
	Process p2 = new Process("Process 2", 4, 2, 2);
	PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	jobQueue.add(p1);
	jobQueue.add(p2);
	cpuScheduler cpuScheduler = new cpuScheduler(jobQueue);
	CPU cpu = new CPU();
	System.out.println("Job queue " + jobQueue);
	//System.out.println("Ready queue " + cpuScheduler.readyQueue);
	//cpuScheduler.fcfs(jobQueue);
	System.out.println("Ready queue " + cpuScheduler.readyQueue);
	cpuScheduler.fcfs(cpuScheduler.readyQueue);
//	int size = cpuScheduler.readyQueue.size();
//	for (int i = 0 ; i < size; i++)
//	{
//		System.out.println(cpuScheduler.readyQueue.poll().getName());
//	}
	
}

}
