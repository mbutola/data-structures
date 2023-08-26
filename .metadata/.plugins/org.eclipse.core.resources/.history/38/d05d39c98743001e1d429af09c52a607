package com.msb.lrg.ds.backtracking;

public class StringPermutation1 {

	public static int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringPermutation1.permute("ABCD", 0);
		System.out.println("Count : " + count);
	}
	
	public static void permute(String in, int l) {
		count++;
		if(l == in.length()-1) {
			System.out.println(in);
		} else {
			for(int i=l; i < in.length(); i++) {
				in = swap(in, l, i);
				permute(in, l+1);
				in = swap(in, l, i);
			}
		}
	}
	
	public static String swap(String in, int i, int j) {
		if(i == j)
			return in;
		return in.substring(0, i) + in.charAt(j) + in.substring(i+1, j) + in.charAt(i) + in.substring(j+1, in.length());
	}

}
