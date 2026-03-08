package com.msb.lrg.problems;

import java.util.HashSet;
import java.util.Set;

public class BTPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(swap("abc", 0, 2));
		String in = "abc";
		recursive(in, 0);
	}
	
	static void recursive(String in, int s) {
		if(s == in.length()-1)
			System.out.println(in);
		
		Set<Character> exists = new HashSet<>();
		for(int i=s; i<=in.length()-1; i++) {
			if(!exists.contains(in.charAt(i))) {
				exists.add(in.charAt(i));
				in = swap(in, s, i);
				recursive(in, s+1);
				in = swap(in, s, i);
			}
		}		
	}
	
	static String swap(String in, int i, int j) {
		char[] arr = in.toCharArray();
		char temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
		return new String(arr);		
	}

}
