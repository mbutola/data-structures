package com.msb.lrg.ds.graph;

import java.util.Stack;

import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;
import com.msb.lrg.ds.Edge;

public class DirGraphSCC {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = DirGraphSCC.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		DirGraphSCC.printSCC(graph, vertices);
	}
	
	public static void printSCC(AdjGraph graph, int vertices) {
		
		boolean[] visited = new boolean[vertices];
		int[] topoOrder = DirGraphSCC.getTopoOrderDFS(graph, vertices, visited);
		
		AdjGraph graphTrans = DirGraphSCC.getTranspose(graph, vertices);
		graphTrans.printGraph();
		
		System.out.println("OP :");
		DirGraphSCC.printDFS(graphTrans, vertices, topoOrder);
	}

	public static int[] getTopoOrderDFS(AdjGraph graph, int vertices, boolean[] visited) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				DirGraphSCC.getTopoOrderDFSRec(graph, visited, 1, stack);
			}
		}
		
		int i = 0;
		int[] topoOrder = new int[vertices];
		while(!stack.isEmpty()) {
			topoOrder[i++] = stack.pop();
		}
		return topoOrder;
	}
	
	public static void getTopoOrderDFSRec(AdjGraph graph, boolean[] visited, int s, Stack<Integer> stack) {
		visited[s] = true;
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				DirGraphSCC.getTopoOrderDFSRec(graph, visited, e.destination, stack);
			}
		}
		stack.push(s);
	}

	public static AdjGraph getTranspose(AdjGraph graph, int vertices){ 
		AdjGraph graphT = new AdjGraph(vertices); 
		for (int v = 0; v < vertices; v++) { 
			for(Edge e : graph.get(v)) {
				graphT.get(e.destination).add(new Edge(e.destination, e.source, e.weight));
			}
		}
		return graphT; 
	} 

	public static void printDFS(AdjGraph graphTrans, int vertices, int[] topoOrder) {
		boolean[] visited = new boolean[vertices];
		for(int v : topoOrder) {
			if (!visited[v]) { 
				DirGraphSCC.printDFSRec(graphTrans, visited, v);
				System.out.println(); 
			} 
		}
	}
	
	public static void printDFSRec(AdjGraph graph, boolean[] visited, int s) {
		visited[s] = true;
		System.out.printf("%4s", s);
		for (Edge e : graph.get(s)) {
			if(!visited[e.destination]) {
				DirGraphSCC.printDFSRec(graph, visited, e.destination);
			}
		}
	}

	public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 0, 1, dir);
		graph.addEgde(3, 4, 1, dir);

//		graph.addEgde(0, 1, 1, dir);
//		graph.addEgde(1, 2, 1, dir);
//		graph.addEgde(2, 3, 1, dir);
//		graph.addEgde(3, 0, 1, dir);
//		graph.addEgde(3, 4, 1, dir);
//		graph.addEgde(4, 5, 1, dir);
//		graph.addEgde(5, 4, 1, dir);

		return graph;
    }
	
}
