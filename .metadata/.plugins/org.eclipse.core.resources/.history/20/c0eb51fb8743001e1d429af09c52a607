package com.msb.lrg.ds.recursion;

public class Factorial {

	public static void main(String[] args) {
		System.out.println("Simple : " + Factorial.calculate(5));
		System.out.println("Simple : " + Factorial.calculateTailRecursive(5, 1));
	}

	public static int calculate(int n) {
		if(n == 0)
			return 1;
		
		return n*calculate(n-1);
	}

	public static int calculateTailRecursive(int n, int value) {
		if(n==0 || n==1)
			return value;
		
		return calculateTailRecursive(n-1, n*value);
	}
}
