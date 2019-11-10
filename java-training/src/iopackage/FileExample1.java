package iopackage;

import java.io.File;
import java.io.IOException;

public class FileExample1 {

	public static void main(String[] args) throws IOException {
			//Creating a file
		
		File fileObject = new File("test1.txt");
		
		if(fileObject.exists()){
			System.out.println("test1.txt file exists");
		}else{
			System.out.println("test1.txt file doesn't exist");
		}
		
		fileObject.createNewFile();
		
		if(fileObject.exists()){
			System.out.println("test1.txt file exists");
		}else{
			System.out.println("test1.txt file doesn't exist");
		}

		
		System.out.println(fileObject.getName());
		System.out.println(fileObject.getAbsolutePath());
	}

}
