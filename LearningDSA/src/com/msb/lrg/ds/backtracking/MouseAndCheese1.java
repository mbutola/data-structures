package com.msb.lrg.ds.backtracking;

public class MouseAndCheese1 {

	static int[][] maze = MouseAndCheese1.getMaze();
	static int[][] path = new int[maze.length][maze[0].length];

	public static void main(String[] args) {
		PrintMatrix.printArray(maze);
		boolean exists = MouseAndCheese1.next(0, 0);
		System.out.println("Path exists : " + exists);
		PrintMatrix.printArray(path);
	}
	
	public static boolean next(int row, int col) {
		
		if(row == maze.length-1 && col == maze[0].length-1) {
			path[row][col] = 1;
			return true;
		}
		if(isAllowed(row, col)) {
			path[row][col] = 1;
			if(next(row+1, col)) {
				return true;
			}else if(next(row, col+1)) {
				return true;
			}
			path[row][col] = 0;
		}
		return false;
	}
	
	public static boolean isAllowed(int row, int col) {
		
		if(row >= maze.length || col >= maze[row].length || (maze[row][col] == 0))
			return false;

		return true;
		
	}
	
	public static int[][] getMaze(){
		int[][] maze = new int[4][4];
		maze[0][0] = 1;
		maze[0][1] = 1;
		maze[0][2] = 1;
		maze[0][3] = 1;
		maze[1][0] = 1;
		maze[1][1] = 0;
		maze[1][2] = 1;
		maze[1][3] = 1;
		maze[2][0] = 0;
		maze[2][1] = 1;
		maze[2][2] = 1;
		maze[2][3] = 1;
		maze[3][0] = 1;
		maze[3][1] = 1;
		maze[3][2] = 1;
		maze[3][3] = 1;
		
		return maze;
	}
	
}
