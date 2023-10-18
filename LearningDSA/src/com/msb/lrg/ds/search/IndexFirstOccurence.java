package com.msb.lrg.ds.search;

import java.util.Arrays;

public class IndexFirstOccurence {

	public static void main(String[] args) {
		int val = 20;
		int[] a = new int[]{5,10,10,15,20,20,20};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + IndexFirstOccurence.firstOcc(a, val));
	}

	public static int firstOcc(int[] a, int val){
		int low = 0;
		int high = a.length - 1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(a[mid] > val) {
				high = mid-1;
			}else if(a[mid] < val){
				low = mid+1;
			}else {
				if(mid == 0 || a[mid] != a[mid-1])
					return mid;
				else
					high = mid-1;
			}
		}
		return -1;
	}

}
