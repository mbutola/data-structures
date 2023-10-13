package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class LomutoQuickSort {

	public static void main(String[] args) {
		int[] a = new int[]{8,4,7,9,3,10,5};
		System.out.println("I/P : " + Arrays.toString(a));
		LomutoQuickSort.qSort(a, 0, a.length-1);
		System.out.println("O/P : " + Arrays.toString(a));
	}
	
	public static void qSort(int[] a, int low, int high) {
		if(low < high) {
			int p = LomutoPartition.lomutoPartition(a, low, high);
			qSort(a, low, p-1);
			qSort(a, p+1, high);
		}
	}
}
