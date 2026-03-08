package com.msb.lrg.problems.graph;

import java.util.Arrays;

public class GraphBellmanFord {

	public static void main(String[] args) {
	
		int V = 5;
		int[][] edges = {{1, 3, 2},{4, 3, -1},{2, 4, 1},{1, 2, 1},{0, 1, 5}};
		int src = 0;
		int[] res = bellmanFord(V, edges, src);
		System.out.println("BelmanFord :: " + Arrays.toString(res));
				
	}
	
	static int[] bellmanFord(int v, int[][] edges, int src) {
		int[] res = new int[v];
		Arrays.fill(res, (int)1e8);
		res[0] = 0;
		
		for(int count=0; count<v; count++) {
			for(int[] edge : edges) {
				int u = edge[0];
				int f = edge[1];
				int d = edge[2];
				if(res[u] != 1e8 && res[f] >  res[u] + d)
					res[f] = res[u] + d;
			}
		}
		
		for(int[] edge : edges) {
			int u = edge[0];
			int f = edge[1];
			int d = edge[2];
			if(res[u] != 1e8 && res[f] >  res[u] + d) {
				return new int[] {-1};
			}
		}
		
		return res;
	}

}
