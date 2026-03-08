package com.msb.lrg.problems.practice.package2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
	Critical Connections in a Network :: LeetCode (1192, Hard)

	There are n servers numbered from 0 to n - 1 connected by undirected server-to-server 
	connections forming a network where connections[i] = [ai, bi] represents a connection 
	between servers ai and bi. Any server can reach other servers directly or indirectly through the 
	network.
	
	A critical connection is a connection that, if removed, will make some servers unable to reach some 
	other server.
	
	Return all critical connections in the network in any order.
	
	Example 1:
		Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
		Output: [[1,3]]
		Explanation: [[3,1]] is also accepted.
	Example 2:
		Input: n = 2, connections = [[0,1]]
		Output: [[0,1]]
	Constraints:
		2 <= n <= 105
		n - 1 <= connections.length <= 105
		0 <= ai, bi <= n - 1
		ai != bi
		There are no repeated connections.

 */
public class Problem29 {
	
	public static int time = 0;

	public static void main(String[] args) {
		int N = 5;
//		List<List<Integer>> connections = List.of(List.of(0,1),
//												List.of(1,2),
//												List.of(2,0),
//												List.of(1,3));
		List<List<Integer>> connections = List.of(List.of(1,0),
												List.of(2,0),
												List.of(3,2),
												List.of(4,2),
												List.of(4,3),
												List.of(3,0),
												List.of(4,0));
		List<List<Integer>> graph = getGraph(connections, N);
		List<List<Integer>> bridges = criticalConnections(graph, N);
		for(List<Integer> bridge : bridges) {
			System.out.println(bridge.toString());
		}
	}

    public static List<List<Integer>> criticalConnections(List<List<Integer>> graph, int N) {
    	
    	List<List<Integer>> bridges = new ArrayList<>();
    	int[] disc = new int[N];
    	Arrays.fill(disc, -1);
    	int[] low = new int[N];
    	int parent = -1;
    	
    	for(int i = 0; i < N; i++) {
    		if(disc[i] == -1) {
    			dfs(bridges, graph, disc, low, parent, i);
    		}
    	}    	
        
    	return bridges;
    }
    
	public static void dfs(List<List<Integer>> bridges, List<List<Integer>> graph, int[] disc, int[] low, int parent, int u) {
		disc[u] = time;
		low[u] = time;
		time++;
		
		for(int v : graph.get(u)) {
			if(v == parent)
				continue;
			
			if(disc[v] == -1) {
				dfs(bridges, graph, disc, low, u, v);
				low[u] = Math.min(low[u], low[v]);
				if(low[v] > disc[u]) {
					List<Integer> bridge = new ArrayList<>();
					bridge.add(u);
					bridge.add(v);
					bridges.add(bridge);
				}
			} else {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
	}

    public static List<List<Integer>> getGraph(List<List<Integer>> connections, int N){
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0; i < N; i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(List<Integer> edge : connections) {
   			graph.get(edge.get(0)).add(edge.get(1));
   			graph.get(edge.get(1)).add(edge.get(0));
    	}
     	
    	return graph;
    }
}
