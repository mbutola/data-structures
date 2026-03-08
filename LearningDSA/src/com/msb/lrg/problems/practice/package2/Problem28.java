package com.msb.lrg.problems.practice.package2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Problem28 {
	
	static int time = 0;

	public static void main(String[] args) {
		int[][] adj = {{2, 3},{0},{1},{4},{}};
		int N = adj.length;
		List<List<Integer>> graph = getGraph(adj);
		int res = kosarajuSCC(graph, N);
		System.out.println("Kosaraju Result :: " + res);
		res = tarjanSCC(graph, N);
		System.out.println("Tarjan Result :: " + res);
	}

    public static int tarjanSCC(List<List<Integer>> graph, int N) {
        boolean[] inStack = new boolean[N];
        Stack<Integer> st = new Stack<>();
        int[] disc = new int[N];
        Arrays.fill(disc, -1);
        int[] low = new int[N];
        List<List<Integer>> scc = new ArrayList<>(); 
        
		for(int i = 0; i < N; i++) {
			if(disc[i] == -1) {
				dfs3(scc, graph, st, inStack, disc, low, i);
			}
		}

		Collections.sort(scc, (a,b) -> a.get(0) - b.get(0));;
		for(List<Integer> item : scc) {
			System.out.println(item.toString());
		}
		return scc.size();
    }

    public static void dfs3(List<List<Integer>> scc, 
    							List<List<Integer>> graph, 
    							Stack<Integer> st, 
    							boolean[] inStack, 
    							int[] disc, 
    							int[] low, 
    							int u) {
    	st.push(u);
    	inStack[u] = true;
    	disc[u] = time;
    	low[u] = time;
    	time++;
    	
    	for(int v : graph.get(u)) {
    		if(disc[v] == -1) {
    			dfs3(scc, graph, st, inStack, disc, low, v);
   				low[u] = Math.min(low[v], low[u]);
    		} else if(inStack[v]) {
   				low[u] = Math.min(disc[v], low[u]);
    		}
    	}
    		
   		if(low[u] == disc[u]) {
   			List<Integer> sccItem = new ArrayList<>();
//   			System.out.print("SCC: ");
   			while(true) {
   				int node = st.pop();
   				inStack[node] = false;
   				sccItem.add(node);
//   				System.out.print(node + " ");
   				if(node == u) {
   					break;
   				}
   			}
   			Collections.sort(sccItem);
   			scc.add(sccItem);
    	}
    }

    public static int kosarajuSCC(List<List<Integer>> graph, int N) {
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[N];
        List<List<Integer>> scc = new ArrayList<>(); 
        
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				dfs(graph, visited, st, i);
			}
		}
        
		List<List<Integer>> revGraph = revGraph(graph);
        visited = new boolean[N];
		
		while(!st.isEmpty()) {
			int from = st.pop();
			if(!visited[from]) {
				List<Integer> sccItem = new ArrayList<>();
				dfs2(revGraph, visited, st, sccItem, from);
				scc.add(sccItem);
			}
		}
		
//		for(List<Integer> item : scc) {
//			System.out.println(item.toString());
//		}
        return scc.size();
    }
    
    public static void dfs2(List<List<Integer>> graph, boolean[] visited, Stack<Integer> st, List<Integer> sccItem, int n) {
    	visited[n] = true;
    	sccItem.add(n);
    	for(int to : graph.get(n)) {
    		if(!visited[to]) {
    			dfs2(graph, visited, st, sccItem, to);
    		}
    	}
    }

    public static List<List<Integer>> revGraph(List<List<Integer>> graph){
		List<List<Integer>> revGraph = new ArrayList<>();
		
		for(int i = 0; i < graph.size(); i++) {
			revGraph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < graph.size(); i++) {
			for(int to : graph.get(i)) {
				revGraph.get(to).add(i);
			}
		}
		
		return revGraph;
    }
    
    public static void dfs(List<List<Integer>> graph, boolean[] visited, Stack<Integer> st, int n) {
    	visited[n] = true;
    	
    	for(int to : graph.get(n)) {
    		if(!visited[to]) {
    			dfs(graph, visited, st, to);
    		}
    	}
    	st.push(n);
    }
		
	public static List<List<Integer>> getGraph(int[][] adj) {
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i < adj.length; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < adj.length; i++) {
			for(int j = 0; j < adj[i].length; j++) {
				graph.get(i).add(adj[i][j]);
			}
		}	
		return graph;
	}
}
