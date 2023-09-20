package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;
import com.msb.lrg.ds.Edge;

public class UnDirGraphBridges {

	public static void main(String[] args) {
		int vertices = 9;
		AdjGraph graph = UnDirGraphBridges.getGraph(vertices, Direction.BI);
		graph.printGraph();
		
		int source = 0;
		System.out.println("OP : ");
		UnDirGraphBridges.printArtiPoint(graph, vertices, source);
	}
	
	public static void printArtiPoint(AdjGraph graph, int vertices, int s) {
		
		int[] id = new int[vertices];
		int[] low = new int[vertices];
		int parent = -1;
		boolean[] visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			id[i] = i;
			low[i] = i;
		}
		UnDirGraphBridges.printArtiPointRec(graph, id, low, visited, parent, s);
	}
	
	
	public static void printArtiPointRec(AdjGraph graph, int[] id, int[] low, boolean[] visited, int parent, int source) {
		visited[source] = true;
		for (Edge e : graph.get(source)) {
			if(!visited[e.destination]) {
				UnDirGraphBridges.printArtiPointRec(graph, id, low, visited, e.source, e.destination);
				low[e.source] = Math.min(low[e.source], low[e.destination]);
				if(id[e.source] < low[e.destination])
					System.out.printf("%2s :%2s\n",e.source, e.destination);
			}else{
				if(parent != e.destination)
					low[e.source] = Math.min(low[e.source], low[e.destination]);
			}
		}
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(2, 0, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 4, 1, dir);
		graph.addEgde(2, 5, 1, dir);
		graph.addEgde(5, 6, 1, dir);
		graph.addEgde(6, 7, 1, dir);
		graph.addEgde(7, 8, 1, dir);
		graph.addEgde(8, 5, 1, dir);

		return graph;
    }
	
}
