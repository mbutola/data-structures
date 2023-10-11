package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class UnionSortedArrays {

	public static void main(String[] args) {
		int[] a = new int[]{10,20,30};
		int[] b = new int[]{5,20,20,40,40};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("I/P : " + Arrays.toString(b));
		UnionSortedArrays.union(a, b, a.length, b.length);
	}

	public static void union(int[] a, int[] b, int m, int n) {
		int i=0, j=0;
		System.out.printf("O/P : ");
		while(i<m && j<n) {
			if(i > 0 && a[i] == a[i-1]) {
				i++;
				continue;
			}
			if(j > 0 && b[j] == b[j-1]) {
				j++;
				continue;
			}
			if(a[i] < b[j]) {
				System.out.printf("%4s", a[i]);
				i++;
			}else if(a[i] > b[j]) {
				System.out.printf("%4s", b[j]);
				j++;
			}else {
				System.out.printf("%4s", a[i]);
				i++; j++;
			}
		}		

		while(i < m) {
			if(i > 0 && a[i] != a[i-1])
				System.out.printf("%4s", a[i]);
			i++;
		}
		
		while(j < n) {
			if(j > 0 && b[j] != b[j-1])
				System.out.printf("%4s", b[j]);
			j++;
		}

	}

}
