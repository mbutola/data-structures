package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDetCycle {

	public static void main(String[] args) {
		int vertices = 6;
		AdjGraph graph = UnDirGraphDetCycle.getGraphConn(vertices, Direction.BI);
		graph.printGraph();
		
		System.out.printf("OP connected : " + UnDirGraphDetCycle.detectCycleDFSDisconn(graph, vertices));
		System.out.println("");
		
		graph = UnDirGraphDetCycle.getGraphDisconn(vertices, Direction.BI);
		graph.printGraph();
		
		System.out.printf("OP disconnected : " + UnDirGraphDetCycle.detectCycleDFSDisconn(graph, vertices));
	}

	public static boolean detectCycleDFSDisconn(AdjGraph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				if(UnDirGraphDetCycle.detectCycleDFSDisconnRec(graph, visited, i, -1))
						return true;
			}
		}
		
		return false;
	}
	
	public static boolean detectCycleDFSDisconnRec(AdjGraph graph, boolean[] visited, int s, int parent) {
		visited[s] = true;
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				if(UnDirGraphDetCycle.detectCycleDFSDisconnRec(graph, visited, e.destination, e.source)) 
					return true;
			}else {
				if(e.destination != parent) {
					return true;
				}
			}
		}
		
		return false;
	}

    public static AdjGraph getGraphConn(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);
		graph.addEgde(4, 5, 1, dir);

		return graph;
    }
    public static AdjGraph getGraphDisconn(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(3, 4, 1, dir);
		graph.addEgde(3, 5, 1, dir);
		graph.addEgde(4, 5, 1, dir);

		return graph;
    }
}
