package com.msb.lrg.ds.dp;

import java.util.Arrays;
import java.util.List;

import com.msb.lrg.ds.Utility;

public class ZeroOneKnapsackDPBU {

	static int[][] dp;
			
	public static void main(String[] args) {
		int[] val = new int[] {10,40,30,50};
		int[] wt = new int[] {5,4,6,3};
		int W = 10;
		dp = new int[wt.length+1][W+1];

		initializeDp(dp);
		int result = ZeroOneKnapsackDPBU.knapSack(val, wt, W, wt.length);

		Utility.printDp(Utility.getList(wt), Utility.getList(W), dp);
		System.out.println("O/P : " + result);
	}

	public static int knapSack(int[] val, int[] wt, int W, int n) {
		if(W == 0 || n == 0) {
			return 0;
		}
		
		if(dp[n][W] != -1) {
			return dp[n][W];
		}
		
		if(W >= wt[n-1]) {
			return dp[n][W] = Math.max( val[n-1] + knapSack(val, wt, W - wt[n-1], n-1),
											knapSack(val, wt, W, n-1));
		}else{
			return dp[n][W] = knapSack(val, wt, W, n-1);
		}
	}

	public static void initializeDp(int[][] dp) {
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
	}

}
