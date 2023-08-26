package com.msb.lrg.ds.backtracking;

public class Sudoku {

//	static int[][] board = Sudoku.getBoard();
	static int[][] board = new int[9][9];
	static int N = board.length;

	public static void main(String[] args) {
		
		PrintMatrix.printArray(board);
		System.out.println("------------");
		System.out.println("Solution : " + next(0, 0));
		PrintMatrix.printArray(board);

	}
	
	public static boolean next(int row, int col) {
		
//		System.out.println("===> Next  ["+row+"]["+col+"]");

		if(row == N) {
			return true;
		}
		if(board[row][col] != 0) {
			if(col < N-1) {
				if(next(row, col+1)) 
					return true;
			}else {
				if(next(row+1, 0)) 
					return true;
			}
		}else{
			for (int i = 1; i <= N; i++) {
				if(isAllowed(i, row, col)) {
					board[row][col] = i;
//					System.out.println("===> Set   ["+row+"]["+col+"] = "+i);
					if(col < N-1) {
						if(next(row, col+1)) {
							return true;
						}
					}else {
						if(next(row+1, 0)) {
							return true;
						}
					}
					board[row][col] = 0;
//					System.out.println("===> Unset ["+row+"]["+col+"] = 0");
				}
			}
		}
		return false;
	}

	public static boolean isAllowed(int num, int row, int col) {
		
//		System.out.println("===> Check "+num+" ,["+row+"]["+col+"]");
		
//		if(board[row][col] != 0) {
//			System.out.println("1. true");
//			return true;
//		}
		
		for(int i=0; i<N; i++) {
				if(board[row][i] == num ) {
//					System.out.println(row +" "+ i +" "+ num);
//					System.out.println("1. false");
					return false;
				}
		}			
		
		for(int i=0; i<N; i++) {
				if(board[i][col] == num) {
//					System.out.println("2. false");
					return false;
				}
		}
		
		int size = (int)Math.sqrt(N);
		int x = (row/size)*size;
		int y = (col/size)*size;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
					if(board[i+x][j+y] == num) {
//						System.out.println("3. false");
						return false;
					}
			}
		}

//		System.out.println("2. true");
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
