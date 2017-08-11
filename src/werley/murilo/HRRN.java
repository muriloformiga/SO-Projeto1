package werley.murilo;

import java.util.Collections;

public class HRRN extends Scheduling {

	public HRRN (int alpha) {
		super(alpha);		
	}

	public int calculateServiceTime (Process p) {
		
		int serviceTime = p.getSubmitionTime() - p.getReturnTime();				
		return serviceTime;
	}	

	public void ration (Process p) {
		
		p.setRation((calculateServiceTime(p) + calculateServiceTime(p)) / calculateServiceTime(p));			
	}

	@Override
	public void prepareProcess () {		
		
		Collections.sort(ready, new OrdenarHRRN());
		super.prepareProcess();
	}


	@Override
	public void decrementBlocked () {

		if (blocked.size() > 0) {
			blocked.get(0).setBlockTime(blocked.get(0).getBlockTime() - 1);
			blocked.get(0).setServiceTime(blocked.get(0).getServiceTime() - 1);

			if (blocked.get(0).getBlockTime() <= 0 || (blocked.get(0).getSecondTimeToBlock() != -1 &&
					blocked.get(0).getBlockTime() == blocked.get(0).getHalfBlockTime())) {
				running = blocked.get(0);
				blocked.remove(blocked.get(0));
			}
		}
	}
}