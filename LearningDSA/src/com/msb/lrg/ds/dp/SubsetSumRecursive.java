package com.msb.lrg.ds.dp;

public class SubsetSumRecursive {

	public static void main(String[] args) {
		int[] arr = new int[] {10,5,2,6,3};
		int sum = 8;
		System.out.println("O/P : " + SubsetSumRecursive.subSum(arr, sum, arr.length));
	}
	
	public static int subSum(int[] arr, int sum, int n) {
		
		if(n == 0)
			return (sum == 0) ? 1 : 0;
		
		return subSum(arr, sum - arr[n-1], n-1) 
				+ subSum(arr, sum, n-1);
		
	}

}
