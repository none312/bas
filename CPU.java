
public class CPU {

	public int cpu_clock;
	private int time=0;
	public CPU() {
		//cpu_clock = burstTime;
		
	}
	public void advanceClock()
	{
       // IoInterrupt.interruptOccurred = false;
        //return cpuClock.advanceClock();
		time++;
	}
	
	public boolean detectInterrupt(){ //process stops and gets put into the waiting queue
        return InterruptProcessor.interruptOccured;
		 
	}
	
	public  boolean detectPreemption(){ //the process completely stops
		//NEED TO IMPLEMENT
		return false;
	}
	
}
