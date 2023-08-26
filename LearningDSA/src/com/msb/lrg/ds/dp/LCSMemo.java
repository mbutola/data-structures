package com.msb.lrg.ds.dp;

public class LCSMemo {

	public static String s1 = "CDAXMZ";
	public static String s2 = "EDBAZ";
	public static int M = s1.length();
	public static int N = s2.length();
	public static int[][] memo = new int[M+1][N+1];
	static {
		for (int i = 0; i < M+1; i++) {
			for (int j = 0; j < N+1; j++) {
				memo[i][j] = -1;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Longest lcs ("+s1+","+s2+") is : " 
								+ LCSMemo.lcs(s1, s2, M, N));
		Utility.printDp(s1, s2, memo);
	}

	public static int lcs(String s1, String s2, int m, int n){

		if(memo[m][n] != -1) {
			return memo[m][n]; 
		}else {
			if (n == 0 || m == 0) {
				memo[m][n] = 0;
			} else {
				if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
					memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1);
				} else {
					memo[m][n] = Math.max(lcs(s1, s2, m - 1, n), lcs(s1, s2, n-1, m));
				}
			}
		}
		return memo[m][n];
	}

	public static void printMemo(int[][] memo) {
		for (int i = 0; i < M+1; i++) {
			for (int j = 0; j < N+1; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}
}
