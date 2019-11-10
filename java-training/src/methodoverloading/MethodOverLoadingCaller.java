package methodoverloading;

public class MethodOverLoadingCaller {

	public static void main(String[] args) {

		MethodOverLoading m = new MethodOverLoading();
		
		m.calculateSum(5,10);
		m.calculateSum("sachin ", "Tendulkar");
	}

}
