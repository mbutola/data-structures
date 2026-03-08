package com.msb.lrg.problems.practice.package4;

import java.util.Arrays;

/*
75. Sort Colors :: LeetCode (75 Medium)
	Given an array nums with n objects colored red, white, or blue, sort them 
	in-place so that objects of the same color are adjacent, with the colors in the 
	order red, white, and blue.
	We will use the integers 0, 1, and 2 to represent the color red, white, and 
	blue, respectively.
	You must solve this problem without using the library's sort function.
	Example 1:
		Input: nums = [2,0,2,1,1,0]
		Output: [0,0,1,1,2,2]
	Example 2:
		Input: nums = [2,0,1]
		Output: [0,1,2]
	Constraints:
		n == nums.length
		1 <= n <= 300
		nums[i] is either 0, 1, or 2.

 */
public class Problem40 {

	public static void main(String[] args) {
//		 int[] nums = {2,0,2,1,1,0};
		 int[] nums = {1,2,1};
		 sortColors(nums);
		 System.out.println(Arrays.toString(nums));
	}

	static void sortColors(int[] nums) {
		int low = 0, mid = 0;
		int high = nums.length - 1;
		
		while(mid <= high) {
			if(nums[mid] == 0) {
				swap(nums, low, mid);
				low++;
				mid++;
			}else if(nums[mid] == 1) {
				mid++;
			}else {
				swap(nums, mid, high);
				high--;
			}
		}
		
	}
	
//    static void sortColors(int[] nums) {
//    	int i = 0;
//        int j = nums.length - 1;
//        
//        while(i < j) {
//        	while(i < j && nums[i] == 0) 
//        		i++;
//        	
//        	while(i < j && nums[j] != 0)
//        		j--;
//        	
//        	if(i < j) {
//        		swap(nums, i, j);
//            	i++; j--;
//        	}
//        }
//        
//        j = nums.length - 1;
//        while(i < j) {
//        	while(i < j && nums[i] <= 1)
//        		i++;
//        	
//        	while(i < j && nums[j] != 1)
//        		j--;
//        	
//        	swap(nums, i, j);        	
//        	i++; j--;
//        }
//    }
    
    static void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }

}
