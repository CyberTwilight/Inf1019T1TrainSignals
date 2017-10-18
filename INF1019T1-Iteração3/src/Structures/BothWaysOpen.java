package Structures;

public class BothWaysOpen extends Signal{

	@Override
	public Signal LeftEntranceSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().AddLeftLane();
		return new LeftLaneOpen();
	}

	@Override
	public Signal LeftExitSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().RemoveRightLane();
		return null;
	}

	@Override
	public Signal RightEntranceSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().AddRightLane();
		return new RightLaneOpen();
	}

	@Override
	public Signal RightExitSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().RemoveRightLane();
		return null;
	}

	@Override
	public String CurrentState() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

}
