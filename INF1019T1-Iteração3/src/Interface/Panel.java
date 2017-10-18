package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Structures.SystemController;
import Structures.Train;


public class Panel extends JPanel implements Observer {
	public static Panel _instance = null;
	private int _height = 830;
	private int _width = 1580;
	private Image _scenarioImage;
	private Image [] _trafficLight = new Image[2];
	ArrayList<Train> _trainList;
	ActionListener createTrain60;
	ActionListener createTrain80;
	ActionListener createTrain100;
	//Padrão Singleton
	public static Panel GetPanel(){
		if(_instance == null){
			_instance = new Panel();
		}
		return _instance;
	}
	
	public Panel(){
		setBounds(0, 0, _width, _height);
		setVisible(true);
		setBackground(Color.WHITE);
		this.setLayout(null);
		GetScenarioImages();
		//_trainList.add(new Train(0, 347, 60, 60, "linha1"));
		//_trainList.add(new Train(0, 347, 80, 80, "linha1"));
		//_trainList.add(new Train(0, 347, 100, 100, "linha1"));
		//_trainList.add(new Train(1560, 475, 60, 60,"linha2"));
		//_trainList.add(new Train(1560, 475, 80, 80,"linha2"));
		//_trainList.add(new Train(1560, 475, 100, 100,"linha2"));
		
		//Radio buttons para selecionar a linha do trem a ser adicionado
		JRadioButton lane1 = new JRadioButton("Linha 1");
		lane1.setBounds(730, 40, 80, 50);
		lane1.setName("Linha 1");
		this.add(lane1);
		lane1.setVisible(true);
		lane1.setBackground(Color.WHITE);
		lane1.setSelected(true);
		
		JRadioButton lane2 = new JRadioButton("Linha 2");
		lane2.setBounds(810, 40, 80, 50);
		lane2.setName("Linha 2");
		this.add(lane2);
		lane2.setVisible(true);
		lane2.setBackground(Color.WHITE);
		
		ButtonGroup trainLanes = new ButtonGroup();
		trainLanes.add(lane1);
		trainLanes.add(lane2);
		
		createTrain60 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lane1.isSelected()){
					//_trainList.add(new Train(0, 347, 60, 60, "linha1"));
					//Alterar todos para utilizar o facade ao invez do singleton
					SystemController.GetInstance().CreateNewTrain(0, 347, 60, 60, "linha1");
					
				}
				else
					//_trainList.add(new Train(1560, 475, 60, 60, "linha2"));
					SystemController.GetInstance().CreateNewTrain(1560, 475, 60, 60, "linha2");
				
			}
		};
		
		createTrain80 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lane1.isSelected()){
					//_trainList.add(new Train(0, 347, 80, 80, "linha1"));
					SystemController.GetInstance().CreateNewTrain(0, 347, 80, 80, "linha1");
				}
				else
					//_trainList.add(new Train(1560, 475, 80, 80,"linha2"));
					SystemController.GetInstance().CreateNewTrain(1560, 475, 80, 80,"linha2");
			}
		};
		
		createTrain100 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lane1.isSelected()){
					//_trainList.add(new Train(0, 347, 100, 100, "linha1"));
					SystemController.GetInstance().CreateNewTrain(0, 347, 100, 100, "linha1");
				}
				else
					//_trainList.add(new Train(1560, 475, 100, 100,"linha2"));
					SystemController.GetInstance().CreateNewTrain(1560, 475, 100, 100,"linha2");
			}
		};
		
		//Jbuttons para adicionar novos trens ao entroncamento
		//Jbutton de trens a 60Km/h
		JButton TrainWith60Speed = new JButton("60 km/h");
		TrainWith60Speed.setBounds(650, 100, 87, 30);
		TrainWith60Speed.setName("60 Km/h");
		this.add(TrainWith60Speed);
		TrainWith60Speed.setVisible(true);
		TrainWith60Speed.addActionListener(createTrain60);
		
		//Jbutton de trens a 80Km/h
		JButton TrainWith80Speed = new JButton("80 km/h");
		TrainWith80Speed.setBounds(760, 100, 87, 30);
		TrainWith80Speed.setName("80 Km/h");
		this.add(TrainWith80Speed);
		TrainWith80Speed.setVisible(true);
		TrainWith80Speed.addActionListener(createTrain80);
		
		//JButton de trens a 100Km/h
		JButton TrainWith100Speed = new JButton("100 km/h");
		TrainWith100Speed.setBounds(870, 100, 87, 30);
		TrainWith100Speed.setName("100 Km/h");
		this.add(TrainWith100Speed);
		TrainWith100Speed.setVisible(true);
		TrainWith100Speed.addActionListener(createTrain100);
		repaint();
		
		//Evento para achar as coordenadas da tela.
		//Meio do trilho superior = 347+- Y esquerda e 352+- Y direita
		//Meio do trilho inferior = 475 Y esquerda e 475 Y direita
		MouseListener click = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Coordenada x: " + e.getX() + " Coordenada Y: " + e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Coordenada x: " + e.getX() + " Coordenada Y: " + e.getY());
			}
		};
		
		this.addMouseListener(click);
	}
	
	//Método que pega as imagens do sinal e do entroncamento
	private void GetScenarioImages(){
		try {
			_scenarioImage = ImageIO.read(new File("src/Images/Trem.jpg"));
			_trafficLight[0] = ImageIO.read(new File("src/Images/SinalVerde.png"));
			_trafficLight[1] = ImageIO.read(new File("src/Images/SinalVermelho.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possivel encontrar os arquivos.");
		}
	}
	
	//Metodo responsável por colocar as imagens do cenário e dos semáforos
	public void paintComponent(Graphics Image){
		Graphics2D display = (Graphics2D) Image;
		super.paintComponent(display);
		display.drawImage(_scenarioImage, 0, 0, null);
		System.out.println(SystemController.GetInstance().GetRightLane());
		System.out.println(SystemController.GetInstance().GetLeftLane());
		System.out.println(SystemController.GetInstance().GetCurrentState());
		switch(SystemController.GetInstance().GetCurrentState()) {
		case "BothWaysOpen" :
			display.drawImage(_trafficLight[0], 470, 230, null);
			display.drawImage(_trafficLight[0], 1095, 230, null);
			break;
		case "LeftLaneOpen" :
			display.drawImage(_trafficLight[0], 470, 230, null);
			display.drawImage(_trafficLight[1], 1095, 230, null);
			break;
		case "RightLaneOpen":
			display.drawImage(_trafficLight[1], 470, 230, null);
			display.drawImage(_trafficLight[0], 1095, 230, null);
			break;
		}
		int index;
		Train currentTrain;
		ArrayList<Train> _trainList = SystemController.GetInstance().GetArrayList();
		for(index = 0 ; index < _trainList.size(); index++){
			currentTrain = _trainList.get(index);
			switch (currentTrain.GetLane()) {
			
			case "linha1":		
				DrawCircle(display, currentTrain.GetXCoordinate(), currentTrain.GetYCoordinate(), 30, Color.black);
				break;
			
			case "linha2":
				DrawCircle(display, currentTrain.GetXCoordinate(), currentTrain.GetYCoordinate(), 30, Color.red);	
				break;
			
			default:
				break;
			}
		}	
	}
	
	//Metodo responsável por desenhar os circulos
	public void DrawCircle(Graphics2D g, double d, double e, int r, Color color) {
		  d = d-(r/2);
		  e = e-(r/2);
		  g.setColor(color);
		  g.fillOval((int)d,(int)e,r,r);
		}

	@Override
	public void update(Observable systemController, Object trainList) {
		//List<x,y>_test = (List<x,y>) trainList;
		//Alterar
		_trainList = (ArrayList<Train>) trainList;
		repaint();
	}
}
