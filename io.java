import java.util.Random;

/*
 *Define Process object 
 *Variables: name, arrivalTime, priority, burstTime (expected run time)
 */
public class io implements Comparable {
	long arrivalTime;

	public int generateIoBurstTime()
	{
		Random r = new Random();
		return r.nextInt(10);
	}
	@Override
	public int compareTo(Object o) {
		io io = (io) o;
		return this.arrivalTime < io.arrivalTime ? -1 : 1;
	}
}