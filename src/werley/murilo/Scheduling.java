package werley.murilo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Scheduling {

	public static ArrayList<Process> ready = new ArrayList<>();
	public static ArrayList<Process> blocked = new ArrayList<>();
	public static ArrayList<Process> incoming = new ArrayList<>();
	public static Process running;

	private int totalTurnaroundTime;
	private int totalResponseTime;
	private int totalWaitingTime;
	private int totalProcesses;
	private int CPUExecuting;

	public boolean changeProcess = true;

	private int alpha;
	public long unitTime;

	private Scanner scanFile;

	private File inFile = new File("cenario-teste-1.txt");
	private File outFile = new File("resultado-teste.txt");

	public Scheduling (int alpha) {
		this.alpha = alpha;
		this.unitTime = 1;
		this.totalTurnaroundTime = 0;
		this.totalResponseTime = 0;
		this.totalWaitingTime = 0;
		this.totalProcesses = 0;
		this.CPUExecuting = 0;
	}

	public void startScheduling () {
		try {
			scanFile = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		loadProcessFromFile();
		while (ready.size() + blocked.size() + incoming.size() > 0 || running != null) {
			decrementIncoming();
			decrementBlocked();
			prepareProcess();
			calculatePerformanceMetrics();
			executeProcess();
			exibe();
			unitTime++;
		}
		saveProcessMetrics();
	}

	public void loadProcessFromFile () {
		while(scanFile.hasNext()) {
			String line = scanFile.nextLine().replace(",", " ");
			Scanner scanLine = new Scanner(line);
			Process process = new Process(scanLine.nextInt(),
					scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt());
			distributeProcess(process);
			totalProcesses++;
		}
	}

	private void distributeProcess (Process p) {
		if (p.getSubmitionTime() > 0) {
			incoming.add(p);
		} else {
			if (numberOfProcesses() < alpha) {
				ready.add(p);
			} else {
				p.setSubmitionTime(p.getSubmitionTime() + 1);
			}
		}
	}

	public void prepareProcess () {
		if (changeProcess) {
			if (ready.size() > 0) {
				ready.get(0).setExecutionStarted(true);
				running = ready.get(0);
				ready.remove(0);
			} else {
				running = null;
			}
		}
	}

	public void executeProcess () {
		if (running != null) {
			running.setServiceTime(running.getServiceTime() - 1);
			running.setTurnaroundTime(running.getTurnaroundTime() + 1);
			if (running.getServiceTime() == 0) {
				totalTurnaroundTime += running.getTurnaroundTime();
				totalResponseTime += running.getResponseTime();
				totalWaitingTime += running.getWaitingTime();
				saveProcess(running);
				running = null;
				changeProcess = true;
			} else if (running.isTimeToBlock()) {
				blocked.add(running);
				running = null;
				changeProcess = true;
			} else {
				changeProcess = false;
			}
		}
	}

	public void decrementIncoming () {
		Iterator<Process> processIterator = incoming.iterator();
		while (processIterator.hasNext()) {
			Process process = processIterator.next();
			process.setSubmitionTime(process.getSubmitionTime() - 1);
			if (process.getSubmitionTime() == 0 ) {
				if (numberOfProcesses() < alpha) {
					processIterator.remove();
					ready.add(process);
				} else {
					process.setSubmitionTime(process.getSubmitionTime() + 1);
				}
			}
		}
	}

	public void decrementBlocked () {
		for (int i = 0; i < blocked.size(); i++) {
			blocked.get(i).setBlockTime(blocked.get(i).getBlockTime() - 1);
			blocked.get(i).setServiceTime(blocked.get(i).getServiceTime() - 1);
			blocked.get(i).setTurnaroundTime(blocked.get(i).getTurnaroundTime() + 1);
			if (blocked.get(i).getBlockTime() <= 0 || (blocked.get(i).getSecondTimeToBlock() != -1 &&
					blocked.get(i).getBlockTime() == blocked.get(i).getHalfBlockTime())) {
				ready.add(blocked.get(i));
				blocked.remove(blocked.get(i));
			}
		}
	}

	public void calculatePerformanceMetrics () {
		for (int i = 0; i < ready.size(); i++) {
			ready.get(i).setWaitingTime(ready.get(i).getWaitingTime() + 1);
			ready.get(i).setTurnaroundTime(ready.get(i).getTurnaroundTime() + 1);
			if (!ready.get(i).isExecutionStarted()) {
				ready.get(i).setResponseTime(ready.get(i).getResponseTime() + 1);
			}
		}
		if (running != null) {
			CPUExecuting++;
		}
	}

	public int numberOfProcesses () {
		int run = 0;
		if (running != null) {
			run = 1;
		}
		return (ready.size() + blocked.size() + run);
	}

	public void saveProcess (Process p) {		      
		try { 
			if (!outFile.exists()) {            
				outFile.createNewFile();
			}
			FileWriter fw = new FileWriter(outFile, true); 
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("----- PID: " + p.getPid() + " -----"); 
			bw.newLine();
			bw.write("Turnaround Time: " + p.getTurnaroundTime());
			bw.newLine();
			bw.write("Response Time: " + p.getResponseTime());
			bw.newLine();
			bw.write("Waiting Time: " + p.getWaitingTime());
			bw.newLine();
			bw.newLine();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveProcessMetrics () {
		try {
			if (!outFile.exists()) {            
				outFile.createNewFile();
			}
			FileWriter fw = new FileWriter(outFile, true); 
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("-------------------- PROCESS SCHEDULING INFORMATIONS --------------------");
			bw.newLine();
			bw.write("Avg. Turnaround Time: " + String.format("%.2f", totalTurnaroundTime / (float) totalProcesses));
			bw.newLine();
			bw.write("CPU Usage (%): " + String.format("%.2f", (CPUExecuting * 100.0) / unitTime));
			bw.newLine();
			bw.write("Avg. Response Time: " + String.format("%.2f", totalResponseTime / (float) totalProcesses));
			bw.newLine();
			bw.write("Avg. Waiting Time: " + String.format("%.2f", totalWaitingTime / (float) totalProcesses));
			bw.newLine();
			bw.write("Simulation Time: " + unitTime);
			bw.newLine();
			bw.write("-------------------------------------------------------------------------");
			bw.newLine();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exibe () {
		System.out.println("----- Time " + unitTime + " -----");
		System.out.println("----- Running -----");
		if (running != null) {
			System.out.println(running.getPid() + " : " + running.getServiceTime());
		}
		System.out.println("----- Ready -----");
		for (Process p : ready) {
			System.out.println(p.getPid() + " : " + p.getServiceTime());
		}
		System.out.println("----- Blocked -----");
		for (Process p : blocked) {
			System.out.println(p.getPid() + " : " + p.getServiceTime());
		}
		System.out.println("----- Incoming -----");
		for (Process p : incoming) {
			System.out.println(p.getPid() + " : " + p.getServiceTime());
		}
		System.out.println("");
	}
}
