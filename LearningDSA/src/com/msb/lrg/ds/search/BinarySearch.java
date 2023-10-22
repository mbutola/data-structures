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
		int low = 0;
		int high = a.length - 1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(a[mid] == val) {
				return mid;
			}else if(a[mid] > val) {
				high = mid-1;
			}else {
				low = mid+1;
			}
		}
		return -1;
	}
}
