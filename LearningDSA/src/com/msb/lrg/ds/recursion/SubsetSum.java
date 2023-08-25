package com.msb.lrg.ds.recursion;

public class SubsetSum {

	public static int total = 11;
	public static int[] in = new int[]{10,5,2,3,6,11};
	
	public static void main(String[] args) {
		System.out.println("Exists : " + SubsetSum.solve(0,0));
	}
	
	public static int solve(int i, int sum) {
		if (i == in.length)
			return (sum == total)? 1:0;
		
		return solve(i+1, sum) + 
					solve(i+1, in[i] + sum);
	}	

}
