package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*
 
You are given: 
	* k eggs 
	* n floors (a building) 
Goal: 
	Find the minimum number of attempts needed in the worst case to determine the highest floor 
	from which an egg can be dropped without breaking.
 
 */
public class DPEggDropping {

    public static void main(String[] args) {
    	int eggs = 4; 
    	int floors = 15;
    	int resR = recursive(eggs, floors);
    	System.out.println(resR);
    	
    	int[][] dp = new int[eggs + 1][floors + 1];
    	for(int[] floor : dp) {
    		Arrays.fill(floor, -1);
    	}
    	int resTD = topDown(dp, eggs, floors);
    	System.out.println(resTD);
    	int resBU = bottomUp(eggs, floors);
    	System.out.println(resBU);
    }

    public static int recursive(int eggs, int floors) {
    	if(floors == 0 || floors == 1)
    		return floors;
    	
    	if(eggs == 1)
    		return floors;
    	
    	int min = Integer.MAX_VALUE;
    	
    	for(int i = 1; i <= floors; i++) {
    		
    		int breaks = recursive(eggs - 1, i - 1);
    		int survives = recursive(eggs, floors - i);
    		
    		int worsts = 1 + Math.max(breaks, survives);
    		
    		min = Math.min(min, worsts);	
    	}
    	
    	return min;
	}
    
    public static int topDown(int[][] dp, int eggs, int floors) {
    	if(floors == 0 || floors == 1)
    		return floors;
    	
    	if(eggs == 1)
    		return floors;
    	
    	if(dp[eggs][floors] != -1) {
    		return dp[eggs][floors]; 
    	}
    	
    	int min = Integer.MAX_VALUE;
    	
    	for(int i = 1; i <= floors; i++) {
    		
    		int breaks = recursive(eggs - 1, i - 1);
    		int survives = recursive(eggs, floors - i);
    		
    		int worsts = 1 + Math.max(breaks, survives);
    		
    		min = Math.min(min, worsts);	
    	}
    	
    	return dp[eggs][floors] = min;
	}

    public static int bottomUp(int eggs, int floors) {
    	int[][] dp = new int[eggs + 1][floors + 1];
    	for(int[] floor : dp) {
    		Arrays.fill(floor, -1);
    	}
    	
    	for(int i = 0; i < dp.length; i++) {
    		dp[i][1] = 1;
    	}

    	for(int i = 0; i < dp[0].length; i++) {
    		dp[1][i] = floors;
    	}
    	
    	for(int e = 0; e < dp.length; e++) {
        	for(int f = 0; f < dp[0].length; f++) {
            	
        		dp[e][f] = Integer.MAX_VALUE;
            	
            	for(int x = 1; x <= f; x++) {
            		
            		int breaks = recursive(e - 1, x - 1);
            		int survives = recursive(e, f - x);
            		
            		int worsts = 1 + Math.max(breaks, survives);
            		
            		dp[e][f] = Math.min(dp[e][f], worsts);	
            	}
        	}
    	}
    	return dp[eggs][floors];
	}
    
}
