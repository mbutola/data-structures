package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*
 
Subarray Count Problem — Clear Description
	🔍 What is a Subarray?
		A subarray is a contiguous (continuous) part of an array.
	Example for array [1, 2, 3]:
		[1], [2], [3], [1,2], [2,3], [1,2,3]
	🎯 Subarray Count Problem (Most Common Form)
	Problem statement
		Given an integer array arr and an integer k, count how many subarrays have sum equal to k.
	🧪 Example
		arr = [1, 1, 1]
		k = 2
	Valid subarrays
		[1,1] (index 0–1)
		[1,1] (index 1–2)
	✅ Answer = 2
 
 */
public class DPSubarrayCount {

	static int S = 2;
	static int N = 3;
	static int[] nums = {1,1,1};
	static int[][] dp = new int[N+1][S+1];
	
	public static void main(String[] args) {
		
		int res;
		
		res = recursive(nums, N, S);
		System.out.println("Recursive :: " + res);

		for(int i=0; i<N+1; i++) {
			Arrays.fill(dp[i], -1);
		}

		res = topDown(nums, N, S);
		System.out.println("Top down :: " + res);

		res = bottomUp(nums, N, S);
		System.out.println("Bottom up :: " + res);
	}
	
	static int recursive(int[] nums, int n, int s) {
		
		if(s == 0)
			return 1;
		
		if(n == 0)
			return 0;
		
		if(s - nums[n-1] >= 0) {
			return recursive(nums, n-1, s - nums[n-1]) + recursive(nums, n-1, s);
		}else {
			return recursive(nums, n-1, s);
		}
		
	}
	
	static int topDown(int[] nums, int n, int s) {
		
		if(n == 0)
			dp[n][s] = 0;

		if(s == 0)
			dp[n][s] = 1;
				
		if(dp[n][s] != -1)
			return dp[n][s];
		
		if(s - nums[n-1] >= 0) {
			return dp[n][s] = topDown(nums, n-1, s - nums[n-1]) + topDown(nums, n-1, s); 
		}else {
			return dp[n][s] = topDown(nums, n-1, s);
		}
		
	}

	static int bottomUp(int[] nums, int n, int s) {
		
		for(int i=0; i<N+1; i++) {
			dp[i][0] = 1;
		}

        for(int i=0; i <=nums.length; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<N+1; i++){
            for(int j=1; j<S+1; j++){
                if(j - nums[i-1] >= 0){
                    dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j];
                }else{
                     dp[i][j] = dp[i-1][j];
                }
            }
        }
 		return dp[n][s];
	}
}
