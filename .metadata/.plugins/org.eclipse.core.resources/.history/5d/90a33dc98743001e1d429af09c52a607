package com.msb.lrg.ds.backtracking;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pass = "ABCD";
		Person p = new Person();
		p.setName("ONE");
		System.out.println("person name before: " + p.getName());
		Test.call(pass, p);
		System.out.println("pass : " + pass);
		System.out.println("person name after: " + p.getName());
	}
	
	public static void call(String in, Person p) {
		char[] a = in.toCharArray();
		a[2] = 'E';
		in = String.copyValueOf(a);
		p.setName("TWO");
		System.out.println("in : " + in);
	}

}

class Person{
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
