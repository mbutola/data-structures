package com.msb.lrg.ds.graph;

import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;
import com.msb.lrg.ds.Edge;

public class UnDirGraphArtiPoint {

	public static void main(String[] args) {
		int vertices = 9;
		AdjGraph graph = UnDirGraphArtiPoint.getGraph(vertices, Direction.BI);
		graph.printGraph();
		
		int source = 0;
		System.out.printf("OP : ");
		UnDirGraphArtiPoint.printArtiPoint(graph, vertices, source);
	}
	
	public static void printArtiPoint(AdjGraph graph, int vertices, int s) {
		
		int[] id = new int[vertices];
		int[] low = new int[vertices];
		int parent[] = new int[vertices];
		boolean[] ap =  new boolean[vertices];
		boolean[] visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			id[i] = i;
			low[i] = i;
			parent[i] = -1;
		}

		UnDirGraphArtiPoint.printArtiPointRec(graph, id, low, visited, parent, ap, s);

		for (int i = 0; i < vertices; i++) 
			if (ap[i] == true) 
				System.out.printf("%4s", i);		
	}
	
	
	public static void printArtiPointRec(AdjGraph graph, int[] id, int[] low, boolean[] visited, int[] parent, boolean[] ap, int source) {
		int children = 0;
		visited[source] = true;
		for (Edge e : graph.get(source)) {
			children++;
			if(!visited[e.destination]) {
				parent[e.destination] = source;
				UnDirGraphArtiPoint.printArtiPointRec(graph, id, low, visited, parent, ap, e.destination);
				low[e.source] = Math.min(low[e.source], low[e.destination]);
				if(parent[source] != -1 && id[e.source] <= low[e.destination])
					ap[e.source] = true;
				if(parent[source] == -1 && children > 1)
					ap[e.source] = true;
			}else{
				if(parent[source] != e.destination)
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
