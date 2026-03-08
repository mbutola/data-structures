package com.msb.lrg.problems.practice.package3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
	
	You are given an undirected graph with V vertices and E edges. The graph is represented 
	as a 2D array edges[][], where each element edges[i] = [u, v] indicates an undirected edge 
	between vertices u and v.
	Your task is to return all the articulation points (or cut vertices) in the graph.
	An articulation point is a vertex whose removal, along with all its connected edges, 
	increases the number of connected components in the graph.
	
	Note: The graph may be disconnected, i.e., it may consist of more than one connected component.
	If no such point exists, return {-1}.
	
	Examples :
		Input: V = 5, edges[][] = [[0, 1], [1, 4], [4, 3], [4, 2], [2, 3]]
		Output: [1, 4]
		Explanation: Removing the vertex 1 or 4 will disconnects the graph as-
	Input: V = 4, edges[][] = [[0, 1], [0, 2]]
		Output: [0]
		Explanation: Removing the vertex 0 will increase the number of disconnected components to 3.  
	Constraints:
		1 ≤ V, E ≤ 104

 */
public class Problem30 {
	
	static int time = 0;

	public static void main(String[] args) {
		 int N  = 5;
		 int[][] edges = {{0,1},{1,4},{4,3},{4,2},{2,3}};
//		 int N  = 6;
//		 int[][] edges = {{1,2},{1,3},{1,4},{0,4},{0,3},{2,4},{0,2},{3,5},{2,3}};
		 List<List<Integer>> graph = getGraph(edges, N);
		 List<Integer> res = articulationPoints(graph, N);
		 System.out.println("Result :: " + res.toString());
	}

    public static List<Integer> articulationPoints(List<List<Integer>> graph, int N) {
    	
    	Set<Integer> ap = new HashSet<>();
    	int[] disc = new int[N];
    	Arrays.fill(disc, -1);
    	int[] low = new int[N];
    	int[] parent = new int[N];
    	
    	
    	for(int i = 0; i < N; i++) {
    		if(disc[i] == -1) {
    			parent[i] = -1;
    			dfs(ap, graph, disc, low, parent, i);
    		}
    	}
    	List<Integer> result = new ArrayList<Integer>(ap);
    	if(ap.size() == 0) {
    		return new ArrayList<>(List.of(-1));
    	}

    	Collections.sort(result);
        return result;
    }
    
	public static void dfs(Set<Integer> ap, List<List<Integer>> graph, int[] disc, int[] low, int[] parent, int u) {
		disc[u] = time;
		low[u] = time;
		time++;
		int children = 0;
		
		for(int v : graph.get(u)) {
			if(disc[v] == -1) {
				children++;
				parent[v] = u;
				dfs(ap, graph, disc, low, parent, v);
				low[u] = Math.min(low[u], low[v]);
				if(parent[u] != -1 && low[v] >= disc[u]) {
					ap.add(u);
				}
			} else {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
		
		if(parent[u] == -1 && children > 1)
			ap.add(u);
			
	}

    public static List<List<Integer>> getGraph(int[][] edges, int N){
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0; i < N; i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(int i = 0; i < edges.length; i++) {
   			graph.get(edges[i][0]).add(edges[i][1]);
   			graph.get(edges[i][1]).add(edges[i][0]);
    	}
     	
    	return graph;
    }
	
}
