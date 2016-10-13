import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operacao {
	
	public Map<Process, Integer> FCFS(ArrayList<Process> proc){
		int execTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();
		
		ArrayList<Process> processList = proc;
		
		for(Process aux : processList){
			execTime += aux.getBurstTime();
			processMap.put(aux, execTime);
		}
		
		return processMap;
	}
	
	public Map<Process, Integer> SJF(ArrayList<Process> proc){
		int execTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();

		ArrayList<Process> processList = proc;
		
		Collections.sort(processList, new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getBurstTime() < p2.getBurstTime())
					return -1;
				if(p1.getBurstTime() > p2.getBurstTime())
					return 1;
				return 0;
			}
			
		});
		int i = 0;
		boolean foundProcess;
		while(!processList.isEmpty()){
			foundProcess = false;
			for(i = 0; i<processList.size(); i++){
				if(processList.get(i).getArrivalTime() <= execTime){
					//execTime += aux.getBurstTime();
					execTime +=processList.get(i).getBurstTime();
					processMap.put(processList.get(i), execTime);
					processList.remove(processList.get(i));
					foundProcess = true;
				}
			}
			if(!foundProcess)
				execTime++;
		}
		
		return processMap;
	}
	
	public Map<Process, Integer> SJFP(ArrayList<Process> proc){
		int execTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();
		
		Map<Process, Integer> waitList = new LinkedHashMap<Process, Integer>();
		
		ArrayList<Process> processList = proc;
		
		Collections.sort(processList, new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getBurstTime() < p2.getBurstTime())
					return -1;
				if(p1.getBurstTime() > p2.getBurstTime())
					return 1;
				return 0;
			}
			
		});
		
		int i = 0;
		while(!processList.isEmpty()){
			for(i = 0; i<processList.size(); i++){
				if(processList.get(i).getArrivalTime() <= execTime){
					//execTime += aux.getBurstTime();
					//execTime +=processList.get(i).getBurstTime();
					processMap.put(processList.get(i), execTime);
					processList.remove(processList.get(i));
				}
			}
			execTime++;
		}
		
		
		return processMap;
	}
}

