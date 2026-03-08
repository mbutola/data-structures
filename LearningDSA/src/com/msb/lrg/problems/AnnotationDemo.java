package com.msb.lrg.problems;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AnnotationDemo {

	public static void main(String[] args) {
		Report report = new Report();
		report.print();
		
		System.out.println("Author :: " + Report.class.getAnnotation(Author.class).name());
		System.out.println("Version :: " + Report.class.getAnnotation(Author.class).version());
	}

}

interface Printable {
	void print();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
	String name();
	String version();
}

@Author(name="Manmohan", version="1.0")
class Report implements Printable {
	public void print() {
		System.out.println("Printing report ...");
	}
}
