package com.msb.lrg.problems.practice.package0;

/*
LeetCode(209::Medium) Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target, 
return the minimal length of a subarray whose sum is greater than or 
equal to target. If there is no such subarray, return 0 instead.

Example 1:
	Input: target = 7, nums = [2,3,1,2,4,3]
	Output: 2
	Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:
	Input: target = 4, nums = [1,4,4]
	Output: 1
	Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
	Output: 0
 */
public class Problem3 {

	public static void main(String[] args) {
		
		int[] nums = {2,3,1,2,4,3};
		int target = 7;
		
		int res = minSubArrayLen(target, nums);
		System.out.println("Result :: " + res);		
	}
	
	public static int minSubArrayLen(int target, int[] nums) {
		int start = 0;
		int len = Integer.MAX_VALUE;
		int sum = 0;
		
		for(int end=0; end<nums.length; end++ ) {
			sum+=nums[end];
			while(sum >= target) {
				len = Math.min(len, (end+1)-start);
				sum-=nums[start++];
			}
		}
		return len == Integer.MAX_VALUE ? 0 : len;
	}

}
