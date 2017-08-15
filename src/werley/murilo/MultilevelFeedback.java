package werley.murilo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MultilevelFeedback extends Scheduling {			
	
	public MultilevelFeedback(int alpha) {
		super(alpha);		
	}	
	
	protected void PreProcessamento (Process p) {		
		if(p.getPriority() >= 0 && p.getPriority() <= 4 ) {
			p.setFila(3);
			p.setQuantum(80); 
		}else if (p.getPriority() >= 5 && p.getPriority() <= 9) {
			p.setFila(2);	
			p.setQuantum(40); 
		}else if (p.getPriority() >= 10 && p.getPriority() <= 14) {
			p.setFila(1);
			p.setQuantum(20); 
		}else if (p.getPriority() >= 15 && p.getPriority() <= 19) {
			p.setFila(0);	
			p.setQuantum(10); 
		}		
	}	

	@Override
	public void loadProcessFromFile () {
		while(scanFile.hasNext()) {
			String line = scanFile.nextLine().replace(",", " ");
			Scanner scanLine = new Scanner(line);
			Process process = new Process(scanLine.nextInt(),
					scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt());
			PreProcessamento (process);
			distributeProcess(process);
			totalProcesses++;
		}
	}	

	
	@Override
	public void prepareProcess () {
		if(ready.size() > 0) {
			if ( ready.get(0).getQuantum() > 0  || super.changeProcess) {
				if (super.changeProcess) {				
					super.prepareProcess();
				} else {
					if (running != null) {
						ready.add(running);
					}
					super.changeProcess = true;
					super.prepareProcess();
				}
			}
		ready.get(0).setQuantum(ready.get(0).getQuantum() - 1);
		}
	}
	
	class OrdenarMultilevel implements Comparator<Process> {	
		
		public int compare2(Process arg0, Process arg1) {
		
			if (arg0.getSubmitionTime() > arg1.getSubmitionTime()) {
				return -1;
			} else if (arg0.getSubmitionTime() < arg1.getSubmitionTime()) {
				return 1;
			} else { 		
				return 0;
			}
		}
		
		@Override
		public int compare(Process arg0, Process arg1) {
			
			if (arg0.getFila() > arg1.getFila()) {
				return -1;
			} else if (arg0.getFila() < arg1.getFila()) {
				return 1;
			} else { 		
				return compare2(arg0, arg1);
			}
		}		
		
	}	
	
}
