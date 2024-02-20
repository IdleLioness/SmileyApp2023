package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SmileyApp2023 {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		Controller controller = new Controller();
		
		NestedComponentsFrame frame = new NestedComponentsFrame(controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setJMenuBar(new SmileyMenubar(controller));
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	private static class NestedComponentsFrame extends JFrame{
		public NestedComponentsFrame(Controller controller) {
			setTitle("Smiley App");
			Dimension picPanelDim = new Dimension(600, 400);
			
//			String smileyPLine = "lastname firstname 2013 20.0 0.5 0.5 smiling 100 100";
//			SmileyPerson smileyP = SmileyPerson.readFrom(smileyPLine);
//			SmileyPersonView view = new SmileyPersonView(smileyP);
			
			getContentPane().setLayout(new BorderLayout());
//			getContentPane().add(new PicturePanel(), BorderLayout.CENTER);
			
			getContentPane().add(new SmileyToolbar(controller), BorderLayout.NORTH);
			
//			getContentPane().add(new ControlPanelSouth(Color.gray, 
//														smileyP, 
//														view), BorderLayout.SOUTH);
			
			getContentPane().add(new ControlPanel(Color.white, 
													controller), BorderLayout.WEST);
			
			getContentPane().add(new PicturePanel(Color.blue,
													picPanelDim,
													controller), BorderLayout.CENTER);
			
//			getContentPane().add(view);
//			getContentPane().add(new SmileyView(new SmileyModel(200)), BorderLayout.CENTER);
		}
	}
	
	@SuppressWarnings("serial")
	private static class PicturePanel extends JPanel{
//		protected SmileyModel model;
		protected SmileyPersonView view; 
		protected JLabel label;
		
		public PicturePanel(Color color, Dimension dim, Controller controller) {
//			this.model = model;
			this.view = controller.view;
			
			label = new JLabel();
			
			setPreferredSize(dim);
			setBackground(color);
			setLayout(new BorderLayout());
			add(view);
		}
	}
}