package com.msb.lrg.problems.graph;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

record Pair(int node, int parent) {};

public class GraphDetectCycle {

	public static void main(String[] args) {

//		int v = 4;
//		int[][] edges = {{0,1},{0,2},{1,2},{2,3}};
		int v = 4;
		int[][] edges = {{0,1},{1,2},{2,3}};
		List<List<Integer>> graph =  getGraph(v, edges);
		
		boolean res = isCycleDFS(graph, v);
		System.out.println("isCycle DFS :: " + res);

		res = isCycleBFS(graph, v);
		System.out.println("isCycle BFS :: " + res);
	}
	
	static boolean isCycleDFS(List<List<Integer>> graph, int v) {
		boolean visited[] = new boolean[v];
		
		for(int i=0; i<v; i++) {
			if(!visited[i] && dfs(graph, visited, v, i, -1))
				return true;
		}
		
		return false;
	}
	
	static boolean dfs(List<List<Integer>> graph, boolean[] visited, int v, int i, int parent) {
		
		visited[i] = true;
		
		for(int to : graph.get(i)) {
			
			if(to != parent) {
				if(visited[to])
					return true;
				
				if(dfs(graph, visited, v, to, i))
					return true;				
			}
		}
		
		return false;
	}
	
	static boolean isCycleBFS(List<List<Integer>> graph, int v) {
		
		boolean visited[] = new boolean[v];
		
		for(int i=0; i<v; i++) {
			if(!visited[i] && bfs(graph, visited, v, i))
				return true;
		}
		
		return false;
	}
	
	static boolean bfs(List<List<Integer>> graph, boolean[] visited, int v, int i) {
		
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(i, -1));
		visited[i] = true;
		
		while(!queue.isEmpty()) {
			
			Pair from = queue.poll();
			
			for(int to : graph.get(from.node())) {
				if(to != from.parent()) {
					if(visited[to])
						return true;
					
					queue.offer(new Pair(to, from.node()));
					visited[to] = true;
				}
			}
		}
		
		return false;
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
