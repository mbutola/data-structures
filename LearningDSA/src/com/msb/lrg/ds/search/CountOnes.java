package com.msb.lrg.ds.search;

import java.util.Arrays;

public class CountOnes {

	public static void main(String[] args) {
		int val = 1;
		int[] a = new int[]{0,0,1,1,1,1,1};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + CountOnes.countOnes(a, val));
	}

	public static int countOnes(int[] a, int val){

		int count = 0;	
		int fi = IndexFirstOccurence.firstOcc(a, val);
		if(fi != -1) {
			count = a.length-fi;
		}		
		return count;
	}
}
