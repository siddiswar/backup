package oopsaccessmodifiers.package2;

import oopsaccessmodifiers.package1.GrandFather;

public class Father extends GrandFather {
		
	public int f_asset1= 300000;
	public int f_asset2 = 400000;
	
	
	public void calculateTotalAssetsOfFather(){
		System.out.println(f_asset1+f_asset2+gf_asset1+gf_asset2);
	}

}
