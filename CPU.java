
public class CPU {
	public int cpuTime;
	public boolean interupted, preempted;

	public CPU()
	{
		cpuTime = 0;
	}
	public void advanceClock() {
	
		cpuTime++;
		
	}
	
	public int getClock()
	{
		return cpuTime;
	}

	public boolean detectInterupt() {
		interupted = true;
		return interupted;
	}

	public boolean detectPreemption() {
		preempted = true;
		return preempted;
	}
	

}
