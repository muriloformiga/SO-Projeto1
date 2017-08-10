package werley.murilo;

public class PriorityScheduling extends Scheduling{

	public PriorityScheduling (int alpha) {
		super(alpha);		
	}	

	@Override
	public void prepareProcess() {
		if (changeProcess) {
			if (ready.size() > 0) {
				Process mostPriority = null;
				for (int i = 0; i < ready.size(); i++) {
					if (i == 0) {
						mostPriority = ready.get(i);
					} else {
						if (ready.get(i).getPriority() > mostPriority.getPriority()) {
							mostPriority = ready.get(i);
						}
					}
				}
				running = mostPriority;
				ready.remove(mostPriority);
			} else {
				running = null;
			}
		}
		//Collections.sort(ready, new OrdenarPriority());
		//super.prepareProcess();
	}


	
	/*@Override
	public void executeProcess () {

		if (running != null) {
			running.setServiceTime(running.getServiceTime() - 1);
			if (running.getServiceTime() == 0) {
				//saveProcess(running);
				//exibe();
				running = null;
				changeProcess = true;
				loadProcess(1);
			} else if (running.isTimeToBlock()) {
				blocked.add(running);
				running = null;
				changeProcess = true;
			} else {
				changeProcess = false;
			}
		}
	}*/
	/*
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
	}*/
}
