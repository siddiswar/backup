package collectionspackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class HashMapExample {

	public static void main(String[] args) {
//		ArrayList<Integer> arrayListObject = new ArrayList<Integer>();

		HashMap<String,Integer> hashMapObject = new HashMap<String,Integer>();
		
		hashMapObject.put("karthik", 964354325);
		hashMapObject.put("mahender", 89765432);
		hashMapObject.put("shiva", 323243676);
		hashMapObject.put("kalyan", 767678723);
		
		System.out.println(hashMapObject.get("kalyan"));
		System.out.println(hashMapObject.get("karthik"));
		System.out.println("===================");

		//{karthik,mahender,shiva,kalyan}
		Set<String> keysOfMap = hashMapObject.keySet();
		
		for(String key:keysOfMap){
			System.out.println("Key is :" + key);
			System.out.println("Value is :" + hashMapObject.get(key));
			System.out.println("-------");
		}

		if(hashMapObject.containsKey("asdada")){
			System.out.println("the key karthik is present");
		}else{
			System.out.println("the key karthik is not present");

		}
		
	}

}
