package com.msb.lrg.ds.bitwise;

import java.util.HashSet;
import java.util.Set;

public class TwoOdd {

	public static void main(String[] args) {
		int[] in = {5,6,10,3,10,6,3,3};
		TwoOdd.naive1(in);
		TwoOdd.usingBit(in);
		
//		int num = 24;
//		System.out.println("      "+num+" : " + Integer.toBinaryString(num));
//		System.out.println("      "+(num-1)+" : " + Integer.toBinaryString(num-1));
//		System.out.println(" "+num+" & "+(num-1)+" : " + Integer.toBinaryString(num & (num-1)));
//		System.out.println(" "+num+" & "+(num-1)+" : " + (num & (num-1)));
//		System.out.println(""+num+" & ~"+(num-1)+" : " + Integer.toBinaryString(num & ~(num-1)));
	}
	
	public static void usingBit(int[] in) {
		int res=0;
		for (int i = 0; i < in.length; i++) {
			res = res^in[i];
		}
		
		int k = res & ~(res-1);
		int res1 = 0, res2 =0;
		
		for (int i = 0; i < in.length; i++) {
			if((in[i] & k) != 0) 
				res1 = in[i]^res1;
			else 
				res2 = in[i]^res2;
		}

		System.out.println("  Bit Solution : [" + res1 + ", " + res2 + "]");
	}

	public static void naive1(int[] in) {
		Set<Integer> sol = new HashSet<>();
		for (int i = 0; i < in.length; i++) {
			int count = 0;
			for (int j = 0; j < in.length; j++) {
				if(in[i] == in[j])
					count++;
			}
			if(count%2 != 0)
				sol.add(in[i]);
		}
		System.out.println("Naive solution : " + sol.toString());
	}	
}
