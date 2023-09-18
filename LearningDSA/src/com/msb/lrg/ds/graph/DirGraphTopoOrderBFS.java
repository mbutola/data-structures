package com.msb.lrg.ds.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DirGraphTopoOrderBFS {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = DirGraphTopoOrderBFS.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		System.out.printf("OP :");
		DirGraphTopoOrderBFS.printTopoOrder(graph, vertices);
	}

	public static void printTopoOrder(AdjGraph graph, int vertices){
		int[] inDeg = new int[vertices];
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < vertices; i++) {
			for(Edge e : graph.get(i))
				inDeg[e.destination]+=1; 
		}

		for (int i = 0; i < vertices; i++) {
			if(inDeg[i] == 0) {
				q.add(i);
			}
		}
		DirGraphTopoOrderBFS.printTopoOrderRec(graph, vertices, inDeg, q);
	}

	public static void printTopoOrderRec(AdjGraph graph, int vertices, int[] inDeg, Queue<Integer> q){
		while(!q.isEmpty()){
			int s = q.poll();
			System.out.printf("%4s", s);
			for(Edge e : graph.get(s)) {
				inDeg[e.destination]--;
				if(inDeg[e.destination] == 0)
					q.add(e.destination);
			}			
		}
	}
	
    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 2, 1, dir);
		graph.addEgde(0, 3, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(1, 3, 1, dir);
		graph.addEgde(1, 4, 1, dir);

		return graph;
    }
}
