package werley.murilo;

import java.util.Collections;

public class PriorityScheduling extends Scheduling{

	public PriorityScheduling (int alpha) {
		super(alpha);		
	}	

	@Override
	public void prepareProcess() {		
		Collections.sort(ready, new OrdenarPriority());
		super.prepareProcess();
	}
}
