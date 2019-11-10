package constructors;

public class EmployeeCaller {

	public static void main(String[] args) {

		System.out.println("Starting ....");
		
		//When a new object of a class is created ,The constructor gets executed first,
		//Newly created object is assigned to the object variable
		
		
/*		System.out.println("Creating first object :");
		
		Employee emp1 = new Employee(1,"Mahender","TCS");
		emp1.printAllData();

		System.out.println("====================");
		System.out.println("Creating Second object :");
		
		Employee emp2 = new Employee();
		emp2.printAllData();
		
		System.out.println("====================");
		System.out.println("Creating Second object :");
		
		Employee emp3 = new Employee(3);
		emp3.printAllData();*/
		
/*		Employee emp2 = new Employee(2,"Shivakrishna","TCS");
		emp2.printAllData();

		
		
		System.out.println("====================");
		System.out.println("Creating third object :");
		Employee emp3 = new Employee(3,"Karthik","TCS");
		emp3.printAllData();
		System.out.println("Done...");*/
		
		EmployeeWithoutConstructor obj1 = new EmployeeWithoutConstructor();
		obj1.printAllData();
		

	}

}
