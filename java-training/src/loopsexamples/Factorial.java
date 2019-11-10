package loopsexamples;

public class Factorial {

	public static void main(String[] args) {

			//5! = 5*4*3*2*1
		
		
		int number =5;
		
		int factorialValue = 1;
		
		for(int i=5;i>=1;i--){
			factorialValue = factorialValue * i;
		}
		
		System.out.println(factorialValue);
	}

}
