package com.msb.lrg.ds.dp;

public class MaxSumNonConsRecursive {

	public static void main(String[] args) {
		int[] items = new int[] {10,5,15,20};
		System.out.println("O/P : " + MaxSumNonConsRecursive.sum(items, items.length-1));
	}

	public static int sum(int[] items, int n) {
		
		if(n == 0)
			return items[0];
		
		if(n == 1)
			return Math.max(items[0],items[1]);

		int res =  Math.max(sum(items, n-1),
							items[n-1]) + sum(items, n-2) ;
		
		return res; 
	}
}
