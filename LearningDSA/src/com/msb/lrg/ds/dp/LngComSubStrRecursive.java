package com.msb.lrg.ds.dp;

public class LngComSubStrRecursive {

	public static void main(String[] args) {
		String s1 = "AXMZ";
		String s2 = "BAZ";
		System.out.println("Longest lcs ("+s1+","+s2+") is : " 
								+ LngComSubStrRecursive.lcs(s1, s2, s1.length(), s2.length()));
	}

	public static int lcs(String s1, String s2, int n1, int n2){
		if(n1 == 0 || n2 == 0) {
			return 0;
		}
		
		if(s1.charAt(n1-1) == s2.charAt(n2-1)) {
			return 1 + lcs(s1, s2, n1-1, n2-1);
		}else {
			return Math.max(lcs(s1, s2, n1-1, n2), lcs(s1, s2, n1, n2-1));
		}
	}
}
