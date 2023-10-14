package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class CountSort {

	public static void main(String[] args) {
		int k = 5;
		int[] a = new int[]{1,4,4,1,0,1};
		System.out.println("I/P : " + Arrays.toString(a));
		CountSort.countSort(a, k);
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static void countSort(int[] a, int k) {
		int[] count = new int[k];
		
		for (int i = 0; i < k; i++) {
			count[i] = 0;
		}
		
		for (int i = 0; i < a.length; i++) {
			count[a[i]]++;
		}
		
		for (int i = 1; i < k; i++) {
			count[i] = count[i] + count[i-1];
		}

		int[] output = new int[a.length];
		for (int i = a.length-1; i >= 0; i--) {
			output[count[a[i]]-1] = a[i];
			count[a[i]]--;
		}
		for (int i = 0; i < output.length; i++) {
			a[i] = output[i];
		}
	}
}
