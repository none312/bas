/*
 *Define Process object 
 *Variables: name, arrivalTime, priority, burstTime (expected run time)
 */
public class io implements Comparable {
	private String name;
	private int arrivalTime; // [0, 100] Only applies to unscheduled process
	private int priority; // [1, 4] Only applies to unscheduled processes
	private int burstTime; // [0, 10]
	private int startTime; // [0, 100] Only applies to already scheduled
							// processes

	public io(String name, int arrivalTime, int priority, int burstTime) {
		this.name = name;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public int getStartTime() {
		return startTime;
	}

	public String getName() {
		return this.name;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ": Arrival time: " + arrivalTime + " Burst time: " + burstTime;
	}
	
	@Override
	public int compareTo(Object o) {
		io io = (io) o;
		return this.arrivalTime < io.arrivalTime ? -1 : 1;
	}
}