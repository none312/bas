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
		info[2] = Integer.toString(p.pcb.getMemReq())+"kB";
		info[3] = p.pcb.getState();
		try {
			extractProgInfo(p.pcb.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info[4] = Integer.toString(totalCycles);
		return info;
	}
	
	
	
	public static void extractProgInfo(String fileName) throws FileNotFoundException
	{
		totalCycles=0;
		IOcount=0;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			for (String line = null; (line=br.readLine()) != null;) {
				System.out.println(line);
				if (line.startsWith("CALCULATE ")) {
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
