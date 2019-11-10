package ArraysTests;

public class OneDimensionalArray {

	public static void main(String[] args) {
		// declaring
		int[] arr = new int[10];

		// Putting values in array
		// First wat to do this
		int[] arr1 = { 1, 2, 3, 4, 5, 6 };

		// Second way of doing this
	
		int[] arr2 = new int[5];
/*		arr2[0] = 10;
		arr2[1] = 20;
		arr2[2] = 30;
		arr2[3] = 40;
		arr2[4] = 50;*/
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = i * 10;
		}
		
		
/*		for (int i=0;i<arr2.length;i++){
			System.out.print(arr2[i]+" ");
		}
*/
		
/*		for(int i:arr2){
			System.out.print(i+ " ");
		}
		*/
		
		char[] charArr = {'a','b','c','d','e'};
		
		for(char ch : charArr ){
			System.out.print(ch + " ");
		}
		
		
	}

}
