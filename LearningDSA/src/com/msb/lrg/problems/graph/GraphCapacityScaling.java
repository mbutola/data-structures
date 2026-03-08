package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class GraphCapacityScaling {

	public static void main(String[] args) {
		int N = 6;
		int S = N-2;
		int T = N-1;
		int[][] edges = {{4,0,6},{4,1,14},{0,1,1},
						{0,2,5},{1,2,7},{1,3,10},
						{2,5,11},{2,3,1},{3,5,12}};
		List<List<Edge>> graph = getGraph(N, edges);
		int res = getMaxFlow(N, graph, S, T);
		System.out.println("Capacity Scaling :: " + res);
	}
	
	static int getMaxFlow(int N, List<List<Edge>> graph, int source, int sink) {
		int flow = 0;
		int delta = Integer.MIN_VALUE;
		int augmentFlow;
		
		for(int i=0; i<N; i++) {
			for(Edge edge : graph.get(i)) {
				delta = Math.max(delta, edge.capacity);
			}
		}
		
		delta = Integer.highestOneBit(delta);
		
		do {
			do {
				augmentFlow = dfs(graph, new boolean[N], delta, Integer.MAX_VALUE, sink, source);
				flow += augmentFlow;
			}while(augmentFlow > 0);
			delta = delta/2;
		}while(delta > 0);
		
		return flow;
	}
	
	static int dfs(List<List<Edge>> graph, boolean[] visited, int delta, int augmentFlow, int sink, int i) {
		visited[i] = true;
		if(i == sink)
			return augmentFlow;
		
		for(Edge edge : graph.get(i)) {
			if(!visited[edge.to] && edge.getResidualCapacity() >= delta) {
				augmentFlow = Math.min(augmentFlow, edge.getResidualCapacity());
				int pathFlow = dfs(graph, visited, delta, augmentFlow, sink, edge.to);
				if(pathFlow > 0) {
					edge.augment(pathFlow);
					return pathFlow;
				}
			}
		}
		return 0;
	}
	
	
//	static int getMaxFlow(int N, List<List<Edge>> graph, int source, int sink) {
//		int flow = 0;
//		int delta = Integer.MIN_VALUE;
//		
//		for(int i=0; i<N; i++) {
//			for(Edge edge : graph.get(i)) {
//				delta = Math.max(delta, edge.capacity);
//			}
//		}
//		
//		delta = Integer.highestOneBit(delta);
//		
//		do {
//			while(dfs(graph, new boolean[N], delta, sink, source)){
//				flow += delta;
//			}
//			delta = delta/2;
//		}while(delta > 0);
//		
//		return flow;
//	}
//	
//	static boolean dfs(List<List<Edge>> graph, boolean[] visited, int delta, int sink, int i) {
//		visited[i] = true;
//		if(i == sink)
//			return true;
//		
//		for(Edge edge : graph.get(i)) {
//			if(!visited[edge.to] && edge.getResidualCapacity() >= delta) {
//				if(dfs(graph, visited, delta, sink, edge.to)) {
//					edge.augment(delta);
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	
	static List<List<Edge>> getGraph(int N, int[][] edges){
		
		List<List<Edge>> graph = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		for(int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int capacity = edge[2];
			
			Edge forwardEdge = new Edge(from, to, capacity, false);
			Edge reverseEdge = new Edge(to, from, 0, true);
			
			graph.get(from).add(forwardEdge);
			graph.get(to).add(reverseEdge);
			
			forwardEdge.reverse = reverseEdge;
			reverseEdge.reverse = forwardEdge;
		}
		
		return graph;
	}
	
	static class Edge {
		int from;
		int to;
		int capacity;
		int flow;
		boolean isReverse;
		Edge reverse;
		
		Edge(int from, int to, int capacity, boolean isReverse){
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}
		
		int getResidualCapacity() {
			return capacity - flow;
		}
		
		void augment(int augmentFlow) {
			flow += augmentFlow;
			reverse.flow -= augmentFlow;
		}
	}

}
