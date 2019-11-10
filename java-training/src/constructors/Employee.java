package constructors;

public class Employee {
//data or variables
	int id;
	String employeeName;
	String companyName;

	

	// Constructor without parameters
	
	Employee() {
		System.out.println("Inside Constructor without parametrs");
		id = 2;
		employeeName = "shivakrishna";
		companyName = "TCS";
		System.out.println("Done with initialization in constructor");
	}

	// constructor with parameters
	Employee(int paramID, String empNameParam, String companyNameParam) {
		System.out.println("Inside Constructor with parametrs");
		id = paramID;
		employeeName = empNameParam;
		companyName = companyNameParam;
		System.out.println("Done with initialization in constructor");
	}

	Employee(int idParam) {
		System.out.println("Inside constructor with single parameter");
		id = idParam;
	}
//Methods which act on data
	void printAllData() {
		System.out.println("Inside Method");
		System.out.println("ID :" + id);
		System.out.println("Emp Name :" + employeeName);
		System.out.println("Company Name :" + companyName);
	}
	

}
