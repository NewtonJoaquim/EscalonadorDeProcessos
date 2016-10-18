import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	public void executeFCSF(int arg){
		ArrayList<Process> processList = op.FCFS(this.processList);
		
		System.out.println("-------------------Utilizando First Come First Served------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
		//op.generateReport(processMap);
	}
	
	public void executeSJF(int arg){
		ArrayList<Process> processList = op.SJF(this.processList);
	
		System.out.println("-------------------Utilizando Shortest Job First------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
		
	}
	
	public void executeSJFP(int arg){
		ArrayList<Process> processList = op.SJFP(this.processList);
		
		System.out.println("--------------------Utilizand Shortest Job First Preemptivo-----------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
	}
	
	public void executeRR(int quantum, int arg){
		ArrayList<Process> processList = op.RR(this.processList, quantum);
		
		System.out.println("--------------------Utilizando Round-Robin-----------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
	}
	public void executePriority(int arg){
		ArrayList<Process> processList = op.Priority(this.processList);
		
		System.out.println("--------------------Utilizando Priority-----------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
	}
	public void executePriorityP(int arg){
		ArrayList<Process> processList = op.PriorityP(this.processList);
		
		System.out.println("--------------------Utilizando Priority Preemptivo-----------------------");
		if(arg == 1){
			op.generateReport(processList);
		}
		else if(arg == 2)
			writeInfo(processList);
		else
			System.out.println("Argumento inválido");
	}
	
	private void writeInfo(ArrayList<Process> processList){
		try {
			PrintWriter writer = new PrintWriter("processosResultado.txt", "UTF-8");
			for(Process aux : processList){
				writer.println("|||||||||||||||||||||||||||||");
				writer.println("Processo: " + aux.getpID());
				writer.println("Tempo de Espera: " + aux.getWaitTime());
				writer.println("Tempo ao término da execução: " + aux.getExecutionTime());
			}
			writer.close();
		}catch (FileNotFoundException | UnsupportedEncodingException e) {}
		System.out.println("Resultado gravado no arquivo processosResultado.txt");
	}
	
	public static void main(String[] args) throws IOException{
		CSVHandler cHandler = new CSVHandler();
		Escalonador scheduler = new Escalonador(0.1);
		
		scheduler.insertProcesses(cHandler.readFile(args[0]));
		
		switch(args[1]){
			case "RR" : scheduler.executeRR(Integer.parseInt(args[2]), Integer.parseInt(args[3]));break;
			case "SJF" : scheduler.executeSJF(Integer.parseInt(args[3]));break;
			case "SJFP" : scheduler.executeSJFP(Integer.parseInt(args[3]));break;
			case "FCFS" : scheduler.executeFCSF(Integer.parseInt(args[3]));break;
			case "Priority" : scheduler.executePriority(Integer.parseInt(args[3]));break;
			case "PriorityP" : scheduler.executePriorityP(Integer.parseInt(args[3]));break;
			default:System.out.println("Você digitou um algoritmo inválido");
		}
		
		//scheduler.listProcesses();
	}
}
