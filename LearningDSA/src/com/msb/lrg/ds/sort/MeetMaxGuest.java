package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class MeetMaxGuest {

	public static void main(String[] args) {
		int[] in = new int[]{900,600,700};
		int[] out = new int[]{1000,800,730};
		System.out.println("O/P : " + MeetMaxGuest.maxCount(in, out));
	}
	
	public static int maxCount(int[] in, int[] out) {
		
		int res = 0, curr = 0;
		Arrays.sort(in);
		Arrays.sort(out);
		
		int i = 0, j = 0;
		while (i < in.length) {
			if(in[i] <= out[j]) {
				curr++; i++;
			}else {
				curr--; j++;
			}
			res = Math.max(res, curr);
		}
		return res;
	}

}
