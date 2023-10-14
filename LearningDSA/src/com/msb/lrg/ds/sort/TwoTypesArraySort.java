package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class TwoTypesArraySort {

	public static void main(String[] args) {
		int[] a = new int[]{-12,18,-10,15};
		System.out.println("I/P : " + Arrays.toString(a));
		TwoTypesArraySort.twoTypessort(a, 0, a.length-1);
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static void twoTypessort(int[] a, int low, int high) {
		
		int pivot = 0;
		int i = low - 1;
		int j = high + 1;
		
		while(true) {
			do {
				i++;
			}while(a[i] < pivot);

			do {
				j--;
			}while(a[j] > pivot);
			
			if(i >= j) 
				return ;
			
			Utility.swap(a, i, j);
		}		
	}
}
