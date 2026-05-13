package com.msb.lrg.problems.hackerrank;

import java.util.List;

/*

	Given an array of integers, calculate the ratios of its elements that are , , and . Print the 
	decimal value of each fraction on a new line with 6 places after the decimal.
	Note: This challenge introduces precision problems. The test cases are scaled to six decimal places, 
	though answers with absolute error of up to  are acceptable.
	Example
		There are  elements: two positive, two negative and one zero. Their ratios are ,  and . Results are printed as:
			0.400000
			0.400000
			0.200000
	Function Description
		Complete the  function with the following parameter(s):
			: an array of integers
		Print
			Print the ratios of positive, negative and zero values in the array. Each value should be printed on a separate line with  digits after the decimal. The function should not return a value.
	Input Format
		The first line contains an integer, , the size of the array.
		The second line contains  space-separated integers that describe .
	Sample Input
		STDIN           Function
		-----           --------
		6               arr[] size n = 6
		-4 3 -9 0 4 1   arr = [-4, 3, -9, 0, 4, 1]
	Sample Output
		0.500000
		0.333333
		0.166667

 */
public class H003PlusMinus {

	public static void main(String[] args) {
		List<Integer> arr = List.of(-4, 3, -9, 0, 4, 1);
		plusMinus(arr);
	}

    public static void plusMinus(List<Integer> arr) {
    	int plus = 0, minus = 0, zero = 0;
    	int n = arr.size();
    	
    	for(int i : arr) {
    		if(i > 0)
    			plus++;
    		else if(i < 0)
    			minus++;
    		else
    			zero++;
    	}
		System.out.printf("%.6f%n", (float)plus/n);
		System.out.printf("%.6f%n", (float)minus/n);
		System.out.printf("%.6f%n", (float)zero/n);
    }

}
