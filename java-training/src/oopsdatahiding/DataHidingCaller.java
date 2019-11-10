package oopsdatahiding;

public class DataHidingCaller {

	public static void main(String[] args) {

		DataHidingExample obj = new DataHidingExample();
		
		System.out.println(obj.name);
		
		System.out.println(obj.salary);
		
		//System.out.println(obj.getSalary());
		
		//obj.setSalary(150000);
		
		//System.out.println(obj.getSalary());

		
		obj.salary = 200000;
		
		System.out.println(obj.salary);
	}

}
