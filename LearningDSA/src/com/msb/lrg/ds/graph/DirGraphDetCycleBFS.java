package com.msb.lrg.ds.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;

public class DirGraphDetCycleBFS {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = DirGraphDetCycleBFS.getGraph(vertices, Direction.UNI);
		graph.printGraph();
		
		System.out.printf("OP : " + DirGraphDetCycleBFS.printTopoOrder(graph, vertices));
	}

	public static boolean printTopoOrder(AdjGraph graph, int vertices){
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
		return DirGraphDetCycleBFS.printTopoOrderRec(graph, vertices, inDeg, q);
	}

	public static boolean printTopoOrderRec(AdjGraph graph, int vertices, int[] inDeg, Queue<Integer> q){
		int count = 0;
		while(!q.isEmpty()){
			int s = q.poll();
			count++;
			for(Edge e : graph.get(s)) {
				inDeg[e.destination]--;
				if(inDeg[e.destination] == 0)
					q.add(e.destination);
			}			
		}
		return count != vertices ? true : false;
	}
	
    public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);
		graph.addEgde(0, 1, 1, dir);
		graph.addEgde(1, 2, 1, dir);
		graph.addEgde(2, 3, 1, dir);
		graph.addEgde(3, 1, 1, dir);
		graph.addEgde(4, 1, 1, dir);

		return graph;
    }
}
