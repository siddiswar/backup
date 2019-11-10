package iopackage.textfilesreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadingTextFileExample {

	public static void main(String[] args) throws IOException {
		// This example shows how to read data from text file using BufferedReader class
		
/*		BufferedReader bufferedReaderObject =  new BufferedReader(new FileReader("printwriter.txt"));
		
		//read method returns ascii value of character .Once it reaches end of file it returns -1
		int asciiValueOfCharacter = bufferedReaderObject.read();
		
		while(asciiValueOfCharacter!=-1){
			System.out.println((char)asciiValueOfCharacter);
			asciiValueOfCharacter = bufferedReaderObject.read();
		}
		System.out.println(asciiValueOfCharacter);*/
		
		//Disadvantages od read() method 
		//It reads character by character.That leads to too many read operations
		//It returns ascii value of character and not character itself.We need to do additional type casting to get actual character
		
		
		//There is another method called readLine() method which overcomes short comings of read() method
		//It reads line by line and not character by character. less read operations
		//It returns actual characters and not ascii values
		//readLine() method returns one line at a time .When it reaches end of the file it returns null
		
		
		BufferedReader bufferedReaderObject =  new BufferedReader(new FileReader("printwriter.txt"));
		
		String str = bufferedReaderObject.readLine();
		
		while(str!=null){
			System.out.println(str);
			str = bufferedReaderObject.readLine();
		}
		
		System.out.println(str);
		
		System.out.println("Done with reading text file");

	}

}
