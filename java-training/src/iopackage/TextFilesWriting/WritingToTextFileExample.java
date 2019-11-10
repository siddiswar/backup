package iopackage.TextFilesWriting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WritingToTextFileExample {

	public static void main(String[] args) throws FileNotFoundException {

		//This example shows how to write text data to text files using PrintWriter class
		
		PrintWriter printWriterObject = new PrintWriter("printwriter.txt");
		
		printWriterObject.println("sachin");
		printWriterObject.println(" Tendulkar");
		
		printWriterObject.println("virender ");
		printWriterObject.println(" sehwag");
		
		printWriterObject.println(100);
		
		printWriterObject.println(true);
		
		printWriterObject.println(100.23);
		
		printWriterObject.flush();
		printWriterObject.close();
		
		System.out.println("Done with writing to text file");
		
	}

}
