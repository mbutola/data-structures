package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class SubsetSumDP {

	public static void main(String[] args) {
		int[] arr = new int[] {2,5,3};
		int sum = 5;
		System.out.println("O/P : " + SubsetSumDP.subSum(arr, sum, arr.length));
	}
	
	public static int subSum(int[] arr, int s, int n) {
		
		int[][] dp = new int[n+1][s+1];
		
		for (int i = 0; i <= n; i++)
			dp[i][0] = 1;

		for (int i = 1; i <= s; i++)
			dp[0][i] = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= s; j++) {
				dp[i][j] = dp[i-1][j];
				if(j >= arr[i-1])
					dp[i][j] += dp[i-1][j-arr[i-1]];
			}
		}
		
		Utility.printDp(Utility.getList(arr), Utility.getList(s), dp);
		return dp[n][s];
	
	}

}
