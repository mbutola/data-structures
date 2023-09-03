package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class AllocPageRecursive {

	public static void main(String[] args) {
		int[] pages = new int[] {10,20,30,40};
		int k = 2;
		System.out.println("O/P : " + AllocPageRecursive.allocPage(pages, pages.length, k));
	}

	public static int allocPage(int[] pages, int n, int k) {
		
		if(k==1)
			return Utility.sumPages(pages, 0, n-1);
		
		if(n == 1)
			return pages[0];
		
		int res = Integer.MAX_VALUE;
		
		for (int i = 1; i < n; i++) {
			res = Math.min(res, Math.max(allocPage(pages, i, k-1),
											Utility.sumPages(pages, i, n-1)));
		}
		
		return res;
	}
	
}
