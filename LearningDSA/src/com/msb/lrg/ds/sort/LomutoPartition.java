package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class LomutoPartition {

	public static void main(String[] args) {
		int[] a = new int[]{10,80,30,90,40,50,70};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + LomutoPartition.lomutoPartition(a, 0, a.length-1));
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static int lomutoPartition(int[] a, int low, int high) {
		int pivot = a[high];
		int i = low - 1;
		for (int j = low; j <= high - 1 ; j++) {
			if(a[j] < pivot) {
				Utility.swap(a, i+1, j);
				i++;
			}
		}
		Utility.swap(a, i+1, high);
		return i+1;
	}
}
