package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class MaxSumNonConsDP {

	public static void main(String[] args) {
		int[] items = new int[] {10,5,15,20};
		System.out.println("O/P : " + MaxSumNonConsDP.sum(items, items.length));
	}

	public static int sum(int[] items, int n) {
		
		int[] dp = new int[n+1];
		dp[1] = items[0];
		dp[2] = Math.max(items[0], items[1]);
		
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i-1], items[i-1] + dp[i-2]) ;			
		}

		System.out.println(Arrays.toString(dp));
		return dp[n];
	}

}
