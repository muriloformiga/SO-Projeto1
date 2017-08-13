package werley.murilo;

public class StateDependentPriority extends Scheduling {

	private int quantum;
	private int countQuantum = 0;

	public StateDependentPriority (int alpha) {
		super(alpha);
		quantum = recalculateQuantum();
	}

	@Override
	public void prepareProcess () {
		if (countQuantum % this.quantum == 0 || super.changeProcess) {
			quantum = recalculateQuantum();
			countQuantum = 0;
			if (super.changeProcess) {
				super.prepareProcess();
			} else {
				if (running != null) {
					ready.add(running);
				}
				super.changeProcess = true;
				super.prepareProcess();
			}
		}
		countQuantum++;
	}

	private int recalculateQuantum () {

		if (ready.size() > 0) {
			return super.alpha / ready.size();
		} else {
			return 1;
		}
	}
}
