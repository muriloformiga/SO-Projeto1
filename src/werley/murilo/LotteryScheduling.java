package werley.murilo;

import java.util.ArrayList;

public class LotteryScheduling {

	private ArrayList<Process> processes;

	private ArrayList<Process> ready;
	private ArrayList<Process> blocked;

	public LotteryScheduling (ArrayList<Process> processes) {
		this.processes = processes;
	}

	private void run () {

		//for(Process p : processes) {
		int exTime = processes.get(0).getServiceTime();
		processes.get(0).setServiceTime(exTime-1);
		//}
	}

	private int loterry () {
		return 0;
	}
}
