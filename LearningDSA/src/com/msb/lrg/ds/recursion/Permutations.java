package com.msb.lrg.ds.recursion;

public class Permutations {

	public static void main(String[] args) {
		Permutations.permute("ABCD", 0);
	}
	
	public static void permute(String str, int i) {
		if(i == str.length()-1) {
			System.out.println(str);
			return;
		}
			
		for (int j = i; j < str.length(); j++) {
			String in = swap(str, i,j);
			permute(in, i+1);
			in = swap(str, j, i);
		}	
	}
	
	public static String swap(String str, int i, int j) {
		char[] c = str.toCharArray();
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
		return String.valueOf(c);
	}

}
