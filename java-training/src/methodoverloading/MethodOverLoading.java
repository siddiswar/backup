package methodoverloading;

public class MethodOverLoading {

	public void calculateSum(int a, int b) {
		System.out.println("I am 2 param method");
		System.out.println(a + b);
	}

	public void calculateSum(int a, int b, int c) {
		System.out.println("I am 3 param method");
		System.out.println(a + b + c);
	}

	public void calculateSum(int a, int b, int c, int d) {
		System.out.println("I am 4 param method");
		System.out.println(a + b + c + d);
	}
	
	public void calculateSum(String a,String b){
		System.out.println("I am 2 param method but parm type is string");
		System.out.println(a+b);
		
	}
}
