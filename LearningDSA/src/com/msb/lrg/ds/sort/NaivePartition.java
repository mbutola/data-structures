package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class NaivePartition {

	public static void main(String[] args) {
		int[] a = new int[]{5,13,6,9,12,11,8};
		System.out.println("I/P : " + Arrays.toString(a));
		NaivePartition.partition(a, 0, a.length-1, a.length-1);
	}

	public static void partition(int[] a, int l, int h, int p) {
		int[] temp = new int[h-l+1];
		int index = 0;
		for (int i = l; i <= h; i++) {
			if(a[i] <= a[p])
				temp[index++] = a[i];
		}
		for (int i = l; i <= h; i++) {
			if(a[i] > a[p])
				temp[index++] = a[i];
		}
		for (int i = l; i <= h; i++) {
			a[i] = temp[i-l];
		}
		System.out.println("O/P : " + Arrays.toString(a));
	}
}
