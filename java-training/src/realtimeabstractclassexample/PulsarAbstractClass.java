package realtimeabstractclassexample;

public abstract class PulsarAbstractClass {

	public void startBike() {
		System.out.println("All pulsar bikes use 12v electric motor for starting the bike");
	}

	public abstract void accelerateBike();

	public void stopBike() {
		System.out.println("All pulsar bikes use 120mm disc brakes technology for braking");
	}

	public abstract void getDimensions();

}
