package com.msb.lrg.ds.backtracking;

public class StringPermutation {

	public static int count = 0;
	
	public static void main(String[] args) {
		StringPermutation.permute("ABCD", "");
		System.out.println("Count : " + count);
	}
	
	public static void permute(String in, String out) {
		count++;
		if(in.length() == 1) {
			System.out.println(out.concat(in));
		} else {
			for(int i=0; i<in.length(); i++) {
				String in1 = in.substring(0, i) + in.substring(i+1,  in.length());
				permute(in1, out.concat(in.charAt(i)+""));
			}
		}
	}

}
