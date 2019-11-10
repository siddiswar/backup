package abstractpackage;

public class AbstractCaller {

	public static void main(String[] args) {

		//AbstExample obj1 = new AbstExample(); 
		AbstInherited obj1 = new AbstInherited();
		obj1.calcMul(5, 10);
		obj1.calcSum(5, 10);
		obj1.calSqrt(10);
		obj1.calLog(10);
		
	}

}
