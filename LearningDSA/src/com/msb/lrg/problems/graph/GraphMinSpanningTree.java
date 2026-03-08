package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphMinSpanningTree {
	
	record Pair(int v, int w) implements Comparable<Pair>{
		@Override
		public int compareTo(Pair other) {
			int cmp = Integer.compare(this.w, other.w);
			if(cmp != 0) 
				return cmp;
			return Integer.compare(this.v, other.v);
		}
	}
	
	record Edge(int u, int v, int w) implements Comparable<Edge>{
		@Override
		public int compareTo(Edge other) {
			int cmp = Integer.compare(this.w, other.w);
			if(cmp != 0) 
				return cmp;

			cmp = Integer.compare(this.v, other.v);
			if(cmp != 0) 
				return cmp;

			return Integer.compare(this.u, other.u);
		}
	}

	public static void main(String[] args) {
		int V = 7;
		int[][] edges = {{0,3,20},{0,1,5},{1,2,5},{2,3,5},{3,4,1},{4,5,2},{5,6,2},{6,4,4}};
		List<List<Pair>> graph = getGraph(V, edges);
		boolean[] visited = new boolean[V];
		int[] parent = new int[V];
		int res = Prims(V, graph, visited, parent);
		System.out.println("MST :: " + res);
		System.out.println("MST parent:: " + Arrays.toString(parent));
		
		parent = new int[V];
		int[] rank = new int[V];
		res = Kruskal(V, edges, parent, rank);
		System.out.println("Kruskal :: " + res);
	}

	static int Kruskal(int s, int[][] edges, int[] parent, int[] rank) {
		int res = 0;
		Arrays.setAll(parent, i -> i);
		Arrays.fill(rank, 1);
//		Queue<Edge> queue = new PriorityQueue<>();
//		System.out.println("Parent :: " + Arrays.toString(parent));
//		System.out.println("Rank :: " + Arrays.toString(rank));
		
//		for(int[] edge : edges) {
//			queue.add(new Edge(edge[0], edge[1], edge[2]));
//		}
			
		List<Edge> el = new ArrayList<>();
		for(int[]  edge : edges) {
			el.add(new Edge(edge[0], edge[1], edge[2]));
		}
		
		Comparator<Edge> comp = new Comparator<>(){
			@Override
			public int compare(Edge o1, Edge o2) {
				int cmp = o1.w - o2.w;
				if(cmp != 0) 
					return cmp;

				cmp = o1.v - o2.v;
				if(cmp != 0) 
					return cmp;

				return o1.u - o2.u;
			}
		};

		el.sort(comp);
		
		for(Edge edge : el) {
			int u = edge.u;
			int v = edge.v;
			int w = edge.w;
			
			int u_p = find(u, parent);
			int v_p = find(v, parent);
			
			if(u_p != v_p) {
				res+=w;
				Union(u, v, parent, rank);
			}
		}
		
		return res;
	}
	
	static void Union(int i, int j, int[] parent, int[] rank) {
		int i_p = find(i, parent);
		int j_p = find(j, parent);
		
		if(i_p == j_p)
			return;
		
		if(rank[i_p] == rank[j_p]) {
			parent[j_p] = i_p;
			rank[i_p]++;
		}
		
		if(rank[i_p] > rank[j_p]) {
			parent[j_p] = i_p;
		}else {
			parent[i_p] = j_p;
		}
	}
	
	static int find(int i, int[] parent) {
		if(parent[i] == i)
			return i;
		
		return parent[i] = find(parent[i], parent);
	}
	
	static int Prims(int s, List<List<Pair>> graph, boolean[] visited, int[] parent) {
		int res = 0;
		Queue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(0,0));
		parent[0] = -1;
		
		while(!queue.isEmpty()) {
			Pair from = queue.poll();
			if(!visited[from.v]) {
				visited[from.v] = true;
				res += from.w;
				
				for(Pair to : graph.get(from.v)) {
					if(!visited[to.v]) {
						parent[to.v] = from.v;
						queue.add(to);
					}
				}
			}
		}
		
		return res;
	}
	
	static List<List<Pair>> getGraph(int s, int[][] edges){
		List<List<Pair>> graph = new ArrayList<>();
		
		for(int i=0; i<s; i++) {
			graph.add(new ArrayList<Pair>());
		}
		
		for(int[] edge: edges) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];
			graph.get(u).add(new Pair(v,w));
			graph.get(v).add(new Pair(u,w));
		}
		
		return graph;
	}
}
