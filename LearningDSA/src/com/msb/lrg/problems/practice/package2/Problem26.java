package com.msb.lrg.problems.practice.package2;

import java.util.Arrays;

/*

Coin Change II :: LeetCode (518, Medium)
	You are given an integer array coins representing coins of different 
	denominations and an integer amount representing a total amount of money.
	Return the number of combinations that make up that amount. If that amount 
	of money cannot be made up by any combination of the coins, return 0.
	You may assume that you have an infinite number of each kind of coin.
	The answer is guaranteed to fit into a signed 32-bit integer.
	Example 1:
		Input: amount = 5, coins = [1,2,5]
		Output: 4
		Explanation: there are four ways to make up the amount:
			5=5
			5=2+2+1
			5=2+1+1+1
			5=1+1+1+1+1
	Example 2:
		Input: amount = 3, coins = [2]
		Output: 0
		Explanation: the amount of 3 cannot be made up just with coins of 2.
	Example 3:
		Input: amount = 10, coins = [10]
		Output: 1
	Constraints:
		1 <= coins.length <= 300
		1 <= coins[i] <= 5000
		All the values of coins are unique.
		0 <= amount <= 5000

 */
public class Problem26 {

	public static void main(String[] args) {
		int amount = 5;
		int[] coins = {1,2,5};
		
//		int res = change(amount, coins);
		int res = changeDPBottomUp(amount, coins);
		System.out.println("Result :: " + res);
	}
	
    public static int changeDPBottomUp(int amount, int[] coins) {
    	
    	int n = coins.length;

    	int[][] dp = new int[n + 1][amount + 1]; 
    	
    	for(int  i = 0; i <= n; i++ ) {
    		dp[i][0] = 1;
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		int coin = coins[i - 1];
    		for(int a = 1; a <= amount; a++) {
    			dp[i][a] = dp[i - 1][a];
    			if(a >= coin)
    				dp[i][a] += dp[i][a - coin];
    		}
    	}

    	 return dp[n][amount];
    }
 
	
	
    public static int change(int amount, int[] coins) {
    	int[][] memo = new int[coins.length + 1][amount + 1];
    	for(int i = 0; i < memo.length; i++) {
    		Arrays.fill(memo[i], -1);
    	}
    	return count(amount, coins, memo, 0);
    }
	
    public static int count(int amount, int[] coins, int[][] memo, int n) {
    	if(n == coins.length || amount < 0)
    		return 0;
    	
    	if(amount == 0)
    		return 1;
    	
    	if(memo[n][amount] != -1)
    		return memo[n][amount];
    	
    	return memo[n][amount] = count(amount - coins[n], coins, memo, n) 
    									+ count(amount, coins, memo, n + 1);
    	
    }
}
