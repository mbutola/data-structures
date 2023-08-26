package com.msb.lrg.ds.bitwise;

public class PowerSet {

	public static void main(String[] args) {
		PowerSet.printUsingBit("abc");
	}
	
	public static void printUsingBit(String s) {
		int n = s.length();
		int pSize = 1<<n;
		char[] a = s.toCharArray();

		System.out.println(pSize);
		for (int i = 0; i < pSize; i++) {
			for (int j = 0; j < n; j++) {
				if((i&(1<<j)) != 0) {
					System.out.print(a[j]);
				}
			}
			System.out.println("");
		}
	}

}

