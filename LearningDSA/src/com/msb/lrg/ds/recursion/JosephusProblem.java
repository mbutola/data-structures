package com.msb.lrg.ds.recursion;

public class JosephusProblem {

	public static void main(String[] args) {
		System.out.println("Person alive : " + JosephusProblem.solve(1, 3));
	}
	
	public static int solve(int n, int k) {
		if(n == 1)
			return 0;
		return (solve(n-1, k) + k) % n;
	}

}
