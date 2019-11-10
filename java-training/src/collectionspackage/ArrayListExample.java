package collectionspackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class ArrayListExample {

	public static void main(String[] args) {
		//ArrayList
		//In angular brackets provide data type => provide Wrapper class and not primitive data type
		//Use angular brackets
		ArrayList<Integer> arrayListObject = new ArrayList<Integer>();

		
		arrayListObject.add(1000);
		arrayListObject.add(50);
		arrayListObject.add(100);
		arrayListObject.add(30);
		
		
		System.out.println("Size of array list :" +arrayListObject.size());
		
		//for 
		//advanced for loop also called as for each loop
		
		for(Integer intValue : arrayListObject){
			System.out.println(intValue);
		}
		
		
		if(arrayListObject.isEmpty()){
			System.out.println("arrayList is empty");
		}else{
			System.out.println("array list is not empty");
		}
		
		
		if(arrayListObject.contains(700)){
			System.out.println("Element is present in array list collection");
		}else{
			System.out.println("Element is not present in array list collection");

		}
		
		Collections.sort(arrayListObject);
		System.out.println("=========Values after sortinig================");
		
		for(Integer intValue : arrayListObject){
			System.out.println(intValue);
		}
		
	}

}
