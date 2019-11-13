package misc;

import java.util.Date;

/**
 * @author Siddu
 */
public class Test2 {

	public static void main(String[] args) {
		/*Integer i = 2;
		System.out.println(i.doubleValue());
		System.out.println(i.toString()+1);
		
		//String s1 = "sachin";
		//String s2 = "sachin";
		String s1 = new String("sachin");
		String s2 = new String("sachin");
		
		if(s1==s2){
			System.out.println("matched");
		}else{
			System.out.println("not matched");
		}
		
		if(s1.equals(s2)){
			System.out.println("matched");
		}else{
			System.out.println("not matched");
		}*/
		
		/*Date date = new Date();
		System.out.println(date);
		System.out.println(date.getTime());*/
		
		String s1 = new String("sachinasdad");
		String s2 = new String("sachin");
		
		System.out.println(s1.matches(s2));
	}
	
}
