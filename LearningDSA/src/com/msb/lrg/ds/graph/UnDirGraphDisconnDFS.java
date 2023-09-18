package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDisconnDFS {

	public static void main(String[] args) {
		int vertices = 9;

		AdjGraph graph = UnDirGraphDisconnDFS.getGraph(vertices, Direction.BI);
		graph.printGraph();
						
		System.out.println("OP :");
		UnDirGraphDisconnDFS.printDFSDiscont(graph, vertices);
	}

	public static void printDFSDiscont(AdjGraph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				UnDirGraphDisconnDFS.printDFSRec(graph, visited, i);
				System.out.println("");
			}
		}
	}
	
	public static void printDFSRec(AdjGraph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				UnDirGraphDisconnDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 2, 1, dir);

		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
	
}
