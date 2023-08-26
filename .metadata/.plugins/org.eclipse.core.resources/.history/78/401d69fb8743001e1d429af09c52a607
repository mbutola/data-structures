package com.msb.lrg.ds.backtracking;

public class MouseAndCheese {

	static int[][] maze = MouseAndCheese.getMaze();
	static int[][] path = new int[maze.length][maze[0].length];

	public static void main(String[] args) {
		boolean exists = MouseAndCheese.next(0, 0);
		PrintMatrix.printArray(maze);
		System.out.println("Path exists : " + exists);
		PrintMatrix.printArray(path);
	}
	
	public static boolean next(int row, int col) {
		
		if(row == maze.length-1 && col == maze[0].length-1) {
			path[row][col] = 1;
			return true;
		}else{
			if(isAllowed(row+1, col)) {
				path[row][col] = 1;
				if(!next(row+1, col)) {
					path[row][col] = 0;
					return false;
				}
				return true;
			} else if(isAllowed(row, col+1)) {
				path[row][col] = 1;
				if(!next(row, col+1)) {
					path[row][col] = 0;
					return false;
				}
				return true;
			}
			path[row][col] = 0;
			return false;
		}
	}
	
	public static boolean isAllowed(int row, int col) {
		
		if(row >= maze.length || col >= maze[row].length || (maze[row][col] == 0))
			return false;

		return true;
		
	}
	
	public static int[][] getMaze(){
		int[][] maze = new int[4][4];
		maze[0][0] = 1;
		maze[0][1] = 0;
		maze[0][2] = 0;
		maze[0][3] = 0;
		maze[1][0] = 1;
		maze[1][1] = 1;
		maze[1][2] = 0;
		maze[1][3] = 1;
		maze[2][0] = 0;
		maze[2][1] = 1;
		maze[2][2] = 0;
		maze[2][3] = 0;
		maze[3][0] = 1;
		maze[3][1] = 1;
		maze[3][2] = 1;
		maze[3][3] = 1;
		
		return maze;
	}
	
}
