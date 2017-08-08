package werley.murilo;

import java.util.Collections;
import java.util.List;

public class Priority extends Scheduling{

	public Priority (int alpha) {
		super(alpha);		
	}	

	@Override
	public void prepareProcess() {		
		Collections.sort(ready, new OrdenarPriority());
		super.prepareProcess();
	}
	
	@Override
	public void executeProcess () {

		if (running != null) {
			running.setServiceTime(running.getServiceTime() - 1);
			if (running.getServiceTime() == 0) {				
				running = null;
				changeProcess = true;
				loadProcess(1);
			} else if (running.isTimeToBlock()) {
				blocked.add(running);
				running = null;
				//changeProcess = true;
			} else {
				changeProcess = false;
			}
		}
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
