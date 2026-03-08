package com.msb.lrg.problems.practice.package5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 
Intersection of Two Arrays :: LeetCode (349, easy)
	Given two integer arrays nums1 and nums2, return an array of their 
	intersection. Each element in the result must be unique and you may return 
	the result in any order.
	Example 1:
		Input: nums1 = [1,2,2,1], nums2 = [2,2]
		Output: [2]
	Example 2:
		Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
		Output: [9,4]
		Explanation: [4,9] is also accepted.
	Constraints:
		1 <= nums1.length, nums2.length <= 1000
		0 <= nums1[i], nums2[i] <= 1000 

 */
public class Problem501 {

	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1}; 
		int[] nums2 = {2,2};
		
		int[] res = intersection(nums1, nums2);
		System.out.println("Result :: " + Arrays.toString(res));
	}
	
    static int[] intersection(int[] nums1, int[] nums2) {
        
		Set<Integer> nums1Set = new HashSet<>();
		for(int i : nums1) {
			nums1Set.add(i);
		}
		
		Set<Integer> res = new HashSet<>();
		for(int i : nums2) {
			if(nums1Set.contains(i))
				res.add(i);
		}
		
		return res.stream().mapToInt(Integer::intValue).toArray();
	}

}
