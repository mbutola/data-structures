package com.msb.lrg.ds.dp;

import java.util.Arrays;
import java.util.List;

import com.msb.lrg.ds.Utility;

public class ZeroOneKnapsackDP {

	public static void main(String[] args) {
		int[] val = new int[] {10,40,30,50};
		int[] wt = new int[] {5,4,6,3};
		int W = 10;
		System.out.println("O/P : " + ZeroOneKnapsackDP.knapSack(val, wt, W, wt.length));
	}
	
	public static String[] getArray(List<String> lis){
		String[] a = new String[lis.size()];
		for (int i = 0; i < lis.size(); i++) {
			a[i] = lis.get(i);
		}
		return a;
	}

	public static int knapSack(int[] val, int[] wt, int W, int n) {
		
		int dp[][] = new int[n+1][W+1];
		
		for (int i=0; i<=W; i++) {
			dp[0][i] = 0;
		}
		
		for (int i=1; i<=n; i++) {
			dp[i][0] = 0;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= W; j++) {
				if(wt[i-1] > j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], val[i-1] + dp[i-1][j-wt[i-1]]);
				}
			}
		}
		
		Utility.printDp(Utility.getList(wt), Utility.getList(W), dp);
		return dp[n][W];
	}

}
