import java.util.Random;

public class IoBurst {

	private static int min = 25;
	private static int max = 50;
	
	public int generateIOBurst(){
		Random num = new Random();
		int ioBurstTime = num.nextInt(max - min) + min;
		return ioBurstTime;
	}
}