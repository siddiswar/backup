package VariableTypes;

public class VariableTypesCaller {


	
	
	public static void main(String[] xyz) {
		
		
		int[] intArr = new int[5];

		VariableTypesExamples.printOnlyStaticData();
		
		
		System.out.println("Before even first object got created :");

		System.out.println("Value of static variable :" +VariableTypesExamples.companyName); 
		
		System.out.println("Now Creating first object ..");
		//step1
		VariableTypesExamples obj1 = new VariableTypesExamples(1, "shiva", 9, 0.9);
		obj1.printAllData();
		obj1.calculateSalary();
		
		System.out.println("===========================");
		//step2
		VariableTypesExamples obj2 = new VariableTypesExamples(2, "mahi", 8, 0.8);
		obj2.printAllData();
		obj2.calculateSalary();
		System.out.println("===========================");

		//step3
		VariableTypesExamples obj3 = new VariableTypesExamples(3, "saran", 10, 0.99);
		obj3.printAllData();
		obj3.calculateSalary();
		
	}

}
