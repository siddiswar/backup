package ArraysTests;

public class SummingElementsArray {

	public static void main(String[] args) {

			int[] intAr = {1,2,3,4,5,6};
			
			int lengthOfArray = intAr.length;
			System.out.println("Length Of Array : " + lengthOfArray);
			
			int sum=0;
			for(int i=0;i<lengthOfArray;i++){
				sum = sum + intAr[i];
			}
			
			System.out.println(" Sum : "+ sum);
	}

}
