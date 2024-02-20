package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class ControlPanelSouth extends JPanel{
	protected SmileyPerson model;
	protected SmileyPersonView view; 
	protected JLabel label;
	
	
	public ControlPanelSouth(Color color, SmileyPerson model, SmileyPersonView view) {
		this.model = model;
		this.view = view;
		
		label = new JLabel();
		
		setPreferredSize(new Dimension(300, 150));
		setBackground(color);
				
		Slider();
		buttons();
		buttonsPos();
	}
	
	public void buttons() {
		JButton buttonMouthJoy = new JButton("joy");
		buttonMouthJoy.setBounds(100, 100, 200, 200); //TODO not working
		buttonMouthJoy.setPreferredSize(new Dimension(100, 50)); //TODO not working
		buttonMouthJoy.setActionCommand(Command.SMILEY_MOUTH_JOY);
		buttonMouthJoy.addActionListener(this::actionChangeModel);
		
		JButton buttonMouthSad = new JButton("sadness");	
		buttonMouthSad.setActionCommand(Command.SMILEY_MOUTH_SAD);
		buttonMouthSad.addActionListener(this::actionChangeModel);
		
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
		buttonPosUp.setActionCommand(Command.SMILEY_POS_UP);
		buttonPosUp.addActionListener(this::actionChangeModel);
		
		JButton buttonPosDown = new JButton(iconPosDown);
		buttonPosDown.setActionCommand(Command.SMILEY_POS_DOWN);
		buttonPosDown.addActionListener(this::actionChangeModel);
		
		JButton buttonPosLeft = new JButton(iconPosLeft);
		buttonPosLeft.setActionCommand(Command.SMILEY_POS_LEFT);
		buttonPosLeft.addActionListener(this::actionChangeModel);
		
		JButton buttonPosRight = new JButton(iconPosRight);	
		buttonPosRight.setActionCommand(Command.SMILEY_POS_RIGHT);
		buttonPosRight.addActionListener(this::actionChangeModel);
		
		add(buttonPosUp);
		add(buttonPosDown);
		add(buttonPosLeft);
		add(buttonPosRight);
	}
	
	public void Slider() {
//		JLabel headerLabel = new JLabel("Header", JLabel.CENTER);
//	    headerLabel.setText("Control in action: JSlider"); 
		
		JSlider sliderHeadSize = new JSlider(50, 150, 100);
		sliderHeadSize.setName(ComponentName.SLIDER_HEAD_SIZE);
		JSlider sliderEyePct = new JSlider(10, 40, 25);
		sliderEyePct.setName(ComponentName.SLIDER_EYES_SIZE);
		JSlider sliderPupulPct = new JSlider(10, 90, 50);
		sliderPupulPct.setName(ComponentName.SLIDER_PUPILS_SIZE);
		
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
//		sliderSize.setOrientation(SwingConstants.VERTICAL);
		
		//actionlistener
		sliderHeadSize.addChangeListener(this::sliderChangeModel);
		sliderEyePct.addChangeListener(this::sliderChangeModel);
		sliderPupulPct.addChangeListener(this::sliderChangeModel);
		
		//panel.add(slider);
//		sliderHeadSize.add(headerLabel);
		add(sliderHeadSize);
		add(sliderEyePct);
		add(sliderPupulPct);
//		add(label);
	}
	
	//TODO up,down,left,right not implemented jet
	private void actionChangeModel(ActionEvent event) {
		Point2D.Double pos = new Point2D.Double(view.getPosition().x, 
												view.getPosition().y);
		switch(event.getActionCommand()) {
		case Command.SMILEY_MOUTH_JOY:
			model.setSmile(true);
			break;
		case Command.SMILEY_MOUTH_SAD:
			model.setSmile(false);
			break;
		case Command.SMILEY_POS_UP:
			view.setPosition(new Point2D.Double(view.getPosition().x, 
												view.getPosition().y - 10));
			break;
		case Command.SMILEY_POS_DOWN:
			view.setPosition(new Point2D.Double(view.getPosition().x, 
												view.getPosition().y + 10));
			break;
		case Command.SMILEY_POS_LEFT:
			view.setPosition(new Point2D.Double(view.getPosition().x - 10, 
												view.getPosition().y));
			break;
		case Command.SMILEY_POS_RIGHT:
			view.setPosition(new Point2D.Double(view.getPosition().x + 10, 
												view.getPosition().y));
			break;
		}
		view.prepareDrawing();
		view.repaint();
	}
	
	private void sliderChangeModel(ChangeEvent event) {
		Object source = event.getSource();
		if (! (source instanceof JSlider)) {
			System.out.println("Something went wrong.Better log this");
			return;
		}
		JSlider slider = (JSlider) source;
		double value = slider.getValue();
		String slidername = slider.getName();
		switch (slidername) {
		case ComponentName.SLIDER_HEAD_SIZE:
			model.setHeadRadius(value);
			break;
		case ComponentName.SLIDER_EYES_SIZE:
			model.setEyePct(value / 100);
			break;
		case ComponentName.SLIDER_PUPILS_SIZE:
			model.setPupilPct(value / 100);
			break;
		}
		view.prepareDrawing();
		view.repaint();
	}

	private static class Command{
		public final static String SMILEY_MOUTH_JOY = "SMILEY_MOUTH_JOY";
		public final static String SMILEY_MOUTH_SAD = "SMILEY_MOUTH_SAD";
		
		public final static String SMILEY_POS_UP = "SMILEY_POS_UP";
		public final static String SMILEY_POS_DOWN = "SMILEY_POS_DOWN";
		public final static String SMILEY_POS_LEFT = "SMILEY_POS_LEFT";
		public final static String SMILEY_POS_RIGHT = "SMILEY_POS_RIGHT";
	}
	
	private static class ComponentName{
		public final static String SLIDER_HEAD_SIZE = "SLIDER_HEAD_SIZE";
		public final static String SLIDER_EYES_SIZE = "SLIDER_EYES_SIZE";
		public final static String SLIDER_PUPILS_SIZE = "SLIDER_PUPILS_SIZE";
	}
}