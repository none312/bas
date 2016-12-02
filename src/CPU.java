
public class CPU {
	public int cpuTime;
	public boolean interupted, preempted;

	public CPU()
	{
		cpuTime = 0;
	}
	public void advanceClock() {
		// if(interupted == false && preempted == false) //advance clock if
		// process is not getting intereupted or preempted
		//cpuTime = clock.getClock();
		cpuTime++;
		// clock.getClock()++;
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
