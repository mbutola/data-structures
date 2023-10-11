package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[]{10,5,30,15,7};
		System.out.println("I/P : " + Arrays.toString(a));
		MergeSort.mergeSort(a, 0, a.length-1);
		System.out.println("O/P : " + Arrays.toString(a));
	}
	
	public static void mergeSort(int[]a, int low, int high) {
		if(high > low) {
			int mid = low + (high-low)/2;
			mergeSort(a, low, mid);
			mergeSort(a, mid+1, high);
			merge(a,low,mid,high);
		}
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] left = new int[n1];
		int[] right = new int[n2];

		for (int i = 0; i < n1; i++) { left[i] = a[low+i]; }
		for (int i = 0; i < n2; i++) { right[i] = a[mid+i+1]; }

		int i = 0, j = 0, k = low;
		while(i < n1 && j < n2) {
			if(left[i] <= right[j]) { 
				a[k] = left[i]; i++; k++ ;
			}else { 
				a[k] = right[j]; j++; k++; 
			}
		}
		
		while(i < n1) { a[k] = left[i]; i++; k++; }
		while(j < n2) { a[k] = right[j]; j++; k++; }
	
	}
}
