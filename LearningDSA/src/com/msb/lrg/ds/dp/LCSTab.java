package com.msb.lrg.ds.dp;

public class LCSTab {

	public static void main(String[] args) {
		String s1 = "AXYZ";
		String s2 = "BAZ";
		System.out.println("Longest lcs ("+s1+","+s2+") is : " + LCSTab.lcs(s1, s2));
	}

	public static int lcs(String s1, String s2){
		
		int m = s1.length();
		int n = s2.length();
		int[][]dp = new int[m+1][n+1];
		
		for (int i = 0; i < n; i++) {
			dp[0][i] = 0;
		}
	
		for (int i = 0; i < m; i++) {
			dp[1][0] = 0;
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] =  1 + dp[i-1][j-1];
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		printMemo(s1, s2, dp);
		return dp[m][n];
	}
	
	public static void printMemo(String s1, String s2, int[][] memo) {
		char[] col = s1.toCharArray();
		char[] row = s2.toCharArray();
		for (int i = 0; i < memo[0].length; i++) {
			if(i == 0)
				System.out.printf("%8s", "");
			else
				System.out.printf("%2s  ", row[i-1]);
		}
		System.out.println("");
		for (int i = 0; i < memo.length; i++) {
			if(i == 0)
				System.out.printf("%4s", "");
			else
				System.out.printf("%2s  ", col[i-1]);
			for (int j = 0; j < memo[0].length; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}
}
