package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class CutDP {

	public static void main(String[] args) {
		System.out.println("O/P : " + CutDP.cut(9, 2, 3, 4));	}
	
	public static int cut(int len, int a, int b, int c) {
		
		int[] dp = new int[len+1];
		
		dp[0] = 0;
		
		for (int i = 1; i <= len; i++) {
			dp[i] = -1;
			if(i-a >= 0)
				dp[i] = Math.max(dp[i], dp[i-a]);
			if(i-b >= 0)
				dp[i] = Math.max(dp[i], dp[i-b]);
			if(i-c >= 0)
				dp[i] = Math.max(dp[i], dp[i-c]);
			if(dp[i] != -1)
				dp[i]+=1;
		}
		
		System.out.println(Arrays.toString(dp));
		return dp[len];
	}

}
