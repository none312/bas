import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ProcessGenerator {

	public static Queue <Process> generateprocess() {
		String names ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		  // Use a priority queue to order processes by arrival time (IMPORTANT!!)
        Queue<Process> q = new LinkedList<Process>();

        // get random arrival, expected time, and priority
        Random randomArrival = new Random();
        Random randomPriority = new Random();        
        Random randomExpectedTime = new Random();
        
        //Generate process 
        int nextArrival = 0;
        int processAmount = 4; 
        
        for (int i = 0; i < processAmount && nextArrival < 80; i++)
        {
        	Process p = new Process();
        	p.setArrivalTime(nextArrival); 
            p.setBurstTime(randomExpectedTime.nextInt(14) + 1);
            p.setPriority(randomPriority.nextInt(7) + 1);
            p.setName(names.charAt(i));
            q.add(p);
            
            nextArrival += randomArrival.nextInt(15) +1;
        }
        return q;
	}

	public static void main(String[] args) {
		Queue<Process> q = generateprocess();
			System.out.println(q.toString() );
		

	}

}
