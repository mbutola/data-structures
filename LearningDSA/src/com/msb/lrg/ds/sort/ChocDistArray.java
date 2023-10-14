package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class ChocDistArray {

	public static void main(String[] args) {
		int m = 5;
		int[] a = new int[]{3,4,1,9,56,7,9,12};
		System.out.println("I/P : " + Arrays.toString(a));
		ChocDistArray.chocDist(a, m);
	}
	
	public static void chocDist(int[] a, int m) {
		int index = 0;
		int res = Integer.MAX_VALUE;
		Arrays.sort(a);
		for (int i = m-1; i < a.length; i++) {
			int temp = a[i] - a[i-(m-1)];
			if(res > temp){
				res = temp;
				index = i-(m-1);
			}
		}
		System.out.print("O/P :");
		for (int j = 0; j < m; j++) {
			System.out.printf("%4s", a[index+j]);
		}
	}
}
