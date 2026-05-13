package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.CertPathTrustManagerParameters;

/*

	Given an array of integers and a positive integer , determine the number of  pairs where  and  +  is divisible by .
	Example
		Three pairs meet the criteria:  and .
	Function Description
		Complete the divisibleSumPairs function in the editor below.
		divisibleSumPairs has the following parameter(s):
			int n: the length of array 
			int ar[n]: an array of integers
			int k: the integer divisor
	Returns
		- int: the number of pairs
	Input Format
		The first line contains  space-separated integers,  and .
		The second line contains  space-separated integers, each a value of .
	Sample Input
		STDIN           Function
		-----           --------
		6 3             n = 6, k = 3
		1 3 2 6 1 2     ar = [1, 3, 2, 6, 1, 2]
	Sample Output
		 5

 */
public class H014DivisibleSumPair {

	public static void main(String[] args) {
		List<Integer> ar = Arrays.asList(1,2,3,4,1,9);
		int n = ar.size();
		int k = 5;
		int res = divisibleSumPairs(n, k, ar);
		System.out.println(res);
	}

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
    	int[] freq = new int[k];
    	int count = 0;
    	for(int i = 0; i < ar.size(); i++) {
    		int remainder = ar.get(i) % k;
    		int compliment = (k - remainder) % k;
    		
    		count += freq[compliment];
    		freq[remainder]++;
    	}
    	return count;
    }

//	public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
//    	int count = 0;
//    	for(int i = 0; i < ar.size() - 1; i++) {
//    		for(int j = i + 1; j < ar.size(); j++) {
//    			if((ar.get(i) + ar.get(j)) % k == 0) count++;
//    		}
//    	}
//    	return count;
//    }

}
