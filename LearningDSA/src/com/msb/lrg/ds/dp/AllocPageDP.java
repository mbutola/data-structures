package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class AllocPageDP {

	public static void main(String[] args) {
		int[] pages = new int[] {10,20,30,40};
		int k = 2;
		System.out.println("O/P : " + AllocPageDP.allocPage(pages, k));
	}

	public static int allocPage(int[] pages, int k) {
		
		int n = pages.length;
		int[][] dp = new int[k+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			dp[1][i] = Utility.sumPages(pages, 0, i-1);
		}

		for (int i = 2; i <= k; i++) {
			dp[i][1] = pages[0];
		}

		for (int i = 2; i <=k; i++) {
			for (int j = 2; j <=n ; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int p = 1; p < j; p++) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][p],
														Utility.sumPages(pages, p, j-1)));
				}
			}
		}
		
		Utility.printDp(Utility.getList(n), Utility.getList(n), dp);
		return dp[k][n];
		
	}
}
