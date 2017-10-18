package Structures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

import Interface.Frame;
import Interface.Panel;

public class SystemController extends Observable{
	private static SystemController _instance = null;
	private int _rightLane = 0;
	private int _leftLane = 0;
	private Signal _signal;
	private Signal temporaryState;
	Timer timer;
	ActionListener _trainMovement;
	ArrayList<Train> _trainList = new ArrayList<Train>();
	
	//Padrão Singleton
	public static SystemController GetInstance(){
		if(_instance == null){
			_instance = new SystemController();
		}	
		return _instance;
	}
	
	public SystemController (){
		Frame frame = Frame.GetFrame();
		_signal = new BothWaysOpen();
		
		_trainMovement = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NotifyObservers();
			}
			
		};
		temporaryState = null;
		this.addObserver(Panel.GetPanel());
		timer = new Timer(500, _trainMovement);
		timer.setInitialDelay(0);
		timer.start();
	}
	
	public String GetCurrentState() {
		return _signal.CurrentState();
	}
	
	public int GetRightLane() {
		return _rightLane;
	}

	public void AddRightLane() {
		this._rightLane++;
	}
	
	public void RemoveRightLane(){
		this._rightLane--;
	}
	
	public int GetLeftLane(){
		return _leftLane;
	}
	
	public void AddLeftLane() {
		this._leftLane++;
	}
	
	public void RemoveLeftLane(){
		this._leftLane--;
	}
	
	//Transições dos estados
	public void ActivateLeftEntranceSensor(){
		temporaryState = _signal.LeftEntranceSensorActivated();
		if(temporaryState != null){
			_signal = temporaryState;
		}
		//TODO:Chama o state para fazer a mudança de estado
	}
	
	public void ActivateLeftExitSensor(){
		temporaryState = _signal.LeftExitSensorActivated();
		if(temporaryState != null){
			_signal = temporaryState;
		}
	}
	
	public void ActivateRightEntranceSensor(){
		temporaryState = _signal.RightEntranceSensorActivated();
		if(temporaryState != null){
			_signal = temporaryState;
		}
		//TODO:Chama o state para fazer a mudança de estado
	}
	
	public void ActivateRightExitSensor(){
		temporaryState = _signal.RightExitSensorActivated();
		if(temporaryState != null){
			_signal = temporaryState;
		}
	}
	
	public void CreateNewTrain(int cordX, int cordY, int speed, int minDistance, String lane){
		_trainList.add(new Train(cordX,cordY, speed, minDistance,lane));
	}
	
	public ArrayList<Train> GetArrayList(){
		return _trainList;
	}
	
	public ArrayList<Train> GetLaneList(String lane){
		ArrayList<Train> singleLaneList = null;
			for(int index = 0; index < _trainList.size();index++){
				if(_trainList.get(index).GetLane() == lane){
					singleLaneList.add(_trainList.get(index));
				}
			}
			return singleLaneList;
	}
	
	public void RemoveTrainFromList(Train train){
		int index = _trainList.indexOf(train);
		_trainList.remove(index);
	}
	
	public void NotifyObservers(){
		setChanged();
		notifyObservers(_trainList);
		
	}
}
