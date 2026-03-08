package com.msb.lrg.problems.practice.package5;

import java.util.Arrays;

/*

Beautiful Arrangement :: LeetCode (526, medium)
	Suppose you have n integers labeled 1 through n. A permutation of those n integers perm 
	(1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the 
	following is true:
		perm[i] is divisible by i.
		i is divisible by perm[i].
	Given an integer n, return the number of the beautiful arrangements that you can construct.
	Example 1:
		Input: n = 2
		Output: 2
		Explanation: 
			The first beautiful arrangement is [1,2]:
			    - perm[1] = 1 is divisible by i = 1
			    - perm[2] = 2 is divisible by i = 2
			The second beautiful arrangement is [2,1]:
			    - perm[1] = 2 is divisible by i = 1
			    - i = 2 is divisible by perm[2] = 1
	Example 2:
		Input: n = 1
		Output: 1
	Constraints:
		1 <= n <= 15

 */
public class Problem54 {

	public static void main(String[] args) {
		int n = 2;
		int res = countArrangement(n);
		System.out.println("Result :: " + res);
	}

    public static int countArrangement(int n) {
    	int[] arr = new int[n];
    	for(int i = 0; i < n; i++) {
    		arr[i] = i + 1;
    	}
    	
    	return solve(arr, 0);
    }
    
    public static int solve(int[] arr, int index) {
    	
    	if(index == arr.length)
    		return 1;
    	
    	int count = 0;
    	for(int i = index; i < arr.length; i++) {
    		swap(arr, index, i);
    		if(arr[index] % (index + 1) == 0 || (index + 1) % arr[index] == 0)
    			count += solve(arr, index + 1);
    		swap(arr, i, index);
    	}
    	return count;
    }
    
    public static void swap(int[] arr, int i, int j) {
    	int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }
    
}
