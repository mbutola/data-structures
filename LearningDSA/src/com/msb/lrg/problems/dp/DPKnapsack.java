package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*

What is the Knapsack Problem?
	You are given:
		n items
	Each item has:
		weight wt[i]
		value val[i]
		A knapsack with capacity W
	👉 Goal:
		Maximize total value without exceeding capacity.
	🔒 0/1 Knapsack Rule
		You can either take an item once or not take it
		You cannot split items
			Hence: 0 or 1

 */
public class DPKnapsack {
	
	static int N = 3;
	static int W = 3;
	static int[][] dp = new int[N+1][W+1];
	
	public static void main(String[] args) {
		
		int[] val = {1,2,3};
		int[] wt = {4,5,1};

		int res = solveRecursive(val, wt, N, W);
		System.out.println("Recursion :: " + res);
		
		for (int i = 0; i <= N ; i++) {
		    Arrays.fill(dp[i], -1);
		}
		
		res = topDown(val, wt, N, W);
		System.out.println("Top down :: " + res);
		
		res = bottomUp(val, wt, N, W);
		System.out.println("Bottom up :: " + res);
	}
	
	static int solveRecursive(int[] val, int[] wt, int n, int w) {
		
		if(w == 0 || n == 0) {
			return 0;
		}
		
		if(wt[n-1] <= w ) {
			return Math.max(val[n-1] + solveRecursive(val, wt, n-1, w - wt[n-1]), solveRecursive(val, wt, n-1, w));
		}else {
			return solveRecursive(val, wt, n-1, w);
		}
	}
	
	static int topDown(int[] val, int[] wt, int n, int w) {
		
		if(w == 0 || n == 0) {
			return 0;
		}
		
		if(dp[n][w] != -1)
			return dp[n][w];
				
		if(wt[n-1] <= w ) {
			dp[n][w] = Math.max(val[n-1] + topDown(val, wt, n-1, w - wt[n-1]), topDown(val, wt, n-1, w));
		}else {
			dp[n][w] = topDown(val, wt, n-1, w);
		}
		
		return dp[n][w];

	}
	
	static int bottomUp(int[] val, int[] wt, int n, int w) {
		
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<W+1; j++) {
				if(i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}

		for(int i=1; i<N+1; i++) {
			for(int j=1; j<W+1; j++) {
				if(wt[i-1] <= j ) {
					 dp[i][j] = Math.max(val[i-1] + dp[i-1][j - wt[i-1]], dp[i-1][j]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}

		return dp[n][w];
	}

	public static void print2DArray(int[][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	public static void print2DArrayChar(char[][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println("");
		}
	}

}
