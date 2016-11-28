import java.util.ArrayList;
import java.util.Random;

public class ECB implements Comparable{
	String name;
	String description;
	long arrivalTime;
	int ioBurstTime;
	
	public ECB(String name, int ioBurstTime, long arrivalTime)
	{
		this.name=name;
		this.description="Request " + ECBdesc();
		this.ioBurstTime=ioBurstTime;
		this.arrivalTime=arrivalTime;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setDescription(String des)
	{
		description=des;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public long getArrivalTime(){
		return arrivalTime;
	}
	
	@Override
	public String toString(){
		return name + ": Description " + description + " IOburstTime " + ioBurstTime;
	}
	@Override
	public int compareTo(Object o) {
		ECB e = (ECB) o;
		return e.getArrivalTime() < e.getArrivalTime() ? -1 : 1;
	}
	
	private String ECBdesc()
	{
		ArrayList<String> hardwares = new ArrayList<String>();
		hardwares.add("Monitor");
		hardwares.add("Speaker");
		hardwares.add("Keyboard");
		hardwares.add("Mouse");
		hardwares.add("Disk");
		Random r = new Random();
		return hardwares.get(r.nextInt(hardwares.size()));
	}
}
