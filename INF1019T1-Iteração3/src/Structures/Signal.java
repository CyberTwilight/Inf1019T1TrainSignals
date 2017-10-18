package Structures;

public abstract class Signal {
	private static SystemController sc;
	public static Signal getInitialState (SystemController syscont) {
		sc = syscont;
		return new BothWaysOpen();
	}
	public abstract Signal LeftEntranceSensorActivated();
	public abstract Signal LeftExitSensorActivated();
	public abstract Signal RightEntranceSensorActivated();
	public abstract Signal RightExitSensorActivated();
	public abstract String CurrentState();
	
}
