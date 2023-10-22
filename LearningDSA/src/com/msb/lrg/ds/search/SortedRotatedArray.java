package com.msb.lrg.ds.search;

import java.util.Arrays;

public class SortedRotatedArray {

	public static void main(String[] args) {
		int val = 5;
		int[] a = new int[]{10,20,40,60,5,8};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + SortedRotatedArray.search(a, val));
	}

	public static int search(int[] a, int val) {
		int low = 0;
		int high = a.length - 1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(a[mid] == val) {
				return mid;
			}else if(a[mid] > a[low]) {
				if(val >= a[low] && val < a[mid]) 
					high = mid-1;
				else
					low = mid+1;
			}else {
				if(val > a[mid] && val <= a[high]) 
					low = mid+1;
				else
					high = mid-1;
			}
		}
		return -1;
		
	}
}
