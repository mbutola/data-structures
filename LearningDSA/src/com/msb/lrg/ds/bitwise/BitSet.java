package com.msb.lrg.ds.bitwise;

public class BitSet {

	public static void main(String[] args) {
		int num = 5;
		int k = 3;
		System.out.println("num:"+num+", k:"+k+" is set :" + BitSet.checkBit1(num, k));
		System.out.println("num:"+num+", k:"+k+" is set :" + BitSet.checkBit2(num, k));
		System.out.println("num:"+num+", k:"+k+" is set :" + BitSet.checkNaiveMul(num, k));
		System.out.println("num:"+num+", k:"+k+" is set :" + BitSet.checkNaiveDiv(num, k));
	}

	public static boolean checkBit1(int num, int k) {
		int c = 1 << (k-1);
		if((num & c) != 0) {
			return true;
		}
		return false;
	}
	
	public static boolean checkBit2(int num, int k) {
		num = num >> (k-1);
		if((num & 1) != 0) {
			return true;
		}
		return false;
	}

	public static boolean checkNaiveMul(int num, int k) {
		int c = 1;
		for (int i = 0; i < (k-1); i++) {
			c = c*2;
		}
		
		if((num & c) != 0) {
			return true;
		}
		return false;
		
	}	

	public static boolean checkNaiveDiv(int num, int k) {
		for (int i = 0; i < (k-1); i++) {
			num = num/2;
		}
		
		if((num & 1) != 0) {
			return true;
		}
		return false;
	}	
}
