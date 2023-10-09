package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class SelectionSort {

	public static void main(String[] args) {
		int [] in = new int[]{10,5,8,20,2,18};
		System.out.println("I/P : " + Arrays.toString(in));
		SelectionSort.selectionSort(in, in.length);
	}
	
	public static void selectionSort(int[] in, int n) {
		for (int i = 0; i < n-1; i++) {
			int inx = i;
			for (int j = i+1; j < n; j++) {
				if(in[j] < in[inx])
					inx = j; 
			}
			Utility.swap(in, i, inx);
		}
		System.out.println("O/P : " + Arrays.toString(in));
	}
}
