package main;

import java.util.Locale;
import java.util.Scanner;

public class SmileyPerson extends SmileyModel{
	//implements Comparator<SmileyPerson>
	protected String lastname;
	protected String firstname;
	protected int year;

	public static SmileyPerson readFrom(String line) {
		Scanner lineSC = new Scanner(line);
		
		String lastname = lineSC.next();
		String firstname = lineSC.next();
		int year = lineSC.nextInt();
		double r_head = lineSC.nextDouble();
		double r_eye_pct = lineSC.nextDouble();
		double r_pupil_pct = lineSC.nextDouble();
		String smile = lineSC.next();
		double x = lineSC.nextDouble();
		double y = lineSC.nextDouble();
		
		lineSC.close();
		return(new SmileyPerson(
					lastname,
					firstname,
					year,
					r_head,
					r_eye_pct,
					r_pupil_pct,
					smile.equals("smiling"),
					x,
					y));
	}
	
	private SmileyPerson(String lastname, String firstname, int year, 
							double r_head, double r_eye_pct, double r_pupil_pct, 
							boolean smile, double x, double y) {
		super(r_head, r_eye_pct, r_pupil_pct, smile, x, y);
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString() {
		String smileS = "not_smiling";
		if (smile) {
			smileS = "smiling";
		}

		return String.format("%-20s %-20s %-8d %-8.2f %-8.2f %-8.2f %-20s %-8.2f %-8.2f",
				lastname,
				firstname,
				year,
				r_head,
				r_eye_pct,
				r_pupil_pct,
				smileS,
				x,
				y);
	}
	
	public String getInfo() {
		return String.format("%-20s %-20s %-8d", 
				firstname, 
				lastname, 
				year);
	}
	
	public int compareByName(SmileyPerson other) {
		return this.lastname.compareTo(other.getLastname());
	}
	
	public int compareByFirstName(SmileyPerson other) {
		return this.firstname.compareTo(other.getFirstname());
	}
	
	//Cast Integer
	public int compareByYear(SmileyPerson other) {
		return ((Integer)this.year).compareTo(other.getYear());
	}
	
	//Cast Double
	public int compareByHeadRadius(SmileyPerson other) {
		return ((Double)this.r_head).compareTo(other.getHeadRadius());
	}
	
	public boolean hasTallHead() {
		return this.r_head > 100;
	}
	
	public boolean hasBigEyes() {
		return this.r_eye_pct > 0.25;
	}
	
	public boolean isYoung() {
		return (2023 - this.year) < 25;
	}
	
	public boolean isOld() {
		return (2023 - this.year) > 70;
	}
}
