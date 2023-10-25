package com.msb.lrg.ds.search;

import java.util.Arrays;

public class ThreePointer {

	public static void main(String[] args) {
		int val = 33;
		int[] a = new int[]{2,5,10,15,18};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + ThreePointer.approach(a, val));
	}
	
	public static boolean approach(int[] a, int val) {
		
		for (int i = 0; i < a.length -3; i++) {
			if(twoPointerApproach(a, val - a[i], i+1)) {
				return true;
			}
		}		
		return false;	
	}

	public static boolean twoPointerApproach(int[] a, int val, int s) {
		
		int i = s, j = a.length - 1;
		
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
