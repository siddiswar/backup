package VariableTypes;

public class StaticVaribaleExample {

	
	int nonStaticVar=0;
	static int staticVar=0;
	
	
	void incrementValues(){
		nonStaticVar++;
		staticVar++;
	}
	
	void printAllData(){
		System.out.println("non static :" + nonStaticVar);
		System.out.println("static " + staticVar);
	}
}
