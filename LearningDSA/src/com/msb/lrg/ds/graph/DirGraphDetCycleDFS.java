package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DirGraphDetCycleDFS {

	public static void main(String[] args) {
		int vertices = 6;
		AdjGraph graph = DirGraphDetCycleDFS.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		System.out.printf("OP : " + DirGraphDetCycleDFS.detectCycleDFSDisconn(graph, vertices));
	}

	public static boolean detectCycleDFSDisconn(AdjGraph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		boolean[] recSt = new boolean[vertices]; 
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				if(DirGraphDetCycleDFS.detectCycleDFSDisconnRec(graph, visited, i, recSt))
						return true;
			}
		}
		
		return false;
	}
	
	public static boolean detectCycleDFSDisconnRec(AdjGraph graph, boolean[] visited, int s, boolean[] recSt) {
		visited[s] = true;
		recSt[s] = true;
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				if(DirGraphDetCycleDFS.detectCycleDFSDisconnRec(graph, visited, e.destination, recSt))
					return true;
			}else {
				if(recSt[e.destination]) 
					return true;
			}
		}
		recSt[s] = false;
		return false;
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(2, 1, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 4, 1, dir);
		graph.addEgde(4, 5, 1, dir);
		graph.addEgde(5, 3, 1, dir);

		return graph;
    }
}
