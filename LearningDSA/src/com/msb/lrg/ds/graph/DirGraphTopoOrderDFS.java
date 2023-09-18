package com.msb.lrg.ds.graph;

import java.util.Stack;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DirGraphTopoOrderDFS {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = DirGraphTopoOrderDFS.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		System.out.printf("OP :");
		DirGraphTopoOrderDFS.printTopoOrderDFS(graph, vertices);
	}

	public static void printTopoOrderDFS(AdjGraph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				DirGraphTopoOrderDFS.printTopoOrderDFSRec(graph, visited, i, stack);
			}
		}
		while(!stack.isEmpty()) {
			System.out.printf("%4s", stack.pop());
		}
	}
	
	public static void printTopoOrderDFSRec(AdjGraph graph, boolean[] visited, int s, Stack<Integer> stack) {
		visited[s] = true;
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				DirGraphTopoOrderDFS.printTopoOrderDFSRec(graph, visited, e.destination, stack);
			}
		}
		stack.push(s);
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);
		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
	
}
