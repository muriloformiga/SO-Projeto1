package werley.murilo;

import java.util.Collections;
import java.util.Comparator;

public class HRRN extends Scheduling {

	public HRRN (int alpha) {
		super(alpha);		
	}

	@Override
	public void prepareProcess () {	
		CalculeRation ();
		Collections.sort(ready, new OrdenarHRRN());
		super.prepareProcess();
	}	

	public void CalculeRation () {
		for (int i = 0; i < ready.size(); i++) {
			ready.get(i).setRation((ready.get(i).getWaitingTime() + ready.get(i).getServiceTime() )/ready.get(i).getServiceTime());
		}			
	}

	class OrdenarHRRN implements Comparator<Process> {
	
			
		public int compare2(Process arg0, Process arg1) {
		
			if (arg0.getRation() > arg1.getRation()) {
				return -1;
			} else if (arg0.getRation() < arg1.getRation()) {
				return 1;
			} else { 		
				return 0;
			}
		}
		
		@Override
		public int compare(Process arg0, Process arg1) {
			
			if (arg0.getSubmitionTime() > arg1.getSubmitionTime()) {
				return -1;
			} else if (arg0.getSubmitionTime() < arg1.getSubmitionTime()) {
				return 1;
			} else { 		
				return compare2(arg0, arg1);
			}
		}		
		
	}
	
}