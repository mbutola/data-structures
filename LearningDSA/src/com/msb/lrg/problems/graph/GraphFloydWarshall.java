package com.msb.lrg.problems.graph;

import java.util.Arrays;

import com.msb.lrg.problems.dp.DPKnapsack;

public class GraphFloydWarshall {

	public static void main(String[] args) {
//		int V = 5;
//		int[][] dist = {{0, 4, (int)1e8, 5, (int)1e8},
//						{(int)1e8, 0, 1, (int)1e8, 6},
//						{2, (int)1e8, 0, 3, (int)1e8},
//						{(int)1e8, (int)1e8, 1, 0, 2},
//						{1, (int)1e8, (int)1e8, 4, 0}};

		int V = 4;
		int[][] dist = {{0, 8, 7, -3},
						{1, 0, -1, 6},
						{9, 5, 0, 5},
						{100000000, 100000000, 100000000, 0}};
		
		FloydWarshall(V, dist);
		System.out.println("BelmanFord :: ");
		DPKnapsack.print2DArray(dist);
		
	}
	
	static void FloydWarshall(int v, int[][] dist) {
		
		for(int k=0; k<v; k++) {
			for(int i=0; i<v; i++) {
				for(int j=0; j<v; j++) {
					if(dist[i][k] != 1e8 && dist[k][j] != 1e8)
						dist[i][j] = Math.min(dist[i][j],
											dist[i][k] + dist[k][j]);
				}
			}
		}
		
	}

}
