package werley.murilo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static ArrayList<Process> queue = new ArrayList<>();

	public static void main (String[] args) {

		loadProcesses();
		RoundRobin roundRobin = new RoundRobin(queue);
		roundRobin.exibe();
	}
	
	
	
	private static void loadProcesses () {

		Scanner scanFile;
		try {
			scanFile = new Scanner(new File("cenario-teste.txt"));
			while(scanFile.hasNext()) {
				String line = scanFile.nextLine().replace(",", " ");
				Scanner scanLine = new Scanner(line);
				Process process = new Process(scanLine.nextInt(),
						scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt(), scanLine.nextInt());
				queue.add(process);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
