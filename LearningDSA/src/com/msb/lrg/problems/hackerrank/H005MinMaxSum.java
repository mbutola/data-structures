package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.List;

/*

	Given five positive integers, find the minimum and maximum values that can be calculated by 
	summing exactly four of the five integers. Then print the respective minimum and maximum values 
	as a single line of two space-separated long integers.
	Example
		The minimum sum is  and the maximum sum is . The function prints
			16 24
	Function Description
		Complete the  function with the following parameter(s):
			: an array of  integers
	Print
		Print two space-separated integers on one line: the minimum sum and the maximum sum of  
		of  elements.No value should be returned.
		Note For some languages, like C, C++, and Java, the sums may require that you use a long 
		integer due to their size.
	Input Format
		A single line of five space-separated integers.
	Sample Input
		1 2 3 4 5
	Sample Output
		10 14

 */
public class H005MinMaxSum {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
		miniMaxSum(arr);
	}

    public static void miniMaxSum(List<Integer> arr) {
    	long sum = 0;
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	
    	for(int num : arr) {
    		sum += num;
    		min = Math.min(min, num);
    		max = Math.max(max, num);
    	}
    	
    	System.out.println((sum - max) + " " + (sum - min));
    }

}
