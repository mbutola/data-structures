package com.msb.lrg.problems.hackerrank;

import java.util.List;

/*

	Given an array of integers, find the longest subarray where the absolute difference between any 
	two elements is less than or equal to .
	Example
		There are two subarrays meeting the criterion:  and . The maximum length subarray has  elements.
	Function Description
		Complete the pickingNumbers function in the editor below.
		pickingNumbers has the following parameter(s):
			int a[n]: an array of integers
		Returns
			int: the length of the longest subarray that meets the criterion
	Input Format
		The first line contains a single integer , the size of the array .
		The second line contains  space-separated integers, each an .
	Constraints
		The answer will be .
	Sample Input 0
		6
		4 6 5 3 3 1
	Sample Output 0
		3

 */
public class H024PickingNumber {

	public static void main(String[] args) {
		List<Integer> a = List.of(1,1,2,2,4,4,5,5,5);
		int res = pickingNumbers(a);
		System.out.println(res);
	}

    public static int pickingNumbers(List<Integer> a) {
        int max = Integer.MIN_VALUE;
        int[] freq = new int[101];
        
        for(int i : a){
            freq[i]++;
        }
        
        for(int i = 0; i < 100; i++){
            max = Math.max(max, freq[i] + freq[i + 1]);
        }
        
        return max;
    }

}
