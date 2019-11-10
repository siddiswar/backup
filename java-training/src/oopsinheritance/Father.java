package oopsinheritance;

public class Father extends GrandFather {
	
	int f_asset1 = 300000;
	int f_asset2 = 400000;
	
	
	void f_calculateTotalAssetCost(){
		System.out.println("Father's Asset Total Cost :" + (f_asset1+f_asset2));
	}

}
