package ArraysTests;

public class FindingLargestWithoutTemp {

	public static void main(String[] args) {

		//Without using temp variable
		int[] intArr = {245,234,1340,200,13};
/*						234,245
						234,134,245
						234,134,200,245
						234,134,200,13,245
*/
		
		for(int i=0;i<intArr.length-1;i++){

				if(intArr[i]>intArr[i+1]){
					intArr[i+1] = intArr[i];
				}
		}
		
		
		System.out.println(intArr[4]);
		
	}

}
