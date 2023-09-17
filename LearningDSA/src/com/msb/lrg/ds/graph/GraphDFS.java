package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.Graph;
import com.msb.lrg.ds.Graph.Direction;

public class GraphDFS {

	public static void main(String[] args) {
		Graph graph = GraphDFS.getGraph();
		graph.printGraph();
		
		int source = 0;
		System.out.printf("OP : ");
		GraphDFS.printDFS(graph, 5, source);
	}
	
	public static void printDFS(Graph graph, int v, int s) {
		
		boolean[] visited = new boolean[v];
		GraphDFS.printDFSRec(graph, visited, s);
	}
	
	
	public static void printDFSRec(Graph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				GraphDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static Graph getGraph() {

		Direction dir = Direction.BI;
    	int vertices = 5;
		Graph graph = new Graph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(1, 4, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
	
}
