package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class FibonacciMemo {

	static int num = 6;
	static int[] memo = new int[num+1];
	
	static {
		for (int i = 0; i < memo.length; i++) {
			memo[i] = -1;
		}
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(memo));
		System.out.println("Fibonacci 0f (" + num + ") : " + FibonacciMemo.fibonacci(num));
		System.out.println(Arrays.toString(memo));
	}
	
	public static int fibonacci(int n) {

		if(memo[n] != -1) 
			return memo[n];

		if(n <= 1) {
			memo[n] = n;
		}else{
			memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
		return memo[n];
	}

}
