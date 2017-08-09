package werley.murilo;

import java.util.ArrayList;

public class MultilevelFeedback extends Scheduling {

	public static ArrayList<Process> RQ0 = new ArrayList<>(); // prioridade 15-19, q = 10
	public static ArrayList<Process> RQ1 = new ArrayList<>(); // prioridade 10-14, q = 20
	public static ArrayList<Process> RQ2 = new ArrayList<>(); // prioridade 05-09, q = 40
	public static ArrayList<Process> RQ3 = new ArrayList<>(); // prioridade 00-04, q = 80
	
	public MultilevelFeedback(int alpha) {
		super(alpha);		
	}
	
	
	
	
	
	
}
