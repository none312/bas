import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;
import java.util.Iterator;

public class IOScheduler {
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public IOScheduler() {
	
	
	public int randomIOburstTime()
	{
		Random r = new Random();
		int ioBurstTime = r.nextInt(25) + 25;
		return ioBurstTime;
	}
	public static void main (String args [] )
	{

	}

}
