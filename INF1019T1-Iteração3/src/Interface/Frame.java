package Interface;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static Frame _instance = null;
	private Panel _jPanel = null;
	private int _height = 830;
	private int _width = 1580;
	
	//Padrão Singleton
	public static Frame GetFrame(){
		if(_instance == null){
			_instance = new Frame();
		}
		return _instance;
	}
	
	public Frame(){
		setTitle("Entrucamento Ferroviário");
		setBounds(0, 0, _width, _height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		_jPanel = Panel.GetPanel();
		_jPanel.setVisible(true);
		_jPanel.setBounds(0, 0, 1800, 800);
		this.add(_jPanel);
		setVisible(true);
	}
}
