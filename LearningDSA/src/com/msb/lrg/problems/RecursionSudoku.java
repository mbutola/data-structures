package com.msb.lrg.problems;

import java.util.HashSet;
import java.util.Set;

import com.msb.lrg.problems.dp.DPKnapsack;

public class RecursionSudoku {

	static int N = 9;
	static char[][] board = new char[N][N];
	
	public static void main(String[] args) {
		populate(board);
//		DPKnapsack.print2DArrayChar(board);
//		boolean res = isValid();
//		System.out.println("isValid :: " + res);
		solve(board);
		System.out.println("========================");
		DPKnapsack.print2DArrayChar(board);	
	}
	
	static boolean solve(char[][] board) {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j] == '.') {
					for(int d=1; d<=N; d++) {
						char c = (char)('0'+d);
						if(isValid(board, i, j, c)){
							board[i][j] = c;
							if(solve(board)){
								DPKnapsack.print2DArrayChar(board);
								System.out.println("========================");
//								return true;
							}
							board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isValid(char[][] board, int row, int col, char d) {
		for(int i=0; i<N; i++) {
			if(board[row][i] == d)
				return false;
			if(board[i][col] == d) 
				return false;
		}

		int i = (row/3)*3;
		int j = (col/3)*3;
		for(int k=i; k<i+3; k++) {
			for(int l=j; l<j+3; l++) {
				if(board[k][l] == d) 
					return false;
			}
		}
		
		return true;
	}

//	static boolean isValid() {
//		for(int i=0; i<N; i++) {
//			Set<Integer> exists = new HashSet<>();
//			for(int j=0; j<N; j++) {
//				if(exists.contains(board[i][j])) {
//					System.out.printf("invalid row :: (%d,%d)%n", new Object[] {i,j});
//					return false;
//				}
//				if(board[i][j] != 0) 
//					exists.add(board[i][j]);
//			}
//		}
//		
//		for(int i=0; i<N; i++) {
//			Set<Integer> exists = new HashSet<>();
//			for(int j=0; j<N; j++) {
//				if(exists.contains(board[j][i])) {
//					System.out.printf("invalid column:: (%d,%d)%n", new Object[] {j,i});
//					return false;
//				}
//				if(board[j][i] != 0) 
//					exists.add(board[j][i]);
//			}
//		}
//
//		for(int i=0; i<N; i+=3) {
//			for(int j=0; j<N; j+=3) {
//				Set<Integer> exists = new HashSet<>();
//				for(int k=i; k<i+3; k++) {
//					for(int l=j; l<j+3; l++) {
//						if(exists.contains(board[k][l])) {
//							System.out.printf("invalid grid:: (%d,%d)%n", new Object[] {k,l});
//							return false;
//						}
//						if(board[k][l] != 0) 
//							exists.add(board[k][l]);
//					}
//				}	
//			}
//		}
//		return true;
//	}
	
	static void populate(char[][] board){
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				board[i][j] = '.';
			}
		}
		
		board[0][0] = '5';
		board[0][1] = '3';
		board[0][4] = '7';
		board[1][0] = '6';
		board[1][3] = '1';
		board[1][4] = '9';
		board[1][5] = '5';
		board[2][1] = '9';
		board[2][2] = '8';
		board[2][7] = '6';
		board[3][0] = '8';
		board[3][4] = '6';
		board[3][8] = '3';
		board[4][0] = '4';
		board[4][3] = '8';
		board[4][5] = '3';
		board[4][8] = '1';
		board[5][0] = '7';
		board[5][4] = '2';
		board[5][8] = '6';
		board[6][1] = '6';
		board[6][6] = '2';
		board[6][8] = '8';
		board[7][3] = '4';
		board[7][4] = '1';
		board[7][5] = '9';
		board[7][8] = '5';
		board[8][4] = '8';
		board[8][7] = '7';
		board[8][8] = '9';
	}

}
