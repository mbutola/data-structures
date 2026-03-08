package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*

What is the Rod Cutting Problem?
	You are given:
		A rod of length N
		An array price[i] → price of a rod of length i
	👉 Goal:
		Cut the rod (or don’t cut it) to maximize total profit.
		⚠️ You can cut the rod any number of times.
	🧠 Key Insight
		This is an Unbounded Knapsack problem because:
			Each piece length can be used multiple times
	We want to maximize value
	🧩 Example
		Rod length = 8
		Length	1	2	3	4	5	6	7	8
		Price	1	5	8	9	10	17	17	20
	👉 Maximum profit = 22
		(using cuts: 2 + 6 → 5 + 17)

 */
public class DPRodCutting {

	static int N = 8;
	static int L = 8;
	static int[] len = {1,2,3,4,5,6,7,8};
	static int[] pr = {1,5,8,9,10,17,17,20};
	static int[][] dp;
	
	public static void main(String[] args) {
		
		int res;
		res = recursive(pr, len, N, L);
		System.out.println("Recursive :: " + res);

		dp = new int[N+1][L+1];	
		for(int i=0; i<N+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		res = topDown(pr, len, N, L);
		System.out.println("Top down :: " + res);

		
		for(int i=0; i<N+1; i++) {
			Arrays.fill(dp[i], 0);
		}
		res = bottomUp(pr, len, N, L);
		System.out.println("Bottom up :: " + res);
	}
	
	static int recursive(int[] pr, int[] len, int n, int l) {
		
		if(n==0 || l == 0)
			return 0;
		
		if(l - len[n-1] >= 0){
			return Math.max(pr[n-1] + recursive(pr, len, n, l - len[n-1]), recursive(pr, len, n-1, l));
		}else {
			return recursive(pr, len, n-1, l);
		}
	}
	
	static int topDown(int[] pr, int[] len, int n, int l) {
		
		if(n == 0 || l == 0) {
			return 0;
		}
		
		if(dp[n][l] != -1){
			return dp[n][l];
		}
		
		if(l- len[n-1] >= 0) {
			return dp[n][l] = Math.max(pr[n-1] + topDown(pr, len, n, l-len[n-1]), topDown(pr, len, n-1, l));
		}else {
			return dp[n][l] = topDown(pr, len, n-1, l);
		}

	}

	static int bottomUp(int[] pr, int[] len, int n, int l) {
		
		for(int i=0; i<=pr.length; i++) {
			for(int j=0; j<=len.length; j++) {
				if(i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<L+1; j++) {
				if(j - len[i-1] >= 0) {
					dp[i][j] = Math.max(pr[i-1] + dp[i][j-len[i-1]], dp[i-1][j]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n][l];
	}
}

