import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Operacao {
	
	private int totalTime, numberOfRunningProcesses, numberOfRunningProcessesPerQueue, contextSwitch;
	private double usagePercent, averageThroughput, averageTurnAround, averageWaitTime, averageAnswerTime, averageContextSwitch;
	
	Operacao(){
		this.contextSwitch = 0;
	}
	
	public ArrayList<Process> FCFS(ArrayList<Process> proc){
		this.contextSwitch = 0;
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
			this.contextSwitch++;
		}
		
		return processList;
	}
	
	/*public ArrayList<Process> SJF(ArrayList<Process> proc){
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
					processList.get(i).setWaitTime(waitTime);
					executedList.add(processList.get(i));
					processList.remove(processList.get(i));
					foundProcess = true;
				}
			}
			if(!foundProcess)
				execTime++;
		}
		return executedList;
	}*/
	
	public ArrayList<Process> SJF(ArrayList<Process> proc){
		this.contextSwitch = 0;
		int execTime = 0, waitTime = 0;
		
		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		
		int i = 0;
		boolean foundProcess;
		while(!processList.isEmpty()){
			foundProcess = false;
			for(i = 0; i<processList.size(); i++){
				if(processList.get(i).getArrivalTime() <= execTime){
					
					if(execTime != 0)
						waitTime = Math.abs(execTime - processList.get(i).getArrivalTime());
					
					execTime += processList.get(i).getBurstTime();
					processList.get(i).setExecutionTime(execTime);	
					processList.get(i).setWaitTime(waitTime);
					executedList.add(processList.get(i));
					processList.remove(processList.get(i));
					this.contextSwitch++;
					foundProcess = true;
				}
			}
			if(!foundProcess)
				execTime++;
		}
		return executedList;
	}
	
	public ArrayList<Process> SJFP(ArrayList<Process> proc){
		this.contextSwitch = 0;
		int execTime = 0;

		Process prevProcess;

		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		ArrayList<Process> waitList = new ArrayList<Process>();

		execTime = processList.get(0).getArrivalTime();
		waitList.add(processList.remove(0));
		prevProcess = waitList.get(0);

		int i = 0;
		while(!waitList.isEmpty()){
			try{
				for(i = 0; i<processList.size(); i++){
					if(processList.get(i).getArrivalTime() <= execTime){
						waitList.add(processList.get(i));
						processList.remove(processList.get(i));
						//processList.get(i).setExecutionTime(execTime);
						if(processList.get(i).getBurstTime() < processList.get(0).getBurstTime()){
							waitList.get(0).setInterruped(true);
							waitList.get(0).setInterruptedExecutionTime(execTime - 1);
						}
					}
				}
			}catch(Exception e){}
			sortListByBurstTime(waitList);

			waitList.get(0).setExecutionTime(waitList.get(0).getExecutionTime() + 1);

			try{
				if(!prevProcess.equals(waitList.get(0))){
					if(waitList.get(0).isInterruped()){
						waitList.get(0).setWaitTime(waitList.get(0).getWaitTime() + (execTime - waitList.get(0).getInterruptedExecutionTime()));
						waitList.get(0).setInterruped(false);
					}
					else{
						waitList.get(0).setWaitTime(waitList.get(0).getWaitTime() + (execTime - waitList.get(0).getArrivalTime()));
					}
				}
			}catch(IndexOutOfBoundsException e){}
			prevProcess = waitList.get(0);
			
			if(waitList.get(0).getExecutionTime() == waitList.get(0).getBurstTime()){
				waitList.get(0).setExecutionTime(waitList.get(0).getExecutionTime() + waitList.get(0).getWaitTime());
				executedList.add(waitList.remove(0));
			}

			execTime++;
		}
		return executedList;
	}
	
	public ArrayList<Process> Priority(ArrayList<Process> proc){
		this.contextSwitch = 0;
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
			this.contextSwitch++;
		}
		
		return processList;
	}
	
	public ArrayList<Process> PriorityP(ArrayList<Process> proc){
		this.contextSwitch = 0;
		int execTime = 0, waitTime = 0;

		Process prevProcess;

		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		ArrayList<Process> waitList = new ArrayList<Process>();

		execTime = processList.get(0).getArrivalTime();
		waitList.add(processList.remove(0));
		prevProcess = waitList.get(0);

		int i = 0;
		while(!waitList.isEmpty()){
			try{
				for(i = 0; i<processList.size(); i++){
					if(processList.get(i).getArrivalTime() <= execTime){
						waitList.add(processList.get(i));
						processList.remove(processList.get(i));
						
						if(processList.get(i).getPriority() < processList.get(0).getPriority()){
							waitList.get(0).setInterruped(true);
							waitList.get(0).setInterruptedExecutionTime(execTime);
							this.contextSwitch++;
						}
					}
				}
			}catch(Exception e){}
			sortListByPriority(waitList);

			waitList.get(0).setExecutionTime(waitList.get(0).getExecutionTime() + 1);

			try{
				if(!prevProcess.equals(waitList.get(0))){
					if(waitList.get(0).isInterruped()){
						waitList.get(0).setWaitTime(waitList.get(0).getWaitTime() + (execTime - waitList.get(0).getInterruptedExecutionTime()));
						waitList.get(0).setInterruped(false);
					}
					else{
						waitList.get(0).setWaitTime(waitList.get(0).getWaitTime() + (execTime - waitList.get(0).getArrivalTime()));
					}
				}
			}catch(IndexOutOfBoundsException e){}
			prevProcess = waitList.get(0);
			
			if(waitList.get(0).getExecutionTime() == waitList.get(0).getBurstTime()){
				waitList.get(0).setExecutionTime(waitList.get(0).getExecutionTime() + waitList.get(0).getWaitTime());
				executedList.add(waitList.remove(0));
			}

			execTime++;
		}
		return executedList;
	}
	
	public ArrayList<Process> RR(ArrayList<Process> proc, int quantum){
		this.contextSwitch = 0;
		int execTime = 0;
		
		ArrayList<Process> processList = proc;
		ArrayList<Process> executedList = new ArrayList<Process>();
		
		Process aux = null;
		
		while(!processList.isEmpty()){
			
			aux = processList.remove(0);
			
			if(aux.getBurstTime() <= quantum){
				execTime += aux.getBurstTime();
				aux.setExecutionTime(execTime);
				aux.setWaitTime(execTime - aux.getArrivalTime());
				executedList.add(aux);
			}
			else{
				execTime += quantum;
				aux.setBurstTime(aux.getBurstTime() - quantum);
				processList.add(aux);
			}
			this.contextSwitch++;
		}
			
		return executedList;
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
	
	public void generateReport(ArrayList<Process> processList){
		this.averageTurnAround = 0;
		this.averageWaitTime = 0;
		for(Process p : processList){
			averageWaitTime += p.getWaitTime();
			averageTurnAround +=p.getExecutionTime();
		}
		averageWaitTime = averageWaitTime/processList.size();
		averageTurnAround = averageTurnAround/processList.size();
		
		System.out.println("Tempo total de Execução: " +processList.get(processList.size()-1).getExecutionTime());
		System.out.println("Throughput : 1");
		System.out.println("Turnaround: " + averageTurnAround);
		System.out.println("Média do tempo de Espera: " + averageWaitTime); 
		System.out.println("Número de processos executados: " + processList.size());
	}
}

