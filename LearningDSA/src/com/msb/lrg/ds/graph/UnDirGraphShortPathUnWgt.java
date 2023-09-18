package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class UnDirGraphShortPathUnWgt {

	public static void main(String[] args) {
		int vertices = 4;
		AdjGraph graph = UnDirGraphShortPathUnWgt.getGraph(vertices, Direction.BI);
		graph.printGraph();
		
		int source = 0;
		System.out.printf("OP : ");
		UnDirGraphShortPathUnWgt.printShortestPath(graph, vertices, source);
	}
	
	public static void printShortestPath(AdjGraph graph, int vertices, int source) {
		
		int[] dist = new int[vertices];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[0] = 0;
		boolean[] visited = new boolean[vertices];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		visited[source] = true;

		while(!q.isEmpty()) {
			int s1 = q.poll();
			ArrayList<Edge> adj = graph.get(s1);
			for(Edge e : adj) {
				if(!visited[e.destination]) {
					q.add(e.destination);
					visited[e.destination] = true;
					dist[e.destination] = dist[e.source] + 1;
				}
			}
		}
		System.out.println(Arrays.toString(dist));
	}

    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);

		return graph;
    }
	
}
