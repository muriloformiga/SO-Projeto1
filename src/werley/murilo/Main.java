package werley.murilo;

public class Main {

	public static void main (String[] args) {

		// Round Robin: alpha = 50; quantum = 4.
		//RoundRobin rr = new RoundRobin(50, 4);
		//rr.startScheduling();
		
		// Lottery Scheduling: alpha = 50; quantum = 5.
		//LotteryScheduling ls = new LotteryScheduling(50, 5);
		//ls.startScheduling();
		
		// Priority Scheduling: alpha = 50.
		//PriorityScheduling ps = new PriorityScheduling(50);
		//ps.startScheduling();
		
		 //HRRN (Highest Response Ratio Next): alpha = 50.
		//HRRN hrrn = new HRRN(50);
		//hrrn.startScheduling();
		
		// State-dependent Priority (1): alpha = 50.
		//StateDependentPriority sdp = new StateDependentPriority(50);
		//sdp.startScheduling();
		
		// Multilevel Feedback quanto 10,20,40,80
		MultilevelFeedback mf = new MultilevelFeedback(50);
		mf.startScheduling();
		
	}
}
