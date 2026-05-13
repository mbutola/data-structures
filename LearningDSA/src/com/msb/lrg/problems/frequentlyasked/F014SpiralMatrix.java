package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayList;
import java.util.List;

/*

	🌀 Spiral Matrix (boundary tracking)
	🧠 One-line idea
		👉 Keep four boundaries (top, bottom, left, right) and shrink them after each pass
	🧩 Problem
		matrix =[ [1, 2, 3], [4, 5, 6], [7, 8, 9]]
	👉 Output:
		[1,2,3,6,9,8,7,4,5]
	⚙️ Boundaries
		top = 0  bottom = rows - 1  left = 0  right = cols - 1
	🔄 Traversal order
		Repeat while boundaries are valid:
			1. Left → Right   (top row)2. Top → Botto   (right column)3. Right → Left   (bottom row)4. Bottom → Top   (left column)
		👉 After each step → shrink boundary
	⚠️ Important checks
		👉 Before step 3 and 4:
			Check if top <= bottom  Check if left <= right

 */
public class F014SpiralMatrix {

	public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
            };
//		int[][] matrix = { 
//        		{ 1,  2,  3,  4 }, 
//        		{ 5,  6,  7,  8 }, 
//        		{ 9,  10, 11, 12 }, 
//        		{ 13, 14, 15, 16 } 
//        	};
		System.out.println(spiralOrder(matrix));
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		
		
		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;
		
		while(top <= bottom || left <= right) {
			for(int i = left; i <= right; i++) {
				result.add(matrix[top][i]); 
			}
			top++;
			
			for(int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]); 
			}
			right--;
			
			if(top <= bottom) {
				for(int i = right; i >= left; i--) {
					result.add(matrix[bottom][i]); 
				}
				bottom--;
			}
			
			if(left <= right) {
				for(int i = bottom; i >= top; i--) {
					result.add(matrix[i][left]); 
				}
				left++;
			}
		}		
	
		return result;
		
		

//		int top = 0, bottom = matrix.length - 1;
//		int left = 0, right = matrix[0].length - 1;
//
//		while (top <= bottom && left <= right) {
//
//			// 1. left → right
//			for (int i = left; i <= right; i++) {
//				result.add(matrix[top][i]);
//			}
//			top++;
//
//			// 2. top → bottom
//			for (int i = top; i <= bottom; i++) {
//				result.add(matrix[i][right]);
//			}
//			right--;
//
//			// 3. right → left
//			if (top <= bottom) {
//				for (int i = right; i >= left; i--) {
//					result.add(matrix[bottom][i]);
//				}
//				bottom--;
//			}
//
//			// 4. bottom → top
//			if (left <= right) {
//				for (int i = bottom; i >= top; i--) {
//					result.add(matrix[i][left]);
//				}
//				left++;
//			}
//		}
//
//		return result;
	}

}
