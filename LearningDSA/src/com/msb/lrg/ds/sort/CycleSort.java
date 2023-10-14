package com.msb.lrg.ds.sort;

import java.util.Arrays;

import com.msb.lrg.ds.Utility;

public class CycleSort {

	public static void main(String[] args) {
		int[] a = new int[]{20,40,50,10,30};
		System.out.println("I/P : " + Arrays.toString(a));
		CycleSort.cycleSort(a);
		System.out.println("O/P : " + Arrays.toString(a));
	}

	public static void cycleSort(int[] a){
		for (int cs = 0; cs < a.length-2; cs++) {
			int pos = cs;
			int itm = a[pos];
			for (int i = cs+1; i < a.length; i++) {
				if(a[i] < itm)
					pos++;
			}
			Utility.swap(a, cs, pos);
			System.out.println("O/P : " + Arrays.toString(a));
			while(cs != pos) {
				pos = cs;
				itm = a[pos];
				for (int i = cs+1; i < a.length; i++) {
					if(a[i] < itm)
						pos++;
				}
				Utility.swap(a, cs, pos);
				System.out.println("O/P : " + Arrays.toString(a));
			}
			System.out.println("end cycle");
		}
		
	}
}
