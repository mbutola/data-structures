package com.msb.lrg.ds.bitwise;

public class PowerOfTwo {

	public static void main(String[] args) {
		int num = 2;
		System.out.println(num + " Naive : " + PowerOfTwo.checkNaive(num));
		System.out.println(num + "   Bit : " + PowerOfTwo.checkUsingBit(num));
	}
	
	public static boolean checkNaive(int num) {
		if(num == 0)
			return false;
		
		while(num > 1) {
			if((num%2) != 0) 
				return false;
			else
				num = num/2;			
		}
		return true;
	}

	public static boolean checkUsingBit(int num) {
		if(num == 0)
			return false;

		return (num&(num-1)) == 0;
	}
}
