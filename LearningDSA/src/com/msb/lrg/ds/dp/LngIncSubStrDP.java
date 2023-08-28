package com.msb.lrg.ds.dp;

import java.util.Arrays;

//Longest increasing Subsequence
public class LngIncSubStrDP {

	public static void main(String[] args) {
		int[] lis = new int[] {3,4,2,8,10,5,1};
		System.out.println("O/P : " + LngIncSubStrDP.LIS(lis, lis.length));
	}
	
	public static int LIS(int[] lis, int n) {
		
		int[] dp = new int[n];
		dp[0] = 1;
		
		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(lis[j] < lis[i])
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		System.out.println(Arrays.toString(dp));
		int res = dp[0];
		for (int i = 1; i < n; i++) {
			res = Math.max(res, dp[i]);
		}
		return res;
	}

}
