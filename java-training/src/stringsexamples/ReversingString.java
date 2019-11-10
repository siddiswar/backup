package stringsexamples;

public class ReversingString {

	public static void main(String[] args) {
		// reversing a String

		String str = "sachin";
		/*
		 * nachis nichas nihcas
		 */

		int strlength = str.length();

		char temp;

		char[] tempcharArr = new char[6];
		
		for (int i = 0; i < strlength / 2; i++) {

			char firstChar = str.charAt(i);
			char lastChar = str.charAt(strlength-i - 1);
/*			temp = firstChar;
			firstChar = lastChar;
			lastChar = temp;*/

			tempcharArr[i] = lastChar;
			tempcharArr[strlength - i - 1] = firstChar;
		}
		
		for(int j=0;j<tempcharArr.length;j++){
			System.out.print(tempcharArr[j]);
		}
	}

}
