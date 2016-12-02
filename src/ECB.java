import java.util.ArrayList;
import java.util.Random;

public class ECB implements Comparable{
	String name;
	String description;
	long arrivalTime;
	int ioBurstTime;
	
	public ECB(String name, long arrivalTime)
	{
		this.name=name;
		this.description=randomDesc();
		this.ioBurstTime=randomIOburstTime();
		this.arrivalTime=arrivalTime;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public void setDescription(String des)
	{
		description=randomDesc();
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
	
	public String randomDesc()
	{
		ArrayList<String> descList = new ArrayList<String>();
		descList.add("Monitor");
		descList.add("Speaker");
		descList.add("USB hub");
		descList.add("Keyboard");
		descList.add("Mouse");
		Random r = new Random();
		String desc = "Request for " + descList.get(r.nextInt(descList.size()));
		return desc;
	}
	
	public int randomIOburstTime()
	{
		Random r = new Random();
		int ioBurstTime = r.nextInt(25) + 25;
		return ioBurstTime;
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
}
