import java.util.Scanner;

public class readingfromconsole {

	public static void main(String[] args) {

		//Java program to console => Standard output => System.out
		//System.out.println("helloo");
		
		//console to java program => standard input => Syatem.in
		
		System.out.println("Enter your Name :");
		Scanner scannerObject = new Scanner(System.in);
		
		String valueFromConsole = scannerObject.nextLine();
		System.out.println("You Name is :" + valueFromConsole);
		
		System.out.println("Enter your Age :");
		valueFromConsole = scannerObject.nextLine();
		System.out.println("Your age is :" + valueFromConsole);
		
		
		
	}

}
