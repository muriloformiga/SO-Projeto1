package werley.murilo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MultilevelFeedback extends Scheduling {
	
	private int quantum;	
	private int countQuantum = 0;
	private boolean distribute = true;

	public static ArrayList<Process> RQ0 = new ArrayList<>(); // prioridade 15-19, quanto = 10
	public static ArrayList<Process> RQ1 = new ArrayList<>(); // prioridade 10-14, q = 20
	public static ArrayList<Process> RQ2 = new ArrayList<>(); // prioridade 05-09, q = 40
	public static ArrayList<Process> RQ3 = new ArrayList<>(); // prioridade 00-04, q = 80	
	
	public MultilevelFeedback(int alpha, int quatum) {
		super(alpha);
		this.quantum = recalculateQuantum();
	}
	
	
	@Override
	public void startScheduling () {
		try {
			super.scanFile = new Scanner(super.inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		loadProcessFromFile();
		for (int i = 0; i < 4; i++) {
			relocateProcess ();
			while (ready.size() + blocked.size() + incoming.size() > 0 || running != null) {
				decrementIncoming();
				decrementBlocked();
				prepareProcess();
				calculatePerformanceMetrics();
				executeProcess();
				super.exibe();
				unitTime++;
		
			}
			super.saveProcessMetrics();
			}
	}
	
	@Override
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
	
	
	private void relocateProcess () {			
		
		if(RQ0.size() > 0 ) {
			while (RQ0.size() > 0) {			
				super.distributeProcess (RQ3.get(0));				
				RQ0.remove(0);				
			}
		}			
		else if (RQ1.size() > 0) {
			while (RQ1.size() > 0) {			
				super.distributeProcess (RQ3.get(0));
				RQ1.remove(0);
			}
		}			
		else if (RQ2.size() > 0) {
			while (RQ2.size() > 0) {			
				super.distributeProcess (RQ3.get(0));
				RQ2.remove(0);
			}
		}			
		else if (RQ3.size() > 0) {
			while (RQ3.size() > 0) {			
				super.distributeProcess (RQ3.get(0));
				RQ3.remove(0);
			}
		}	
	}
		
	private int recalculateQuantum () {
		
		if (RQ0.size() > 0) {
			return 10;
		}			
		else if (RQ1.size() > 0) {
			return 20;
		}			
		else if (RQ2.size() > 0) {
			return 40;
		}			
		else  {
			return 80;
		}
	}

	@Override
	public void prepareProcess () {

		if (countQuantum > recalculateQuantum ()  || super.changeProcess) {
			if (super.changeProcess) {
				countQuantum = 0;
				super.prepareProcess();
			} else {
				if (running != null) {
					ready.add(running);
				}
				super.changeProcess = true;
				super.prepareProcess();
			}
		}
		else {
			
		}
		countQuantum++;
	}
	
	

	
	
	
	
	
	
}
