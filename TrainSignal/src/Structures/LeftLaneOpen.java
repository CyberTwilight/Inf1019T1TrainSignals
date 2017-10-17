package Structures;

public class LeftLaneOpen extends Signal{

	@Override
	public Signal LeftEntranceSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().AddLeftLane();
		return null;
	}

	@Override
	public Signal LeftExitSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().RemoveLeftLane();
		if(SystemController.GetInstance().GetLeftLane() == 0){
			if(SystemController.GetInstance().GetRightLane() > 0){
				return new RightLaneOpen();
			}
		}
		return new BothWaysOpen();
	}

	@Override
	public Signal RightEntranceSensorActivated() {
		// TODO Auto-generated method stub
		SystemController.GetInstance().AddRightLane();
		return null;
	}

	@Override
	public Signal RightExitSensorActivated() {
		// TODO Auto-generated method stub
		System.out.println("Erro, sensor de saida da direita ativado quando não deveria.");
		return null;
	}

	@Override
	public String CurrentState() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

}
