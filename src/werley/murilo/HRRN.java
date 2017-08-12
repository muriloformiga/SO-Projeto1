package werley.murilo;

import java.util.Collections;
import java.util.Comparator;

public class HRRN extends Scheduling {

	public HRRN (int alpha) {
		super(alpha);		
	}
/*
	@Override
	public void prepareProcess () {		
		Collections.sort(ready, new OrdenarHRRN());
		super.prepareProcess();
	}

	private int calculateServiceTime (Process p) {

		int serviceTime = p.getSubmitionTime() - p.getReturnTime();				
		return serviceTime;
	}

	public void calculateRation (Process p) {

		p.setRation((calculateServiceTime(p) + calculateServiceTime(p)) / calculateServiceTime(p));			
		public int service_time(Process p) {
			int s = 0;	
			s = p.getSubmitionTime() - p.getRetorno();		
			return s;
		}

		public void ration (Process p) {
			p.setRation(((p.getEspera() +  service_time(p))/ service_time(p)));
		}
	}
}

class OrdenarHRRN implements Comparator<Process> {
	
	@Override	
	public int compare(Process arg0, Process arg1) {
		
		if (arg0.getRation() > arg1.getRation()) {
			return -1;
		} else if (arg0.getRation() < arg1.getRation()) {
			return 1;
		} else { 
			return 0;
		}
	}
}*/
}