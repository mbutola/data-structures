package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDFS {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = UnDirGraphDFS.getGraph(vertices, Direction.BI);
		graph.printGraph();
		
		int source = 0;
		System.out.printf("OP : ");
		UnDirGraphDFS.printDFS(graph, vertices, source);
	}
	
	public static void printDFS(AdjGraph graph, int v, int s) {
		
		boolean[] visited = new boolean[v];
		UnDirGraphDFS.printDFSRec(graph, visited, s);
	}
	
	
	public static void printDFSRec(AdjGraph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				UnDirGraphDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(1, 4, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
	
}
