package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Queue;

import com.msb.lrg.problems.frequentlyasked.F030NumberOfIslands.Pair;

/*

Flood Fill
	Given an image/grid:
		starting pixel (sr, sc)
		new color
	Replace all connected cells having the:
		same original color
		with new color.
	Connection allowed:
		up, down, left, right
	Example
		Image:
			1 1 1
			1 1 0
			1 0 1
		Start:
			sr = 1
			sc = 1
		newColor = 2
		Result
			2 2 2
			2 2 0
			2 0 1
	Only connected 1s changed.
	Bottom-right 1 remains because not connected.
	Technique: DFS
		Starting from source cell:
			Change color
			Explore neighbors
			Continue recursively
	This is basically:
		graph traversal on grid
		Main Idea
		DFS spreads like paint filling connected area.

 */
public class F031FloodFill {

	public static void main(String[] args) {
        int[][] image = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        floodFill(image, 1, 1, 2);

        for (int[] row : image) {

            for (int val : row) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
	}
	
	public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
		 int rows = image.length;
		 int cols = image[0].length;
//		 dfs(image, rows, cols, sr - 1, sc - 1, color);
		 bfs(image, rows, cols, sr - 1, sc - 1, color);
		 return image;
	}

	 public static void dfs(int[][] image, int rows, int cols, int i, int j, int color) {
		 image[i][j] = color;
		 
		 int[][] cs = {{0,-1},{1,0},{0,1},{-1,0}};
		 for(int[] c : cs) {
			 int x = i + c[0];
			 int y = j + c[1];
			 if(validCell(rows, cols, x, y) && image[x][y] == 1) {
				 dfs(image, rows, cols, x, y, color);
			 }
		 }
	 }

	 public static void bfs(int[][] image, int rows, int cols, int i, int j, int color) {
		 image[i][j] = color;
		 Queue<int[]> queue = new ArrayDeque<>();
		 queue.offer(new int[]{i,j});
		 
		 while(!queue.isEmpty()) {
			 int[] curr = queue.poll();
//			 System.out.println(curr.x + ":" + curr.y);
			 int[][] cs = {{0,-1},{1,0},{0,1},{-1,0}};
			 for(int[] c : cs) {
				 int x = curr[0] + c[0];
				 int y = curr[1] + c[1];
				 if(validCell(rows, cols, x, y) && image[x][y] == 1) {
					 queue.offer(new int[] {x,y});
					 image[x][y] = color;
				 }
			 }
		 }
	 }
	 
	 static boolean validCell(int x, int y, int i, int j) {
		 return i >= 0 && i < x && j >= 0 && j < y;
	 }

}
