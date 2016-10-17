import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Operacao {
	
	public ArrayList<Process> FCFS(ArrayList<Process> proc){
		int execTime = 0, waitTime = 0;
		
		ArrayList<Process> processList = proc;
		
		sortListByArrivalTime(processList);
		
		for(Process aux : processList){
			if(execTime != 0)
				waitTime = Math.abs(execTime - aux.getArrivalTime());
			if(aux.getArrivalTime() <= execTime)
				execTime += aux.getBurstTime();
			else
				execTime = aux.getBurstTime() + aux.getArrivalTime();
			aux.setExecutionTime(execTime);
			aux.setWaitTime(waitTime);
		}
		
		return processList;
	}
	
	public ArrayList<Process> SJF(ArrayList<Process> proc){
		int execTime = 0, waitTime = 0;
		
		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		
		sortListByBurstTime(processList);
		
		int i = 0;
		boolean foundProcess;
		while(!processList.isEmpty()){
			foundProcess = false;
			for(i = 0; i<processList.size(); i++){
				if(processList.get(i).getArrivalTime() <= execTime){
					execTime += processList.get(i).getBurstTime();
					processList.get(i).setExecutionTime(execTime);
					executedList.add(processList.get(i));
					processList.remove(processList.get(i));
					foundProcess = true;
				}
			}
			if(!foundProcess)
				execTime++;
		}
		return executedList;
	}
	
	/*public ArrayList<Process> SJFP(ArrayList<Process> proc){
		int execTime = 0, waitTime = 0;
		
		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		ArrayList<Process> waitList = new ArrayList<Process>();
		
		sortListByBurstTime(processList);
		
		int i = 0, j = 0;
		while(!processList.isEmpty()){
			for(i = 0; i<processList.size(); i++){
				for(j = 0; j<processList.size(); j++){
				if(processList.get(i).getArrivalTime() <= execTime){
					processList.get(i).setExecutionTime(execTime);
					if(processList.get(i).getBurstTime() == processList.get(i).getExecutionTime() - processList.get(i).getArrivalTime())
						executedList.add(processList.get(i));
						processList.remove(processList.get(i));
					}
				else if((processList.get(j).getArrivalTime() <= execTime) && processList.get(j).getBurstTime().)
					
				}
			}
			execTime++;
		}
		return executedList;
	}*/
	
	public ArrayList<Process> Priority(ArrayList<Process> proc){
		int execTime = 0;
		int waitTime = 0;
		
		ArrayList<Process> processList = proc;
		
		sortListByPriority(processList);
		
		for(Process aux : processList){
			if(execTime != 0)
				waitTime = Math.abs(execTime - aux.getArrivalTime());
			if(aux.getArrivalTime() <= execTime)
				execTime += aux.getBurstTime();
			else
				execTime = aux.getBurstTime() + aux.getArrivalTime();
			aux.setExecutionTime(execTime);
			aux.setWaitTime(waitTime);
		}
		
		return processList;
	}
	
	public ArrayList<Process> PriorityP(ArrayList<Process> proc){
		return null;
	}
	
	public ArrayList<Process> RR(ArrayList<Process> proc, int quantum){
		int execTime = 0;
		
		ArrayList<Process> processList = proc;
		ArrayList<Process> executionList = new ArrayList<Process>();
		
		sortListByArrivalTime(processList);
		
		int i = 0;
		while(!processList.isEmpty()){
			for(i = 0; i<processList.size(); i++){
				
				Process actualProcess = processList.get(i);
				
				if(actualProcess.getBurstTime() > quantum){
					if(actualProcess.getArrivalTime() > execTime)
						execTime += (actualProcess.getArrivalTime()-execTime) + quantum;
					else
						execTime += quantum;
					actualProcess.setExecutionTime(execTime);
					actualProcess.setBurstTime(actualProcess.getBurstTime() - quantum);
					executionList.add(actualProcess);
				}
				else{
					execTime += actualProcess.getBurstTime();
					actualProcess.setExecutionTime(execTime);
					executionList.add(actualProcess);
					processList.remove(actualProcess);	
				}
				}
			}
		return executionList;
		//return processList;
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
	
	private void sortListByPriority(ArrayList<Process> proc){
		Collections.sort(proc, new Comparator<Process>(){

			@Override
			public int compare(Process p1, Process p2) {
				if(p1.getPriority() > p2.getPriority())
					return -1;
				if(p1.getPriority() < p2.getPriority())
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

