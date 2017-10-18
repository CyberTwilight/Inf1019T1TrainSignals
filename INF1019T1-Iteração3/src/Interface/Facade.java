package Interface;

import java.util.ArrayList;

import Structures.SystemController;
import Structures.Train;

public class Facade {

	public Facade(){
		SystemController.GetInstance();
	}
	
	public ArrayList<Train> GetLaneList(String lane){
		ArrayList<Train> singleLaneList = null;
		ArrayList<Train> _trainList = SystemController.GetInstance().GetArrayList();
			for(int index = 0; index < _trainList.size();index++){
				if(_trainList.get(index).GetLane() == lane){
					singleLaneList.add(_trainList.get(index));
				}
			}
			return singleLaneList;
	}
	
	public void AddTrainToArrayList(String lane, int speed){
		switch(lane){
		case "linha1":
			SystemController.GetInstance().CreateNewTrain(0, 347, speed, speed, lane);
			break;
			
		case "linha2":
			SystemController.GetInstance().CreateNewTrain(0, 347, speed, speed, lane);
			break;
			default:
				break;
		}
	}
}
