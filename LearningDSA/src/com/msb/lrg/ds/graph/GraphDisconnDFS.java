package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.Graph;
import com.msb.lrg.ds.Graph.Direction;

public class GraphDisconnDFS {

	public static void main(String[] args) {
		int vertices = 9;

		Graph graph = GraphDisconnDFS.getGraph(vertices);
		graph.printGraph();
						
		System.out.println("OP :");
		GraphDisconnDFS.printDFSDiscont(graph, vertices);
	}

	public static void printDFSDiscont(Graph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				GraphDisconnDFS.printDFSRec(graph, visited, i);
				System.out.println("");
			}
		}
	}
	
	public static void printDFSRec(Graph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				GraphDisconnDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static Graph getGraph(int vertices) {

		Direction dir = Direction.BI;
		Graph graph = new Graph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 2, 1, dir);

		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
	
}
