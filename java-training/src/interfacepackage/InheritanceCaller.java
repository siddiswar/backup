package interfacepackage;

public class InheritanceCaller {

	public static void main(String[] args) {
		InterfaceInheriter obj = new InterfaceInheriter();
		
		obj.cashDeposit();
		obj.cashTransfer();
		obj.cashwithdraw();
		obj.checkbalance();
	}

}
