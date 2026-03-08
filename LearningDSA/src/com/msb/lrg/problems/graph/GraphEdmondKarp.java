package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphEdmondKarp {

	public static void main(String[] args) {
		int N = 11;
		int source = N-2;
		int sink = N-1;
		int[][] edges = {{9,0,5},{9,1,10},{9,2,5},{1,0,15},
						{0,3,10},{1,4,20},{4,2,5},{2,5,10},
						{3,4,25},{3,6,10},{4,7,30},{5,7,5},
						{7,3,15},{5,8,10},{7,8,5},{6,10,5},
						{7,10,15},{8,10,10}};
		List<List<Edge>> graph = getGraph(N, edges);
		int res = getMaxFlow(N, graph, source, sink);
		System.out.println("EdmondKarp :: " + res);

	}

	static int getMaxFlow(int N, List<List<Edge>> graph, int source, int sink) {
		int flow = 0;
		int augmentFlow = 0;
		boolean[] visited;
		Edge[] parent;
		
		do {
			augmentFlow = 0;
			visited = new boolean[N];
			parent = new Edge[N];
			Arrays.fill(parent, null);
			augmentFlow = bfs(graph, visited, parent, source, sink);
				flow += augmentFlow;
		}while(augmentFlow > 0);
		
		return flow;
	}
	
	static int bfs(List<List<Edge>> graph, boolean[] visited, Edge[] parent, int source, int sink) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[source] = true;
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			if(from == sink)
				break;
			
			for(Edge edge : graph.get(from)) {
				if(!visited[edge.to] && edge.getResidualCapacity() > 0) {
					visited[edge.to] = true;
					parent[edge.to] = edge;
					queue.offer(edge.to);
				}
			}
		}

		if(parent[sink] == null)
				return 0;
		
		int augmentFlow = Integer.MAX_VALUE;
		
		for(Edge e = parent[sink]; e != null; e = parent[e.from]){
			augmentFlow = Math.min(augmentFlow, e.getResidualCapacity());
		}
		
		for(Edge e = parent[sink]; e != null; e = parent[e.from]){
			e.augment(augmentFlow);
		}

		return augmentFlow;
	}
	
	
//	static int getMaxFlow(int N, List<List<Edge>> graph, int source, int sink) {
//		int flow = 0;
//		int augmentFlow = 0;
//		boolean[] visited;
//		int[] parent;
//		
//		do {
//			augmentFlow = 0;
//			visited = new boolean[N];
//			parent = new int[N];
//			Arrays.fill(parent, -1);
//			if(bfs(graph, visited, parent, source, sink)) {
//				augmentFlow = Integer.MAX_VALUE;
//				int to = sink;
//				int from = parent[to];
//				while(from != -1) {
//					for(Edge edge : graph.get(from)) {
//						if(edge.to == to)
//							augmentFlow = Math.min(augmentFlow, edge.getResidualCapacity());
//					}
//					to = from;
//					from = parent[to];
//				}
//				
//				to = sink;
//				from = parent[to];
//				while(from != -1) {
//					for(Edge edge : graph.get(from)) {
//						if(edge.to == to)
//							edge.augment(augmentFlow);
//					}
//					to = from;
//					from = parent[to];
//				}
//				
//				flow += augmentFlow;
//			};
//		}while(augmentFlow > 0);
//		
//		return flow;
//	}
//	
//	static boolean bfs(List<List<Edge>> graph, boolean[] visited, int[] parent, int source, int sink) {
//
//		Queue<Integer> queue = new LinkedList<>();
//		queue.add(source);
//		visited[source] = true;
//		
//		while(!queue.isEmpty()) {
//			int from = queue.poll();
//			if(from == sink)
//				break;
//			
//			for(Edge edge : graph.get(from)) {
//				if(!visited[edge.to] && edge.getResidualCapacity() > 0) {
//					if(edge.to == sink) {
//						parent[edge.to] = edge.from;
//						return true;
//					}
//					visited[edge.to] = true;
//					parent[edge.to] = edge.from;
//					queue.offer(edge.to);
//				}
//			}
//		}
//
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
			
			forwardEdge.reverse = reverseEdge;
			reverseEdge.reverse = forwardEdge;
			
			graph.get(from).add(forwardEdge);
			graph.get(to).add(reverseEdge);
		}
		
		return graph;
	}

	static class Edge{
		
		int from;
		int to;
		int capacity;
		int flow;
		boolean isReverse;
		Edge reverse;
		
		Edge (int from, int to, int capacity, boolean isReverse){
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
			if(isReverse) {
				this.capacity = 0;
			}
		}
		
		int getResidualCapacity() {
			return capacity - flow;
		}
		
		void setFlow(int augmentFlow) {
			flow += augmentFlow;
		}
		
		void augment(int augmentFlow) {
			flow += augmentFlow;
			reverse.flow -= augmentFlow;;
		}
	}
}

