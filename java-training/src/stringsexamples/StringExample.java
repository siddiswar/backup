package stringsexamples;

public class StringExample {

	public static void main(String[] args) {

		
		//Declaring strings
		
		//Case 1 
/*			String str = "sachin ";
			
			System.out.println(str);
			
			
			//length
			int strLength= str.length();
			System.out.println("Length of String Is :" + strLength);
			
			//Concatenation
			//using concat()
			String str1 = str.concat(" Ramesh ");
			System.out.println("Concatenated String is :"+ str1);
			
			//Using +
			
			String str2 = str + str1;
			System.out.println("Concat using + :" + str2);
			
			
			String str3 = " viru " + " sehwag";
			System.out.println(str3);*/
			
			
/*			int i= 5;
			int j=6;
			String s1 = "sachin";
			
			String s2 = s1+(i+j);
			System.out.println(s2);*/
			
/*			String str1 = "asdsadsadsa";
			String str2 = "sachin ramesh tendulkar";
			
			if(str2.equals(str1)){
				System.out.println("Strings are equal");
			}else{
				System.out.println("Strings are not equal");
			}
			
		System.out.println("============================");	
			if(str2.equalsIgnoreCase(str1)){
				System.out.println("Strings are equal");
			}else{
				System.out.println("Strings are not equal");
			}*/
		
		
/*		String str1 ="Sachin Ramesh Tendulkar";
		
		String substr1 = str1.substring(5);
		System.out.println(substr1);
		
		String substr2 = str1.substring(5, 10);
		System.out.println(substr2);	*/
		
		
/*		
		String str1 ="Sachin Ramesh Tendulkar";
		
		String[] spliVales = str1.split("m");
		
		for(int i=0;i<spliVales.length;i++){
			System.out.println(spliVales[i]);
		}*/
/*		String str1 ="saranya";
		String str2 ="ananya";

		
		int i=str1.compareTo(str2);
		
		System.out.println(i);
		
		if(i==0){
			System.out.println("Both strings are equl");
		}else if(i<0){
			System.out.println("Left string is greater");
		}else{
			System.out.println("Right string is greater");
		}
		*/
		
/*		String s1 = " SACHIN ramesh 1233252376523765427632576527326523765467";
		System.out.println(s1.trim());
		
		System.out.println(s1.toUpperCase());
		System.out.println(s1.toLowerCase());*/
		
		
		String s1 = "SACHIN ramesh Tendulkar";
		for(int i=0;i<s1.length();i++){
			char ch;
			if(i==0||i==s1.length()-1){
				ch = s1.charAt(i);
			}else{
				ch = '.';
			}
			System.out.print(ch);
		}
		
	
		

		
	}

}
