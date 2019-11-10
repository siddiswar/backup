package ArraysTests;

public class ReversingArray {

	public static void main(String[] args) {
		int[] intArr = {245,234,1340,200,13,45};
/*						45,   ,     ,   ,   ,245
						   13,   ,   ,234
						      200 ,1340
						45,13,200,1340,234,245*/
		
		int temp;
		int len = intArr.length;
		
		for(int i=0;i<(len)/2;i++ ){
			temp = intArr[i];
			intArr[i] = intArr[len-i-1];
			intArr[len-i-1] = temp;
		}
		
		
		for(int i=0;i<(len);i++){
			System.out.print(intArr[i] + " ");
		}
		
		
						
						
		
		
	}

}
