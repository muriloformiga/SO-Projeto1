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
<<<<<<< HEAD:src/werley/murilo/HRRN.java

	public void ration (Process p) {
		
		p.setRation((calculateServiceTime(p) + calculateServiceTime(p)) / calculateServiceTime(p));			
=======
	public int service_time(Process p) {
		int s = 0;	
		s = p.getSubmitionTime() - p.getRetorno();				
		return s;
	}		
	
	public void ration(Process p) {
		p.setRation(((p.getEspera() +  service_time(p))/ service_time(p)));			
>>>>>>> b827d98286585555ceea017b63322f23600d9260:src/werley/murilo/HNNR.java
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