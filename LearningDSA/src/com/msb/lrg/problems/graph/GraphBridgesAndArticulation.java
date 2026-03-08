package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphBridgesAndArticulation {

	public static void main(String[] args) {
		int V = 5;
//		int[][] edges = {{0,1},{1,2},{2,0},{2,3},{3,4},{2,5},{5,6},{6,7},{7,8},{5,8}};
//		int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
		int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 0}, {3, 4}};
		List<List<Integer>> graph = getGraph(V, edges);
		
		String[] res = bridges(V, graph);
		System.out.println("Bridges :: " + Arrays.toString(res));		
	}

	static String[] bridges (int n, List<List<Integer>> graph) {
		List<String> res = new ArrayList<>();
		boolean[] visited = new boolean[n];
		int[] low = new int[n];
		Arrays.setAll(low, i -> i);
		System.out.println("low before :: " + Arrays.toString(low));
		
		for(int i=0; i<n; i++) {
			if(!visited[i])
				dfs(graph, visited, low, n, 0, res, -1);
		}
		System.out.println("low after  :: " + Arrays.toString(low));
		return res.stream().toArray(String[]::new);
	}
	
	static void dfs(List<List<Integer>> graph, boolean[] visited, int[] low, int n, int u, List<String> res, int parent) {
		visited[u] = true;
	
		for(int v: graph.get(u)) {
			if(v != parent) {
				if(!visited[v]) {
					dfs(graph, visited, low, n, v, res, u);
					low[u] = Math.min(low[u], low[v]);
					if(low[v] > u)
						res.add(u + "-" + v);
				}else{
					low[u] = Math.min(low[u], v);
				}
			}
		}
		
	}

	static List<List<Integer>> getGraph(int n, int[][] edges){
		List<List<Integer>> graph = new ArrayList<>();
				
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			graph.get(u).add(v);		
			graph.get(v).add(u);		
		}
		return graph;
	}

	
//	public static void main(String[] args) {
//	int V = 9;
//	int[][] edges = {{0,1},{1,2},{2,0},{2,3},{3,4},{2,5},{5,6},{6,7},{7,8},{5,8}};
//	List<List<Integer>> graph = getGraph(V, edges);
//	int c = 2;
//	int d = 3;
//	boolean res = bridges(V, graph, c, d);
//	System.out.println("Bridges :: " + res);		
//}
//
//static boolean bridges (int n, List<List<Integer>> graph, int c, int d) {
//	List<String> res = new ArrayList<>();
//	boolean[] visited = new boolean[n];
//	int[] low = new int[n];
//	Arrays.setAll(low, i -> i);
//	System.out.println("low before :: " + Arrays.toString(low));
//	
//	for(int i=0; i<n; i++) {
//		if(!visited[i] && dfs(n, graph, c, d, visited, low, 0, res, -1))
//			return true;
//	}
//	return false;
//}
//
//static boolean dfs(int n, List<List<Integer>> graph, int c, int d, boolean[] visited, int[] low, int u, List<String> res, int parent) {
//	visited[u] = true;
//
//	for(int v: graph.get(u)) {
//		if(v != parent) {
//			if(!visited[v]) {
//				if(dfs(n, graph, c, d, visited, low, v, res, u))
//					return true;
//				low[u] = Math.min(low[u], low[v]);
//				if(low[v] > u) {
//					if((u == c && v == d) || (u == c && v == d))
//						return true;
//				}
//			}else{
//				low[u] = Math.min(low[u], v);
//			}
//		}
//	}
//	return false;
//	
//}
}
