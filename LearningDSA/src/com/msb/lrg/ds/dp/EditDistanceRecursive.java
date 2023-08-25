package com.msb.lrg.ds.dp;

public class EditDistanceRecursive {

	public static void main(String[] args) {
		String s1 = "saturday";
		String s2 = "sunday";
		System.out.println("O/P : " + EditDistanceRecursive.ed(s1, s2, s1.length(), s2.length()));
	}
	
	public static int ed(String s1, String s2, int m, int n) {
		
		if(m==0)
			return n;
		
		if (n==0)
			return m;
		
		if(s1.charAt(m-1) == s2.charAt(n-1)){
			return ed(s1,s2,m-1,n-1);
		}else {
			return 1 + Math.min(
							Math.min(ed(s1,s2,m-1,n), 
									ed(s1,s2,m,n-1)), 
							ed(s1,s2,m-1,n-1));
		}	
	}

}
