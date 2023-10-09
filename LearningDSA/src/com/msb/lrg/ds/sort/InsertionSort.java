package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int[] in = new int[]{20,5,40,60,10,30};
		System.out.println("I/P : " + Arrays.toString(in));
		InsertionSort.insertionSort(in, in.length);
	}
	
	public static void insertionSort(int[] arr, int n) {
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = key;
		}
		System.out.println("O/P : " + Arrays.toString(arr));
	}

}
