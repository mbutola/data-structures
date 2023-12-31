package com.msb.lrg.ds.dp;

import com.msb.lrg.ds.Utility;

public class MtxMultDP {

	public static void main(String[] args) {
		int[] matrixes = new int[] {10,20,40,30,50};
		System.out.println("O/P : " + MtxMultDP.multiplications(matrixes, matrixes.length));
	}

	public static int multiplications(int[] mat, int n) {
		
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}
		
		for (int i = 0; i < n-1; i++) {
			dp[i][i+1] = 0;
		}

		for (int gap = 2; gap < n; gap++) {
			for (int i = 0; i+gap < n; i++) {
				int j = i+gap;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i+1; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], 
										dp[i][k]
											+ dp[k][j]
											+ mat[i]*mat[k]*mat[j]);
				}
			}
		}
		
		Utility.printDp(Utility.getList(n), Utility.getList(n), dp);
		return dp[0][n-1];
		
	}
}
