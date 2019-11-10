package oopsdatahiding;

public class DataHidingExample {

	// Best practice is to declare variables private always and use public
	// getter and setter methods to access variables from outside the class
	// pubic,private,protected
	
	String name = "shiva";
	public int salary = 100000;

/*	public int getSalary() {
		return salary;
	}

	public void setSalary(int newSalaryValue) {
		salary = newSalaryValue;
	}*/

	public void printSalary() {
		System.out.println(salary);
	}

}
