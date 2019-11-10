package loopsexamples;

public class FibonacciSeries {

	public static void main(String[] args) {	
		
		//0 1 1 2 3 5 8 13 21 .......
		
		//Print first 10 fibonacci numbers
		
		int a=0;
		int b=1;
		
		int c;
		
		for(int i=0;i<20;i++){
			System.out.print(a);
			c=a+b;
			a=b;
			b=c;
/*			System.out.print(" " +b);
			System.out.print(" " + c);
			System.out.println();*/
		}
		
	}

}
