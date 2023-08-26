package com.msb.lrg.ds.dp;

public class MinCoinRecursive {

	public static void main(String[] args) {
		int[] coins = new int[] {25,5,10};
		int sum = 30;
		System.out.println("O/P : " + MinCoinRecursive.getCoin(coins, sum));
	}
	
	public static int getCoin(int[] coins, int sum) {
		
		if(sum == 0)
			return 0;
		
		int res = Integer.MAX_VALUE;
		
		for (int i = 0; i < coins.length; i++) {
			if(sum >= coins[i]) {
				int sub_res = getCoin(coins, sum-coins[i]);
				if(sub_res != Integer.MAX_VALUE) {
					res = Math.min(res, sub_res+1);
				}
			}
		}		
		return res;
	}

}
