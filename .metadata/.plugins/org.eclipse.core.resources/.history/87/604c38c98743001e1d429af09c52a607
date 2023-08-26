package com.msb.lrg.ds.bitwise;

public class OneOdd {

	public static void main(String[] args) {
		int[] in = {5,6,10,6,10,5,3};
		OneOdd.naive1(in);
		OneOdd.usingBit(in);
	}
	
	public static void usingBit(int[] in) {
		int res=0;
		for (int i = 0; i < in.length; i++) {
			res = res^in[i];
		}
		System.out.println("  Bit Solution : " + res);
	}

	public static void naive1(int[] in) {
		for (int i = 0; i < in.length; i++) {
			int count = 0;
			for (int j = 0; j < in.length; j++) {
				if(in[i] == in[j])
					count++;
			}
			if(count%2 != 0) {
				System.out.println("Naive solution : " + in[i]);
				break;
			}
		}
	}	
}
