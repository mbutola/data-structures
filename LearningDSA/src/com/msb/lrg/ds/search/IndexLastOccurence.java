package com.msb.lrg.ds.search;

import java.util.Arrays;

public class IndexLastOccurence {

	public static void main(String[] args) {
		int val = 10;
		int[] a = new int[]{5,10,10,10,10,20,20};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + IndexLastOccurence.lastOcc(a, val, 0, a.length-1));
	}

	public static int lastOcc(int[] a, int val, int low, int high){
		if(low > high)
			return -1;
		
		int mid = low + (high-low)/2;
		if(a[mid] > val) {
			return lastOcc(a, val, low, mid-1);
		} else if(a[mid] < val){
			return lastOcc(a, val, mid+1, high);
		} else {
			if(mid == 0 || a[mid] != a[mid+1])
				return mid;
			else
				return lastOcc(a, val, mid+1, high);
		}
	}
}
