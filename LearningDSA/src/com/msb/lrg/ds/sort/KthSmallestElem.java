package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class KthSmallestElem {

	public static void main(String[] args) {
		int k = 5;
		int[] a = new int[]{10,4,5,8,11,6,26};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + KthSmallestElem.solve(a, k));
		Arrays.sort(a);
		System.out.println("I/P : " + Arrays.toString(a));
	}
	
	public static int solve(int[] a, int k) {
		int low = 0;
		int high = a.length - 1;
		while(low <= high) {
			int p = LomutoPartition.lomutoPartition(a, low, high);
			if(p == k-1)
				return a[p];
			else if (p > k-1)
				high = p-1;
			else
				low = p+1;
		}
		return -1;
	}

}
