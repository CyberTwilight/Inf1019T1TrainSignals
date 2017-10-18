package Structures;

import java.util.ArrayList;
import java.util.Iterator;

public class Train extends Thread {
	private double _cordX;
	private double _cordY;
	private int _speed;
	private int _minDistance;
	private String _lane;
	private boolean _activatedEntrySensor;
	private boolean _activatedExitSensor;
	private boolean active = true;
	private boolean inSystem = true;
	
	public Train (int cordX, int cordY, int speed, int minDistance, String lane){
		this._cordX = cordX;
		this._cordY = cordY;
		this._speed = speed;
		this._minDistance = minDistance;
		this._lane = lane;
		this._activatedEntrySensor = false;
		this._activatedExitSensor = false;
		this.start();
	}
	
	public double GetXCoordinate(){
		return _cordX;
	}
	
	public void UpdateCoordinateX(double displacement){
		_cordX = _cordX + displacement;
	}
	
	public double GetYCoordinate(){
		return _cordY;
	}
	
	public void UpdateCoordinateY(double displacement){
		_cordY = _cordY + displacement;
	}
	
	public int GetSpeed(){
		return _speed;
	}
	
	public int GetMinDistance(){
		return _minDistance;
	}
	
	public String GetLane(){
		return _lane;
	}
	
	public boolean GetActivatedEntrySensor(){
		return _activatedEntrySensor;
	}
	
	public void SetActivatedEntrySensor(boolean activated){
		_activatedEntrySensor = activated;
	}
	
	public boolean GetActivatedExitSensor(){
		return _activatedExitSensor;
	}
	
	public void SetActivatedExitSensor(boolean activated){
		_activatedExitSensor = activated;
	}
	//Retorna true se estiver respeitando a distancia minima entre trens e falso caso o contrario.
	public boolean CalculateMinimumDistance(double cordX){
		if(Math.abs(_cordX - cordX) > _minDistance)
			return true;
		else
			return false;
	}
	
	public void TrainOutOfSystem(){
		switch (_lane) {
		case "linha1":
			if(_cordX > 1650)
				inSystem = false;
				SystemController.GetInstance().RemoveTrainFromList(this);
			break;
		case "linha2":
			if(_cordX < -100)
				inSystem = false;
				SystemController.GetInstance().RemoveTrainFromList(this);
		default:
			System.out.println("Erro ao tentar remover um trem.");
			break;
		}
	}
	
	//Metodo que atualiza as coordenadas do trem de acordo com a sua velociade e o caminho que segue
	public void MoveTrain(){
		double verticalMovement = 0;
		double horizontalMovement = 0;
		double movementSpeed = (_speed /3.6);
		String lane = this.GetLane();
		boolean respectingMinDistance = true;
		// sen cos
		//0.98131374967715724386103451510043
		//0.19241446072101122428647735590205
		//mais próximo: 0.95640822849673980297549722684343, 0.38203304686923352762610602346364
		//int index = SystemController.GetInstance()._trainList.indexOf(this);
		ArrayList<Train> trainList = SystemController.GetInstance().GetArrayList();
		for(int index = 0; index < trainList.size(); index++){
			Train listTrain = trainList.get(index);
			if( listTrain.GetLane() == _lane){
				if(CalculateMinimumDistance(listTrain.GetXCoordinate()) == false){
					respectingMinDistance = false;
				}
			}
		}
		
		switch (lane) {
		case "linha1":
			//TODO:Não está certo, trabalhar isso
			if( _cordX >= (216 - movementSpeed) && _cordX <= (216 + movementSpeed)){
				if(_activatedEntrySensor == false){
					_activatedEntrySensor = true;
					SystemController.GetInstance().ActivateLeftEntranceSensor();
				}
			}
			
			if(_cordX >= 216 && _cordX < 471){
				horizontalMovement = movementSpeed * 0.95640822849673980297549722684343;
				verticalMovement = movementSpeed * 0.19203304686923352762610602346364;
				_cordX = _cordX + horizontalMovement;
				_cordY = _cordY + verticalMovement;
			}
			
			else if(_cordX >= 1150 && _cordX < 1378){
				horizontalMovement = movementSpeed * 0.9660236474645590344317574875768;
				verticalMovement = movementSpeed * 0.19945369515499167149270704711484;
				_cordX = _cordX + horizontalMovement;
				_cordY = _cordY - verticalMovement;
			}
			
			else
			_cordX = _cordX + movementSpeed;
			
			 if( _cordX >= (1378 - movementSpeed) && _cordX <= (1378 + movementSpeed)){
					if(_activatedExitSensor == false){
						_activatedExitSensor = true;
						SystemController.GetInstance().ActivateLeftExitSensor();
					}
				} 
			
			break;
			
		case "linha2":
			
			 if( _cordX >= (1377 - movementSpeed) && _cordX <= (1377 + movementSpeed)){
				if(_activatedEntrySensor == false){
					_activatedEntrySensor = true;
					SystemController.GetInstance().ActivateRightEntranceSensor();
				}
			} 
			
			if(_cordX <= 1377 && _cordX >= 1151){
				horizontalMovement = movementSpeed * 0.94572661673264970251468744560679;
				verticalMovement = movementSpeed * 0.69496333085967699029139039981202;
				_cordX = _cordX - horizontalMovement;
				_cordY = _cordY - verticalMovement;
			}
			else if(_cordX <= 471 && _cordX >= 216){
				horizontalMovement = movementSpeed * 0.96058899227697505455517888027989;
				verticalMovement = movementSpeed * 0.61797263879077300113501513385389;
				_cordX = _cordX - horizontalMovement;
				_cordY = _cordY + verticalMovement;
			}
			_cordX = _cordX - movementSpeed;
			
			if( _cordX >= (216 - movementSpeed) && _cordX <= (216 + movementSpeed)){
				if(_activatedExitSensor == false){
					_activatedExitSensor = true;
					SystemController.GetInstance().ActivateRightExitSensor();
				}
			} 
			
			break;
			
		default:
			System.out.println("Erro na definição da linha. Linha foi preenchida como: '" + lane + "'.");
			break;
		}
	}
	
	public void run(){
		while(inSystem){
			try {
				if(active){
					MoveTrain();
				}
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
