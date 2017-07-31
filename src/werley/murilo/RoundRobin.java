package werley.murilo;

import java.util.ArrayList;

public class RoundRobin {
	
	private int quantum;
	private Process running;
	private ArrayList<Process> ready = new ArrayList<>();
	private ArrayList<Process> blocked = new ArrayList<>();
	private ArrayList<Process> incoming = new ArrayList<>();
	
	public RoundRobin (ArrayList<Process> processes) {
		for (Process p : processes) {
			if(p.getSubmitionTime() > 0) {
				incoming.add(p);
			} else {
				ready.add(p);
			}
		}
		execute();
	}
	
	public void execute () {
		if (ready.size() > 0) {
			running = ready.get(0);
		}
		quantum = 4;
		while(quantum > 0) {
			running.setExecutionTime(running.getExecutionTime() - 1);
			quantum--;
		}
	}
	
	public void exibe () {
		for (Process p : ready) {
			System.out.println(p.getPid());
		}
		System.out.println("---");
		for (Process p : incoming) {
			System.out.println(p.getPid());
		}
	}
}
