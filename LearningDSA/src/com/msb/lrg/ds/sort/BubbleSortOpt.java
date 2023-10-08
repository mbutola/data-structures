package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class BubbleSortOpt {
	public static void main(String[] args) {
		int[] input = new int[]{3,5,10,40,20};
		BubbleSortOpt.bubbleSort(input, input.length);
	}

	public static void bubbleSort(int[] in, int n) {
		boolean swapped = true;
		for (int i = 0; swapped && i < n-1; i++) {
			swapped = false;
			for (int j = 0; j < n-i-1; j++) {
				if(in[j] > in[j+1]) {
					Utility.swap(in, j, j+1);
					swapped = true;
				}
			}
			if(!swapped)
				break;
		}
		System.out.println(Arrays.toString(in));
	}

}
