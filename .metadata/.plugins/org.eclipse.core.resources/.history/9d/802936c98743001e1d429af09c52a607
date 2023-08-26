package com.msb.lrg.ds.bitwise;

public class CountSetBit {

	public static void main(String[] args) {
		int num = 241;
		System.out.println(num + " Binary : " + Integer.toBinaryString(num));
		System.out.println(num + "  Naive : " + CountSetBit.countNaive(num));
		System.out.println(num + "    Bit : " + CountSetBit.countUsingSetBit(num));
		System.out.println(num + "    Bit : " + CountSetBit.countUsingBit(num));
	}

	public static int countNaive(int num) {
		int count = 0;
		while(num > 0) {
			count = count + (num&1);
			num = num/2;
		}
		return count;
	}

	public static int countUsingSetBit(int num) {
		//Brian Kennigham Algorithm
		int count = 0;
		while(num > 0) {
			count++;
			num = num&(num-1);
		}
		return count;
	}

	public static int countUsingBit(int num) {
		int[] tbl = new int[256];
		tbl[0] = 0;
		for (int i = 1; i < tbl.length; i++) {
			tbl[i] = tbl[i&(i-1)] + 1;
		}
		return tbl[num%255]
				+ tbl[(num>>8)%255]
				+ tbl[(num>>16)%255]
				+ tbl[num>>24];
	}
}
