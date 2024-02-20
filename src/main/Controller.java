package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Controller extends JPanel implements ActionListener, ChangeListener{
	protected SmileyPerson model;
	protected SmileyPersonView view; 
	protected int modelIndex;
	protected int maxIndex;
	protected List<SmileyPerson> listOfSmileyPerson;
	protected SmileyPersons dataListOfPersons;
	
	public Controller() {
		
		String smileyDefaultString = "Lastname Firstname 2013 100.0 0.3 0.5 smiling 100 100";
		SmileyPerson smileyDefaultPerson = SmileyPerson.readFrom(smileyDefaultString);
		SmileyPersonView smileyDefaultView = new SmileyPersonView(smileyDefaultPerson);
		
		this.model = smileyDefaultPerson;
		this.view = smileyDefaultView;
		this.maxIndex = (int) SmileyPersons.countLines("data/SmileyPersons/test01.txt") - 1;
		System.out.println(maxIndex);
		this.dataListOfPersons = new SmileyPersons();
		dataListOfPersons.readFrom("data/SmileyPersons/test01.txt");
		listOfSmileyPerson = dataListOfPersons.getPersonListCopy();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		if (! (source instanceof JSlider)) {
			System.out.println("Something went wrong. Better log this");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Point2D.Double pos = new Point2D.Double(view.getPosition().x, 
												view.getPosition().y);
//		SmileyPerson sp = listOfSmileyPerson.get(modelIndex);
		
		switch(e.getActionCommand()) {
		case Command.SMILEY_MOUTH_JOY:
			model.setSmile(true);
			break;
		case Command.SMILEY_MOUTH_SAD:
			model.setSmile(false);
			break;
		case Command.SMILEY_POS_UP:
			view.setPosition(new Point2D.Double(pos.x, pos.y - 10));
			break;
		case Command.SMILEY_POS_DOWN:
			view.setPosition(new Point2D.Double(pos.x, pos.y + 10));
			break;
		case Command.SMILEY_POS_LEFT:
			view.setPosition(new Point2D.Double(pos.x - 10, pos.y));
			break;
		case Command.SMILEY_POS_RIGHT:
			view.setPosition(new Point2D.Double(pos.x + 10, pos.y));
			break;
		case Command.SMILEY_SAVE:
			SmileyPerson currentSP = view.person;
			listOfSmileyPerson.set(modelIndex, currentSP);
			SmileyPersons spList = new SmileyPersons(listOfSmileyPerson);
			spList.writeTo("data/SmileyPersons/test01.txt");
			break;
		case Command.SMILEY_LOAD:
			dataListOfPersons = new SmileyPersons();
			dataListOfPersons.readFrom("data/SmileyPersons/test01.txt");
			listOfSmileyPerson = dataListOfPersons.getPersonListCopy();
			this.model = listOfSmileyPerson.get(modelIndex);
			view.setModel(model);
			break;
		case Command.SMILEY_PREV:
			this.modelIndex--;
			if (modelIndex < 0) modelIndex = maxIndex;
			model = listOfSmileyPerson.get(modelIndex);
			view.setModel(model);
			break;
		case Command.SMILEY_NEXT:
//			SmileyPersons spList = new SmileyPersons(view.person);
//			spList.writeTo("data/SmileyPersons/test01.txt");
			this.modelIndex = modelIndex + 1;
			if (modelIndex > maxIndex) modelIndex = 0;
			this.model = listOfSmileyPerson.get(modelIndex);
			view.setModel(model);
			break;
		}
		
		view.prepareDrawing();
		view.repaint();
	}
	
	protected static class Command{
		public final static String SMILEY_MOUTH_JOY = "SMILEY_MOUTH_JOY";
		public final static String SMILEY_MOUTH_SAD = "SMILEY_MOUTH_SAD";
		
		public final static String SMILEY_POS_UP = "SMILEY_POS_UP";
		public final static String SMILEY_POS_DOWN = "SMILEY_POS_DOWN";
		public final static String SMILEY_POS_LEFT = "SMILEY_POS_LEFT";
		public final static String SMILEY_POS_RIGHT = "SMILEY_POS_RIGHT";
		
		public final static String SMILEY_SAVE = "SMILEY_SAVE";
		public final static String SMILEY_LOAD = "SMILEY_LOAD";
		
		public final static String SMILEY_PREV = "SMILEY_PREV";
		public final static String SMILEY_NEXT = "SMILEY_NEXT";
	}
	
	protected static class ComponentName{
		public final static String SLIDER_HEAD_SIZE = "SLIDER_HEAD_SIZE";
		public final static String SLIDER_EYES_SIZE = "SLIDER_EYES_SIZE";
		public final static String SLIDER_PUPILS_SIZE = "SLIDER_PUPILS_SIZE";
	}
}
