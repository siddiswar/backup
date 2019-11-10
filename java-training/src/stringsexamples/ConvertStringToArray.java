package stringsexamples;

public class ConvertStringToArray {

	public static void main(String[] args) {

//We want to convert String to Character array
		
		
		String str= "sachin";
		
/*		char[] charArray = str.toCharArray();
		
		for(int i=0;i<charArray.length;i++){
			System.out.print(charArray[i]+" ");
		}
		*/
		
		for(int i=0;i<str.length();i++){
			System.out.println(str.charAt(i));
		}
	}

}
