package com.msb.lrg.problems.frequentlyasked;

import java.util.Arrays;

/*

	🧮 Set Matrix Zeroes (mark rows & columns)
	🧠 One-line idea
		👉 If a cell is 0, mark its row and column, then zero them out
	🧩 Problem
		matrix =
			[
			 [1,1,1],
			 [1,0,1],
			 [1,1,1]
			]
		👉 Output:
			[
			 [1,0,1],
			 [0,0,0],
			 [1,0,1]
			]
	⚙️ Approach 1: Extra space (easy)
		👉 Use two arrays:
			rows[]
			cols[]
	🔄 Steps
		1. Scan matrix → mark rows[i] and cols[j] if matrix[i][j] == 0  
		2. Set matrix[i][j] = 0 if rows[i] or cols[j] is marked

 */
public class F015SetMatrixZero {

	public static void main(String[] args) {
        int[][] matrix = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };

        setZeroes(matrix);

        System.out.println("Optimal Result:");
        printMatrix(matrix);
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        boolean firstRow = false, firstCol = false;

        // Check first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) firstRow = true;
        }

        // Check first column
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) firstCol = true;
        }

        // Mark using first row/col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Update matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // First row
        if (firstRow) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }

        // First column
        if (firstCol) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }
    
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        // Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // Set zeroes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
}
