package wrapperclasses;

public class WrapperClassExample {

	public static void main(String[] args) {
			
		
/*		int i = 10;
	
		
		//Integer is the wrapper class of int
		//wrapping
		Integer intObject = new Integer(i);
		
		//unwrapping
		intObject.intValue();
		System.out.println(intObject.intValue());
		*/
		
		//Constructors
		//const1
/*		Integer intObject1 = new Integer(100);
		System.out.println(intObject1.intValue());
		
		Integer intObject2 = new Integer("200");
		System.out.println(intObject2.intValue());*/
		
		//double => Double
		
/*		Double dbObject1= new Double(100.2);
		System.out.println(dbObject1.doubleValue());
		
		Double dbObject2 = new Double("100.2");
		System.out.println(dbObject2.doubleValue());*/
		
		//boolean ==> Boolean
		//true,false
		
		
/*		Boolean bnObject1 = new Boolean(true);
		System.out.println(bnObject1.booleanValue());
		
		Boolean bnObject2 = new Boolean("true");
		System.out.println(bnObject2.booleanValue());
		
		Boolean bnObject3 = Boolean.valueOf(true);*/
		
		Integer iObj = Integer.valueOf(100);
		
		iObj.intValue();
		iObj.byteValue();
		iObj.shortValue();
		iObj.longValue();
		iObj.floatValue();
		iObj.doubleValue();
		
		Long lObj = new Long(20000);
		
		//"sachin tendulkar"
		//"100"
		
		//parse method converts a string into corresponding number primitive data type
/*		int i = Integer.parseInt("100");
		Double d = Double.parseDouble("100.45");*/
		
		//converts primitive dta into string
		int i= 100;
		Integer iObj1 = new Integer(100);
		iObj1.toString();
		
		
		
		
		
		
	}

}
