package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphTopologicalSort {

	public static void main(String[] args) {
		
		int V = 6;
		int[][] prerequisites = {{0,3},{0,2},{2,3},{3,1},{1,4},{2,1},{5,4},{5,1}};
		List<List<Integer>> graph = getGraph(V, prerequisites);
		
		List<Integer> res = new ArrayList<>();
		topSortDFS(res, graph, V);
		System.out.println("Result DFS ::" + res.reversed().toString() );

		res = topSortBFS(graph, V);
		System.out.println("Result BFS :: " + res.toString() );
		
	}
	
	static void topSortDFS(List<Integer> res, List<List<Integer>> graph, int v) {
		boolean[] visited = new boolean[v];
		for(int i=0; i<v; i++) {
			if(!visited[i])
				dfs(res, graph, visited, v, i);
		}
	}
	
	static void dfs(List<Integer> res, List<List<Integer>> graph, boolean[] visited, int v, int i){
		visited[i] = true;
		
		for(int to : graph.get(i)) {
			if(!visited[to]) {
				dfs(res, graph, visited, v, to);
			}
		}		
		res.add(i);		
	}
	
	static List<Integer> topSortBFS(List<List<Integer>> graph, int v){
		List<Integer> res = new ArrayList<>();
		int[] inDegree = new int[v];
		
		for(int i=0; i<v; i++) {
			for(int to : graph.get(i)) 
				inDegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0; i<v; i++) {
			if(inDegree[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			res.add(from);
			
			for(int to : graph.get(from)) {
				inDegree[to]--;
				
				if(inDegree[to] == 0)
					queue.add(to);
			}
		}
		return res;
	}

	static List<List<Integer>> getGraph(int v, int[][] prerequisites){
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i=0; i<v; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : prerequisites) {
			graph.get(edge[0]).add(edge[1]);
		}
		
		return graph;
	}

}
