package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SmileyPersonTest {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		test00();
		System.out.println();
		test01();
		System.out.println();
		test02();
		System.out.println();
//		TODO how to make OR-filter by removeIf -> Predicate Chain
		test03();
		System.out.println();
		testStreams();
//		
//		int z = -1 % 10;
//		System.out.println("z:"+z);
	}
	
	public static void test00() {
		String sps1 = "hanks 	tom 	1956 	20.0 	0.5 	0.5 	not_smiling 20 	20";
		String sps2 = "sparrow 	jack 	1889 	100.0 	0.3 	0.8 	smiling 	200 200";
		String sps3 = "good 	michell 1999 	30.0 	0.2 	0.2 	smiling 	50 	50";
		String sps4 = "prenom 	mon 	2000 	5.0 	0.9 	0.9 	not_smiling 10 	10";
		String sps5 = "arch 	steven 	2010 	12.0 	0.35 	0.55 	smiling 	60 	100";
		String sps6 = "airbind 	momu 	2015 	20.0 	0.1 	0.1 	smiling 	80 	98";
		String sps7 = "samson 	sam 	1000 	120.0 	0.5 	0.5 	smiling 	150 150";
		String sps8 = "richi 	rick 	2003 	32.0 	0.1 	0.3 	not_smiling 140 12";
		String sps9 = "python 	monty 	1979 	80.0 	0.75 	0.75 	smiling 	150 120";
		String sps10= "quijote 	don 	1605 	200.0 	0.9 	0.9 	smiling 	250 250";
		
		SmileyPerson sp1 = SmileyPerson.readFrom(sps1);
		SmileyPerson sp2 = SmileyPerson.readFrom(sps2);
		SmileyPerson sp3 = SmileyPerson.readFrom(sps3);
		SmileyPerson sp4 = SmileyPerson.readFrom(sps4);
		SmileyPerson sp5 = SmileyPerson.readFrom(sps5);
		SmileyPerson sp6 = SmileyPerson.readFrom(sps6);
		SmileyPerson sp7 = SmileyPerson.readFrom(sps7);
		SmileyPerson sp8 = SmileyPerson.readFrom(sps8);
		SmileyPerson sp9 = SmileyPerson.readFrom(sps9);
		SmileyPerson sp10= SmileyPerson.readFrom(sps10);
		
		SmileyPersons spList1 = new SmileyPersons();
		SmileyPersons spList2 = new SmileyPersons(sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9, sp10);
		
		spList2.writeTo("data/SmileyPersons/test00.txt");
		
		spList1.readFrom("data/SmileyPersons/test00.txt");
		
		System.out.println("Whole List element by element(List.forEach(Consumer<SmileyPerson>)):");
		spList1.getPersonListCopy().forEach(System.out::println);
//		spList1.getPersonListCopy().forEach(SmileyPerson -> System.out.println(SmileyPerson.toString()));
	}
	
