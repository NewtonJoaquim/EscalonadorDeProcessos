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
	
	public void executeFCSF(){
		Map<Process, Integer> processMap = op.FCFS(this.processList);
		
		Iterator<Entry<Process, Integer>> it = processMap.entrySet().iterator();
		
		while(it.hasNext()){
			Entry<Process, Integer> pair = it.next();
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println("Processo: " + pair.getKey().pID);
			System.out.println("Tempo de Execução: " + pair.getValue());
		}
			
		
	}
	
	public void executeSJF(){
		Map<Process, Integer> processMap = op.SJF(this.processList);
		
		Iterator<Entry<Process, Integer>> it = processMap.entrySet().iterator();
		
		while(it.hasNext()){
			Entry<Process, Integer> pair = it.next();
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println("Processo: " + pair.getKey().pID);
			System.out.println("Tempo de Execução: " + pair.getValue());
		}
			
	}
	public void executeSJFP(){}
	public void executeRR(){}
	public void executePriority(){}
	public void executePriorityP(){}
	
	public static void main(String[] args) throws IOException{
		CSVHandler cHandler = new CSVHandler();
		Escalonador scheduler = new Escalonador(0.1);
		
		scheduler.insertProcesses(cHandler.readFile(args[0]));
		
		switch(args[1]){
			case "RR": scheduler.executeRR();
			case "SJF" : scheduler.executeSJF();
			case "SJFP" : scheduler.executeSJFP();
			case "FCFS" : scheduler.executeFCSF();
			case "Priority" : scheduler.executePriority();
			case "PriorityP" : scheduler.executePriorityP();
		}
		
		//scheduler.listProcesses();
	}
}
