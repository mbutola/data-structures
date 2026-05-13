package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*

	You are given a number of sticks of varying lengths. You will iteratively cut the sticks into 
	smaller sticks, discarding the shortest pieces until there are none left. At each iteration 
	you will determine the length of the shortest stick remaining, cut that length from each of 
	the longer sticks and then discard all the pieces of that shortest length. When all the 
	remaining sticks are the same length, they cannot be shortened so discard them.
	Given the lengths of  sticks, print the number of sticks that are left before each iteration 
	until there are none left.
	Example
		The shortest stick length is , so cut that length from the longer two and discard the pieces of length . Now the lengths are . Again, the shortest stick is of length , so cut that amount from the longer stick and discard those pieces. There is only one stick left, , so discard that stick. The number of sticks at each iteration are .
	Function Description
		Complete the cutTheSticks function in the editor below. It should return an array of 
		integers representing the number of sticks before each cut operation is performed.
		cutTheSticks has the following parameter(s):
			int arr[n]: the lengths of each stick
		Returns
			int[]: the number of sticks after each iteration
	Input Format
		The first line contains a single integer , the size of .
		The next line contains  space-separated integers, each an , where each value represents 
		the length of the  stick.
	Sample Input 0
		STDIN           Function
		-----           --------
		6               arr[] size n = 6
		5 4 4 2 2 8     arr = [5, 4, 4, 2, 2, 8]
	Sample Output 0
		6
		4
		2
		1

 */
public class H030CutTheStick {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(5,4,4,2,2,8);
		List<Integer> res = cutTheSticks(arr);
		System.out.println(res);
	}
	
	
    public static List<Integer> cutTheSticks(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        Collections.sort(arr);
        int n = arr.size(); 
        for(int i = 0; i < n; i++) {
        	if(i == 0 || !arr.get(i).equals(arr.get(i - 1)))
        		res.add(n - i);
        }
        return res;

    }

	/*
		🚨 Major Problem (IMPORTANT)
		❌ Time Complexity = O(n²)
		Why?
			Each iteration:
				You loop over entire list → O(n)
				Number of iterations ≈ number of unique values → up to n
			👉 Total:
				O(n) * O(n) = O(n²)
	 */
    public static List<Integer> cutTheSticks2(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        Collections.sort(arr);
        res.add(arr.size());
        
        while(arr.size() > 0){
        	int base = arr.get(0);
        	arr = arr.stream()
        			.map(x -> x - base)
        			.filter(x -> x != 0)
        			.toList();
        	
        	if(arr.size() > 0)
                res.add(arr.size());
        }
        return res;

    }

}
