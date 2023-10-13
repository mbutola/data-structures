package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class HoareQuickSort {

	public static void main(String[] args) {
		int[] a = new int[]{8,4,7,9,3,10,5};
		System.out.println("I/P : " + Arrays.toString(a));
		HoareQuickSort.qSort(a, 0, a.length-1);
		System.out.println("O/P : " + Arrays.toString(a));
	}
	
	public static void qSort(int[] a, int low, int high) {
		if(low < high) {
			int p = HoarePartition.hoarePartition(a, low, high);
			qSort(a, low, p);
			qSort(a, p+1, high);
		}
	}
	
}
