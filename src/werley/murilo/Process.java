package werley.murilo;

public class Process {
	
	private int pid;
	private int submitionTime;
	private int priority;
	private int executionTime;
	private int blockTime;
	
	public Process (int pid, int submitionTime, int priority, int executionTime, int blockTime) {
		this.pid = pid;
		this.submitionTime = submitionTime;
		this.priority = priority;
		this.executionTime = executionTime;
		this.blockTime = blockTime;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSubmitionTime() {
		return submitionTime;
	}

	public void setSubmitionTime(int submitionTime) {
		this.submitionTime = submitionTime;
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

	public int getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(int blockTime) {
		this.blockTime = blockTime;
	}
	
}
