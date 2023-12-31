package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class CountBSTDP {

	public static void main(String[] args) {
		
		/*
		 * Catalan numbers
		 * 
		 *              1     2n
		 * res(n) :  -------    C
		 *            n + 1      n
		 * 
		 */
		
		int nodes = 6;
		System.out.println("O/P : " + CountBSTDP.count(nodes));
	}

	public static int count(int n) {
		
		int[] dp = new int[n+1]; 
		dp[0] = 1;
		
		for (int i = 1; i <= n ; i++) {
			dp[i] = 0;
			for (int j = 0; j < i; j++) {
				dp[i] = dp[i] + dp[j]*dp[i-j-1];
			}
			
		}
		
		System.out.println(Arrays.toString(dp));		
		return dp[n];
	}
}
