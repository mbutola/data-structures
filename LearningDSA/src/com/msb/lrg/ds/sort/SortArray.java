package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class SortArray {

	public static void main(String[] args) {
		int[] arr1 = new int[]{35,20,12,30,25};
		char[] arr2 = new char[]{'B','B','A','C','A'};
		
		System.out.println("I/P : " + Arrays.toString(arr1));
		Arrays.sort(arr1);
		System.out.println("O/P Sort : " + Arrays.toString(arr1));
		arr1 = new int[]{35,20,12,30,25};
		Arrays.sort(arr1,1,3);
		System.out.println("O/P Sort (1,3) : " + Arrays.toString(arr1));
		System.out.println();

		System.out.println("I/P : " + Arrays.toString(arr2));
		Arrays.sort(arr2);
		System.out.println("O/P : " + Arrays.toString(arr2));
		
	}

}
