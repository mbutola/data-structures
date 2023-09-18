package com.msb.lrg.ds.graph;

import java.util.Arrays;

import com.msb.lrg.ds.MatGraph;
import com.msb.lrg.ds.MatGraph.Direction;

public class UnDirDijkstraSSSP {

	public static void main(String[] args) {
		int vertices = 4;

		MatGraph graph = UnDirDijkstraSSSP.getGraph(vertices, Direction.BI);
		graph.printGraph();

		int source = 0;
		System.out.printf("OP : ");
		System.out.println(Arrays.toString(UnDirDijkstraSSSP.printSSSP(graph, vertices, source)));
	}

	public static int[] printSSSP(MatGraph graph, int vertices, int source) {

		int[][] mGraph = graph.getGraph();

		int[] dist = new int[vertices];
		for (int i = 0; i < dist.length; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[source] = 0;

		boolean[] fin = new boolean[vertices];

		for (int count = 0; count < vertices; count++) {

			int u = -1;
			for (int i = 0; i < vertices; i++) {
				if (!fin[i] && (u == -1 || dist[i] < dist[u]))
					u = i;
			}

			fin[u] = true;
			for (int v = 0; v < vertices; v++) {
				if (!fin[v] && mGraph[u][v] != 0)
					dist[v] = Math.min(dist[v], dist[u] + mGraph[u][v]);
			}
		}
		return dist;
	}

	public static MatGraph getGraph(int vertices, Direction dir) {

		MatGraph graph = new MatGraph(vertices);
		graph.addEdge(0, 1, 50, dir);
		graph.addEdge(0, 2, 100, dir);
		graph.addEdge(1, 2, 30, dir);
		graph.addEdge(1, 3, 200, dir);
		graph.addEdge(2, 3, 20, dir);

		return graph;
	}
}
