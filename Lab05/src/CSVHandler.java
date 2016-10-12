import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVHandler {
	public ArrayList<Process> readFile(String file) throws IOException{
		ArrayList<Process> processes= new ArrayList<Process>();
		
		int processPID, processBurstTime, processPriority, processArrivalTime;
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine()) != null){
			String[] lineField = line.split(",");

			processPID = Integer.parseInt(lineField[0]);
			processArrivalTime = Integer.parseInt(lineField[1]);
			processBurstTime = Integer.parseInt(lineField[2]);
			processPriority = Integer.parseInt(lineField[3]);
			
			processes.add(new Process(processArrivalTime, processPID, processBurstTime, processPriority));
		}
		return processes;
	}
}
