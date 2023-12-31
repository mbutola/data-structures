package com.msb.lrg.ds.dp;

import java.util.Arrays;
import java.util.List;

import com.msb.lrg.ds.Utility;

public class CoinTab {

	public static void main(String[] args) {
		List<Integer> coins = Arrays.asList(1,2,3);
		int sum = 4;
		System.out.println("O/P : " + CoinTab.solve(coins, sum));
	}

	public static int solve(List<Integer> coins, int sum) {
		
		/**
		 *             |- 1 if s=0 irrespective of n
		 *             |- 0 id n==0 irrespective of s
		 *  dp[i][j] = |- dp[i-1][j] id coin[i-1] > j the sum left
		 *             |- else
		 *             |- dp[i-1][j] + dp[i][j]-coin[i-1]
		 *                coin not		 coin included 
		 *				  included		 so i is same
		 *				  so i-1 and	 sum j decrease 
		 *				  sum j is same	 by coin value
		 */
		
		int[][] dp = new int[coins.size()+1][sum+1];
		
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i-1][j];
				if(coins.get(i-1) <= j)
					dp[i][j]+=dp[i][j-coins.get(i-1)];
			}
		}
		
		Utility.printDp("123", "1234", dp);
		return dp[coins.size()][sum];
		
	}
}
