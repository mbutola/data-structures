package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class H009AppleAndOrange {

	public static void main(String[] args) {
		int s = 7;
		int t = 11;
		int a = 5;
		int b = 15;
		List<Integer> apples = List.of(-2,2,1);
		List<Integer> oranges = List.of(5,-6);
		countApplesAndOranges(s, t, a, b, apples, oranges);
	}

//	public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
//		int appleCount = 0;
//		int orangeCount = 0;
//
//		for(int loc : apples) {
//			if(a + loc >= s & a + loc <= t) appleCount++;
//		}
//		for(int loc : oranges) {
//			if(b + loc >= s & b + loc <= t) orangeCount++;
//		}
//		
//		System.out.println(appleCount+ "\n" +orangeCount);
//	}

	public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
		long appleCount = apples.stream()
							.map(loc -> a + loc)
							.filter(loc -> loc >= s & loc <= t)
							.count();
		
		long orangeCount = oranges.stream()
				.map(loc -> a + loc)
				.filter(loc -> loc >= s & loc <= t)
				.count();
		
		System.out.println(appleCount+ "\n" +orangeCount);
	}
}
