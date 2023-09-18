package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphBFS {

	public static void main(String[] args) {
		AdjGraph graph = UnDirGraphBFS.getGraph();
		graph.printGraph();
		
		System.out.printf("OP : ");
		UnDirGraphBFS.printBFS(graph, 5, 0);
	}
	
	public static void printBFS(AdjGraph graph, int v, int s) {
		
		boolean[] visited = new boolean[v];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(s);
		visited[s] = true;
		
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

    public static AdjGraph getGraph() {

		Direction dir = Direction.BI;
    	int vertices = 5;
		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(2, 4, 1, dir);
		graph.addEgde(3, 4, 1, dir);

		return graph;
    }
}
