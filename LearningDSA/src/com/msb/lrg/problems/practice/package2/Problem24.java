package com.msb.lrg.problems.practice.package2;

import java.util.Arrays;

/*

Burst Balloons :: LeetCode (312, Hard)
	You are given n balloons, indexed from 0 to n - 1. Each balloon is painted 
	with a number on it represented by an array nums. You are asked to burst all 
	the balloons.
	
	If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] 
	coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if 
	there is a balloon with a 1 painted on it.
	
	Return the maximum coins you can collect by bursting the balloons wisely.
	
	Example 1:
		Input: nums = [3,1,5,8]
		Output: 167
		Explanation:
			nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
			coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
	Example 2:
		Input: nums = [1,5]
		Output: 10
	Constraints:
		n == nums.length
		1 <= n <= 300
		0 <= nums[i] <= 100

 */
public class Problem24 {

	public static void main(String[] args) {
		int[] input = {3,1,5,8};
		int[] arr = new int[input.length + 2];
		arr[0] = arr[arr.length - 1] = 1;
		System.arraycopy(input, 0, arr, 1, arr.length - 2);
		int resRec = recursive(arr, 0, arr.length - 1);
		System.out.println("Result recursive :: " + resRec);

		int[][] memo = new int[arr.length][arr.length];
		for(int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
		int resTopDown = dpTopDown(arr, memo, 0, arr.length - 1);
		System.out.println("Result recursive :: " + resTopDown);

		int resBottonUp = dpBottomUp(arr);
		System.out.println("Result recursive :: " + resBottonUp);
	} 

	public static int recursive(int[] input, int left, int right) {
		
		if(left + 1 == right) return 0;
		
		int max = 0;
		for(int k = left + 1; k < right ; k++) {
			int temp = recursive(input, left, k) + input[left]*input[k]*input[right] + recursive(input, k, right);
			max = Math.max(max, temp);
		}
		return max;
	}
	
	public static int dpTopDown(int[] input, int[][] memo, int left, int right) {
		
		if(left + 1 == right) return 0;
		
		if(memo[left][right] != -1)
			return memo[left][right];
		
		int max = 0;
		for(int k = left + 1; k < right ; k++) {
			int temp = recursive(input, left, k) + input[left]*input[k]*input[right] + recursive(input, k, right);
			max = Math.max(max, temp);
		}
		return memo[left][right] = max;
	}

	public static int dpBottomUp(int[] input) {
		
		int[][] dp = new int[input.length][input.length];
		
		for(int len = 2; len < input.length; len++) {
			for(int left = 0; left + len < input.length; left++) {
				int right = left + len;

				for(int k = left + 1; k < right ; k++) {
					dp[left][right] = Math.max(dp[left][right], 
							dp[left][k] + input[left]*input[k]*input[right] + dp[k][right]);
				}
			}
		}
		for(int m = 0; m < dp.length; m++) {
			System.out.println(Arrays.toString(dp[m]));
		}
		return dp[0][input.length - 1];
	}
}
