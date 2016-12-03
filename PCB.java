import java.util.Date;
public class PCB {
	public String name;
	public String id;
	public long arrivalTime; // [0, 100] Only applies to unscheduled process
	public int priority; // [1, 4] Only applies to unscheduled processes
	public int burstTime; // [0, 10]
	public int startTime; // [0, 100] Only applies to already scheduled
							// processes
	public String state;
	public int memReq;
	public int memAddress;
	public String currentOperation;
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
	
	public PCB(String name, String state, int memReq)
	{
		this.name=name;
		this.state=state;
		this.memReq=memReq;
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
	
	public void setCurrentOperation(String lineNumber){
		this.currentOperation = lineNumber;
	}
	public String getCurrentOperation(){
		return currentOperation;
	}
	
	
	public void setArriveTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime.getTime();
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

	public void setMemAddress(int memAddress) {
		this.memAddress=memAddress;	
	}
	public void setMemReq(int memReq)
	{
		this.memReq =memReq;
	}
}
