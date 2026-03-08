package com.msb.lrg.problems.practice.package5;

import java.util.Arrays;

public class Problem540 {

	public static void main(String[] args) {
		int n = 2;
//		String s = "abc";
//		int i = 0;
//		System.out.println(s.substring(0,i) + s.substring(i + 1));
//		System.out.println(s.charAt(i));
		countArrangement(n);
	}

    public static int countArrangement(int n) {
        String s = "abc";
        
        solve(s.toCharArray(), 0);
        solve(s, "", s.length());
    	
    	return 0;
    }
    
    public static void solve(String in, String out, int l) {
    	
    	if(out.length() == l) {
    		System.out.println(out);
    		return;
    	}
    	
    	for(int j = 0; j < in.length(); j++) {
    		String new_in = in.substring(0,j) + in.substring(j + 1);
    		String new_out = out + in.charAt(j);
       		solve(new_in, new_out, l);
    	}
    		
    }
    
    public static void solve(char[] arr, int i) {
    	
    	if(i == arr.length - 1) {
    		System.out.println(Arrays.toString(arr));
    		return;
    	}
    	
    	for(int j = i; j < arr.length; j++) {
       		swap(arr, i, j);
       		solve(arr, i + 1);
       		swap(arr, j, i);
    	}
    }
    
    public static void swap(char[] arr, int i, int j) {
    	char c = arr[j];
    	arr[j] = arr[i];
    	arr[i] = c;
    }

}
