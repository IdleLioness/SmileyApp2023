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
public class ControlPanelWest extends JPanel{
	protected SmileyPerson model;
	protected SmileyPersonView view; 
	
	//TODO should label exist here?
//	JLabel label = new JLabel();
	
	public ControlPanelWest(Color color, SmileyPerson model, SmileyPersonView view) {
		this.model = model;
		this.view = view;
		
		setPreferredSize(new Dimension(100, 150));
		setBackground(color);
				
		Buttons();
	}
	
	public void Buttons() {
		JButton buttonSave = new JButton("Save");
//		buttonSave.setBounds(100, 100, 200, 200); //TODO not working
//		buttonSave.setSize(200, 200);; //TODO not working
		
		JButton buttonLoad = new JButton("Load");	
		
		JButton buttonExit = new JButton("Exit");	
		
		buttonSave.setFocusable(false);
		buttonLoad.setFocusable(false);
		buttonExit.setFocusable(false);
		
		add(buttonSave);
		add(buttonLoad);
		add(buttonExit);
	}
	
}