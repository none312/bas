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
	
	
	
	public static void extractProgInfo()
	{
		
	}
}
