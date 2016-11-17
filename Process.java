/*
 *Define Process object 
 *Variables: name, arrivalTime, priority, burstTime (expected run time)
 */
public class Process implements Comparable {
	private String name;
	private int arrivalTime; // [0, 100] Only applies to unscheduled process
	private int priority; // [1, 4] Only applies to unscheduled processes
	private int burstTime; // [0, 10]
	private int startTime; // [0, 100] Only applies to already scheduled
							// processes
	private String state; 
	
	public cpuClock clock = new cpuClock();
	public Process(String name, int arrivalTime, int priority, int burstTime, String State) {
		this.name = name;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.state = State;
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
	public int getWaitTime(){
		return cpuClock.getClock() - arrivalTime;
	}
	public String getState(){
		return state;
	}
	public void setState(){
		this.state = state;
	}
	@Override
	public String toString() {
		return name + ": Arrival time: " + arrivalTime + " Burst time: " + burstTime;
	}
	
	@Override
	public int compareTo(Object o) {
		Process p = (Process) o;
		return this.arrivalTime < p.arrivalTime ? -1 : 1;
	}
}