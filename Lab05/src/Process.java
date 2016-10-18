

public class Process {
	private int arrivalTime, pID, burstTime, priority, waitTime, executionTime, interruptedExecutionTime;

	private boolean interruped;

	Process(int arrivalTime, int pID, int burstTime, int priority){
		this.arrivalTime = arrivalTime;
		this.pID = pID;
		this.burstTime = burstTime;
		this.priority = priority;
		this.interruped = false;
		this.waitTime = 0;
		this.executionTime = 0;
		this.interruptedExecutionTime = 0;
		
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isInterruped() {
		return interruped;
	}

	public void setInterruped(boolean interruped) {
		this.interruped = interruped;
	}
	
	public int getInterruptedExecutionTime() {
		return interruptedExecutionTime;
	}

	public void setInterruptedExecutionTime(int interruptedExecutionTime) {
		this.interruptedExecutionTime = interruptedExecutionTime;
	}
}
