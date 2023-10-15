package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		int[] a = new int[]{319,212,6,8,100,50};
		System.out.println("I/P : " + Arrays.toString(a));
		RadixSort.radixSort(a);
		System.out.println("O/P : " + Arrays.toString(a));
		
	}
	
	public static void radixSort(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[i] > max)
				max = a[i];
		}
		
		for (int exp = 1; max/exp > 0; exp*=10 ) {
			countSort(a, exp);
		}
	}
	
	public static void countSort(int[] a, int exp) {
		int k = 10;
		int[] count = new int[k];
		
		for (int i = 0; i < k; i++) {
			count[i] = 0;
		}

		for (int i = 0; i < a.length; i++) {
			count[(a[i]/exp)%10]++;
		}

		for (int i = 1; i < k; i++) {
			count[i] = count[i] + count[i-1];
		}
		System.out.println(Arrays.toString(count));
		
		int[] output = new int[a.length];
		for (int i = a.length-1; i >= 0; i--) {
			output[count[(a[i]/exp)%10]-1] = a[i];
			count[(a[i]/exp)%10]--;
		}
		for (int i = 0; i < output.length; i++) {
			a[i] = output[i];
		}
		System.out.println(Arrays.toString(a));
	}
	

}
