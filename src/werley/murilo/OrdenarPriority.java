package werley.murilo;

import java.util.Comparator;

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
