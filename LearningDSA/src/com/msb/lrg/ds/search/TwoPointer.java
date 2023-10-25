package com.msb.lrg.ds.search;

import java.util.Arrays;

public class TwoPointer {

	public static void main(String[] args) {
		int val = 23;
		int[] a = new int[]{2,4,8,9,11,12,20,30};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + TwoPointer.approach(a, val));
	}
	
	public static boolean approach(int[] a, int val) {
		
		int i = 0, j = a.length - 1;
		
		while(i < j) {
			if(a[i] + a[j] == val) {
				return true;
			}else if(a[i] + a[j] < val) {
				i++;
			}else {
				j--;
			}
		}
		return false;
		
	}

}
