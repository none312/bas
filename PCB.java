
public class PCB {
	private String name;
	private String id;
	private long arrivalTime; // [0, 100] Only applies to unscheduled process
	private int priority; // [1, 4] Only applies to unscheduled processes
	private int burstTime; // [0, 10]
	private int startTime; // [0, 100] Only applies to already scheduled
							// processes
	private String state;
	private int memReq;
	private int memAddress;
	public PCB(String id, String name, long arrivalTime, String state, int memReq, int memAddress) {
		this.id = id;
		this.name = name;
//		this.priority = priority;
		this.arrivalTime = arrivalTime;
//		this.burstTime = burstTime;
		this.state = state;
		this.memReq=memReq;
		this.memAddress=memAddress;
		
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
	public int getMemReq(){
		return this.memReq;
	}
	
	public int getMemAddress(){
		return this.memAddress;
	}
	public String getId()
	{
		return this.id;
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
	public void setId(String id)
	{
		this.id= id;
	}
}
