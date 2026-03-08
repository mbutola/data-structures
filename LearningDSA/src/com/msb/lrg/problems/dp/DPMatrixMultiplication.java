package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*

What is Matrix Chain Multiplication?
	You are given a sequence (chain) of matrices and you must find the most efficient way to multiply them.
	⚠️ Important
		We are NOT changing the order of matrices
		We are choosing where to put parentheses
	Goal: Minimize total scalar multiplications
	🧩 Problem Setup
		Suppose you have matrices:
			A1: 10 × 30
			A2: 30 × 5
			A3: 5 × 60
		Possible ways:
			(A1 × A2) × A3
			A1 × (A2 × A3)
	👉 Both give same result matrix, but cost is different.
		📊 Cost Formula
			If:
				A = p × q
				B = q × r
			Then multiplication cost:
				p × q × r
	🎯 Goal of MCM
	Find the minimum number of scalar multiplications needed to multiply all matrices.

 */

public class DPMatrixMultiplication {

	static int[][] dp;
	
	public static void main(String[] args) {
		
		int res;
		int[] in = {1,2,3,4,3};
		res = recursive(in, 1, in.length-1);
		System.out.println("Recirsive :: " + res);

		dp = new int[in.length+1][in.length+1];
		for(int i=0; i<=in.length; i++)
			Arrays.fill(dp[i], -1);
		res = bottomUp(in, 1, in.length-1);
		System.out.println("Bottom up :: " + res);
		
	}
	
	static boolean isPalindrome(String s, int i, int j) {
		String temp = s.substring(i, j+1);
		return temp.equals(new StringBuffer(temp).reverse().toString());
	}
	
	static int recursive(int[] in, int i, int j) {
		
		if(i>=j)
			return 0;
		
		int min = Integer.MAX_VALUE;
		for(int k=i; k<j; k++) {
			
			int temp  = recursive(in, i, k) 
						+ recursive(in, k+1, j)
						+ in[i-1]*in[k]*in[j];
			
			min = Math.min(min, temp);
		}
		return min;
	}

	static int bottomUp(int[] in, int i, int j) {
		
		if(i>=j)
			return 0;
		
		if(dp[i][j] != -1)
			return dp[i][j];
		
		dp[i][j] = Integer.MAX_VALUE;
		for(int k=i; k<j; k++) {
			
			int temp  = bottomUp(in, i, k) 
						+ bottomUp(in, k+1, j)
						+ in[i-1]*in[k]*in[j];
			
			dp[i][j] = Math.min(dp[i][j], temp);
		}
//		System.out.println(i + ", " + j);
//		DPKnapsack.print2DArray(dp);
		return dp[i][j];
	}
}
