package com.msb.lrg.ds.backtracking;

public class Sudoku1 {

//	static int[][] board = Sudoku1.getBoard();
	static int[][] board = new int[9][9];
	static int N = board.length;

	public static void main(String[] args) {
		
		PrintMatrix.printArray(board);
		System.out.println("------------");
		System.out.println("Solution : " + Sudoku1.solve());
		PrintMatrix.printArray(board);

	}
	
	public static boolean solve() {
		
		int row=-1, col=-1;

		search:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] == 0) {
					row = i;
					col = j;
					break search;
				}
			}
		}
		
		if(row == -1 && col == -1) {
			return true;
		}
		
		for (int k = 1; k <= N; k++) {
			if(isAllowed(k, row, col)) {
				board[row][col] = k;
				if(solve())
					return true;
				board[row][col] = 0;
			}
		}
		return false;
	}

	public static boolean isAllowed(int num, int row, int col) {
		
		for(int i=0; i<N; i++) {
			if(board[row][i] == num || board[i][col] == num) {
				return false;
			}
		}			
		
		int size = (int)Math.sqrt(N);
		int x = row - row%size;
		int y = col - col%size;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(board[i+x][j+y] == num) 
					return false;
			}
		}

		return true;
	}
	
	public static int[][] getBoard(){
		int[][] maze = new int[4][4];
		maze[0][0] = 1;
		maze[0][1] = 0;
		maze[0][2] = 3;
		maze[0][3] = 0;
		maze[1][0] = 0;
		maze[1][1] = 0;
		maze[1][2] = 2;
		maze[1][3] = 1;
		maze[2][0] = 0;
		maze[2][1] = 1;
		maze[2][2] = 0;
		maze[2][3] = 2;
		maze[3][0] = 2;
		maze[3][1] = 4;
		maze[3][2] = 0;
		maze[3][3] = 0;
		
		return maze;
	}

}
