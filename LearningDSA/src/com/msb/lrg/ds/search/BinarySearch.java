package com.msb.lrg.ds.search;

import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		int val = 20;
		int[] a = new int[]{10,20,30,40,50,60,70};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + BinarySearch.binarySearch(a, val));
	}

	public static int binarySearch(int[] a, int val){
		int l = 0;
		int r = a.length - 1;
		while(l<=r) {
			int m = l + (r-l)/2;
			if(a[m] == val) {
				return m;
			}else if(a[m] > val) {
				r = m-1;
			}else {
				l = m+1;
			}
		}
		return -1;
	}
}
