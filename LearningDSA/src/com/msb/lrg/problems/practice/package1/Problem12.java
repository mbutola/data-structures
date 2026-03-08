package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

	Works for 4-sum, 3-sum, 5-sum, k-sum
		(because it is built using a general K-sum pattern)
	Avoids duplicates automatically
		Correctly skips repeated values at all levels.
	Uses long to prevent overflow
		Important when numbers are large.
	Efficient pruning
		If even the smallest or largest possible values cannot reach target → stop early.

 */
public class Problem12 {

	public static void main(String[] args) {
//		int[] nums = {1,0,-1,0,-2,2};
//		int target = 0;
		int[] nums = {1,1,1,1,1};
		int target = 4;
		Arrays.sort(nums);
		List<List<Integer>> res = kSum(nums, target, 2, 0);
		res.stream()
			.forEach(e -> System.out.println(e.toString()));
	}
	
	private static List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
		List<List<Integer>> res = new ArrayList<>();
		
		if(start == nums.length) return res;
		
		long average = target/k;		
		if(average < nums[0] || average > nums[nums.length -1]) return res;
		
		if(k == 2) return twoSum(nums, target, start);
		
		for(int i = start; i < nums.length - (k - 1); i++) {
			if(i > start && nums[i] == nums[i - 1]) continue;
			
			for(List<Integer> subset : kSum(nums, target - nums[i], k - 1, i + 1)) {
				List<Integer> list = new ArrayList<>();
				list.add(nums[i]);
				list.addAll(subset);
				res.add(list);
			}
		}
		
		return res;
	}
	
	public static List<List<Integer>> twoSum(int[] nums, int target, int start){
		List<List<Integer>> res = new ArrayList<>();
		int lo = start;
		int hi = nums.length - 1;
		
		while(lo < hi) {
			int sum = nums[lo] + nums[hi];
			if(sum == target) {
				res.add(new ArrayList<>(List.of(nums[lo], nums[hi])));
				lo++; hi--;
			}else if(sum < target || (lo > start && nums[lo] == nums[lo - 1])) {
				lo++;
			}else if(sum > target || (hi < nums.length -1  && nums[hi] == nums[hi + 1])) {
				hi--;
			}
		}
		return res;
	}

}
