package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
	public Controller controller;
	protected JLabel label;
	
	public ControlPanel(Color color, Controller controller) {
		this.controller = controller;
		
		label = new JLabel();
		
		setPreferredSize(new Dimension(250, 150));
		setBackground(color);
		
		smileyInfoPanel();
		slider();
		buttons();
		buttonsPos();
	}
	
	public void smileyInfoPanel() {
		//TODO not working
		JLabel labelInfo = new JLabel("Save Slot: " + controller.modelIndex, SwingConstants.CENTER);
//		System.out.println(controller.modelIndex);
//		JLabel labelInfo = new JLabel("<html>Smiley Info: <br/>" + controller.model.getInfo() + "</html>", SwingConstants.CENTER);
		labelInfo.setFont(new Font("Serif", Font.BOLD, 14));
		add(labelInfo);
	}
	
	public void buttons() {
		JButton buttonMouthJoy = new JButton("joy");
//		buttonMouthJoy.setBounds(100, 100, 200, 200);
		buttonMouthJoy.setPreferredSize(new Dimension(100, 50)); 
		buttonMouthJoy.setActionCommand(Controller.Command.SMILEY_MOUTH_JOY);
		buttonMouthJoy.addActionListener(controller);
		
		JButton buttonMouthSad = new JButton("sadness");
		buttonMouthSad.setPreferredSize(new Dimension(100, 50));
		buttonMouthSad.setActionCommand(Controller.Command.SMILEY_MOUTH_SAD);
		buttonMouthSad.addActionListener(controller);
		
		buttonMouthJoy.setFocusable(false);
		buttonMouthSad.setFocusable(false);
		
		add(buttonMouthJoy);
		add(buttonMouthSad);
	}
	
	// TODO position buttons, icons of buttons
		public void buttonsPos() {
			ImageIcon iconPosUp 	= new ImageIcon("./img/upward-arrow.png");
			ImageIcon iconPosDown 	= new ImageIcon("./img/downward-arrow.png");
			ImageIcon iconPosLeft 	= new ImageIcon("./img/back-arrow.png");
			ImageIcon iconPosRight 	= new ImageIcon("./img/forward-arrow.png");
			
			JButton buttonPosUp = new JButton(iconPosUp);
			buttonPosUp.setActionCommand(Controller.Command.SMILEY_POS_UP);
			buttonPosUp.addActionListener(controller);
			
			JButton buttonPosDown = new JButton(iconPosDown);
			buttonPosDown.setActionCommand(Controller.Command.SMILEY_POS_DOWN);
			buttonPosDown.addActionListener(controller);
			
			JButton buttonPosLeft = new JButton(iconPosLeft);
			buttonPosLeft.setActionCommand(Controller.Command.SMILEY_POS_LEFT);
			buttonPosLeft.addActionListener(controller);
			
			JButton buttonPosRight = new JButton(iconPosRight);	
			buttonPosRight.setActionCommand(Controller.Command.SMILEY_POS_RIGHT);
			buttonPosRight.addActionListener(controller);
			
			add(buttonPosUp);
			add(buttonPosDown);
			add(buttonPosLeft);
			add(buttonPosRight);
		}
		
		public void slider() {
//			JLabel headerLabel = new JLabel("Header", JLabel.CENTER);
//		    headerLabel.setText("Control in action: JSlider"); 
			
			JSlider sliderHeadSize = new JSlider(50, 150, 100);
			sliderHeadSize.setName(Controller.ComponentName.SLIDER_HEAD_SIZE);
			JSlider sliderEyePct = new JSlider(10, 50, 30);
			sliderEyePct.setName(Controller.ComponentName.SLIDER_EYES_SIZE);
			JSlider sliderPupulPct = new JSlider(10, 90, 50);
			sliderPupulPct.setName(Controller.ComponentName.SLIDER_PUPILS_SIZE);
			
			sliderHeadSize.setPreferredSize(new Dimension(200, 50));
			sliderEyePct.setPreferredSize(new Dimension(200, 50));
			sliderPupulPct.setPreferredSize(new Dimension(200, 50));
			
			sliderHeadSize.setPaintTicks(true);
			sliderHeadSize.setMinorTickSpacing(10);
			sliderEyePct.setPaintTicks(true);
			sliderEyePct.setMinorTickSpacing(5);
			sliderPupulPct.setPaintTicks(true);
			sliderPupulPct.setMinorTickSpacing(5);
			
			sliderHeadSize.setPaintTrack(true);
			sliderHeadSize.setMajorTickSpacing(25);
			sliderEyePct.setPaintTrack(true);
			sliderEyePct.setMajorTickSpacing(10);
			sliderPupulPct.setPaintTrack(true);
			sliderPupulPct.setMajorTickSpacing(20);
			
			//to show numbers by Major Ticks
			sliderHeadSize.setPaintLabels(true);
			sliderEyePct.setPaintLabels(true);
			sliderPupulPct.setPaintLabels(true);
			
			//set Font for text
			sliderHeadSize.setFont(new Font("SansSerif", Font.PLAIN, 10));
			sliderEyePct.setFont(new Font("SansSerif", Font.PLAIN, 10));
			sliderPupulPct.setFont(new Font("SansSerif", Font.PLAIN, 10));
			//set orientation to vertical (default - horizontal)
//			sliderSize.setOrientation(SwingConstants.VERTICAL);
			
			//actionlistener
			sliderHeadSize.addChangeListener(controller);
			sliderEyePct.addChangeListener(controller);
			sliderPupulPct.addChangeListener(controller);
			
			//panel.add(slider);
//			sliderHeadSize.add(headerLabel);
			add(sliderHeadSize);
			add(sliderEyePct);
			add(sliderPupulPct);
		}

}
