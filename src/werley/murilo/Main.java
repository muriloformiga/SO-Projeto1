package werley.murilo;

public class Main {
	
	

	public static void main (String[] args) {

		 
		//Round Robin: alpha = 50; quantum = 4.
		RoundRobin rr = new RoundRobin(50, 4);
		rr.startScheduling();
		
		//LotteryScheduling lottery = new LotteryScheduling(50, 3);
		//lottery.startScheduling();
		
		//PriorityScheduling priorityScheduling = new PriorityScheduling(50);
		//priorityScheduling.startScheduling();
	}
}
