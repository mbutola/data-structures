package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class H011BetweenTwoSets {

	public static void main(String[] args) {
		List<Integer> a = Arrays.asList(2,6);
		List<Integer> b = Arrays.asList(24,36);
		int res = getTotalX(a, b);
		System.out.println(res);
	}

    public static int getTotalX(List<Integer> a, List<Integer> b) {
    	int lcm = lcmList(a);
    	int gcd = gcdList(b);
    	int count = 0;
    	for(int i =lcm; i <= gcd; i += lcm) {
    		if(gcd % i == 0) count++;
    	}
    	return count;
    }
    
    private static int gcd(int a, int b) {
    	return b == 0 ? a : gcd(b,a % b);
    }

    private static  int lcm(int a, int b) {
    	return a * b / gcd(a,b);
    }
    
    private static  int lcmList(List<Integer> a) {
    	int lcm = a.get(0);
    	for(int i : a) {
    		lcm = lcm(lcm, i);
    	}
    	return lcm;
    }

    private static  int gcdList(List<Integer> a) {
    	int gcd = a.get(0);
    	for(int i : a) {
    		gcd = gcd(gcd, i);
    	}
    	return gcd;
    }
}
