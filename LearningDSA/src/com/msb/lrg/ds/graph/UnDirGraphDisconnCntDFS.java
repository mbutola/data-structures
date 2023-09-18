package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDisconnCntDFS {

	public static void main(String[] args) {
		int vertices = 6;

		AdjGraph graph = UnDirGraphDisconnCntDFS.getGraph(vertices);
		graph.printGraph();
						
		System.out.println("OP : " + UnDirGraphDisconnCntDFS.printDFSDiscont(graph, vertices));
	}

	public static int printDFSDiscont(AdjGraph graph, int vertices) {
		int count = 0;
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				UnDirGraphDisconnCntDFS.printDFSRec(graph, visited, i);
				count++;
				System.out.println("");
			}
		}
		return count;
	}
	
	public static void printDFSRec(AdjGraph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				UnDirGraphDisconnCntDFS.printDFSRec(graph, visited, e.destination);
			}
		}
	}

    public static AdjGraph getGraph(int vertices) {

		Direction dir = Direction.BI;
		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);

		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);

		return graph;
    }
	
}
