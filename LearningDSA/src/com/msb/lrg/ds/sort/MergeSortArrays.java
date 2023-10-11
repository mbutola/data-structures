package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class MergeSortArrays {
	
	public static void main(String[] args) {
		int[] a = new int[]{10,20,50};
		int[] b = new int[]{5,15,50,50};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("I/P : " + Arrays.toString(b));
		MergeSortArrays.mergeSort(a, b, a.length, b.length);
	}
	
	public static void mergeSort(int[] a, int[] b, int m, int n) {
		int i=0, j=0;
		System.out.printf("O/P : ");
		while(i<m && j<n) {
			if(a[i]<=b[j]) {
				System.out.printf("%4s", a[i]);
				i++;
			}else {
				System.out.printf("%4s", b[j]);
				j++;
			}
		}
		
		while(i<m) {
			System.out.printf("%4s", a[i]);
			i++;
		}
		
		while(j<n) {
			System.out.printf("%4s", b[j]);
			j++;
		}
		
	}
}
