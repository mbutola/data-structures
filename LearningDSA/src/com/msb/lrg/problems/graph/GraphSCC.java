package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GraphSCC {

	public static void main(String[] args) {
		int V = 8;
		int[][] edges = {{0,1},{1,0},{0,2},{1,3},{2,3},{3,4},{4,2},{3,5},{4,5},{4,6},{5,7},{7,6},{6,5}};
		List<List<Integer>> graph = getGraph(V, edges);
		
		int res = bridges(V, graph);
		System.out.println("Bridges :: " + res);		
	}

	static int bridges (int n, List<List<Integer>> graph) {
		boolean[] visited = new boolean[n];
		int[] low = new int[n];
		Arrays.setAll(low, i -> i);
		System.out.println("low before :: " + Arrays.toString(low));
		Stack<Integer> st = new Stack<>();
		
		for(int i=0; i<n; i++) {
			if(!visited[i])
				dfs(graph, st, visited, low, 0);
		}
		System.out.println("low after  :: " + Arrays.toString(low));
		return (int)Arrays.stream(low).distinct().count();
	}
	
	static void dfs(List<List<Integer>> graph, Stack<Integer> st, boolean[] visited, int[] low, int u) {
		visited[u] = true;
		st.push(u);
	
		for(int v: graph.get(u)) {
			if(!visited[v]) {
				dfs(graph, st, visited, low, v);
				if(st.contains(v))
					low[u] = Math.min(low[u], low[v]);
				if(low[u] == u) {
					while(st.peek() != u) 
						st.pop();
					st.pop();
				}
			}else{
				if(st.contains(v))
					low[u] = Math.min(low[u], v);
			}
		}	
	}

	static List<List<Integer>> getGraph(int n, int[][] edges){
		List<List<Integer>> graph = new ArrayList<>();
				
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);		
		}
		
		return graph;
	}

}
