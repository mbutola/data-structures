package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class CountInversion {

	public static void main(String[] args) {
		int[] a = new int[]{4,3,2,1};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + CountInversion.countInversion(a, 0, a.length-1));
	}
	
	public static int countInversion(int[]a, int low, int high) {
		int count = 0;
		if(high > low) {
			int mid = low + (high-low)/2;
			count += countInversion(a, low, mid);
			count += countInversion(a, mid+1, high);
			count += countMerge(a,low,mid,high);
		}
		return count;
	}

	public static int countMerge(int[] a, int low, int mid, int high) {
		System.out.println(low + ":" + mid + ":" + high);
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] left = new int[n1];
		int[] right = new int[n2];

		for (int i = 0; i < n1; i++) { left[i] = a[low+i]; }
		for (int i = 0; i < n2; i++) { right[i] = a[mid+i+1]; }

		int count  = 0;
		int i = 0, j = 0, k = low;
		while(i < n1 && j < n2) {
			if(left[i] <= right[j]) { 
				a[k] = left[i]; i++; k++ ;
			}else { 
				a[k] = right[j]; j++; k++; 
				count = count + (n1-i);
			}
		}
		
		while(i < n1) { a[k] = left[i]; i++; k++; }
		while(j < n2) { a[k] = right[j]; j++; k++; }

		return count;
	}

}
