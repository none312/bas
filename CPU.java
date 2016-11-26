
public class CPU {
	public Clock clock = new Clock();
	public int cpuTime = 0;
	public boolean interupted, preempted;
	public void advanceClock()
	{
		if(interupted == false && preempted == false)  //advance clock if process is not getting intereupted or preempted
			cpuTime=clock.getClock();
		cpuTime++;
		//clock.getClock()++;
	}
	
	public boolean detectInterupt()
	{
		interupted = true;
		return interupted;
	}
	
	public boolean detectPreemption()
	{
		preempted = true;
		return preempted;
	}
}
