package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class MaxSumNonConsOptDP {

	public static void main(String[] args) {
		int[] items = new int[] {10,5,15,20};
		System.out.println("O/P : " + MaxSumNonConsDP.sum(items, items.length));
	}

	public static int sum(int[] items, int n) {
		
		int prev_prev = items[0];
		int prev = Math.max(items[0], items[1]);
		int res = prev;
		
		for (int i = 3; i <= n; i++) {
			res = Math.max(prev_prev, items[i-1] + prev) ;
			prev_prev = prev;
			prev = res;
		}

		return res;
	}
}
