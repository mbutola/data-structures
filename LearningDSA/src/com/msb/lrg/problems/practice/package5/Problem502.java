package com.msb.lrg.problems.practice.package5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Intersection of Two Arrays II :: LeetCode (350, easy)
	Given two integer arrays nums1 and nums2, return an array of their intersection. Each 
	element in the result must appear as many times as it shows in both arrays and you may 
	return the result in any order.
	Example 1:
		Input: nums1 = [1,2,2,1], nums2 = [2,2]
		Output: [2,2]
	Example 2:
		Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
		Output: [4,9]
		Explanation: [9,4] is also accepted.
	Constraints:
		1 <= nums1.length, nums2.length <= 1000
		0 <= nums1[i], nums2[i] <= 1000
	Follow up:
		What if the given array is already sorted? How would you optimize your 
		algorithm?
		What if nums1's size is small compared to nums2's size? Which algorithm is 
		better?
		What if elements of nums2 are stored on disk, and the memory is limited such that 
		you cannot load all elements into the memory at once? 

*/
public class Problem502 {

	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1}; 
		int[] nums2 = {2,2};
		
		int[] res = intersect(nums1, nums2);
		System.out.println("Result :: " + Arrays.toString(res));
	}
	
    static int[] intersect(int[] nums1, int[] nums2) {
        
		Map<Integer, Integer> map = new HashMap<>();
		for(int i : nums1) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		
		List<Integer> res = new ArrayList<>();
		for(int i : nums2) {
			if(map.getOrDefault(i, 0) > 0) {
				res.add(i);
				map.put(i, map.get(i) -1);
			}
		}

//        int[] ans = new int[res.size()];
//        for (int i = 0; i < res.size(); i++)
//            ans[i] = res.get(i);
//
//        return ans;
		
		return res.stream().mapToInt(Integer::intValue).toArray();
	}

}
