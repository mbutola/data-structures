package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.List;

/*

	Given a square matrix, calculate the absolute difference between the sums of its diagonals.
	For example, the square matrix  is shown below:
			1 2 3
			4 5 6
			9 8 9  
		The left-to-right diagonal = .
		The right-to-left diagonal = .
		Their absolute difference is .
	Function description
		Complete the  function with the following parameter:
			: a 2-D array of integers
		Return
			: the absolute difference in sums along the diagonals
	Input Format
	The first line contains a single integer, , the number of rows and columns in the square matrix .
	Each of the next  lines describes a row, , and consists of  space-separated integers .
	Sample Input
		STDIN      Function
		-----      --------
		3           arr[][] sizes n = 3, m = 3
		11 2 4     arr = [[11, 2, 4], [4, 5, 6], [10, 8, -12]]
		4 5 6
		10 8 -12
	Sample Output
		15

 */
public class H002DiagnalDifference {

	public static void main(String[] args) {
		List<List<Integer>> arr = Arrays.asList(
											Arrays.asList(1,2,3),
											Arrays.asList(4,5,6),
											Arrays.asList(9,8,9));
		
		int res = diagonalDifference(arr);
		System.out.println(res);
	}

    public static int diagonalDifference(List<List<Integer>> arr) {
    	int lSum = 0, rSum = 0;
    	int n = arr.size();
    	for(int i = 0; i < n; i++) {
    		lSum += arr.get(i).get(i);
    		rSum += arr.get(n - 1 - i).get(i);
    	}
    	return Math.abs(lSum - rSum);
    }

}
