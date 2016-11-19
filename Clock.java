
public class Clock {
	int startTime;
	int cpuTime;
	int endTime;
	public void execute(int curTime) {
        startTime = curTime;
        cpuTime=0;
    }
    
    public void advanceClock(int curTime) {
    	curTime++;
    	//        cpuTime --;
//        if (cpuTime <= 0){
//            endTime = curTime;
//            //evt.onFinish(this);
        //}
        
    }
    
    public int getClock()
    {
    	return cpuTime;
    }
}
