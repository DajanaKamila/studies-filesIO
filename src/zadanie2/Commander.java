package zadanie2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Commander {
	
	public static void main(String args[]) throws IOException {
        
		Scanner scan = new Scanner(System.in);
        System.out.println("#".repeat(9) + " FILE MANAGER v0.1 " + "#".repeat(9));
	    System.out.println("Select option: \n\t1. list \n\t2. details \n\t3. foldertree" +
        		"\n\t4. addfolder \n\t5. addfile \n\t6. exit");
	    int choice = 0; 
	    
	    while (choice != 6) {
	    	choice = scan.nextInt();
	    	switch(choice) {
	    		case 1 -> {
	    			System.out.println("File path: ");
	    			String path = scan.next();
	    			simpleFileList(path);
	    		}
	    		case 2 -> {
	    			System.out.println("File path: ");
	    			String path = scan.next();
	    			detailedFileList(path);
	    		}
	    		case 3 -> {
	    			System.out.println("Folder path: ");
	    			String path = scan.next();
	    			printFolderTree(path);
	    		}
	    		case 4 -> {
	    			System.out.println("Folder path: ");
	    			String path = scan.next();
	    			addNewFolder(path);
	    		}
	    		case 5 -> {
	    			System.out.println("File path: ");
	    			String filename = scan.next();
	    			addNewFile(filename);
	    		}
	    		case 6 -> {
	    			System.out.print("Bye!");
	    			break;
	    		}
	    		default -> {
	    			System.out.println("Wrong number. Try again.");
	    		}
	    	}
	    }     
	}
	
	public static void simpleFileList(String path) {
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			
			for (File file : listOfFiles) {
				System.out.println(file.getName());
			}
	}
	
	public static void detailedFileList(String path) throws IOException {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		Path filePath = Paths.get(path);
		BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
		System.out.println("nazwa pliku \tDIR lub rozmiar w bajtach \tdata stworzenia");
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");
		int nameLength = 0;
		
		for (File file : listOfFiles) {
			if (file.getName().length() > nameLength){
				nameLength = file.getName().length();
			}
		}	
		
		for (File file : listOfFiles) {
			int extraSpaces = nameLength - file.getName().length();
			String space = " ";
			System.out.println(file.getName() + space.repeat(extraSpaces) +"\t" +  attributes.size() + 
					"\t" + df.format(attributes.creationTime().toMillis()));
		}
	}
	
	public static void printFolderTree(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			System.out.println(file.getPath());
			if (file.isDirectory()) {
				printFolderTree(file.toString());
			}
		}
	}
	
	public static void addNewFolder(String path) {
		new File(path).mkdirs();
	}
	
	public static void addNewFile(String filename) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(filename);
		printWriter.close();
	}
}
