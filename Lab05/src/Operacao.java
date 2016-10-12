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
		
		/*Collections.sort(proc, new Comparator<Process>(){
			@Override
			public int compare(Process p1, Process p2) {
				if(p1.arrivalTime<p2.arrivalTime)
					return 0;
			}
		});*/
		
		for(Process aux : processList){
			execTime += aux.getBurstTime();
			processMap.put(aux, execTime);
		}
		
		return processMap;
	}
	
	/*private ArrayList<Process> sortProcessList(ArrayList<Process> proc){
		int bound = Integer.MAX_VALUE;

		ArrayList<Process> auxList = proc;
		ArrayList<Process> procList = new ArrayList<Process>();
		
		while(!auxList.isEmpty()){
			for(Process aux: auxList){
				if(aux.arrivalTime < bound)
					bound = aux.arrivalTime;
			}
			for(Process aux2 : auxList){
				if(aux2.arrivalTime == bound)
					procList.add(aux2);
					auxList.remove(aux2);
			}
			bound = Integer.MAX_VALUE;
		}
		
		return procList;
	}*/
	
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
		
		for(Process aux : processList){
			execTime += aux.getBurstTime();
			processMap.put(aux, execTime);
		}
		
		return processMap;
	}
	
	public Map<Process, Integer> SJFP(ArrayList<Process> proc){
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
		
		return null;
	}
}

