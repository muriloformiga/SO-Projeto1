package werley.murilo;

public class Main {
	
	

	public static void main (String[] args) {

		 
		//Round Robin: alpha = 50; quantum = 4.
<<<<<<< HEAD
		//RoundRobin rr = new RoundRobin(50, 4);
		//rr.startScheduling();
=======
		RoundRobin rr = new RoundRobin(50, 4);
		rr.startScheduling();
		
		//LotteryScheduling lottery = new LotteryScheduling(50, 3);
		//lottery.startScheduling();
		
<<<<<<< HEAD
		//PriorityScheduling priorityScheduling = new PriorityScheduling(50);
		//priorityScheduling.startScheduling();
=======
		PriorityScheduling priorityScheduling = new PriorityScheduling(50);
		priorityScheduling.startScheduling();
=======
>>>>>>> bf6ee0ce36cebf367078b1381c099af039decf85
		
		//LotteryScheduling lottery = new LotteryScheduling(50, 3);
		//lottery.startScheduling();		
		Priority priority = new Priority(50);
		priority.startScheduling();
>>>>>>> 756ea85d206669a360ef91fb331b0594a764d1a7
>>>>>>> b827d98286585555ceea017b63322f23600d9260
	}
}
