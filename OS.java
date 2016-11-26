import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class OS {
	
	public static Scheduler scheduler = new Scheduler(2);
	public static PriorityQueue<Process> jobQueue = new PriorityQueue<Process>();
	public static long curTime;
	public static String str = null;
	public static int memory=0;
	private static final int TOTAL_MEMORY = 256;
	private static BufferedReader br;
	public static void main(String[] args) {
		String thisLine = null;
		Scanner sc = new Scanner(System.in);
		while(!sc.nextLine().equals("q"));{
		str = sc.nextLine();

		String cmd = str.substring(0,str.charAt(' '));
		String amt = str.substring(str.charAt(' '),str.length());
		int count = 0;
		executeCmdLine(cmd);
		}
					
	}
	
	private static String delimiter(String s)
	{
		String st = s.substring(s.lastIndexOf(" ")+1, s.length());
		return st;
	}
	
	public static void executeCmdLine(String command)
	{
		//System.out.println(command);
		switch (command) {
		case "LOAD":
				curTime=System.currentTimeMillis();
			Process p=null;
			try {
				br= new BufferedReader(new FileReader(delimiter(str)));
				while(br.readLine()!=null)
				p = new Process(br.readLine(),curTime,"New");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				jobQueue.add(p);
			break;
		case "EXECUTE":
			if(jobQueue.size()==0)
			{
				System.out.println("Error: No jobs availabled for executing");
				break;
			}
			while(jobQueue.size()>0||memory<TOTAL_MEMORY)
			{
			Process pr = jobQueue.poll();
			pr.pcb.setArrivalTime(System.currentTimeMillis());
			pr.pcb.setState("Ready");
			try {
				str=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String burstTime = str.substring(str.indexOf(" ")+1, str.length());
			pr.pcb.setBurstTime(Integer.parseInt(burstTime));
			scheduler.insertPCB(jobQueue.poll());
			//scheduler.fcfs();
			}
			break;
		case "CALCULATE":
			command= "START RUNNING PROCESS";
			//System.out.println(command);
			scheduler.fcfs();
			break;
		case "YIELD":
			command = "NOT YET IMPLEMENTED";
			break;
		case "I/O":
			command = "I/O";
			break;
		case "OUT":
			command = "OUT";
			break;
		default:
			command = "ERROR";
			System.out.println(command);
			break;
		}
	}

}
