package werley.murilo;

import java.util.Collections;
import java.util.Comparator;

public class PriorityScheduling extends Scheduling{

	public PriorityScheduling (int alpha) {
		super(alpha);		
	}	

	@Override
	public void prepareProcess() {		
		Collections.sort(ready, new OrdenarPriority());
		super.prepareProcess();
	}
	
	public class OrdenarPriority implements Comparator<Process> {		
		
		@Override	
		public int compare(Process arg0, Process arg1) {
			
			if(arg0.getPriority() > arg1.getPriority())
				return -1;
			else if(arg0.getPriority() < arg1.getPriority())
				return 1;
			else 
				return	0;	
		}	
		
	}

}
