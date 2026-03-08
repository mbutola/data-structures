package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBiPartite {

	public static void main(String[] args) {
		int V = 4;
		int[][] prerequisites = {{0,1},{1,0},{1,3},{2,3},{3,1},{3,2}};
		List<List<Integer>> graph = getGraph(V, prerequisites);
		boolean res = isBiPartitieDFS(graph, V);
		System.out.println("BiPartiti DFS :: " + res);

		res = isBiPartitieBFS(graph, V);
		System.out.println("BiPartiti BFS :: " + res);
}
	
	static boolean isBiPartitieBFS(List<List<Integer>> graph, int v) {
		int[] color = new int[v];
		Arrays.fill(color, -1);
		
		for(int i=0; i<v; i++) {
			if(color[i] == -1 && !bfs(graph, color, v, 0, i))
				return false;
		}
		return true;
	}
	
	static boolean bfs(List<List<Integer>> graph, int[] color, int v, int c, int n) {
		
		color[n] = c;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		
		while(!queue.isEmpty()) {
			int from =  queue.poll();
			
			for(int to : graph.get(from)) {
				if(color[to] == color[from]) {
					return false;
				} else if(color[to] == -1) {
					color[to] = 1-color[from];
					queue.add(to);
				}
			}
		}		
		return true;
	}	
	
	static boolean isBiPartitieDFS(List<List<Integer>> graph, int v) {
		int[] color = new int[v];
		Arrays.fill(color, -1);
		
		for(int i=0; i<v; i++) {
			if(color[i] == -1 && !dfs(graph, color, v, 0, i))
				return false;
		}
		return true;
	}

	static boolean dfs(List<List<Integer>> graph, int[] color, int v, int c, int n) {
		
		color[n] = c;
		
		for(int to : graph.get(n)) {
			if(color[to] == -1) {
				color[to] = 1-c;
				if(!dfs(graph, color, v, 1-c, to))
					return false;
			}else if(color[to] == c) {
				return false;
			}
		}
		
		return true;
	}
	
	static List<List<Integer>> getGraph(int v, int[][] edges){
		
		List<List<Integer>> graph = new ArrayList<>();
		
		
		for(int i=0; i<v; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		return graph;		
	}

}


