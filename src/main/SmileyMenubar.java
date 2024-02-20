package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class SmileyMenubar extends JMenuBar{
	public Controller controller;
	
	public SmileyMenubar(Controller controller){
		this.controller = controller;
		
		add(new JMenu("File"));
		add(new JMenu("Edit"));
		add(new JMenu("Window")); 
		add(new JMenu("Help"));
	}
}