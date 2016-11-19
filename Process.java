/*
 *Define Process object 
 *Variables: name, arrivalTime, priority, burstTime (expected run time)
 */
public class Process implements Comparable {
//	private String name;
//	private int arrivalTime; // [0, 100] Only applies to unscheduled process
//	private int priority; // [1, 4] Only applies to unscheduled processes
//	private int burstTime; // [0, 10]
//	private int startTime; // [0, 100] Only applies to already scheduled
//							// processes
//	private String state;

	public PCB pcb;
	public Process()
	{
		pcb= new PCB();
	}
	public Process(String name, int arrivalTime, int priority, int burstTime, String state )
	{
		pcb = new PCB (name, arrivalTime, priority, burstTime, state);
	}

	@Override
	public String toString() {
		return  pcb.getName() + ": Arrival time: " + pcb.getArrivalTime() + " Burst time: " + pcb.getBurstTime();
	}
	
	@Override
	public int compareTo(Object o) {
		Process p = (Process) o;
		return p.pcb.getArrivalTime() < p.pcb.getArrivalTime() ? -1 : 1;
	}
}