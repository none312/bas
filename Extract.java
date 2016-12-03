import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Extract {
	public static int totalCycles;
	public static int IOcount;
	public static int IOburstTime;
	public static String[] extractPcbInfo(Process p)
	{
		String[] info = new String[6];
		info[0] = p.pcb.getId();
		info[1] = p.pcb.getName();
		info[2] = Integer.toString(p.pcb.getMemAddress());
		info[3] = Integer.toString(p.pcb.getMemReq());
		info[4] = Long.toString(p.pcb.getArrivalTime());
		info[5] = p.pcb.getState();
		return info;
	}
	
	
	
	public static void extractProgInfo(String fileName) throws FileNotFoundException
	{
		String line = null;
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			while (br.readLine() != null) {
				line = br.readLine();
				if (line.startsWith("CALCULATION ")) {
					totalCycles += Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.length()));
				}
				else if(line.startsWith("IO"))
				{
					IOcount++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
