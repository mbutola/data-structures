package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;

/*

	A person wants to determine the most expensive computer keyboard and USB drive that can be 
	purchased with a give budget. Given price lists for keyboards and USB drives and a budget, 
	find the cost to buy them. If it is not possible to buy both items, return .
	Example
		The person can buy a , or a . Choose the latter as the more expensive option and return .
	Function Description
		Complete the getMoneySpent function in the editor below.
		getMoneySpent has the following parameter(s):
			int keyboards[n]: the keyboard prices
			int drives[m]: the drive prices
			int b: the budget
		Returns
			int: the maximum that can be spent, or  if it is not possible to buy both items
	Input Format
		The first line contains three space-separated integers , , and , the budget, the number of keyboard models and the number of USB drive models.
		The second line contains  space-separated integers , the prices of each keyboard model.
		The third line contains  space-separated integers , the prices of the USB drives.
	Constraints
		The price of each item is in the inclusive range .
	Sample Input 0
		10 2 3
		3 1
		5 2 8
	Sample Output 0
		9

 */
public class H021ElectronicsShop {

	public static void main(String[] args) {
		int[] keyboards = {40,50,60};
		int[] drives = {5,8,12};
		int b = 60;
		int res = getMoneySpent(keyboards, drives, b);
		System.out.println(res);
	}

    static int getMoneySpent(int[] keyboards, int[] drives, int b) {
    	Arrays.sort(keyboards);
    	Arrays.sort(drives);
    	
    	int max = -1;
    	int sum = 0;
    	int i = 0, j = drives.length - 1;
    	
    	while(i < keyboards.length && j >= 0) {
    		sum = keyboards[i] + drives[j];
    		if(sum > b) {
    			j--;
    		} else {
    			max = Math.max(max, sum);
    			i++;
    		}
    	}
    	return max;
    }

    static int getMoneySpent1(int[] keyboards, int[] drives, int b) {
    	int max = -1;
    	int sum = 0;
    	for(int k : keyboards) {
    		for(int d : drives) {
    			sum = k + d;
    			if(sum <= b && sum > max)
    				max = sum;
    		}
    	}
    	return max;
    }

}
