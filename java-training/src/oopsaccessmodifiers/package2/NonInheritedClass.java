package oopsaccessmodifiers.package2;

import java.awt.GradientPaint;

import oopsaccessmodifiers.package1.GrandFather;

public class NonInheritedClass {

	
	public void calculateGrandFatherAssets(){
		GrandFather gf = new GrandFather();
		System.out.println(gf.gf_asset1+gf.gf_asset2);
		
	}
}
