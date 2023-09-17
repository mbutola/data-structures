package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.Graph;
import com.msb.lrg.ds.Graph.Direction;

public class GraphDisconnCntDFS {

	public static void main(String[] args) {
		int vertices = 6;

		Graph graph = GraphDisconnCntDFS.getGraph(vertices);
		graph.printGraph();
						
		System.out.println("OP : " + GraphDisconnCntDFS.printDFSDiscont(graph, vertices));
	}

	public static int printDFSDiscont(Graph graph, int vertices) {
		int count = 0;
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				GraphDisconnCntDFS.printDFSRec(graph, visited, i);
				count++;
				System.out.println("");
			}
		}
		return count;
	}
	
	public static void printDFSRec(Graph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				GraphDisconnCntDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static Graph getGraph(int vertices) {

		Direction dir = Direction.BI;
		Graph graph = new Graph(vertices);
		graph.addEgde(0, 1, 1, dir);

		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);

		return graph;
    }
	
}
