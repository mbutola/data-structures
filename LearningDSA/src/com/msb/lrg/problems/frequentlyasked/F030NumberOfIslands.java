package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Number of Islands
	Given a grid of:
		'1' = land
		'0' = water
	Count how many islands exist.
	An island is connected:
		horizontally or vertically
		(not diagonally)
	Example
		Grid:
			1 1 0 0 0
			1 1 0 0 0
			0 0 1 0 0
			0 0 0 1 1
		Answer:
			3
		Because there are:
			Top-left island
			Middle island
			Bottom-right island
	Main Idea
		Whenever we find unvisited land:
			start DFS/BFS
			and mark the whole island visited.
	Each DFS/BFS call discovers:
		one complete island
	Technique: DFS
		DFS Flow
		Traverse every cell
		If cell is land (1)
			island count++
			run DFS
		DFS marks all connected land visited

 */
public class F030NumberOfIslands {

	public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        System.out.println(numIslands(grid)); // 3
	}
	
	 public static int numIslands(char[][] grid) {
		 
		 int count = 0;
		 boolean[][] visited = new boolean[grid.length][grid[0].length];
		 int rows = grid.length;
		 int cols = grid[0].length;
		 
		 for(int i = 0; i < rows; i++) {
			 for(int j = 0; j < cols; j++) {
				 if(!visited[i][j] && grid[i][j] == '1') {
					 count++;
//					 bfs(grid, visited, i, j);
					 dfs(grid, visited, rows, cols, i, j);
				 }
			 }
		 }
		
		 return count;
	 }
	 
	 public static void dfs(char[][] grid, boolean[][] visited, int rows, int cols, int i, int j) {
//		 System.out.println(i + ":" + j);
		 visited[i][j] = true;
		 
		 int[][] cs = {{0,-1},{1,0},{0,1},{-1,0}};
		 for(int[] c : cs) {
			 int x = i + c[0];
			 int y = j + c[1];
			 if(validCell(rows, cols, x, y) && grid[x][y] == '1' && !visited[x][y]) {
				 dfs(grid, visited, rows, cols, x, y);
			 }
		 }
	 }

	 public static void bfs(char[][] grid, boolean[][] visited, int i, int j) {
		 visited[i][j] = true;
		 Queue<Pair> queue = new ArrayDeque<>();
		 queue.offer(new Pair(i,j));
		 int rows = grid.length;
		 int cols = grid[0].length;
		 
		 while(!queue.isEmpty()) {
			 Pair curr = queue.poll();
//			 System.out.println(curr.x + ":" + curr.y);
			 int[][] cs = {{0,-1},{1,0},{0,1},{-1,0}};
			 for(int[] c : cs) {
				 int x = curr.x + c[0];
				 int y = curr.y + c[1];
				 if(validCell(rows, cols, x, y) && grid[x][y] == '1' && !visited[x][y]) {
					 queue.offer(new Pair(x,y));
					 visited[x][y] = true;
				 }
			 }
		 }
	 }
	 
	 static boolean validCell(int x, int y, int i, int j) {
//		 System.out.println(x+":"+y+":"+i+":"+j);
		 return i >= 0 && i < x && j >= 0 && j < y;
	 }
	 
	 static class Pair {
		 int x, y;
		 
		 Pair(int x, int y){
			 this.x = x;
			 this.y = y;
		 }
	 }

}
