package werley.murilo;

import java.util.Comparator;

public class OrdenarHRRN implements Comparator<Process> {
	
	@Override	
	public int compare(Process arg0, Process arg1) {
		
		if(arg0.getRation() > arg1.getRation())
			return -1;
		else if(arg0.getRation() < arg1.getRation())
			return 1;
		else 
			return	0;	
	}

}
