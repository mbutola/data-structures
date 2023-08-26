package com.msb.lrg.ds.dp;

public class MinJumpsRecursive {

	public static void main(String[] args) {
		int[] jumps = new int[] {3,4,2,1,2,1};
		System.out.println("O/P : " + MinJumpsRecursive.getJumps(jumps, jumps.length));
	}
	
	public static int getJumps(int[] jumps, int n) {
		
		if(n==1)
			return 0;
		
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < n-2; i++) {
			if(i+jumps[i] >= n-1) {
				int sub_res = getJumps(jumps, i+1);
				if(sub_res != Integer.MAX_VALUE)
					res = Math.min(res, sub_res+1);
			}
		}
		return res;
	}

}
