package classandobjects;

public class MethodExamplesCallingProgram {

	public static void main(String[] args) {

		MethodExamplesClass m1 = new MethodExamplesClass();
		System.out.println("First Method"); 
		
		m1.printArea();
		
		System.out.println("Second Method");
		int area = m1.getArea();
		System.out.println(area);
		
		
		System.out.println("Third Method");
		int area1 =	m1.calculateArea(60,30);
		System.out.println(area1);
		
	}
	
	

}
