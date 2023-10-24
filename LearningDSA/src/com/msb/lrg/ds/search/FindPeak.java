package com.msb.lrg.ds.search;

import java.util.Arrays;

public class FindPeak {

	public static void main(String[] args) {
		int[] a = new int[]{5,20,40,30,20,50,60};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + FindPeak.peakElement(a));
	}
	
	public static int peakElement(int[] a) {
		int low = 0;
		int high = a.length - 1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if((mid == 0 || a[mid-1] <= a[mid]) &&
			        (mid == a.length - 1 || a[mid+1] <= a[mid])) {
				return mid;
			}else if(mid > 0 && a[mid-1] >= a[mid]) {
				high = mid-1;
			}else {
				low = mid+1;
			}
		}
		return -1;
	}

}
