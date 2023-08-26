package com.msb.lrg.ds.recursion;

public class Subsets {

	public static void main(String[] args) {
		print("ABC", 0, "");		
	}
	
	public static void print(String str, int loc, String value) {
		if(loc == str.length()) {
			System.out.println(value);
			return;
		}
		
		print(str, loc+1, value);
		print(str, loc+1, value+str.charAt(loc));
	}

}
