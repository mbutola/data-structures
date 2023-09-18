package com.msb.lrg.ds.graph;

import java.util.Arrays;
import java.util.Stack;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DAGShortPathUsgTopo {

	public static void main(String[] args) {
		int vertices = 6;
		AdjGraph graph = DAGShortPathUsgTopo.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		int source = 0;
		System.out.printf("OP :");
		DAGShortPathUsgTopo.printShortestPath(graph, vertices, source);
		System.out.println("");

		vertices = 4;
		graph = DAGShortPathUsgTopo.getGraph2(vertices, Direction.UNI);
		graph.printGraph();
		
		source = 1;
		System.out.printf("OP :");
		DAGShortPathUsgTopo.printShortestPath(graph, vertices, source);
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

	public static void printShortestPath(AdjGraph graph, int vertices, int source) {
		
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<Integer>();
		DAGShortPathUsgTopo.printTopoOrderDFSRec(graph, visited, source, stack);

		int[] dist = new int[vertices];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[source] = 0;

		while(!stack.isEmpty()) {
			int s1 = stack.pop();
			for(Edge e : graph.get(s1)) {
				if(dist[e.destination] > dist[e.source] + e.weight)
					dist[e.destination] = dist[e.source] + e.weight;
			}
		}
		System.out.println(Arrays.toString(dist));
	}

	public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 2, dir);
		graph.addEgde(0, 4, 1, dir);
		graph.addEgde(1, 2, 3, dir);
		graph.addEgde(2, 3, 6, dir);
		graph.addEgde(4, 2, 2, dir);
		graph.addEgde(4, 5, 4, dir);
		graph.addEgde(5, 3, 1, dir);

		return graph;
    }

	public static AdjGraph getGraph2(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 2, dir);
		graph.addEgde(1, 2, 3, dir);
		graph.addEgde(1, 3, 2, dir);
		graph.addEgde(2, 3, 4, dir);

		return graph;
    }
}
