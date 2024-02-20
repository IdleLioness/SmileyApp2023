package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Scanner;
import java.util.function.Predicate;

public class SmileyPersons {
	
	protected List<SmileyPerson> list = new ArrayList<SmileyPerson>();
	
	public static Comparator<SmileyPerson> compareByName 		= (SmileyPerson sp1, SmileyPerson sp2) -> sp1.getLastname().compareTo(sp2.getLastname());
	public static Comparator<SmileyPerson> compareByFirstName 	= (SmileyPerson sp1, SmileyPerson sp2) -> sp1.getFirstname().compareTo(sp2.getFirstname());
	public static Comparator<SmileyPerson> compareByYear 		= (SmileyPerson sp1, SmileyPerson sp2) -> (sp1.getYear() - sp2.getYear());
	
	public static Predicate<SmileyPerson> hasTallHead 	= SmileyPerson -> (SmileyPerson.getHeadRadius() > 100);
	public static Predicate<SmileyPerson> hasBigEyes 	= SmileyPerson -> (SmileyPerson.getEyePct() > 0.25);
	public static Predicate<SmileyPerson> isYoung 		= SmileyPerson -> (2023 - SmileyPerson.getYear() < 25);
	public static Predicate<SmileyPerson> isOld 		= SmileyPerson -> (2023 - SmileyPerson.getYear() > 75);

	public SmileyPersons(List<SmileyPerson> inputList) {
		// Constructor
		this.list = inputList; 
	}
	
	public SmileyPersons() {
		// Constructor
		this.list = new ArrayList<SmileyPerson>(); 
	}
	
	public SmileyPersons(SmileyPerson... spArray) {
		// Constructor
		for (SmileyPerson sp : spArray) {
			this.list.add(sp);
		}
	}
	
	public void writeTo(String filename) {
		try {
			writeToImpl(filename);
		} catch(IOException e) {
			System.err.println("FEHLER");
		}
	}
	
	private void writeToImpl(String filename) throws IOException{
		FileWriter fileFR = new FileWriter(filename);
		fileFR.write(toString());
		fileFR.close();
	}

	public void readFrom(String filename) {
		try {
			List<String> ls = readFromImpl(filename);
			
			for (String s : ls) {
				list.add(SmileyPerson.readFrom(s));
			}
		} catch (IOException e) {
			System.err.println("FEHLER");
		}
	}
	
	private List<String> readFromImpl(String filename) throws IOException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
//		List<String> lines = Files.readAllLines(new File...)					//Alternative 1
//		list = lines.stream().map(SmileyPerson::readFrom).collect(toList());	//Alternative 2
		List<String> ls = new ArrayList<String>();
		
		while (sc.hasNextLine()) {
			ls.add(sc.nextLine());
		}
		
		sc.close();
		return ls;
	}

	public List<SmileyPerson> getPersonListCopy(){
		List<SmileyPerson> copiedList = list.stream().collect(Collectors.toList());
		
		return copiedList; 
	}
	
	public static long countLines(String fileName) {
	      Path path = Paths.get(fileName);

	      long lines = 0;
	      
	      try {
	          lines = Files.lines(path).count();

	      } catch (IOException e) {
	          e.printStackTrace();
	      }

	      return lines;
	  }
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (SmileyPerson sp : list) {
			sb.append(sp.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
