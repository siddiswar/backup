package exceptionhandling;

import java.io.FileNotFoundException;

public class ExceptionHandlingExampleCaller {

	public static void main(String[] args) {
		// What should do in case of exception?
		// 1 Catch the exception,graceful termination of program
		// 2 Catch the exception,proceed with default values and continue the
		// execution

		ExceptionHandlingExample obj = new ExceptionHandlingExample();

		// obj.byZeroException();

		// obj.nullExceptionMethod();

		// obj.tryCatchFinallyMethod();

		// obj.multipleCatchBlocks();

		/*
		 * try { obj.handleExceptionInCallingProgram(); } catch (Exception e) {
		 * System.out.println("Handling exception in parent program.."); }
		 */

		try {
			obj.thisMethodThrowsException3();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
