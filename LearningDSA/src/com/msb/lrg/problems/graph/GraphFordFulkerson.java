package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphFordFulkerson {

//	static int N = 11;
//	static int S = N-2;
//	static int T = N-1;
//	static int V = N-2;
//	static boolean pathFound = true;
	
	public static void main(String[] args) {
		int N = 11;
		int source = N-2;
		int sink = N-1;
		int[][] edges = {{9,0,10},{9,1,5},{9,2,10},
						{0,3,10},{3,1,20},{4,1,15},
						{1,2,10},{2,5,15},{4,3,3},
						{5,4,4},{3,6,15},{7,4,10},
						{7,5,7},{5,8,10},{6,7,10},
						{6,10,15},{8,10,10}};
		List<List<Edge>> graph = getGraph(N, edges);
		
		int res = getMaxFlow(N, graph, source, sink);
		System.out.println("Ford Fulkerson :: " + res);

	}
	
	static int getMaxFlow(int n, List<List<Edge>> graph, int source, int sink) {
		int flow = 0;
		boolean[] visited;
		int augmentFlow;
		
		do {
			visited = new boolean[n];
			augmentFlow = dfs(graph, visited, Integer.MAX_VALUE, sink, source);
			flow+=augmentFlow;
		}while(augmentFlow > 0);
		
		return flow;
	}
	
	static int dfs(List<List<Edge>> graph, boolean[] visited, int augmentFlow, int sink, int i) {
		
		if(i == sink)
			return augmentFlow;

		visited[i] = true;

		for(Edge edge : graph.get(i)) {
			if(!visited[edge.to] && edge.getResidualCapacity() > 0) {
				int pathFlow = dfs(graph, visited, Math.min(augmentFlow, edge.getResidualCapacity()), sink, edge.to);
				if(pathFlow > 0) {
					edge.augment(pathFlow);
					return pathFlow;
				}
			}
		}
		return 0;
	}
	
//	static int getMaxFlow(int n, List<List<Edge>> graph) {
//		int res = 0;
//		boolean[] visited;
//		
//		while(pathFound){
//			visited = new boolean[n];
//			pathFound = false;
//			augmentFlow = 0;
//			dfs(graph, visited, S);
//			res += augmentFlow; 
//		}
//		
//		return res;
//	}
//	
//	static void dfs(List<List<Edge>> graph, boolean[] visited, int i) {
//		
//		if(i == T) {
//			pathFound = true;
//			return;
//		}
//		
//		visited[i] = true;
//		
//		for(Edge edge : graph.get(i)) {
//			if(!visited[edge.to] && edge.getResidualCapacity() > 0) {
//				int residualCapacity = edge.getResidualCapacity();
//				augmentFlow = (edge.from == S) ? residualCapacity : Math.min(augmentFlow, residualCapacity);
//				if(residualCapacity >= augmentFlow) {
//					dfs(graph, visited, edge.to);
//					if(pathFound) {
//						edge.augment(augmentFlow);
//						return;
//					}
//				}
//			}
//		}
//	}
	
	static List<List<Edge>> getGraph(int n, int[][] edges){
		List<List<Edge>> graph = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		for(int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int capacity = edge[2];
			Edge forwardEdge = new Edge(from, to, capacity, false);
			Edge reverseEdge = new Edge(to, from, capacity, true);
			forwardEdge.reverse = reverseEdge;
			reverseEdge.reverse = forwardEdge;
			
			graph.get(forwardEdge.from).add(forwardEdge);
			graph.get(reverseEdge.from).add(reverseEdge);
		}
		
		return graph;
	}

	static class Edge{
		int from;
		int to;
		int capacity;
		int flow = 0;
		boolean isReverse = false;
		Edge reverse;
		
		Edge(int from, int to, int capacity, boolean isReverse){
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
			if(isReverse) { 
				this.capacity = 0;
				this.flow = -capacity;
			}
			this.isReverse = isReverse;
		}
		
		int getResidualCapacity() {
			return capacity - flow;
		}
		
		void augment(int augmentFlow) {
			setFlow(augmentFlow);
			reverse.setFlow(augmentFlow);
		}
		
		void setFlow(int augmentFlow) {
			this.flow+=augmentFlow;
		}
		
	}		
}


