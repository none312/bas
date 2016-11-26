
public class Clock {
	int startTime;
	int cpuTime;
	int endTime;
	public void execute(int curTime) {
        startTime = curTime;
        cpuTime=0;
    }
   
    public int getClock()
    {
    	return cpuTime;
    }
}
