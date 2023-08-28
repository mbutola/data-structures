package com.msb.lrg.ds.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {

	public static void printDp(String s1, String s2, int[][] memo) {
		char[] col = s1.toCharArray();
		char[] row = s2.toCharArray();
		for (int i = 0; i < memo[0].length; i++) {
			if(i == 0)
				System.out.printf("%8s", "");
			else
				System.out.printf("%2s  ", row[i-1]);
		}
		System.out.println("");
		for (int i = 0; i < memo.length; i++) {
			if(i == 0)
				System.out.printf("%4s", "");
			else
				System.out.printf("%2s  ", col[i-1]);
			for (int j = 0; j < memo[0].length; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}

	public static List<String> getList(int n){
		List<String> lis = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			lis.add(i+"");
		}
		return lis;
	}
	
	public static List<String> getList(int[] n){
		List<String> lis = new ArrayList<>();
		for (int i = 0; i < n.length; i++) {
			lis.add(n[i]+"");
		}
		return lis;
	}
	
	public static String[] getArray(List<String> lis){
		String[] a = new String[lis.size()];
		for (int i = 0; i < lis.size(); i++) {
			a[i] = lis.get(i);
		}
		return a;
	}
	

	public static void printDp(List<String> s1, List<String> s2, int[][] memo) {
		String[] col = getArray(s1);
		String[] row = getArray(s2);
		for (int i = 0; i < memo[0].length; i++) {
			if(i == 0) 
				System.out.printf("%6s", 0);
			else 
				System.out.printf("%2s", row[i-1]);
			System.out.printf("%2s", "");
		}
		System.out.println("");
		for (int i = 0; i < memo.length; i++) {
			if(i == 0)
				System.out.printf("%2s",0);
			else
				System.out.printf("%2s", Integer.parseInt(col[i-1]));
			System.out.printf("%2s", "");
			for (int j = 0; j < memo[0].length; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}
}
