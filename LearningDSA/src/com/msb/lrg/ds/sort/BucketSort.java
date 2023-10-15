package com.msb.lrg.ds.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {

	public static void main(String[] args) {
		int k = 4;
		int[] a = new int[]{30,40,10,80,5,12,70};
		System.out.println("I/P : " + Arrays.toString(a));
		BucketSort.bucketSort(a, k);
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static void bucketSort(int[] a, int k) {
		int max_val = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[i] > max_val)
				max_val = a[i];
		}
		max_val++;
		
		ArrayList<ArrayList<Integer>> bkts = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < k; i++) {
			bkts.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < a.length; i++) {
			int bkt = (k*a[i])/max_val;
			bkts.get(bkt).add(a[i]);
		}
		
		for (int i = 0; i < k; i++) {
			Collections.sort(bkts.get(i));
		}
		
		int l = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < bkts.get(i).size(); j++) {
				a[l++] = bkts.get(i).get(j);
			}
		}
	}
}
