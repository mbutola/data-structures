package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class GameStrategyDP {

	public static void main(String[] args) {
		int[] points = new int[] {20,5,4,6,8,3};
		System.out.println("O/P : "  + GameStrategyDP.solve(points, points.length));
	}
	
	public static int solve(int[] points, int n) {
		
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n-1; i++) {
			dp[i][i+1] = Math.max(points[i], points[i+1]);
		}
		
		for (int gap=3; gap < n  ; gap+=2) {
			for (int i = 0; i+gap < n; i++) {
				int j = i+gap;
				dp[i][j] = Math.max(points[i] + Math.min(dp[i+2][j],
															dp[i+1][j-1]),
									points[j] + Math.min(dp[i+1][j-1],
															dp[i][j-2]));
			}
		}
		Utility.printDp("12345", "12345", dp);
		return dp[0][n-1];
	}
}
