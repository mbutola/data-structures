package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class HoarePartition {

	public static void main(String[] args) {
		int[] a = new int[]{5,3,8,4,2,7,1,10};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + HoarePartition.hoarePartition(a, 0, a.length-1));
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static int hoarePartition(int[] a, int low, int high) {
		
		int pivot = a[low];
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
				return j;
			
			Utility.swap(a, i, j);
		}
		
	}
}
