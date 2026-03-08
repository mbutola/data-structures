package com.msb.lrg.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTNQueen {

	static int N = 4;
	static List<int[]> res = new ArrayList<>();
	
	public static void main(String[] args) {
		int[] temp = new int[N];
		solve(temp, 0); 
//		System.out.println("Size :: " + res.size());
		for(int[] arr: res) {
			System.out.println(Arrays.toString(arr));
		}
	}
	
	static void solve(int[] temp, int col) {
//		System.out.println("======");
//		System.out.println(Arrays.toString(temp) + "," + col);
		if(col >= N) {
//			System.out.println(Arrays.toString(temp));
			res.add(Arrays.copyOf(temp, N));
			return;
		}
		
		for(int i=0; i<=N-1; i++) {
			if(isAllowed(temp, i, col)) {
//				System.out.println(i + "," + col);
				temp[col] = i+1;
				solve(temp, col+1); 
				temp[col] = 0;
			}
		}
	}
	
	static boolean isAllowed(int[] temp, int i_in, int j_in) {
		
//		System.out.println("isAllowed :: " + Arrays.toString(temp) + ", " + i_in + "," + j_in);
		
		for(int j=j_in-1; j>=0; j--){
			if(temp[j]-1 == i_in)
				return false;
		}
		
		for(int i=i_in-1, j=j_in-1; i>=0 && j>=0; i--, j--) {
			if(i == temp[j]-1)
				return false;
		}

		for(int i=i_in+1, j=j_in-1; i<=N-1 && j>=0; i++, j--) {
			if(i == temp[j]-1)
				return false;
		}

		return true;
	}
		
}
