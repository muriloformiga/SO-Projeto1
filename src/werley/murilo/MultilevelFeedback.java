package werley.murilo;

import java.util.ArrayList;

public class MultilevelFeedback extends Scheduling {

	public static ArrayList<Process> RQ0 = new ArrayList<>(); // prioridade 15-19, quanto = 10
	public static ArrayList<Process> RQ1 = new ArrayList<>(); // prioridade 10-14, q = 20
	public static ArrayList<Process> RQ2 = new ArrayList<>(); // prioridade 05-09, q = 40
	public static ArrayList<Process> RQ3 = new ArrayList<>(); // prioridade 00-04, q = 80
	public static ArrayList<Process> novo = new ArrayList<>(); // prioridade 00-04, q = 80
	
	public MultilevelFeedback(int alpha) {
		super(alpha);		
	}
	
	private void distributeProcess (Process p) {
		
		if(p.getPriority() >= 0 && p.getPriority() <= 4 ) {
			p.setFila(3);
			RQ3.add(p);
		}else if (p.getPriority() >= 5 && p.getPriority() <= 9) {
			p.setFila(2);
			RQ2.add(p);
		}else if (p.getPriority() >= 10 && p.getPriority() <= 14) {
			p.setFila(1);
			RQ1.add(p);
		}else if (p.getPriority() >= 15 && p.getPriority() <= 19) {
			p.setFila(0);
			RQ0.add(p);
		}		
	}
	
	

	
	
	
	
	
	
}
