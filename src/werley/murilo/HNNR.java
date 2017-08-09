package werley.murilo;

import java.util.Collections;

public class HNNR extends Scheduling {
	
	public HNNR(int alpha) {
		super(alpha);		
	}	
	public int service_time(Process p) {
		int s = 0;	
		s = p.getSubmitionTime() - p.getRetorno();				
		return s;
	}	
	
	
	
	public void ration(Process p) {
		p.setRation(((service_time(p) +  service_time(p))/ service_time(p)));			
	}
	
	@Override
	public void prepareProcess() {		
		Collections.sort(ready, new OrdenarHRRN());
		super.prepareProcess();
	}
	
	
	//@Override
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
	


