package com.msb.lrg.problems.practice.package0;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
	LeetCode(785)	Is Graph Bipartite

	There is an undirected graph with n nodes, where each node is numbered between 0 
	and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that 
	node u is adjacent to. More formally, for each v in graph[u], there is an undirected 
	edge between node u and node v. The graph has the following properties:
		There are no self-edges (graph[u] does not contain u).
		There are no parallel edges (graph[u] does not contain duplicate values).
		If v is in graph[u], then u is in graph[v] (the graph is undirected).
		The graph may not be connected, meaning there may be two nodes u and v such 
			that there is no path between them.
	A graph is bipartite if the nodes can be partitioned into two independent sets A and B 
	such that every edge in the graph connects a node in set A and a node in set B.

	Return true if and only if it is bipartite.
	
	Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
	Output: false
	
	Input: graph = [[1,3],[0,2],[1,3],[0,2]]
	Output: true

 */
public class Problem1 {

	public static void main(String[] args) {
		int[][] graphArray = {{1,2,3},{0,2},{0,1,3},{0,2}};
//		int[][] graphArray = {{1,3},{0,2},{1,3},{0,2}};
		List<List<Integer>> graph = getGraph(graphArray);
		int V = graphArray.length;
		boolean res = isBipartite(graph, V);
		System.out.println("Result :: " + res);
	}

	static boolean isBipartite(List<List<Integer>> graph, int V) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		for(int i=0; i<V; i++) {
			if(color[i] == -1 && !bfs(graph, color, i)) {
				return false;
			}
		}
		return true;
	}
	
	static boolean bfs(List<List<Integer>> graph, int[] color, int n) {
		color[n] = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(n);
		
		while(!queue.isEmpty()) {
			int u = queue.poll();
			for(int v: graph.get(u)) {
				if(color[v] == -1) {
					color[v] = color[u]^1;
					queue.offer(v);
				}else {
					if(color[v] == color[u]) {
						return false;
					}
				}
			}
		}		
		return true;
	}
	
	static List<List<Integer>> getGraph(int[][] graphArray) {
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i=0; i<graphArray.length; i++) {
			graph.add(new ArrayList<Integer>());
			
			for(int v: graphArray[i]) {
				graph.get(i).add(v);
			
			}
		}
		
		return graph;
 	}
	
	static void printGraph(List<List<Integer>> graph) {
		for(List<Integer> edges: graph) {
			System.out.println(edges.toString());
		}
	}

}


