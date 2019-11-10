package realtimeabstractclassexample;

public class RealAbstractClassExampleCaller {

	public static void main(String[] args) {


		System.out.println("pulsar 150");
		
		Pulsar150 p150 = new Pulsar150();
		p150.startBike();
		p150.accelerateBike();
		p150.stopBike();
		p150.getDimensions();
		
		System.out.println("pulsar 180");
		
		Pulsar180 p180 = new Pulsar180();
		p180.startBike();
		p180.accelerateBike();
		p180.stopBike();
		p180.getDimensions();
		
		
		System.out.println("pulsar 220");
		
		Pulsar220 p220 = new Pulsar220();
		p220.startBike();
		p220.accelerateBike();
		p220.stopBike();
		p220.getDimensions();
	}

}
