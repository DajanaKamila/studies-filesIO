package cwiczenie2;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String input = "";

		while (!input.equals("exit")) {
			System.out.println("Możesz pisać: ");
			input = scanner.nextLine();
			System.out.println("Napisałaś: " + input);
		}
		scanner.close();
	}
}
