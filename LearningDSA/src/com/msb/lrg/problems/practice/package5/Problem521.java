package com.msb.lrg.problems.practice.package5;

import java.util.Arrays;

/*

Climbing Stairs II :: LeetCode (3693, medium)
	You are climbing a staircase with n + 1 steps, numbered from 0 to n.
	You are also given a 1-indexed integer array costs of length n, where 
	costs[i] is the cost of step i.
	From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of 
	jumping from step i to step j is defined as: costs[j] + (j - i)^2
	You start from step 0 with cost = 0.
	Return the minimum total cost to reach step n.
	Example 1:
		Input: n = 4, costs = [1,2,3,4]
		Output: 13
		Explanation:
			One optimal path is 0 → 1 → 2 → 4
				Jump	Cost Calculation				Cost
				0 → 1	costs[1] + (1 - 0)^2 = 1 + 1	2
				1 → 2	costs[2] + (2 - 1)^2 = 2 + 1	3
				2 → 4	costs[4] + (4 - 2)^2 = 4 + 4	8
			Thus, the minimum total cost is 2 + 3 + 8 = 13
	Example 2:
		Input: n = 4, costs = [5,1,6,2]
		Output: 11
		Explanation:
			One optimal path is 0 → 2 → 4
				Jump	Cost Calculation				Cost
				0 → 2	costs[2] + (2 - 0)2 = 1 + 4		5
				2 → 4	costs[4] + (4 - 2)2 = 2 + 4		6
		Thus, the minimum total cost is 5 + 6 = 11
	Example 3:
		Input: n = 3, costs = [9,8,3]
		Output: 12
		Explanation:
			The optimal path is 0 → 3 with total cost = costs[3] + (3 - 0)2 = 3 + 9 = 12
	Constraints:
		1 <= n == costs.length <= 105​​​​​​​
		1 <= costs[i] <= 104


 */
public class Problem521 {

	public static void main(String[] args) {
		int n = 4;
		int[] costs = {1,2,3,4};
		int res = climbStairs(n, costs);
		System.out.println("Results Rec :: " + res);
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		res = climbStairsTD(n, costs, dp);
		System.out.println("Results TD :: " + res);
		res = climbStairsBP(n, costs);
		System.out.println("Results BP :: " + res);
	}

    public static int climbStairs(int n, int[] costs) {
    	
    	if(n == 0)
    		return 0;
    	
    	if(n < 0)
    		return Integer.MAX_VALUE;
    	
    	int cost = Integer.MAX_VALUE;
    	for(int k = 1; k <= 3; k++) {
    		int j = n - k;
    		if(j >= 0)
    			cost = Math.min(cost, climbStairs(j, costs) + costs[n - 1] + k*k);
    	}
    	
    	return cost;
    }
	
    public static int climbStairsTD(int n, int[] costs, int[] dp) {
    	
    	if(n == 0)
    		return 0;
    	
    	if(n < 0)
    		return Integer.MAX_VALUE;
    	
    	if(dp[n] != -1)
    		return dp[n];
    	
    	int cost = Integer.MAX_VALUE;
    	for(int k = 1; k <= 3; k++) {
    		int j = n - k;
    		if(j >= 0)
    			cost = Math.min(cost, climbStairsTD(j, costs, dp) + costs[n - 1] + k*k);
    	}
    	
    	return dp[n] = cost;
    }

    public static int climbStairsBP(int n, int[] costs) {
    	
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0;

    	for(int i = 1; i <= n; i++ ) {
	    	for(int k = 1; k <= 3; k++) {
	    		int j = i - k;
	    		if(j >= 0)
	    			dp[i] = Math.min(dp[i], dp[j] + costs[i - 1] + k*k);
	    	}
    	}
    	
    	return dp[n];
    }

    
}
