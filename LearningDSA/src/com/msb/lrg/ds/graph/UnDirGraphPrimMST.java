package com.msb.lrg.ds.graph;

import java.util.Arrays;

import com.msb.lrg.ds.MatGraph;
import com.msb.lrg.ds.MatGraph.Direction;

public class UnDirGraphPrimMST {

	public static void main(String[] args) {
		int vertices = 4;

		MatGraph graph = UnDirGraphPrimMST.getGraph(vertices, Direction.BI);
		graph.printGraph();

		System.out.println("OP : " + UnDirGraphPrimMST.printMST(graph, vertices));
	}

	public static int printMST(MatGraph graph, int vertices) {

		int res = 0;
		int[][] mGraph = graph.getGraph();

		int[] kSet = new int[vertices];
		for (int i = 0; i < kSet.length; i++)
			kSet[i] = Integer.MAX_VALUE;
		kSet[0] = 0;

		boolean[] mSet = new boolean[vertices];

		for (int count = 0; count < vertices; count++) {

			int u = -1;
			for (int i = 0; i < vertices; i++) {
				if (!mSet[i] && (u == -1 || kSet[i] < kSet[u]))
					u = i;
			}

			mSet[u] = true;
			res += kSet[u];

			for (int v = 0; v < vertices; v++) {
				if (!mSet[v] && (mGraph[u][v] != 0 && kSet[v] > mGraph[u][v]))
					kSet[v] = mGraph[u][v];
			}
		}

		return res;
	}

	public static MatGraph getGraph(int vertices, Direction dir) {

		MatGraph graph = new MatGraph(vertices);
		graph.addEdge(0, 1, 5, dir);
		graph.addEdge(0, 2, 8, dir);
		graph.addEdge(1, 2, 10, dir);
		graph.addEdge(1, 3, 15, dir);
		graph.addEdge(2, 3, 20, dir);

		return graph;
	}
}
