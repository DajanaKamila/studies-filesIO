package cwiczenie1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws Exception {
		scanFile("res/cwiczenie1/poem.txt", "of");
		readFile("res/cwiczenie1/poem.txt", "of", "but");
		readChars("res/cwiczenie1/poem.txt");
//		printFile("res/cwiczenie1/poem.txt");
	}
	
	private static void scanFile(String filename, String keyword) throws FileNotFoundException {
		File poemFile = new File(filename);
		Scanner scanner = new Scanner(poemFile);
		int counter = 0;
		while (scanner.hasNext()) {
			String nextLine = scanner.next();
			if (nextLine.equals(keyword)) {
				System.out.println(nextLine);
				counter ++;
			}
		}
		
		System.out.println("Found tokens: " + counter);
		scanner.close();

		String path = poemFile.getPath();
		System.out.println("File path: " + path);

		long freeSpace = poemFile.getFreeSpace();
		System.out.println("Free space: " + freeSpace + "\nWhich is: " + (freeSpace/1048576) + " MB or " + 
		+ (freeSpace/1073741824) + " GB");
	}

	public static void readFile(String filename, String... keywords) throws IOException {
		File poemFile = new File(filename);
		BufferedReader reader = Files.newBufferedReader(poemFile.toPath());
		String line = null;
		int lineCounter = 0;
		int whichLine = 1;

		while ((line = reader.readLine()) != null){
			whichLine ++;
			for (String keyword:keywords){
				if (line.contains(keyword)){
					System.out.println(line + " || Line number: " + whichLine);
					lineCounter ++;
					break;
				}
			}
		}
		System.out.println("Number of printed lines: " + lineCounter);
	}

	public static void readChars(String filename) throws IOException {
		File poemFile = new File(filename);
		try (BufferedReader reader = Files.newBufferedReader(poemFile.toPath())) {
			int nextChar = 0;
			int numberOfLines = 0;
			int numberOfChar = 0;
			while ((nextChar = reader.read()) != -1){
				numberOfChar ++;
				if (nextChar == '\n'){
					numberOfLines ++;
				}
			}
			System.out.println("Number of lines: " + numberOfLines);
			System.out.println("Number od characters: " + numberOfChar);
		} catch (IOException x) {
			System.err.format("IOException: %s", x);
		}
	}

	public static void printFile(String filename) throws IOException {
		String content = Files.readString(Paths.get(filename));
		System.out.print(content);
	}
}
