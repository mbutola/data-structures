package com.msb.lrg.ds.search;

import java.util.Arrays;

public class RecursiveBinarySearch {

	public static void main(String[] args) {
		int val = 20;
		int[] a = new int[]{10,20,30,40,50,60,70};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + RecursiveBinarySearch.binarySearch(a, val, 0, a.length-1));
	}

	public static int binarySearch(int[] a, int val, int low, int high){
		if(low>high)
			return -1;
		
		int mid = low + (high-low)/2;
		if(a[mid] == val) {
			return mid;
		}else if(a[mid] > val) {
			return binarySearch(a, val, low, mid-1);
		}else {
			return binarySearch(a, val, mid+1, high);
		}
	}
}
