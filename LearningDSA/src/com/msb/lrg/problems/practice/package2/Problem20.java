package com.msb.lrg.problems.practice.package2;

import java.util.Arrays;

/*

Set Matrix Zeroes :: LeetCode (73, Medium)
	Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
	You must do it in place.
	Example 1:
		Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
		Output: [[1,0,1],[0,0,0],[1,0,1]]
	Example 2:
		Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
		Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
	Constraints:
		m == matrix.length
		n == matrix[0].length
		1 <= m, n <= 200
		-231 <= matrix[i][j] <= 231 - 1

 */
public class Problem20 {

	public static void main(String[] args) {
		int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
//		int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		for(int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("********");
		setZeroes(matrix);
		for(int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}

    public static void setZeroes(int[][] matrix) {
    	
    	boolean firstRowZero = false;
    	boolean firstColZero = false;
    	
    	for(int i = 0; i < matrix[0].length; i++) {
    		if(matrix[0][i] == 0) {
    			firstRowZero = true;
    			break;
    		}
    	}
        
    	for(int i = 0; i < matrix.length; i++) {
    		if(matrix[i][0] == 0) {
    			firstColZero = true;
    			break;
    		}
    	}
    	
    	for(int i = 1; i < matrix.length; i++) {
    		for(int j = 1; j < matrix[i].length; j++) {
    			if(matrix[i][j] == 0) {
    				matrix[i][0] = 0;
    				matrix[0][j] = 0;
    			}
    		}
    	}

    	for(int i = 1; i < matrix.length; i++) {
       		for(int j = 1; j < matrix[i].length; j++) {
        		if(matrix[i][0] == 0 || matrix[0][j] == 0) {
        				matrix[i][j] = 0;
        		}
    		}
    	}

    	if(firstRowZero) {
    		for(int j = 0; j < matrix[0].length; j++) {
    			matrix[0][j] = 0;
    		}
		}

		if(firstColZero) {
    		for(int i = 0; i < matrix.length; i++) {
    			matrix[i][0] = 0;
    		}
		}
    }
}
