package ArraysTests;

public class TwoDimensionalArray {

	public static void main(String[] args) {



/*		// Initializing part
		int[][] c = { { 10, 20, 30, 40, 40, 60 }, { 100, 200, 300, 400, 500, 600 },
				{ 1000, 2000, 3000, 4000, 5000, 6000 } };

		
		for(int i=0;i<3;i++){
			for(int j=0;j<6;j++){
				System.out.print(c[i][j]+ " ");
			}
			System.out.println();
		}
*/
		// Declaring
		int[][] b = new int[3][6];
		
		for(int i=0;i<3;i++){
			for(int j=0;j<6;j++){
				b[i][j] = i*j*10;
			}
			
		}
		for(int i=0;i<3;i++){
			for(int j=0;j<6;j++){
				System.out.print(b[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}
