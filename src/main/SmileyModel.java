package main;

public class SmileyModel{
	protected double r_head;
	protected double r_eye_pct;
	protected double r_pupil_pct;
	protected boolean smile;
	protected double x;
	protected double y;
	
	public SmileyModel (double r_head, double r_eye_pct, double r_pupil_pct, 
						boolean smile, double x, double y) {
		this.r_head = r_head;
		this.r_eye_pct = r_eye_pct;
		this.r_pupil_pct = r_pupil_pct;
		this.smile = smile;
		this.x = x;
		this.y = y;
	}
	
	public void setHeadRadius(double r_head) {
		this.r_head = r_head;
	}
	
	public void setEyePct(double r_eye_pct) {
		this.r_eye_pct = r_eye_pct;
	}
	
	public void setPupilPct(double r_pupil_pct) {
		this.r_pupil_pct = r_pupil_pct;
	}
	
	public void setSmile(boolean smile) {
		this.smile = smile;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getHeadRadius() {
		return r_head;
	}
	
	public double getEyePct() {
		return r_eye_pct;
	}
	
	public double getPupilPct() {
		return r_pupil_pct;
	}
	
	public boolean getSmile() {
		return smile;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}