package methodoverriding;

public class MethodOverRidingInherited extends MethodOverriding{
	
	public void calculateSum(int a,int b){
		System.out.println("I am child class method");
		System.out.println(a+b);
		//super.calculateSum(100, 200);
	}
	

}
