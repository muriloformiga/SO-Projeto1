package werley.murilo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Scheduling {

	public static ArrayList<Process> ready = new ArrayList<>();
	public static ArrayList<Process> blocked = new ArrayList<>();
	public static ArrayList<Process> incoming = new ArrayList<>();
	public static Process running;

	public boolean changeProcess = true;

	private int alpha;
	public long unitTime = 1;
	static long TempoTotal;
	private int qtdProcessos;
	private float mediaTempRetorno, mediaTempResposta, mediaTempEspera;

	Scanner scanFile;

<<<<<<< HEAD
	File file = new File("cenario2.txt");
	Path path = Paths.get("resultado-teste.txt");
=======
	File file = new File("cenario-teste-1.txt");
	File arquivo = new File("resultado-teste.txt");	
>>>>>>> 756ea85d206669a360ef91fb331b0594a764d1a7

	public Scheduling (int alpha) {
		this.alpha = alpha;
	}

	public void startScheduling () {
		long start = System.currentTimeMillis();
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		loadProcess(alpha);
		while (ready.size() + blocked.size() + incoming.size() > 0 || running != null) {
			decrementIncoming();
			decrementBlocked();
			prepareProcess();
			executeProcess();
			exibe();
			unitTime++;
		}
		long finish = System.currentTimeMillis();
		TempoTotal = finish - start;
		
		
		saveProcess(TempoTotal);
	}

	public void loadProcess (int n) {

		for (int i = 0; i < n; i++) {
			if(scanFile.hasNext()) {
				String line = scanFile.nextLine().replace(",", " ");
				Scanner scanLine = new Scanner(line);
				Process process = new Process(scanLine.nextInt(),
						scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt());
				distributeProcess(process);
			} else {
				break;
			}
		}
	}

	private void distributeProcess (Process p) {

		if (p.getSubmitionTime() > 0) {
			incoming.add(p);
		} else {
			ready.add(p);
		}
	}

	public void prepareProcess () {
		if (changeProcess) {
			if (ready.size() > 0) {
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
			running.setTempExecucao(running.getTempExecucao() + 1); // alterado
			if (running.getServiceTime() == 0) {				
				running = null;
				changeProcess = true;
				loadProcess(1);
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

		for (int i = 0; i < incoming.size(); i++) {
			incoming.get(i).setSubmitionTime(incoming.get(i).getSubmitionTime() - 1);
			incoming.get(i).setResposta(incoming.get(i).getEspera() + 1); // alterado
			if (incoming.get(i).getSubmitionTime() <= 0) {
				System.out.println("Tempo de espera: efsedjghshsieeroejsdlkdjpwaojdosaljsdlasjdkljaklsjdlkajsdlkjsakdjasdjlkajdslkajdklsjd" ); // n�o entra
				ready.add(incoming.get(i));
				incoming.remove(incoming.get(i));
			}
		}
	}

	public void decrementBlocked () {

		for (int i = 0; i < blocked.size(); i++) {
			blocked.get(i).setBlockTime(blocked.get(i).getBlockTime() - 1);
			blocked.get(i).setServiceTime(blocked.get(i).getServiceTime() - 1);
			if (blocked.get(i).getBlockTime() <= 0 || (blocked.get(i).getSecondTimeToBlock() != -1 &&
					blocked.get(i).getBlockTime() == blocked.get(i).getHalfBlockTime())) {
				ready.add(blocked.get(i));
				blocked.remove(blocked.get(i));
			}
		}
	}

	public void saveProcess (long tempoTotal2) {		      
		 
        try { 
            if (!arquivo.exists()) {            
            arquivo.createNewFile();
        }
 
            FileWriter fw = new FileWriter(arquivo, true); 
            BufferedWriter bw = new BufferedWriter(fw);
 
            bw.write(" -- Tempo m�dia de retorno: " + mediaTempRetorno + " -- Tempo m�dio de Resposta: " + mediaTempResposta + " -- Tempo m�dio de Esoera: "+ mediaTempEspera+ " --"); 
            bw.newLine();             
            bw.close();
            fw.close();   
 
        } catch (IOException ex) {
        	ex.printStackTrace();
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
	
	public void TempRetorno(Process p) {	
		
		p.setRetorno(p.getEspera() + p.getSubmitionTime());	
		
	}
	
	public void media(Process p) {
		
		float somaTempRetorno = 0, somaTempResposta = 0, somaTempEspera = 0;
		for (int i = 0; i < qtdProcessos; i++) { // varia em rela��o ao tamanho do arquivo
			somaTempRetorno  =+ p.getRetorno();
			somaTempResposta =+ p.getResposta();
			somaTempEspera   =+ p.getEspera();			
		}
		mediaTempRetorno  = somaTempRetorno/qtdProcessos;
		mediaTempResposta = somaTempResposta/qtdProcessos;
		mediaTempEspera   = somaTempEspera/qtdProcessos;		
	}
	
}
