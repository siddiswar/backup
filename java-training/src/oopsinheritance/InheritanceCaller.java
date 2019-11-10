package oopsinheritance;

public class InheritanceCaller {

	public static void main(String[] args) {

		System.out.println("=======Grand Father's data=======");
		GrandFather gf = new GrandFather();

		System.out.println(gf.gf_asset1);
		System.out.println(gf.gf_asset2);
		gf.gf_calculateTotalAssetCost();

		System.out.println("=======Father's data========");
		Father f = new Father();

		System.out.println(f.f_asset1);
		System.out.println(f.f_asset2);
		f.f_calculateTotalAssetCost();
		System.out.println("---Inherited by father from grand father---");
		System.out.println(f.gf_asset1);
		System.out.println(f.gf_asset2);
		f.gf_calculateTotalAssetCost();

	}

}
