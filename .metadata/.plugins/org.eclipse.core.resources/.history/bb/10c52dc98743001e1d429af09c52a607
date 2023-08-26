package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class FibonacciTab {

	static int num = 6;
	static int[] memo = new int[num+1];
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(memo));
		System.out.println("Fibonacci of (" + num + ") : " + FibonacciTab.fibonacci(num));
		System.out.println(Arrays.toString(memo));
	}
	
	public static int fibonacci(int n) {

		memo[0] = 0; 
		memo[1] = 1;

		for (int i = 2; i < memo.length; i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}
		return memo[n];
	}
}
