package com.msb.lrg.ds.search;

import java.util.Arrays;

public class SquareRoot {

	public static void main(String[] args) {
		int val = 10;
		System.out.println("I/P : " + val);
		System.out.println("O/P : " + SquareRoot.sqRoot(val));
	}

	public static int sqRoot(int val){
		int low = 0;
		int res = low; 
		int high = val;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(mid*mid > val) {
				high = mid-1;
			}else if(mid*mid < val){
				low = mid+1;
				res = mid;
			}else {
				res = mid;
			}
		}
		return res;
	}
}
