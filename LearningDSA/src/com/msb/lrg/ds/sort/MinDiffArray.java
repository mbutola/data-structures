package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class MinDiffArray {

	public static void main(String[] args) {
		int[] a = new int[]{10,8,1,4};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + MinDiffArray.getMinDiff(a));
	}
	
	public static int getMinDiff(int[] a) {
	
		int res = Integer.MAX_VALUE;
		Arrays.sort(a);
		for (int i = 1; i < a.length; i++) {
			res = Math.min(res, a[i] - a[i-1]);
		}
		return res;
	}

}
