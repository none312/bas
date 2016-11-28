
public class cpuClock {
private static int clock = 0;
	
    public static void execute(){
    }
    
    public static int getClock(){
    	return clock;
    }
    
    public static int advanceClock() {
        return clock++;
    }
}

