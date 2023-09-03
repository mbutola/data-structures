package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class PalinPartitionDP {

	public static void main(String[] args) {
		String str = "geek";
		System.out.println("O/P : " + PalinPartitionDP.PalinPartition(str));
	}

	public static int PalinPartition(String str) {
		
		int n = str.length();
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}
		
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
		}

		for (int gap = 1; gap < n; gap++) {
			for (int i = 0; i+gap < n; i++) {
				int j = i+gap;
				if(Utility.isPalindrome(str, i, j)) {
					dp[i][j] = 0;
				}else{
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], 
										1+ dp[i][k]
											+ dp[k+1][j]);
					}
				}
			}
		}
		
		Utility.printDp(Utility.getList(n), Utility.getList(n), dp);
		return dp[0][n-1];
		
	}
}
