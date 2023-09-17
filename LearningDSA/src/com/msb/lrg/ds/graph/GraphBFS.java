package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.Graph;
import com.msb.lrg.ds.Graph.Direction;

public class GraphBFS {

	public static void main(String[] args) {
		Graph graph = GraphBFS.getGraph();
		graph.printGraph();
		
		System.out.printf("OP : ");
		GraphBFS.printBFS(graph, 5, 0);
	}
	
	public static void printBFS(Graph graph, int v, int s) {
		
		boolean[] visited = new boolean[v];
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<ArrayList<Edge>> adj = graph.getList();
		
		queue.add(s);
		visited[s] = true;
		
		while(!queue.isEmpty()) {
			int s1 = queue.poll();
			System.out.printf("%4s", s1);
			for(Edge e : adj.get(s1)) {
				if(!visited[e.destination]) {
					queue.add(e.destination);
					visited[e.destination] = true;
				}
			}
		}
	}

    public static Graph getGraph() {

		Direction dir = Direction.BI;
    	int vertices = 5;
		Graph graph = new Graph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);
		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
}
