package cwiczenie5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Main {

	public static void main(String args[]) throws Exception {
		String mojaNazwa = "Moja nazwa";
		int liczba = 5;
		
		try {
			 FileOutputStream fileOut =
			 new FileOutputStream("res/nazwa.ser");
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(mojaNazwa);
			 out.close();
			 fileOut.close();
			 } catch(IOException i) {
			 i.printStackTrace();
			 }

		mojaNazwa = "Nazwa moja";
		
		try {
			FileInputStream fileIn =
					new FileInputStream("res/nazwa.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			mojaNazwa = (String) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i) {
			i.printStackTrace();
		}

		System.out.println(mojaNazwa);

		Bureau bureau = initBureau();
		printBureau(bureau);
		saveBureau(bureau, "mi6.ser");
		Bureau loadedBureau = loadBureau("mi6.ser");
		printBureau(loadedBureau);	
	}

	private static Bureau initBureau() {
		Bureau bureau = new Bureau("MI6");
		Agent agent007 = new Agent("James", "Bond", 35, "007");
		bureau.addAgent(agent007);

		Task task = new Task("darkMamba", "infiltrate, spy, destroy");
		agent007.addTask(task);
		task = new Task("redHot", "sing, sink, think");
		agent007.addTask(task);

		return bureau;
	}

	private static void saveBureau(Bureau bureau, String filename) throws Exception {
			FileOutputStream fileOutput = new FileOutputStream(filename);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
			outputStream.writeObject(bureau);
			fileOutput.close();
			outputStream.close();
	}

	private static Bureau loadBureau(String filename) throws Exception {
			FileInputStream fileInput = new FileInputStream(filename);
			ObjectInputStream objectStream = new ObjectInputStream(fileInput);
			Bureau bureau = (Bureau) objectStream.readObject();
			fileInput.close();
			objectStream.close();
			return bureau;
	}
	
	private static void printBureau(Bureau bureau) {
		System.out.println("Name of bureau: " + bureau.getName());
		List<Agent> agents = bureau.getAgents();
		for (Agent agent : agents) {
			String name = agent.getName();
			String surname = agent.getSurname();
			String number = agent.getNumber();
			System.out.println("Agent name: " + name + " " + surname + 
					"\nAgent number: " + number);
			for (Task task : agent.getTasks()) {
				System.out.println("Task: " + task.getName() + "\nDescription:" + task.getDescription()); 
			}
		}
	}
}
