package com.msb.lrg.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Comparision {

	static class Person implements Comparable<Person>{
		String name;
		int age;
		
		Person(String name, int age){
			this.name = name;
			this.age = age;
		}
		
		@Override
		public int compareTo(Person other) {
			int cmp = this.name.compareTo(other.name);
			if(cmp != 0) 
				return cmp;
			
			return Integer.valueOf(this.age).compareTo(other.age); 			
		}
		
		@Override
		public String toString() {
			return this.name + ":" + this.age;
		}
	
	}
	
	public static void main(String[] args) {
		List<Person> people = new ArrayList<>();
		people.add(new Person("Anamika", 47));
		people.add(new Person("Manmohan", 51));
		people.add(new Person("Ayushi", 16));
		people.add(new Person("Rishit", 21));
		people.add(new Person("Ayushi", 10));
		
		System.out.println("==>" + people.toString());
		System.out.println("==> Natural with Stream :: " + people.stream().sorted().toList().toString());
		
		System.out.println("==> Original :: " + people.toString());
		people.sort(Comparator.reverseOrder());
		System.out.println("==> Reversed using Comparable :: " + people.toString());
		
		people.sort(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				int cmp = p1.name.compareTo(p2.name);
				if(cmp != 0)
					return cmp;
				
				return Integer.valueOf(p1.age).compareTo(p2.age);				
			}
		});		
		System.out.println("==> Natural using Comparator :: " + people.toString());

	
		Comparator<Person> comp = (p1, p2) -> {
			int cmp = -p1.name.compareTo(p2.name);
			if(cmp != 0)
				return cmp;
			
			return -Integer.valueOf(p1.age).compareTo(p2.age);				
		};
		
		people.sort(comp);
		System.out.println("==> Reverse using Lambda :: " + people.toString());

		System.out.print("Using Streams :: " + Arrays.toString(IntStream.range(0, 5).toArray()));

	}
}
