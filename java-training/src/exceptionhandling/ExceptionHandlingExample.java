package exceptionhandling;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionHandlingExample {

	void byZeroException() {

		try {
			System.out.println("Opnening database connections...");
			System.out.println("Method starts here");
			int i = 10;
			int j = 0;
			int k;
			k = i / j;
			System.out.println(k);
			System.out.println("Close the database connections");
			System.out.println("Method ends here");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Handling the exception");
			System.out.println("Closing database connections after exection in catch block");
		}

	}

	void nullExceptionMethod() {
		String str = null;
		try {
			if (str.equals("sachin")) {
				System.out.println("I am sachin");
			} else {
				System.out.println("I am not sachin");
			}
		} catch (Exception e) {
			str = "tendulkar";
		}

		System.out.println("Done with comparison");

		System.out.println("I have 100 hundreds on my name");
	}

	void tryCatchFinallyMethod() {
		try {
			System.out.println("Opening database connections");

			int i = 10;
			int j = 0;
			System.out.println(i / j);

		} catch (Exception e) {
			System.out.println("Catch Block");

		} finally {
			System.out.println("I am finally block");
			System.out.println("Closing the database connections");
		}
	}

	void multipleCatchBlocks() {

		try {
			System.out.println("Execution starts here");
			/*
			 * int i=10; int j=0; System.out.println(i/j);
			 */

			/*
			 * String str = null; if (str.equals("xyz")) { System.out.println(
			 * "I am something"); }
			 */

			int[] intArr = new int[5];
			intArr[8] = 10;

			System.out.println("Execution ends here");

		} catch (ArithmeticException e) {
			System.out.println("Arithmetic Exception block");

		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception block");

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array IndexOutOfBounds Exception block");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception block");
		} finally {
			System.out.println("I am finally block ");
		}
	}

	void handleExceptionInCallingProgram() {

		int[] intAr = new int[5];
		intAr[8] = 10;
	}

	void thisMethodThrowsException1() throws FileNotFoundException{
		FileReader fr = new FileReader("notexistingfile.xlsx");

	}

	void thisMethodThrowsException2() throws FileNotFoundException {
		this.thisMethodThrowsException1();
	}

	void thisMethodThrowsException3() throws FileNotFoundException {
		this.thisMethodThrowsException2();
	}
}
