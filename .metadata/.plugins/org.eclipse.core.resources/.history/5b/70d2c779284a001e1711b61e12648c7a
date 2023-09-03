package com.msb.lrg.ds.dp;

public class EditDistanceTab {

	//Used for spelling error and suggestions
	
	public static void main(String[] args) {
		String s1 = "cat";
		String s2 = "cut";
		System.out.println("O/P : " + EditDistanceTab.ed(s1, s2, s1.length(), s2.length()));
	}
	
	public static int ed(String s1, String s2, int m, int n) {
		
		int dp[][] = new int[m+1][n+1];
		
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}
		
		for (int i = 0; i <= n; i++) {
			dp[0][i] = i;
		}
		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1]; 
				}else {
					dp[i][j] = 1 + Math.min(
										Math.min(dp[i-1][j], 
												dp[i][j-1]), 
										dp[i-1][j-1]);
				}
			}
		}
		Utility.printDp(s1, s2, dp);
		return dp[m][n];
	}

}
