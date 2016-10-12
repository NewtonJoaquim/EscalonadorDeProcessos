import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operacao {
	
	@SuppressWarnings("unchecked")
	public Map<Process, Integer> FCFS(ArrayList<Process> proc){
		int execTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();
		
		ArrayList<Process> sortedProcessList = sortProcessList(proc);
		
		/*Collections.sort(proc, new Comparator<Process>(){
			@Override
			public int compare(Process p1, Process p2) {
				if(p1.arrivalTime<p2.arrivalTime)
					return 0;
			}
		});*/
		
		for(Process aux : sortedProcessList){
			execTime += aux.burstTime;
			processMap.put(aux, execTime);
		}
		
		return processMap;
	}
	
	private ArrayList<Process> sortProcessList(ArrayList<Process> proc){
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
	}
	
}

