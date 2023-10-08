package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class BubbleSort {

	public static void main(String[] args) {
		int[] input = new int[]{2,10,8,7};
		BubbleSort.bubbleSort(input, input.length);
	}

	public static void bubbleSort(int[] in, int n) {
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if(in[j] > in[j+1])
					Utility.swap(in, j, j+1);
			}
			System.out.println(Arrays.toString(in));
		}
		System.out.println(Arrays.toString(in));
	}
}
