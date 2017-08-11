package werley.murilo;

public class Process {

	private int pid;
	private int submitionTime;
	private int priority;
	private int serviceTime;
	private int blockTime;
	
<<<<<<< HEAD
	private int returnTime;
	private int responseTime;
	private int waitingTime;
	private int ration;
=======
	private int retorno = 0;
	private int resposta = 0;
	private int espera = 0;
	private int ration = 0;
	private int TempExecucao = 0;
	private int fila;
>>>>>>> b827d98286585555ceea017b63322f23600d9260

	private int firstTimeToBlock;
	private int secondTimeToBlock;
	private int halfBlockTime;

	public Process (int pid, int submitionTime, int priority, int serviceTime, int blockTime) {
		this.pid = pid;
		this.submitionTime = submitionTime;
		this.priority = priority;
		this.serviceTime = serviceTime;
<<<<<<< HEAD
		this.blockTime = blockTime;
=======
		this.blockTime = blockTime;			
>>>>>>> b827d98286585555ceea017b63322f23600d9260
		checkBlockTime();
	}

	private void checkBlockTime () {
		
		if (blockTime == 0) {
			firstTimeToBlock = -1;
			secondTimeToBlock = -1;
		} else if ((float)blockTime == (serviceTime / 2.0)) {
			firstTimeToBlock = serviceTime - 1;
			secondTimeToBlock = (int)(serviceTime * 0.25) + 1;
			halfBlockTime = (int)(blockTime / 2);
		} else if ((float)blockTime >= (serviceTime / 4.0) && (float)blockTime < (serviceTime / 2.0)) {
			firstTimeToBlock = (int) Math.ceil(serviceTime / 2.0);
			secondTimeToBlock = -1;
		} else {
			firstTimeToBlock = serviceTime - 1;
			secondTimeToBlock = -1;
		}
	}

	public boolean isTimeToBlock () {

		if (firstTimeToBlock == serviceTime || secondTimeToBlock == serviceTime) {
			return true;
		} else {
			return false;
		}
	}
<<<<<<< HEAD

	public int getRation () {
=======
	
	
	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getTempExecucao() {
		return TempExecucao;
	}

	public void setTempExecucao(int tempExecucao) {
		TempExecucao = tempExecucao;
	}

	public int getRation() {
>>>>>>> b827d98286585555ceea017b63322f23600d9260
		return ration;
	}

	public void setRation (int ration) {
		this.ration = ration;
	}

	public int getReturnTime () {
		return returnTime;
	}

	public void setReturnTime (int returnTime) {
		this.returnTime = returnTime;
	}

	public int getResposta () {
		return responseTime;
	}

	public void setResposta (int resposta) {
		this.responseTime = resposta;
	}

	public int getEspera () {
		return waitingTime;
	}

	public void setEspera (int espera) {
		this.waitingTime = espera;
	}
	
	public int getPid () {
		return pid;
	}

	public int getSubmitionTime () {
		return submitionTime;
	}

	public void setSubmitionTime (int submitionTime) {
		this.submitionTime = submitionTime;
	}

	public int getPriority () {
		return priority;
	}

	public int getServiceTime () {
		return serviceTime;
	}

	public void setServiceTime (int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getBlockTime () {
		return blockTime;
	}

	public void setBlockTime (int blockTime) {
		this.blockTime = blockTime;
	}
	
	public int getHalfBlockTime () {
		return halfBlockTime;
	}

	public int getSecondTimeToBlock() {
		return secondTimeToBlock;
	}
}
