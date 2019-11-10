package ArraysTests;

public class FindingLargestElementFromArray {

	public static void main(String[] args) {
		
		//Using a temp variable
		int[] intArr = {186,234,134,200,1300};
		int temp = 0;
		
		for(int i=0;i<intArr.length;i++){

			if(intArr[i] > temp){
				temp=intArr[i]; 
			}
		}
		
		System.out.println(temp);
		
		
		
		
	}

}
