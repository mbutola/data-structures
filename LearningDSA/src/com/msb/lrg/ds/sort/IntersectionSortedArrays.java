package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class IntersectionSortedArrays {

	public static void main(String[] args) {
		int[] a = new int[]{2,20,20,40,60};
		int[] b = new int[]{10,20,20,20,60};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("I/P : " + Arrays.toString(b));
		IntersectionSortedArrays.intersection(a, b, a.length, b.length);
	}
	
	public static void intersection(int[] a, int[] b, int m, int n) {
		int i=0, j=0;
		System.out.printf("O/P : ");
		while(i<m && j<n) {
			if(i > 0 && a[i] == a[i-1]) {
				i++;
				continue;
			}else if(a[i] < b[j]) {
				i++;
			}else if(a[i] > b[j]) {
				j++;
			}else {
				System.out.printf("%4s", a[i]);
				i++; j++;
			}
		}		
	}

}
