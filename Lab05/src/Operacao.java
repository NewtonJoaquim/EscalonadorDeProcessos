import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Operacao {
	
	public Map<Process, Integer> FCFS(ArrayList<Process> proc){
		int execTime = 0, waitTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();
		
		ArrayList<Process> processList = proc;
		
		sortListByArrivalTime(processList);
		
		/*for(int i = 0; i<processList.size();i++){
			if(processList.get(i).getArrivalTime() <=execTime)
				execTime += processList.get(i).getBurstTime();
		}*/
		
		for(Process aux : processList){
			if(execTime != 0)
				waitTime = execTime - aux.getArrivalTime();
			if(aux.getArrivalTime() <= execTime)
				execTime += aux.getBurstTime();
			else
				execTime = aux.getBurstTime() + aux.getArrivalTime();
			processMap.put(aux, execTime);
			//System.out.println(waitTime);
		}
		
		return processMap;
	}
	
	public Map<Process, Integer> SJF(ArrayList<Process> proc){
		int execTime = 0;
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();

		ArrayList<Process> processList = proc;
		
		sortListByBurstTime(processList);
		
		int i = 0;
		boolean foundProcess;
		while(!processList.isEmpty()){
			foundProcess = false;
			for(i = 0; i<processList.size(); i++){
				if(processList.get(i).getArrivalTime() <= execTime){
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
		int waitTime = 0;
		
		Map<Process, Integer> processMap = new LinkedHashMap<Process, Integer>();
		
		ArrayList<Process> waitList = new ArrayList<Process>();
		
		ArrayList<Process> processList = proc;
		
		sortListByBurstTime(processList);
		
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
	
	private void sortListByBurstTime(ArrayList<Process> proc){
		Collections.sort(proc, new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getBurstTime() < p2.getBurstTime())
					return -1;
				if(p1.getBurstTime() > p2.getBurstTime())
					return 1;
				return 0;
			}
			
		});
	}
	
	private void sortListByArrivalTime(ArrayList<Process> proc){
		Collections.sort(proc, new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getArrivalTime() < p2.getArrivalTime())
					return -1;
				if(p1.getArrivalTime() > p2.getArrivalTime())
					return 1;
				return 0;
			}
			
		});
	}
	
	public void generateReport(Map<Process, Integer> processMap){
		int totalTime = 0, numberOfRunningProcesses, numberOfRunningProcessesPerQueue;
		double usagePercent, averageThroughput, averageTurnAround, averageWaitTime, averageAnswerTime, averageContextSwitch;
		
		Iterator<Entry<Process, Integer>> it = processMap.entrySet().iterator();
		
		Entry<Process, Integer> pair = null;;
		
		while(it.hasNext())
			pair = it.next();
		System.out.println("Tempo total de Execução: "+ pair.getValue());
	}
}

