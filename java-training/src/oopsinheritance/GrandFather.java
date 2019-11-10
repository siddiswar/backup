package oopsinheritance;

public class GrandFather {

	int gf_asset1 = 100000;
	int gf_asset2 = 200000;
	
	
	void gf_calculateTotalAssetCost(){
		System.out.println("GrandFather's Asset Total Cost :" + (gf_asset1+gf_asset2));
	}
}
