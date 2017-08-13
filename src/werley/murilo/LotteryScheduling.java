package werley.murilo;

import java.util.Random;

public class LotteryScheduling extends Scheduling {

	private int quantum;
	private int countQuantum = 0;

	public LotteryScheduling (int alpha, int quantum) {
		super(alpha);
		this.quantum = quantum;
	}

	@Override
	public void prepareProcess() {

		if (countQuantum % this.quantum == 0 || super.changeProcess) {
			if (super.changeProcess) {
				countQuantum = 0;
			} else {
				if (running != null) {
					ready.add(running);
				}
			}
			if (ready.size() > 0) {
				Process p = chosenProcess(drawNextTicket());
				p.setExecutionStarted(true);
				running = p;
				ready.remove(p);
			} else {
				running = null;
			}
		}
		countQuantum++;
	}

	private int drawNextTicket () {

		Random randomTicket = new Random();
		return randomTicket.nextInt(maxTicketValue()) + 1;
	}

	private int maxTicketValue () {

		int maxNumber = 0;
		for (int i = 0; i < ready.size(); i++) {
			maxNumber += (ready.get(i).getPriority() + 1) * 10; 
		}
		return maxNumber;
	}
	
	private Process chosenProcess (int n) {
		Process p = null;
		int count = 0;
		for (int i = 0; i < ready.size(); i++) {
			if (n <= count + ((ready.get(i).getPriority() + 1) * 10)) {
				p = ready.get(i);
				break;
			} else {
				count += (ready.get(i).getPriority() + 1) * 10;
			}
		}
		return p;
	}
}
