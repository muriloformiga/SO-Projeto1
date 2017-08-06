package werley.murilo;

public class Main {

	public static void main (String[] args) {

		RoundRobin rr = new RoundRobin(50, 4);
		rr.startScheduling();
		//Scheduling scheduling = new Scheduling(2);
		//scheduling.startScheduling();
	}
}
