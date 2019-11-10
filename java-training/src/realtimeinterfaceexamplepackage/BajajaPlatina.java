package realtimeinterfaceexamplepackage;

public class BajajaPlatina implements BajajBikeInterface{


	public void startBike() {
		System.out.println("platina uses manual kick rod technology to start bike");
		
	}

	public void accelerateBike() {
		System.out.println("platina uses 100cc platina technology engine to accelerate bike");
	}

	
	public void stopBike() {
		System.out.println("Platina uses drum brakes technology to stop bike");
	}

	public void getDimensions() {
		System.out.println("Platina has l1 * b1 * w1 dimesnios");
	}
	
	

}
