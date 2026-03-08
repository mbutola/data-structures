package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
4-Sum :: LeetCode (18, Medium)
	Given an array nums of n integers, return an array of all the unique 
	quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
		0 <= a, b, c, d < n
		a, b, c, and d are distinct.
		nums[a] + nums[b] + nums[c] + nums[d] == target
	You may return the answer in any order.
	Example 1:
		Input: nums = [1,0,-1,0,-2,2], target = 0
		Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
	Example 2:
		Input: nums = [2,2,2,2,2], target = 8
		Output: [[2,2,2,2]]
	Constraints:
		1 <= nums.length <= 200
		-109 <= nums[i] <= 109
		-109 <= target <= 109

 */
public class Problem11 {

	public static void main(String[] args) {
//		int[] nums = {1, 0, -1, 0, -2, 2};
//		int target = 0;
//		int[] nums = {2, 2, 2, 2, 2};
//		int target = 8;
		int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
		int target = -294967296;
//		int[] nums = {-3, -1, 0, 2, 4, 5};
//		int target = 2;
		List<List<Integer>> res = fourSum(nums, target);
		res.stream()
			.forEach(e -> System.out.println(e.toString()));
	}

	public static List<List<Integer>> fourSum(int[] nums, int target){
		List<List<Integer>> res = new ArrayList<>();
		if (nums.length < 4) return res;
		
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 3; i++ ) {
			if(i > 0 && nums[i] == nums[i-1])
				continue;

			for(int j = i + 1; j < nums.length - 2; j++ ) {
				if(j > i + 1 && nums[j] == nums[j-1])
					continue;
				
				int low = j + 1;
				int high = nums.length -1;
				while(low < high) {
					long sum = (long) nums[i] + nums[j] + nums[low] + nums[high]; 
					if(target == sum) {
						res.add(new ArrayList<>(List.of(nums[i],nums[j],nums[low],nums[high])));
						high--;
						low++;
						while(low < high && nums[low] == nums[low - 1])
							low++;
						while(low < high && nums[high] == nums[high + 1])
							high--;
					} else if (sum < target) {
						low++;
					} else {
						high--;
					}
				}
			}
		}
		return res;
	}

}
