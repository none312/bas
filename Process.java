public class Process {
	private char name;
    private int arrivalTime; 
    private int priority;   	
    private int burstTime;     	
    private int startTime;    
    
//  public Process(double arrivalTime, int priority, int burstTime, int startTime) {
//	  arrivalTime = this.arrivalTime;
//	  priority = this.priority;
//	  burstTime = this.burstTime;
//	  startTime = this.startTime;
//  }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getPriority() { return priority; }
    public int getStartTime() { return startTime; }
    public char getName() { return this.name; }
    
    
    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }
    public void setBurstTime(int burstTime) { this.burstTime = burstTime; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public void setName(char name){ this.name = name; }    
    
    
    @Override
    public String toString() 
    {
    	String info = "Process " + name + " arrivalTime " + startTime + " priority " + priority + " burstTime " + burstTime ;
        return info;
    }	
}
    


