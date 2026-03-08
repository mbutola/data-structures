package com.msb.lrg.problems.practice.package2;

import java.util.Arrays;

/*

Rotate Image :: LeetCode (48, Medium)
	You are given an n x n 2D matrix representing an image, rotate the image 
	by 90 degrees (clockwise).
	You have to rotate the image in-place, which means you have to modify the 
	input 2D matrix directly. DO NOT allocate another 2D matrix and do the 
	rotation.
	Example 1:
		Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
		Output: [[7,4,1],[8,5,2],[9,6,3]]
	Example 2:
		Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
		Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
	Constraints:
		n == matrix.length == matrix[i].length
		1 <= n <= 20
		-1000 <= matrix[i][j] <= 1000

 */
public class Problem25 {

	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		for(int i = 0; i < matrix.length; i++ ) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("=====");
		rotate(matrix);	
		for(int i = 0; i < matrix.length; i++ ) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
    public static void rotate(int[][] matrix) {

    	int n = matrix.length;
    	
    	for(int i = 0; i < n; i++) {
        	for(int j = i + 1; j < n; j++) {
        		swap(matrix, i, j, j, i);
        	}    		
    	}

    	for(int i = 0; i < n; i++) {
    		int left = 0;
    		int right = (n - 1);
    		while(left < right) {
    			swap(matrix, i, left, i, right);
    			left++;
    			right--;
    		}
    		
    	}
    }
    
    public static void swap(int[][] arr, int i, int j, int k, int l) {
    	int temp = arr[i][j];
    	arr[i][j] = arr[k][l];
    	arr[k][l] = temp;
    	
    }

}
