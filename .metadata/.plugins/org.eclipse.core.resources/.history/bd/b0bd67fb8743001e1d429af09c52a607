package com.msb.lrg.ds.backtracking;

public class NQueen {
	
	public static int[][] board = new int[4][4];

	public static void main(String[] args) {
		boolean possible = NQueen.next(0);
		System.out.println("Possible : " + possible);
		PrintMatrix.printArray(board);
	}
	
	public static boolean next(int col) {
	
		if(col == board.length) {
			return true;
		}
		for (int i = 0; i < board.length; i++) {
			if(isAllowed(i, col)) {
				board[i][col] = 1;
				if(next(col+1))
					return true;
				board[i][col] = 0;
			}
		}
		return false;
	}
	
	public static boolean isAllowed(int row, int col) {
		
		for(int i=0; i<col; i++) {
			if(board[row][i] == 1)
				return false;
		}			
		
		for(int i=row, j=col; j>=0 && i >=0; i--,j--) {
			if(board[i][j] == 1)
				return false;
		}			

		for(int i=row, j=col; j>=0 && i<board.length; i++,j--) {
			if(board[i][j] == 1)
				return false;
		}			
		
		return true;
	}
	
}
