package zadanie1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Skynet {
	
	private String systemLogin = "Reese";
	private String systemPassword = "#Terminate";
		
	public static void main(String args[]) throws IOException {

		Skynet skynet = new Skynet();

		///////   Display SkyNET logo from logo.txt
		skynet.printSkyNetLogo("res/zadanie1/logo.txt");
		System.out.println("\n");

		///////   Get user login and password
		String login = skynet.getTextFromInput("login");
		String password = skynet.getTextFromInput("password");

		///////   Load system credentials from file
		skynet.readCredentialsFromFile();

		///////   Verify user login and password
		if (skynet.verifyCredentials(login, password))
			System.out.println("\n\t\tWelcome!");
		else
			System.out.println("\n\t\tFAIL!");		
	}
	
	private String getTextFromInput(String label) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(label + ": ");
		String input = scanner.nextLine();
		return input;
	}
	
	private boolean verifyCredentials(String login, String password) {
		return this.systemLogin.equals(login) && this.systemPassword.equals(password);
	}
	 
	private void readCredentialsFromFile() throws IOException {
		File credentialsFile = new File("res/zadanie1/skynet.conf");
		BufferedReader reader = Files.newBufferedReader(credentialsFile.toPath());

		this.systemLogin = reader.readLine();
		this.systemPassword = reader.readLine();
	}
	
	private void printSkyNetLogo(String filename) throws IOException  {
			String content = Files.readString(Paths.get(filename));
			System.out.println(content);
		}
}
