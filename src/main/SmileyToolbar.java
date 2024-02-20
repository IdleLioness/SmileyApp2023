package main;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class SmileyToolbar extends JToolBar{
	public Controller controller;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SmileyToolbar(Controller controller) {
		this.controller = controller;
		
//		String comboBoxListe[] = {"Smiley_1", "Smiley_2", "Smiley_3", "Smiley_4", "Smiley_5"};
//		JComboBox comboBox = new JComboBox(comboBoxListe);
		
//		comboBox.setSize(comboBox.getPreferredSize());
//		comboBox.setPreferredSize(new Dimension(100, 50));
//		comboBox.setPrototypeDisplayValue("XXXXXXXXX");
//		comboBox.setPrototypeDisplayValue(widestItem);
		
//		add(comboBox);
//		add(new JTextField());

		JButton buttonPrev = new JButton("Prev");
		buttonPrev.setActionCommand(Controller.Command.SMILEY_PREV);
		buttonPrev.addActionListener(controller);
		
		JButton buttonNext = new JButton("Next");
		buttonNext.setActionCommand(Controller.Command.SMILEY_NEXT);
		buttonNext.addActionListener(controller);
		
		JButton buttonSave = new JButton("Save");
		buttonSave.setActionCommand(Controller.Command.SMILEY_SAVE);
		buttonSave.addActionListener(controller);
		
		JButton buttonLoad = new JButton("Load");
		buttonLoad.setActionCommand(Controller.Command.SMILEY_LOAD);
		buttonLoad.addActionListener(controller);

		add(buttonPrev);
		add(buttonNext);
		add(buttonSave);
		add(buttonLoad);
	}
}