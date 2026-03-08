package com.msb.lrg.problems.practice.package0;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Train Platform Problem (Minimum Number of Platforms)
Problem
	You are given:
		arrival[] times of trains
		departure[] times of trains
	Find the minimum number of platforms required so that no train waits.
	Typical input format (24-hour or integer time):
		arrival[]   = {900, 940, 950, 1100, 1500, 1800}
		departure[] = {910, 1200,1120,1130,1900,2000}

 */
public class Problem6 {

	public static void main(String[] args) {
		int[] arrivals   = {900, 940, 950, 1100, 1500, 1800};
		int[] departures = {910, 1200, 1120, 1130, 1900, 2000};
		int res = solution(arrivals, departures);
		System.out.println("Results :: " + res);
	}
	
	public static int solution(int[] arrivals, int[] departures) {
		Arrays.sort(arrivals);
		Arrays.sort(departures);
		int platforms = Integer.MIN_VALUE;
		int i = 0, j = 0, count = 0;
		while(i < arrivals.length && j < departures.length) {
			if(arrivals[i] < departures[j]) {
				count++;
				platforms = Math.max(platforms, count);
				i++;
			}else {
				count--;
				j++;
			}
		}
		
		return platforms;
	}

}
