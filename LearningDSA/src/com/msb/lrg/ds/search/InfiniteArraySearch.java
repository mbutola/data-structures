package com.msb.lrg.ds.search;

import java.util.Arrays;

public class InfiniteArraySearch {

	public static void main(String[] args) {
		int val = 100;
		int[] a = new int[]{1,10,15,20,40,60,80,100,200,500,1000};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + InfiniteArraySearch.search(a, val));
	}

	public static int search(int[] a, int val) {
		
		if(a[0] == val)
			return 0;

		int i = 1;
		while(a[i] < val) {
			i = i*2;
		}
		
		if(a[i] == val)
			return i;
		
		return RecursiveBinarySearch.binarySearch(a, val, i/2+1, i-1);
		
	}
}
