package werley.murilo;

public class Process {
	
	private int submitionTime;
	private int priority;
	private int serviceTime;
	private int blockTime;
	
	private int turnaroundTime;
	private int responseTime;
	private int waitingTime;
	private boolean executionStarted;

	private int ration;

	private int retorno = 0;
	private int resposta = 0;
	private int espera = 0;
	private int TempExecucao = 0;
	private int fila;

	private int firstTimeToBlock;
	private int secondTimeToBlock;
	private int halfBlockTime;
	
	public Process (int pid, int submitionTime, int priority, int serviceTime, int blockTime) {
		this.pid = pid;
		this.submitionTime = submitionTime;
		this.priority = priority;
		this.serviceTime = serviceTime;
		this.blockTime = blockTime;
		this.turnaroundTime = 0;
		this.responseTime = 0;
		this.waitingTime = 0;
		executionStarted = false;
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

	private int pid;
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSubmitionTime() {
		return submitionTime;
	}

	public void setSubmitionTime(int submitionTime) {
		this.submitionTime = submitionTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(int blockTime) {
		this.blockTime = blockTime;
	}

	public int getTurnaroundTime () {
		return turnaroundTime;
	}

	public void setTurnaroundTime (int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public boolean isExecutionStarted() {
		return executionStarted;
	}

	public void setExecutionStarted(boolean executionStarted) {
		this.executionStarted = executionStarted;
	}

	public int getRation() {
		return ration;
	}

	public void setRation(int ration) {
		this.ration = ration;
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

	public int getTempExecucao() {
		return TempExecucao;
	}

	public void setTempExecucao(int tempExecucao) {
		TempExecucao = tempExecucao;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getFirstTimeToBlock() {
		return firstTimeToBlock;
	}

	public void setFirstTimeToBlock(int firstTimeToBlock) {
		this.firstTimeToBlock = firstTimeToBlock;
	}

	public int getSecondTimeToBlock() {
		return secondTimeToBlock;
	}

	public void setSecondTimeToBlock(int secondTimeToBlock) {
		this.secondTimeToBlock = secondTimeToBlock;
	}

	public int getHalfBlockTime() {
		return halfBlockTime;
	}

	public void setHalfBlockTime(int halfBlockTime) {
		this.halfBlockTime = halfBlockTime;
	}
}
