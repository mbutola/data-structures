package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.msb.lrg.problems.graph.GraphEdmondKarp.Edge;

public class GraphDinic {

	static int[] level;
	
	public static void main(String[] args) {
		int N = 11;
		int source = N-2;
		int sink = N-1;
		int[][] edges = {{9,0,5},{9,1,10},{9,2,15},{1,0,5},
						{0,3,10},{1,4,20},{4,2,5},{2,5,25},
						{3,4,25},{3,6,10},{4,7,30},{5,7,20},
						{7,3,15},{5,8,10},{7,8,15},{6,10,5},
						{7,10,15},{8,10,10}};
		List<List<Edge>> graph = getGraph(N, edges);
		int res = getMaxFlow(N, graph, source, sink);
		System.out.println("Dinic :: " + res);

	}
	
	
	static int getMaxFlow(int N, List<List<Edge>> graph, int source, int sink) {
		int flow = 0;
		int augmentFlow;
		
		while(bfs(N, graph, new boolean[N], sink, source)) {
			do {
				augmentFlow = dfs(graph, new boolean[N], Integer.MAX_VALUE, sink, source);
				flow += augmentFlow;
			}while(augmentFlow > 0);
		}
		return flow;
	}
	
	static int dfs(List<List<Edge>> graph,  boolean[] visited, int augmentFlow, int sink, int i) {
		
		visited[i] = true;
		if(i == sink)
			return augmentFlow;
		
		for(Edge edge : graph.get(i)) {
			if(!visited[edge.to] && level[edge.to] == level[edge.from] + 1 && edge.getResidualCapacity() > 0) {
				augmentFlow = Math.min(augmentFlow, edge.getResidualCapacity());
				int pathFlow = dfs(graph,  visited, augmentFlow, sink, edge.to);
				if(pathFlow > 0) {
					edge.augment(pathFlow);
					return pathFlow;
				}
			}
			
		}
		return 0;
	}

	static boolean bfs(int N, List<List<Edge>> graph, boolean[] visited, int sink, int i) {
		visited[i] = true;
		level = new int[N];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(i);
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			for(Edge edge : graph.get(from)) {
				if(edge.to == sink) {
					level[edge.to] = level[edge.from] + 1;
					return true;
				}	
				if(!visited[edge.to] && edge.getResidualCapacity() > 0) {
					visited[edge.to] = true;
					level[edge.to] = level[edge.from] + 1;
					queue.offer(edge.to);
				}
			}
		}
		
		return false;
	}
	
	
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
