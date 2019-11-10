package sample;

import java.io.IOException;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);
		String str =s.nextLine();
		
		while(!str.contains("END")){
			System.out.println(str);
			str =s.nextLine();
		}
		 s.nextInt();
		System.out.println("Exiting...");
	}
}
