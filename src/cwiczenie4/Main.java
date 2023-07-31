package cwiczenie4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String args[]) throws Exception {
		printFilesSimple("C:\\");
		printFilesDetails("C:\\");
		printTree("res");
	}
 
	public static void printFilesSimple(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			System.out.println(file.getName());
		}
	}

	public static void printFilesDetails(String path) throws Exception {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		Path filePath = Paths.get(path);
		BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
		System.out.println("nazwa pliku \tDIR lub rozmiar w bajtach \tdata stworzenia");
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");
		int nameLength = 0;
		String space = " ";
				
		for (File file : listOfFiles) {
			if (file.getName().length() > nameLength){
				nameLength = file.getName().length();
			}
			int extraSpaces = nameLength - file.getName().length();
			System.out.println(file.getName() + space.repeat(extraSpaces) +"\t" +  attributes.size() + 
					"\t" + df.format(attributes.creationTime().toMillis()));
		}
	}

	public static void printTree(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			System.out.println(file.getPath());
			if (file.isDirectory()) {
				printTree(file.toString());
			}
		}
	}
}
