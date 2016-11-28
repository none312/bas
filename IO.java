import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;
import java.util.Iterator;

public class IO {
	// TODO: generate random number from 25-50
	public int generateIoBurstTime(){
		Random r = new Random();
		return r.nextInt(25) + 25;
	}

}
