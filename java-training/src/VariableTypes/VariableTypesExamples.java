package VariableTypes;

public class VariableTypesExamples {
	// Member variables or Instance variables
	int id;
	String empName;
	static String companyName = "TCS";
	int yearsOfExperience;
	double rating;

	// Constructor
	/*
	 * VariableTypesExamples(int empIDParam, String empNameParam, String
	 * companyNameParam, int yearsOfExperienceParam, double ratingParam) { id =
	 * empIDParam; empName = empNameParam; companyName = companyNameParam;
	 * yearsOfExperience = yearsOfExperienceParam; rating = ratingParam; }
	 */

	VariableTypesExamples(int empIDParam, String empNameParam, int yearsOfExperienceParam, double ratingParam) {
		id = empIDParam;
		empName = empNameParam;
		yearsOfExperience = yearsOfExperienceParam;
		rating = ratingParam;
	}

	// Methods1
	void printAllData() {
		System.out.println("ID :" + id);
		System.out.println("Name :" + empName);
		System.out.println("comName :" + companyName);
		System.out.println("Exp :" + yearsOfExperience);
		System.out.println("Rating :" + rating);
	}

	
	static void printOnlyStaticData(){
		System.out.println("Inside Static Method :" + companyName);
	}
	// Method2
	void calculateSalary() {
		// local variables
		int salaryMultiplier = 100000;
		double calculatedSalary = rating * salaryMultiplier * yearsOfExperience;
		System.out.println("Calculated Salary is :" + calculatedSalary);
	}

}
