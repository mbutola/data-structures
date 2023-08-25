package com.msb.lrg.ds.dp;

import java.util.Arrays;
import java.util.List;

public class CoinRecursive {

	public static List<Integer> coins = Arrays.asList(2,4,6);

	public static void main(String[] args) {
		int sum = 10;
		System.out.println("O/P : " + CoinRecursive.solve(coins.size(), sum));
	}
	
	public static int solve(int n, int sum) {
		
		if(sum == 0)
			return 1;

		if(n == 0 || sum < 0)
			return 0;
		
		return solve(n, sum-coins.get(n-1)) +
				solve(n-1, sum);
		
	}

}
