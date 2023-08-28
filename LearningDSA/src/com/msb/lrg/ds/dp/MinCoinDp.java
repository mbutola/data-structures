package com.msb.lrg.ds.dp;

public class MinCoinDP {

	public static void main(String[] args) {
		int[] coins = new int[] {3,4,1};
		int sum = 5;
		System.out.println("O/P : " + MinCoinDP.getCoin(coins, sum));
	}
	
	public static int getCoin(int[] coins, int sum) {
		
		int[] dp = new int[sum+1];
		dp[0] = 0;
		
		for (int i = 1; i <= sum; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if(sum >= coins[j]) {
					if(i-coins[j] >= 0) {
						int sub_res = dp[i-coins[j]]+1;
						if(sub_res != Integer.MAX_VALUE)		
							dp[i] = Math.min(dp[i], sub_res+1);
					}
				}
			}
		}
		return dp[sum];
	}

}
