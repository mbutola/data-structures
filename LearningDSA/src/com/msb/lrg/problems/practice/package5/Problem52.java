package com.msb.lrg.problems.practice.package5;

import java.util.Arrays;

/*

Climbing Stairs :: LeetCode (70, easy)
	You are climbing a staircase. It takes n steps to reach the top.
	Each time you can either climb 1 or 2 steps. In how many distinct ways 
	can you climb to the top?
	Example 1:
		Input: n = 2
		Output: 2
		Explanation: There are two ways to climb to the top.
			1. 1 step + 1 step
			2. 2 steps
	Example 2:
		Input: n = 3
		Output: 3
		Explanation: There are three ways to climb to the top.
			1. 1 step + 1 step + 1 step
			2. 1 step + 2 steps
			3. 2 steps + 1 step
	Constraints:
	1 <= n <= 45

 */
public class Problem52 {

	public static void main(String[] args) {
		int n = 5;
		int res = climbStairs(n);
		System.out.println("Result Rec :: " + res);
    	int[] cost = new int[n + 1];
    	Arrays.fill(cost, -1);
		res = climbStairsTD(cost, n);
		System.out.println("Result TP :: " + res);
		res = climbStairsBP(n);
		System.out.println("Result BP :: " + res);
    	
	}
	
    public static int climbStairs(int n) {
        return cost(n);
    }

    public static int cost(int n) {
    	
    	if(n <= 2)
    		return n;

    	return cost(n - 1) + cost(n - 2);
    }

    public static int climbStairsTD(int[] cost, int n) {
        return costTD(cost, n);
    }

    public static int costTD(int[] cost, int n) {
    	
    	if(n <= 2)
    		return cost[n] = n;

    	if(cost[n] != -1) {
    		return cost[n]; 
    	}

    	return cost[n] = cost(n - 1) + cost(n - 2);
    }

    public static int climbStairsBP(int n) {

    	int[] cost = new int[n + 1];
    	cost[1] = 1;
    	cost[2] = 2;
    	
    	for(int i = 3; i <= n; i++) {
    		cost[i] = cost[i - 1] + cost[i - 2];
    	}
    	return cost[n];
    }
}
