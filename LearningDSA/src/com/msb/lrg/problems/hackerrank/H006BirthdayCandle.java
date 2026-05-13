package com.msb.lrg.problems.hackerrank;

import java.util.Collections;
import java.util.List;

/*

	You are in charge of the cake for a child's birthday. It will have one candle for each year of 
	their total age. They will only be able to blow out the tallest of the candles. Your task is 
	to count how many candles are the tallest.
	Example
		The tallest candles are 4 units high. There are 2 candles with this height, so the function should return 2.
	Function Description
		Complete the function  with the following parameter(s):
		: the candle heights
	Returns
		: the number of candles that are tallest
	Input Format
		The first line contains a single integer, , the size of .
		The second line contains  space-separated integers, where each integer  describes the height of .
	Sample Input 0
		4
		3 2 1 3
	Sample Output 0
		2

 */
public class H006BirthdayCandle {

	public static void main(String[] args) {
		List<Integer> candles = List.of(3,2,1,3);
		int res = birthdayCakeCandles(candles);
		System.out.println(res);
	}

    public static int birthdayCakeCandles(List<Integer> candles) {
    	int max = Integer.MIN_VALUE;
    	int count = 0;
    	for(int i : candles) {
    		if(i > max) {
    			max = i;
    			count = 1;
    		}else if( i == max) {
    			count++;
    		}
    	}
    	return count;
    }

    /*
     * Complexity is O(n log n)
     */
//    public static int birthdayCakeCandles(List<Integer> candles) {
//    	int max = Collections.max(candles);
//    	return (int)candles.stream()
//    					.filter(c -> c == max)
//    					.count();
//    }
}
