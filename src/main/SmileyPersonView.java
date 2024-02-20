package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
@SuppressWarnings("serial")
public class SmileyPersonView extends JPanel {
	
	protected SmileyPerson person;
	protected Point2D.Double headCenter;
	protected Point2D.Double mouthCenter;
	protected Point2D.Double eyeLeftCenter; 	
	protected Point2D.Double eyeRightCenter;	
	protected Point2D.Double headCorner;
	protected Point2D.Double mouthCorner;
	protected Point2D.Double eyeLeftCorner; 	
	protected Point2D.Double eyeRightCorner;
	protected Point2D.Double pupilLeftCorner; 	
	protected Point2D.Double pupilRightCorner;  
	protected Ellipse2D.Double head;
	protected Ellipse2D.Double eyeLeft;				
	protected Ellipse2D.Double eyeRight;				
	protected Ellipse2D.Double pupilLeft;		
	protected Ellipse2D.Double pupilRight; 
	protected Arc2D.Double mouthArc;
	protected double radiusHead;
	protected double radiusEye;
	protected double radiusPupil;
	
	public SmileyPersonView(SmileyPerson person) {
		this.person = person;
		Point2D position = new Point2D.Double(person.getX(), person.getY());
		radiusHead = person.getHeadRadius();
		radiusEye = radiusHead * person.getEyePct();
		radiusPupil = radiusEye * person.getPupilPct();
		
		headCenter = new Point2D.Double(position.getX(), position.getY());
		mouthCenter = new Point2D.Double();
		eyeLeftCenter = new Point2D.Double();
		eyeRightCenter = new Point2D.Double();
		
		headCorner = new Point2D.Double();
		mouthCorner = new Point2D.Double();
		eyeLeftCorner = new Point2D.Double();
		eyeRightCorner = new Point2D.Double();
		pupilLeftCorner = new Point2D.Double();
		pupilRightCorner = new Point2D.Double();
		
		head = new Ellipse2D.Double();
		mouthArc = new Arc2D.Double(mouthCenter.x, mouthCenter.y,
									radiusHead / 2, radiusHead / 2,
									0.0, 0.0, Arc2D.CHORD);			
		eyeLeft = new Ellipse2D.Double();
		eyeRight = new Ellipse2D.Double();
		pupilLeft = new Ellipse2D.Double();
		pupilRight = new Ellipse2D.Double();
		prepareDrawing();
	}
	
	public void setModel(SmileyPerson newPerson) {
		this.person = newPerson;
		this.headCenter = new Point2D.Double(newPerson.getX(), newPerson.getY());
	}
	
	public Point2D.Double getPosition() {
		return headCenter;
	}
	
	public void setPosition(Point2D.Double position) {
		double x = position.getX();
		double y = position.getY();
		this.person.setX(x);
		this.person.setY(y);
		this.headCenter = position;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawHead(g2);
		drawMouth(g2);
		drawEyes(g2);
		giveInfo(g2);
	}

	public void drawHead(Graphics2D g2d) {
		g2d.setPaint(Color.YELLOW);
		g2d.fill(head);
		g2d.setPaint(Color.black);
		g2d.draw(head);
	}
	
	public void drawEyes(Graphics2D g2d) {
		g2d.setPaint(Color.BLUE);
		g2d.fill(eyeLeft);
		g2d.fill(eyeRight);
		g2d.setPaint(Color.black);
		g2d.draw(eyeLeft);
		g2d.draw(eyeRight);
		g2d.fill(pupilLeft);
		g2d.fill(pupilRight);
	}
	
	public void drawMouth(Graphics2D g2) {				
		g2.setPaint(Color.BLACK);
		g2.fill(mouthArc);
	 }
	
	public void giveInfo(Graphics2D g2) {
		int fontSize = (int) (radiusHead / 8.5);
		Font font = new Font("SansSerif", Font.PLAIN, fontSize);
		g2.setFont(font);
		String infoString = person.getInfo();
		g2.drawString(infoString, 
						(int) (headCorner.getX() - radiusHead * 2), 
						(int) (headCorner.getY() + fontSize));
	}
	 
	protected void prepareDrawing() {
		 radiusHead = person.getHeadRadius();
		 radiusEye = radiusHead * person.getEyePct();
		 radiusPupil = radiusEye * person.getPupilPct();
		 double distEyes = (radiusHead - radiusEye) / 8;
		 
		 eyeLeftCenter.setLocation	(headCenter.x - radiusEye - distEyes, headCenter.y - radiusHead/3);
		 eyeRightCenter.setLocation	(headCenter.x + radiusEye + distEyes, headCenter.y - radiusHead/3);
		 
		 headCorner.setLocation		(headCenter.x + radiusHead, headCenter.y + radiusHead);
		 eyeLeftCorner.setLocation	(eyeLeftCenter.x + radiusEye, eyeLeftCenter.y + radiusEye);
		 eyeRightCorner.setLocation	(eyeRightCenter.x + radiusEye, eyeRightCenter.y + radiusEye);
		 pupilLeftCorner.setLocation(eyeLeftCenter.x + radiusPupil, eyeLeftCenter.y + radiusPupil);
		 pupilRightCorner.setLocation(eyeRightCenter.x + radiusPupil, eyeRightCenter.y + radiusPupil);
		 
		 if (person.getSmile()) {
			 mouthArc.setAngleExtent(-180);
			 mouthCenter.setLocation(headCenter.x, headCenter.y + radiusHead / 3);
			 mouthCorner.setLocation(mouthCenter.x + radiusHead / 2, mouthCenter.y + radiusHead / 2);
		 } else {
			 mouthArc.setAngleExtent(180);
			 mouthCenter.setLocation(headCenter.x, headCenter.y + radiusHead *2 / 3);
			 mouthCorner.setLocation(mouthCenter.x + radiusHead / 2, mouthCenter.y + radiusHead / 2);
		 }
		 
		 head.setFrameFromCenter		(headCenter, headCorner);
		 mouthArc.setFrameFromCenter	(mouthCenter, mouthCorner);
		 eyeLeft.setFrameFromCenter		(eyeLeftCenter, eyeLeftCorner);
		 eyeRight.setFrameFromCenter	(eyeRightCenter, eyeRightCorner);
		 pupilLeft.setFrameFromCenter	(eyeLeftCenter, pupilLeftCorner);
		 pupilRight.setFrameFromCenter	(eyeRightCenter, pupilRightCorner);
		 
	 }
}
