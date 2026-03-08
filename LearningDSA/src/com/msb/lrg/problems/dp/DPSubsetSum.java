package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*

What is the Subset Sum Problem?
	The Subset Sum Problem asks:
		Given a set (array) of integers and a target sum K, determine whether there exists a subset whose elements add up exactly to K.
	Key points:
		Order does not matter
		Elements are not required to be contiguous
		Each element can be used at most once (0/1 choice)
	🧪 Example
		arr = [3, 34, 4, 12, 5, 2]
		K = 9
	Valid subset:
		{4, 5}
	✅ Answer = TRUE

 */
public class DPSubsetSum {
	
	static int[] in = {2,2,2};
	static int S = 2;
	static int N = 3;
	static int[][] dp = new int[N+1][S+1];
	static boolean[][] dpbu = new boolean[N+1][S+1];

	public static void main(String[] args) {
		
		boolean res;
//		res = recursive(in, N, S);
//		System.out.println("Recursive :: " + res);

//		for(int i=0; i<N+1; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		res = topDown(in, N, S);
//		System.out.println("Top down :: " + res);

		res = bottomUp(in, N, S);
		System.out.println("Bottoms up :: " + res);
	}
	
	static boolean recursive(int[] in, int n, int s) {
		
		if(s == 0) {
			return true;
		}
		
		if(n == 0) {
			return false;
		}
		
		if(s - in[n-1] >= 0) {
			return recursive(in, n-1, s - in[n-1]) || recursive(in, n-1, s);
		}else {
			return recursive(in, n-1, s);
		}
	}
	
	static boolean topDown(int[] in, int n, int s) {
		
		if(s == 0)
			return true;
		
		if(n == 0)
			return false;
		
		if(dp[n][s] != -1)
			return dp[n][s] == 1;
		
		if(s - in[n-1] >= 0) {
			dp[n][s] = (topDown(in, n-1, s - in[n-1]) || topDown(in, n-1, s)) ? 1 : 0 ; 
		}else{
			dp[n][s] = topDown(in, n-1, s) ? 1 : 0;
		}
		
		return dp[n][s] == 1;
	}
	
	static boolean bottomUp(int[] in, int n, int s) {
		
		Arrays.fill(dpbu[0], false);
		
		for(int i=0; i<N+1; i++) {
			dpbu[i][0] = true;
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<S+1; j++) {
				if(j - in[i-1] >=0) {
					dpbu[i][j] = dpbu[i-1][j - in[i-1]] || dpbu[i-1][j]; 
				}else{
					dpbu[i][j] = dpbu[i-1][j];
				}
			}
		}
		
		print2DArray(dpbu);
		return dpbu[n][s];
		
	}

	static void print2DArray(boolean[][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println("");
		}
	}
		
}
