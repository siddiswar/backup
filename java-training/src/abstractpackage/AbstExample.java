package abstractpackage;

public abstract class AbstExample {

	
	
	public void calcSum(int a,int b){
		System.out.println(a+b);
	}
	
	public void calcMul(int a,int b){
		System.out.println(a*b);
	}
	
	public abstract void calSqrt(int a) ;
	
	public abstract void calLog(int a);
}
