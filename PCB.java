
public class PCB {
	private String name;
	private long arrivalTime; // [0, 100] Only applies to unscheduled process
	private int priority; // [1, 4] Only applies to unscheduled processes
	private int burstTime; // [0, 10]
	private int startTime; // [0, 100] Only applies to already scheduled
							// processes
	private String state;

	public PCB(String name, long arrivalTime, String state) {
		this.name = name;
//		this.priority = priority;
		this.arrivalTime = arrivalTime;
//		this.burstTime = burstTime;
		this.state = state;
	}
	
	public PCB()
	{
		name="";
		priority=0;
		arrivalTime = 10000000;
		burstTime=0;
		startTime=0;
	}

	public long getArrivalTime() {
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
	public String getState() {
		return this.state;
	}

	public void setArrivalTime(long arrivalTime) {
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
	public void setState(String state) {
		this.state = state;
	}
}
