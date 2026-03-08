package com.msb.lrg.problems.practice.package5;

import java.util.Arrays;

/*

Beautiful Arrangement II :: LeetCode (667, medium)
	Given two integers n and k, construct a list answer that contains n different positive integers 
	ranging from 1 to n and obeys the following requirement:
	Suppose this list is answer = [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, 
	|a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
	Return the list answer. If there multiple valid answers, return any of them.
	Example 1:
		Input: n = 3, k = 1
		Output: [1,2,3]
		Explanation: The [1,2,3] has three different positive integers ranging 
		from 1 to 3, and the [1,1] has exactly 1 distinct integer: 1
	Example 2:
		Input: n = 3, k = 2
		Output: [1,3,2]
		Explanation: The [1,3,2] has three different positive integers ranging 
		from 1 to 3, and the [2,1] has exactly 2 distinct integers: 1 and 2.
	Constraints:
		1 <= k < n <= 104

 */
public class Problem541 {

	public static void main(String[] args) {
		int n = 3;
		int k = 1;
		int[] res = constructArray(n, k);
		System.out.println(Arrays.toString(res));
	}

    public static int[] constructArray(int n, int k) {
    	int[] res = new int[n];
    	int idx = 0;
    	int low = 1;
    	int high = k + 1;
    	
    	while(low <= high) {
    		res[idx++] = low++;
    		if(low <= high)
    			res[idx++] = high--;
    	}
    	
    	int i = k + 2;
    	while(i <= n) 
    		res[idx++] = i++;
    	
    	return res;
    }
	
//    public static int[] constructArray(int n, int k) {
//    	int[] in = new int[n];
//    	int[] res = new int[n];
//    	
//    	for(int i = 0; i < n; i++) {
//    		in[i] = i + 1;
//    	}
//        
//    	for(int i = 0; i <= k; i++) {
//    		if(i % 2 == 0)
//    			res[i] = in[i / 2];
//    		else
//    			res[i] = in[k - i / 2];
//    	}
//    		
//    	int i = k + 1;
//    	while(i < n) {
//    		res[i] = in[i];
//    		i++;
//    	}
//    	
//    	return res;
//    }
}
