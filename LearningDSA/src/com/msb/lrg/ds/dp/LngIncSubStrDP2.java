package com.msb.lrg.ds.dp;

import java.util.Arrays;

public class LngIncSubStrDP2 {

	public static void main(String[] args) {
		int[] lis = new int[]{3,10,20,4,12,6,7};
		System.out.println("O/P : " + LngIncSubStrDP2.lis(lis, lis.length));
	}
	
	public static int lis(int[] lis, int n) {
		
		int[] dp = new int[6];
		dp[0] = lis[0];
		int len = 1;
		for (int i=1; i < lis.length; i++) {
//			System.out.println(Arrays.toString(dp));
//			System.out.println(lis[i] + ":" + len);
			if(lis[i]>dp[len-1]) {
				dp[len++]=lis[i];
			}else {
				int ci = LngIncSubStrDP2.ceilIndx(dp, lis[i], 0, len-1);
				dp[ci] = lis[i];
			}
		}
		System.out.println(Arrays.toString(dp));
		return len;
	}
	
	public static int ceilIndx(int[] dp, int val, int l, int r) {
		while(r>l) { 
			int i = l + (r-l)/2;
			if(dp[i] >= val)
				r=i;
			else
				l=i+1;
		}	
		return r;
	}

}
