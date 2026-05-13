package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

	We define subsequence as any subset of an array. We define a subarray as a contiguous subsequence 
	in an array.
	Given an array, find the maximum possible sum among:
		all nonempty subarrays.
		all nonempty subsequences.
		Print the two values as space-separated integers on one line.
		Note that empty subarrays/subsequences should not be considered.
	Example
		The maximum subarray sum is comprised of elements at inidices . Their sum is . The maximum 
		subsequence sum is comprised of elements at indices  and their sum is .
	Function Description
		Complete the maxSubarray function in the editor below.
		maxSubarray has the following parameter(s):
			int arr[n]: an array of integers
		Returns
			int[2]: the maximum subarray and subsequence sums
	Input Format
		The first line of input contains a single integer , the number of test cases.
		The first line of each test case contains a single integer .
		The second line contains  space-separated integers  where .
	Constraints
		The subarray and subsequences you consider should have at least one element.
	Sample Input 0
		2
		4
		1 2 3 4
		6
		2 -1 2 3 4 -5
	Sample Output 0
		10 10
		10 11

 */
public class F001MaximumSubarray {

	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubarray(nums);
        System.out.println(res);

        List<Integer> arr = Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        List<Integer> resList = maxSubArray(arr);
        System.out.println(resList);
    }

    public static List<Integer> maxSubArray(List<Integer> arr) {

    	int maxSubarray = Integer.MIN_VALUE;
    	int maxSubSequence = 0;
    	int currSum = 0;
    	int maxElement = Integer.MIN_VALUE;;
    	
    	for(int num : arr) {
    		currSum += num;
    		
    		maxSubarray = Math.max(maxSubarray, currSum);
    		if(num > 0)    	
    			maxSubSequence += num;
    		maxElement = Math.max(maxElement, num);
    		
    		if(currSum < 0) {
    			currSum = 0;
    		}
    	}
    	
    	if(maxSubSequence == 0)
    		maxSubSequence = maxElement;
    	
    	return Arrays.asList(maxSubarray, maxSubSequence);
    	
    }
    
    public static int maxSubarray(int[] nums) {
    	int max = nums[0];
    	int currSum = nums[0];
    	
    	for(int i = 0; i < nums.length; i++) {
    		currSum = Math.max(nums[i], currSum + nums[i]);
    		max = Math.max(max, currSum);
    	}
    	
    	return max;
    }

    public static int maxSubarray2(int[] nums) {
    	int max = Integer.MIN_VALUE;
    	int currSum = 0;
    	
    	for(int num : nums) {
    		currSum += num;
    		
    		max = Math.max(max, currSum);
    		
    		if(currSum < 0) {
    			currSum = 0;
    		}
    	}
    	
    	return max;
    }

}
