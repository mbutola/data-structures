package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class ThreeTypeArraySort {

	public static void main(String[] args) {
		int[] a = new int[]{0,1,2,0,2,1};
		System.out.println("I/P : " + Arrays.toString(a));
		ThreeTypeArraySort.threeTypeSort(a);
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static void threeTypeSort(int[] a) {
		
		int pivot = 1;
		int low = 0;
		int mid = 0;
		int high = a.length-1;
		
		while(mid <= high) {
			if(a[mid] < pivot){
				Utility.swap(a, low, mid);
				low++; mid++;
			}else if(a[mid] == pivot){
				mid++;
			}else{
				Utility.swap(a, high, mid);
				high--; 
			}
		}		
	}
}
