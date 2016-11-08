import java.util.LinkedList;
import java.util.Queue;

public class CpuScheduler {

	public CpuScheduler() {
		Queue <Process> list = new LinkedList <Process> (); //List of processes needed to be executed
//		list.add(new Process(0, 3, 3, 0));
//		list.add(new Process(3, 3, 3, 0));
//		list.add(new Process(0, 3, 3, 0));
//		FirstComeFirstServe();
	}
	public static void FirstComeFirstServe(Queue <Process> q) {
		Queue<Process> readyQueue = new LinkedList<Process>();
		int start = 0; // the start time of the process
		int queueSize = q.size();
		int finish = 0; //the finish time of the process
		
		Process p; // job is executed 
		Process next; //next job is going to be executed
		
		//FCFS CPU Scheduling Algorithm Begin
		
		for(int i = 0; i < queueSize; i++)
		{
			p = q.poll(); //execute the first job and remove it from the queue
			start = p.getArrivalTime();
			finish = start + p.getBurstTime();
			
			//Make new process with start time and then add it to the new queue
			next = new Process();
			next.setBurstTime(p.getBurstTime());
			next.setStartTime(start);
			next.setName(p.getName());
			readyQueue.add(next);
			System.out.println(next.toString());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue <Process> list = new LinkedList <Process> ();
		list = ProcessGenerator.generateprocess();
		FirstComeFirstServe(list);
	}
}
