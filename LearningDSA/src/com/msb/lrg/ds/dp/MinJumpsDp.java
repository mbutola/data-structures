package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class MinJumpsDP {

	public static void main(String[] args) {
		int[] jumps = new int[] {3,4,2,1,2,1};
		System.out.println(Arrays.toString(jumps));
		System.out.println("O/P : " + MinJumpsDP.getJumps(jumps, jumps.length));
	}
	
	public static int getJumps(int[] jumps, int n) {
		
		int[] dp = new int[n];
		dp[0] = 0;
		
		for (int i = 1; i < n; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if(j+jumps[j] >= i) {
					if(dp[j] != Integer.MAX_VALUE)		
						dp[i] = Math.min(dp[i], dp[j]+1);
				}
			}
		}
	
		System.out.println(Arrays.toString(dp));
		return dp[n-1];

	}

}
