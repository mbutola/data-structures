package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDisconnBFS {

	public static void main(String[] args) {
		int vertices = 7;

		AdjGraph graph = UnDirGraphDisconnBFS.getGraph(vertices);
		graph.printGraph();
						
		System.out.println("OP :");
		UnDirGraphDisconnBFS.printBFSDiscont(graph, vertices);
	}

	public static void printBFSDiscont(AdjGraph graph, int vertices) {
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				UnDirGraphDisconnBFS.printBFS(graph, visited, vertices, i);
				System.out.println("");
			}
		}
	}
	
	public static void printBFS(AdjGraph graph, boolean[] visited, int vertices, int source) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(source);
		visited[source] = true;
		
		while(!queue.isEmpty()) {
			int s1 = queue.poll();
			System.out.printf("%4s", s1);
			for(Edge e : graph.get(s1)) {
				if(!visited[e.destination]) {
					queue.add(e.destination);
					visited[e.destination] = true;
				}
			}
		}
	}

    public static AdjGraph getGraph(int vertices) {

		Direction dir = Direction.BI;
		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);

		graph.addEgde(4, 5, 1, dir);
		graph.addEgde(4, 6, 1, dir);
		graph.addEgde(5, 6, 1, dir);

		return graph;
    }
}
