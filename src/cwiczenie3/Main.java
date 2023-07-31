package cwiczenie3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static String[] stringsToBeWritten = { "Joh Woo 001", "Robert Duval 002", "James Bond 007" };

	public static void main(String args[]) throws Exception {
		saveAgents(stringsToBeWritten, "res/cwiczenie3/special-agents.txt");
		addAgents(new String[]{"n1", "n2", "n3"}, "res/cwiczenie3/special-agents.txt");
		makeFolders();
		copyAgents("res/cwiczenie3/special-agents.txt", "res/agents/active-agents/special-agents.txt");
		deleteFolder("res/agents/missing-agents");
	}

	private static void saveAgents(String[] agents, String filename) throws Exception {
		PrintWriter printWriter = new PrintWriter(filename);
		for (String agent : agents){
			printWriter.println(agent);
		}
		printWriter.close();
	}
	
	private static void addAgents(String[] agents, String filename) throws Exception {
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(filename),true));
		for (String agent : agents){
			printWriter.println(agent);
		}
		printWriter.close();
	}

	public static void makeFolders(){
		new File("res/agents").mkdirs();
		new File("res/agents/active-agents").mkdirs();
		new File("res/agents/missing-agents").mkdirs();
	}

	public static void copyAgents(String sourceFile, String destinationFile){
		Path sourcePath = Paths.get(sourceFile);
		Path destinationPath = Paths.get(destinationFile);
		try {
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFolder(String folderPath) throws IOException{
		Path path = Paths.get(folderPath);
		try {
			Files.delete(path);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
