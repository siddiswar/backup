package iopackage.binaryfilereading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadingBinaryFileExample {

	public static void main(String[] args) throws IOException {
		
		//This example is for reading a binary file using FileInputStream
		
		
		FileInputStream fisObject = new FileInputStream("sachin1.jpg");
		FileOutputStream fosObject = new FileOutputStream("sachin2.jpg");

		int intEquivalentOfByte = fisObject.read();
		
		while(intEquivalentOfByte!=-1){
			fosObject.write(intEquivalentOfByte);
			
			intEquivalentOfByte = fisObject.read();
		}
		
		fosObject.flush();
		fosObject.close();
		fisObject.close();
		
		System.out.println("Done with writing binary data from one binary file to another binary file");
	}

}
