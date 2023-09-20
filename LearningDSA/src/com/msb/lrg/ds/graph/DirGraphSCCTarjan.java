package com.msb.lrg.ds.graph;

import java.util.Stack;

import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DirGraphSCCTarjan {

	public static int Time = 0;

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = DirGraphSCCTarjan.getGraph(vertices, Direction.UNI);
		graph.printGraph();

		int source = 0;
		System.out.println("OP : ");
		DirGraphSCCTarjan.printSCC(graph, vertices, source);
	}

	public static void printSCC(AdjGraph graph, int vertices, int s) {

		int[] id = new int[vertices];
		int[] low = new int[vertices];
		Stack<Integer> st = new Stack<Integer>();
		boolean[] stackMmember = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			id[i] = -1;
			low[i] = -1;
		}
		DirGraphSCCTarjan.printSCCRec(graph, id, low, stackMmember, st, s);
	}

	public static void printSCCRec(AdjGraph graph, int[] id, int[] low, boolean[] stackMember, Stack<Integer> st,
			int source) {
		low[source] = Time;
		id[source] = Time;
		Time++;
		stackMember[source] = true;
		st.push(source);
		for (Edge e : graph.get(source)) {
			if (id[e.destination] == -1) {
				DirGraphSCCTarjan.printSCCRec(graph, id, low, stackMember, st, e.destination);
				low[e.source] = Math.min(low[e.source], low[e.destination]);
			} else {
				if (stackMember[e.destination])
					low[e.source] = Math.min(low[e.source], id[e.destination]);
			}
		}
		int w = -1;
		if (id[source] == low[source]) {
			while (w != source) {
				w = (int) st.pop();
				System.out.printf("%4s", w);
				stackMember[w] = false;
			}
			System.out.println("");
		}
	}

	public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(2, 1, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 4, 1, dir);
		graph.addEgde(4, 2, 1, dir);

		return graph;
	}

}
