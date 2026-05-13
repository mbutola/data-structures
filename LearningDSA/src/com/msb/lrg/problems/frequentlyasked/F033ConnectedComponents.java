package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

Connected Components
	Given a graph, count how many:
		connected components
		exist.
	What is Connected Component?
		A group of nodes where:
			every node is reachable from every other node
	Example
		Graph:
			0 --- 1      3
			|            |
			2            4
		Components:
			{0,1,2}{3,4}
		Answer:
			2
	Main Idea
		For every unvisited node:
			Start DFS
			DFS visits entire component
			Increase component count
		Each DFS call discovers:
			one complete connected component
	Technique: DFS
		Graph Representation
	Use:
		Adjacency List

 */
public class F033ConnectedComponents {

	public static void main(String[] args) {
        int n = 5;

        int[][] edges = {{0,1},{0,2},{3,4}};
        System.out.println(
                countComponents(n, edges)); // 2
	}
	
	static int countComponents(int n, int[][]edges) {
		List<List<Integer>> graph =  getGraph(n, edges);
		boolean[] visited = new boolean[n];
		int components = 0;
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				components++;
//				dfs(graph, visited, i);
				bfs(graph, visited, i);
			}
			
		}
		
		return components;
	}
	
	static void dfs(List<List<Integer>> graph, boolean[] visited, int from) {
		visited[from] = true;
		
		for(int to : graph.get(from)) {
			if(!visited[to]) {
				dfs(graph, visited, to);
			}
		}
	}
	
	static void bfs(List<List<Integer>> graph, boolean[] visited, int start) {
		visited[start] = true;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			for(int to : graph.get(from)) {
				if(!visited[to]) {
					queue.offer(to);
					visited[to] = true;
				}
			}
		}
	}

	static List<List<Integer>> getGraph(int n, int[][]edges) {
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		return graph;
	}
}
