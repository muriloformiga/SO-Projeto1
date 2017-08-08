package werley.murilo;

public class Process {

	private int pid;
	private int submitionTime;
	private int priority;
	private int serviceTime;
	private int blockTime;
	
	private int retorno;
	private int resposta;
	private int espera;

	private int firstTimeToBlock;
	private int secondTimeToBlock;
	private int halfBlockTime;

	public Process (int pid, int submitionTime, int priority, int serviceTime, int blockTime, int resposta) {
		this.pid = pid;
		this.submitionTime = submitionTime;
		this.priority = priority;
		this.serviceTime = serviceTime;
		this.blockTime = blockTime;	
		this.resposta = resposta;
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

	public int getRetorno() {
		return retorno;
	}

	public void setRetorno(int retorno) {
		this.retorno = retorno;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}
	
	public int getPid() {
		return pid;
	}

	public int getSubmitionTime () {
		return submitionTime;
	}

	public void setSubmitionTime (int submitionTime) {
		this.submitionTime = submitionTime;
	}

	public int getPriority() {
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
