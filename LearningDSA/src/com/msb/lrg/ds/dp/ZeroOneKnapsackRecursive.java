package com.msb.lrg.ds.dp;

public class ZeroOneKnapsackRecursive {

	public static void main(String[] args) {
		int[] val = new int[] {10,40,30,50};
		int[] wt = new int[] {5,4,6,3};
		int W = 10;
		System.out.println("O/P : " + ZeroOneKnapsackRecursive.knapSack(val, wt, W, wt.length));
	}
	
	public static int knapSack(int[] val, int[] wt, int W, int n) {
		
		if(W == 0 || n == 0)
			return 0;
		
		if(wt[n-1] > W) {
			return knapSack(val, wt, W, n-1);
		}else {
			return Math.max(val[n-1] + knapSack(val, wt, W-wt[n-1], n-1),
								knapSack(val, wt, W, n-1));
		}
		
	}

}
