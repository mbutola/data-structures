package com.msb.lrg.ds.search;

import java.util.Arrays;

public class CountOccurence {

	public static void main(String[] args) {
		int val = 20;
		int[] a = new int[]{10,20,20,20,40,40};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + CountOccurence.countOcc(a, val));
	}

	public static int countOcc(int[] a, int val){
		int count = 0;
		
		int fi = IndexFirstOccurence.firstOcc(a, val);
		if(fi != -1) {
			int li = IndexLastOccurence.lastOcc(a, val, 0, a.length-1);
			count = li-fi+1;
		}
		
		return count;
	}
}