//	public static Comparator<SmileyPerson> compareByName = (SmileyPerson sp1, SmileyPerson sp2) -> sp1.getLastname().compareTo(sp2.getLastname());
	
	public static void test01() {
		SmileyPersons spList1 = new SmileyPersons();
		SmileyPersons spList2 = new SmileyPersons();
		SmileyPersons spList3 = new SmileyPersons();
		List<SmileyPerson> list2 = new ArrayList<SmileyPerson>();
		List<SmileyPerson> list3 = new ArrayList<SmileyPerson>();
		
		spList1.readFrom("data/SmileyPersons/test00.txt");
		
		System.out.println("\ntest01");
		System.out.println("\ncompareByName OLD:");
		list2 = spList1.getPersonListCopy();
		list2.sort(SmileyPerson::compareByName);		
		spList2 = new SmileyPersons(list2);
		spList2.getPersonListCopy().forEach(System.out::println);
		
		System.out.println("\ncompareByName NEW:");
		list3 = spList1.getPersonListCopy();
		Collections.sort(list3, SmileyPersons.compareByName);
		spList3 = new SmileyPersons(list3);
		spList3.getPersonListCopy().forEach(System.out::println);
	}
	
	public static void test02() {
		SmileyPersons spList1 = new SmileyPersons();
		SmileyPersons spList2 = new SmileyPersons();
		SmileyPersons spList3 = new SmileyPersons();
		List<SmileyPerson> list2 = new ArrayList<SmileyPerson>();
		List<SmileyPerson> list3 = new ArrayList<SmileyPerson>();
		
		spList1.readFrom("data/SmileyPersons/test00.txt");
		
		System.out.println("\ntest02");
		System.out.println("\nSmileyPersons1 import list:");
		spList1.getPersonListCopy().forEach(System.out::println);
		
		list2 = spList1.getPersonListCopy();
		list3 = spList1.getPersonListCopy();
		
		list2.sort(SmileyPerson::compareByName);
		spList2 = new SmileyPersons(list2);
		Collections.sort(list3, SmileyPersons.compareByName);
		spList3 = new SmileyPersons(list3);
		
		System.out.println("\nSmileyPersons1 after sortByName by SmileyPersons2:");
		spList1.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByName by SmileyPersons2 OLD:");
		spList2.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByName by SmileyPersons2 NEW:");
		spList3.getPersonListCopy().forEach(System.out::println);
		
		list2.sort(SmileyPerson::compareByYear);
		spList2 = new SmileyPersons(list2);
		Collections.sort(list3, SmileyPersons.compareByYear);
		spList3 = new SmileyPersons(list3);
		
		System.out.println("\nSmileyPersons1 after sortByYear by SmileyPersons2:");
		spList1.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByYear by SmileyPersons2 OLD:");
		spList2.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByYear by SmileyPersons2 NEW:");
		spList3.getPersonListCopy().forEach(System.out::println);
		
		list2.sort(SmileyPerson::compareByFirstName);
		spList2 = new SmileyPersons(list2);
		Collections.sort(list3, SmileyPersons.compareByFirstName);
		spList3 = new SmileyPersons(list3);
		
		System.out.println("\nSmileyPersons1 after sortByFirstName by SmileyPersons2:");
		spList1.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByFirstName by SmileyPersons2 OLD:");
		spList2.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after sortByFirstName by SmileyPersons2 NEW:");
		spList3.getPersonListCopy().forEach(System.out::println);
	}
	
	public static void test03() {
		SmileyPersons spList1 = new SmileyPersons();
		SmileyPersons spList2 = new SmileyPersons();
		SmileyPersons spList3 = new SmileyPersons();
		List<SmileyPerson> list2 = new ArrayList<SmileyPerson>();
		List<SmileyPerson> list3 = new ArrayList<SmileyPerson>();
		
		spList1.readFrom("data/SmileyPersons/test00.txt");
		
		System.out.println("\ntest03");
		
		list2 = spList1.getPersonListCopy();
		list3 = spList1.getPersonListCopy();
		
		list2.removeIf(SmileyPerson::hasTallHead);
		spList2 = new SmileyPersons(list2);
		list3 = list3.stream().filter(SmileyPersons.hasTallHead).toList();
		spList3 = new SmileyPersons(list3);
		
		System.out.println("\nSmileyPersons1 after removeIf hasTallHead by SmileyPersons2:");
		spList1.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after removeIf hasTallHead by SmileyPersons2:");
		spList2.getPersonListCopy().forEach(System.out::println);
		
		list2.removeIf(SmileyPerson::isOld);
		spList2 = new SmileyPersons(list2);
		System.out.println("\nSmileyPersons1 after removeIf hasTallHead OR isOld by SmileyPersons2:");
		spList1.getPersonListCopy().forEach(System.out::println);
		System.out.println("\nSmileyPersons2 after removeIf hasTallHead OR isOld by SmileyPersons2:");
		spList2.getPersonListCopy().forEach(System.out::println);
	}
	
	public static void testStreams() {
		//TODO 
		SmileyPersons spList1 = new SmileyPersons();
		List<SmileyPerson> list2 = new ArrayList<SmileyPerson>();
		List<SmileyPerson> list3 = new ArrayList<SmileyPerson>();
		
		System.out.println("\ntestStreams");
		
		spList1.readFrom("data/SmileyPersons/test00.txt");
		System.out.println("\nSmileyPersons default:");
		spList1.getPersonListCopy().forEach(System.out::println);
		
		System.out.println("\ne.1) SmileyPersons sorted(compareByName):");
		list2 = spList1.getPersonListCopy().stream().sorted(SmileyPersons.compareByName).toList();
		list2.forEach(System.out::println);
		
		System.out.println("\ne.2) SmileyPersons sorted(compareByName) and isYoung:");
		list3 = list2.stream().filter(SmileyPersons.isYoung).toList();
		list3.forEach(System.out::println);
		
		System.out.println("\ne.3) SmileyPersons sorted(compareByName) with full names:");		
		Function<SmileyPerson, String> fullName = (SmileyPerson sp) -> String.format("%-20s %-20s", sp.getLastname(), sp.getFirstname());
		List<String> listOfNames = new ArrayList<String>();
		listOfNames = spList1.getPersonListCopy().stream().map(fullName).sorted(String::compareTo).collect(Collectors.toList());		
		listOfNames.forEach(System.out::println);
		
		System.out.println("\ne.4) SmileyPersons length of full names:");
		Function<SmileyPerson, String> fullNameSpliced = (SmileyPerson sp) -> String.format("%s%s", sp.getLastname(), sp.getFirstname());
		List<Integer> listOfNamesSpliced = new ArrayList<Integer>();
		listOfNamesSpliced = spList1.getPersonListCopy().stream().map(fullNameSpliced).mapToInt(String::length).boxed().collect(Collectors.toList());		
		listOfNamesSpliced.forEach(System.out::println);
		System.out.println("SmileyPersons:");
		spList1.getPersonListCopy().forEach(System.out::println);
		
		System.out.println("\ne.5) SmileyPersons sum of lengths of full names:");
		Integer sum = listOfNamesSpliced.stream().reduce(0, Integer::sum);
		System.out.println(sum);
	}
}
