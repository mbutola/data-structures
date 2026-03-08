package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphDijkstra {
	
	record Pair(int node, int weight) implements Comparable<Pair> {
		@Override
		public int compareTo(Pair other) {
			int cmp = Integer.compare(this.weight, other.weight);
			if(cmp != 0)
				return cmp;
			
			return Integer.compare(this.node, other.node);
		}
		
		@Override
		public boolean equals(Object other) {
			return this.node == ((Pair)other).node;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.node);			
		}
	};

	public static void main(String[] args) {
		int V = 3;
		int src = 2;
		int[][] edges = {{0, 1, 1}, {1, 2, 3}, {0, 2, 6}};
		List<List<Pair>> graph = getGraph(V, edges);
		int[] res = dijkstra(V, graph, src);
		System.out.println("Dijkstra queue :: " + Arrays.toString(res));

		res = dijkstraSet(V, graph, src);
		System.out.println("Dijkstra set :: " + Arrays.toString(res));
}

	static List<List<Pair>> getGraph(int v, int[][] edges){
		List<List<Pair>> graph = new ArrayList<>();
		
		for(int i=0; i<v; i++) {
			graph.add(new ArrayList<Pair>());
		}
		
		for(int[] edge : edges) {
			graph.get(edge[0]).add(new Pair(edge[1], edge[2]));
			graph.get(edge[1]).add(new Pair(edge[0], edge[2]));
		}
		
		return graph;
	}
	
	static int[] dijkstra(int v, List<List<Pair>> graph, int src){
		int[] res = new int[v];
		Arrays.fill(res, 1000000);
		
		Queue<Pair> minPq = new PriorityQueue<>();
		res[src] = 0;
		
		minPq.offer(new Pair(src,0));
		
		while(!minPq.isEmpty()) {
			Pair from = minPq.poll();
			for(Pair to : graph.get(from.node())) {
				int new_wt = res[from.node] + to.weight();
				if(res[to.node()] > new_wt) {
					res[to.node()] = new_wt;
					minPq.offer(new Pair(to.node(), new_wt));
				}
			}
		}
		
		return res;
	}
	
	static int[] dijkstraSet(int v, List<List<Pair>> graph, int src){
		int[] res = new int[v];
		Arrays.fill(res, 1000000);
		
		LinkedHashSet<Pair> set = new LinkedHashSet<>();
		res[src] = 0;
		
		set.add(new Pair(src,0));
		
		while(!set.isEmpty()) {
			Pair from = set.getFirst();
			for(Pair to : graph.get(from.node())) {
				int new_wt = res[from.node] + to.weight();
				if(res[to.node()] > new_wt) {
					res[to.node()] = new_wt;
					if(set.contains(to)) 
						set.remove(to);
					set.add(new Pair(to.node(), new_wt));
				}
			}
			set.remove(from);
		}
		
		return res;
	}	
}
