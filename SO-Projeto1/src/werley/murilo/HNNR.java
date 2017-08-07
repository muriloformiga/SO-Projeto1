package werley.murilo;

public class HNNR {
	
	
	public int service_time(Process p) {
		int s = 0;
	
		s = p.getSubmitionTime() -p.getRetorno();
				
		return s;
	}
	public int spent_waiting(Process p) {
		int w = 0;
	
		return w;
	}
	
	
	public int ration(Process p) {
		int  r = (service_time(p) +  service_time(p))/ service_time(p);
		
		return r;
	}
	

}
