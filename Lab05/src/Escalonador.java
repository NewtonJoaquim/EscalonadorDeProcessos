import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Escalonador {
	private ArrayList<Process> processList = new ArrayList<Process>();
	double quantum;
	Operacao op = new Operacao();
	
	public Escalonador(double d) {
		super();
		this.quantum = d;
	}
	
	public void insertProcess(Process proc){
		this.processList.add(proc);
	}
	
	public void insertProcesses(ArrayList<Process> proc){
		//Processo aux = null;
		for(Process aux : proc){
			this.processList.add(aux);
		}
	}
	
	public void listProcesses(){
		for(int i = 0; i<this.processList.size(); i++){
			System.out.println(this.processList.get(i).getpID());
		}
	}
	
	/*public void executeFCSF(){
		Map<Process, Integer> processMap = op.FCFS(this.processList);
		
		Iterator<Entry<Process, Integer>> it = processMap.entrySet().iterator();
		
		System.out.println("-------------------Utilizando First Come First Served------------------");
		
		while(it.hasNext()){
			Entry<Process, Integer> pair = it.next();
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println("Processo: " + pair.getKey().pID);
			System.out.println("Tempo de Execução: " + pair.getValue());
		}
			
		op.generateReport(processMap);
	}*/
	
	public void executeFCSF(){
		ArrayList<Process> processList = op.FCFS(this.processList);
		
		System.out.println("-------------------Utilizando First Come First Served------------------");
		printInfo(processList);
		//op.generateReport(processMap);
	}
	
	/*public void executeSJF(){
		Map<Process, Integer> processMap = op.SJF(this.processList);
		
		Iterator<Entry<Process, Integer>> it = processMap.entrySet().iterator();
		
		System.out.println("-------------------Utilizando Shortest Job First------------------");
		
		while(it.hasNext()){
			Entry<Process, Integer> pair = it.next();
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println("Processo: " + pair.getKey().getpID());
			System.out.println("Tempo de Execução: " + pair.getValue());
		}
			
	}*/
	
	public void executeSJF(){
		ArrayList<Process> processList = op.SJF(this.processList);
	
		System.out.println("-------------------Utilizando Shortest Job First------------------");
		printInfo(processList);
		
	}
	
	public void executeSJFP(){
		ArrayList<Process> processList = op.SJFP(this.processList);
		
		System.out.println("--------------------Utilizand Shortest Job First Preemptivo-----------------------");
		printInfo(processList);
	}
	
	public void executeRR(int quantum){
		ArrayList<Process> processList = op.RR(this.processList, quantum);
		
		System.out.println("--------------------Utilizando Round-Robin-----------------------");
		printInfo(processList);
	}
	public void executePriority(){
		ArrayList<Process> processList = op.Priority(this.processList);
		
		System.out.println("--------------------Utilizando Priority-----------------------");
		printInfo(processList);
	}
	public void executePriorityP(){
		ArrayList<Process> processList = op.PriorityP(this.processList);
		
		System.out.println("--------------------Utilizando Priority Preemptivo-----------------------");
		printInfo(processList);
	}
	
	public void printStatistics(Map<Process, Integer> processMap){
		
	};
	
	private void printInfo(ArrayList<Process> processList){
		for(Process aux : processList){
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println("Processo: " + aux.getpID());
			System.out.println("Tempo de Espera: " + aux.getWaitTime());
			System.out.println("Tempo ao término da execução: " + aux.getExecutionTime());
		}
	}
	
	public static void main(String[] args) throws IOException{
		CSVHandler cHandler = new CSVHandler();
		Escalonador scheduler = new Escalonador(0.1);
		
		scheduler.insertProcesses(cHandler.readFile(args[0]));
		
		switch(args[1]){
			case "RR" : scheduler.executeRR(Integer.parseInt(args[2]));break;
			case "SJF" : scheduler.executeSJF();break;
			case "SJFP" : scheduler.executeSJFP();break;
			case "FCFS" : scheduler.executeFCSF();break;
			case "Priority" : scheduler.executePriority();break;
			case "PriorityP" : scheduler.executePriorityP();break;
			default:System.out.println("Você digitou um algoritmo inválido");
		}
		
		//scheduler.listProcesses();
	}
}
