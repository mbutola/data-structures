package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphDisconnCntBFS {

	public static void main(String[] args) {
		int vertices = 9;

		AdjGraph graph = UnDirGraphDisconnCntBFS.getGraph(vertices);
		graph.printGraph();
						
		System.out.println("OP : " + UnDirGraphDisconnCntBFS.printBFSDiscont(graph, vertices));
	}

	public static int printBFSDiscont(AdjGraph graph, int vertices) {
		int count = 0;
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if(!visited[i]) {
				UnDirGraphDisconnBFS.printBFS(graph, visited, vertices, i);
				count++;
				System.out.println("");
			}
		}
		return count;
	}
		
	public static void printBFS(AdjGraph graph, boolean[] visited, int v, int s) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<ArrayList<Edge>> adj = graph.getList();
		
		queue.add(s);
		visited[s] = true;
		
		while(!queue.isEmpty()) {
			int s1 = queue.poll();
			System.out.printf("%4s", s1);
			for(Edge e : adj.get(s1)) {
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
		graph.addEgde(1, 2, 1, dir);
		
		graph.addEgde(3, 4, 1, dir);

		graph.addEgde(5, 6, 1, dir);
		graph.addEgde(5, 7, 1, dir);
		graph.addEgde(7, 8, 1, dir);

		return graph;
    }
}
