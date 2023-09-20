package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;

import com.msb.lrg.ds.AdjGraph;
import com.msb.lrg.ds.AdjGraph.Direction;
import com.msb.lrg.ds.Edge;
import com.msb.lrg.ds.Subset;
import com.msb.lrg.ds.Utility;

public class BiDirGraphMSTKruskal {

	public static void main(String[] args) {
		int vertices = 5;
		AdjGraph graph = BiDirGraphMSTKruskal.getGraph(vertices, Direction.UNI);
		graph.printGraph();

		System.out.println("OP : ");
		BiDirGraphMSTKruskal.printMSTValue(graph, vertices);
	}
	
	public static void printMSTValue(AdjGraph graph, int vertices) {
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>(); 
		
		for(ArrayList<Edge> list : graph.getList()) {
			for(Edge e : list) {
				edgeList.add(e);
			}
		}
		
		Edge[] edge = new Edge[edgeList.size()]; 
		edge = edgeList.toArray(edge);
		
		Edge result[] = new Edge[vertices]; // Tnis will store the resultant MST 
		int e = 0; // An index variable, used for result[] 
		int i = 0; // An index variable, used for sorted edges 
		for (i=0; i < vertices; ++i) 
			result[i] = new Edge(); 

		// Step 1: Sort all the edges in non-decreasing order of their 
		// weight. If we are not allowed to change the given graph, we 
		// can create a copy of array of edges 
		Arrays.sort(edge); 

		// Allocate memory for creating V ssubsets 
		Subset[] subsets = new Subset[vertices]; 
		for(i=0; i<vertices; ++i) 
			subsets[i]=new Subset(); 

		// Create V subsets with single elements 
		for (int v = 0; v < vertices; ++v) 
		{ 
			subsets[v].parent = v; 
			subsets[v].rank = 0; 
		} 

		i = 0; // Index used to pick next edge 

        int res =0;
		// Number of edges to be taken is equal to V-1 
		while (e < vertices - 1) 
		{ 
			// Step 2: Pick the smallest edge. And increment 
			// the index for next iteration 
			Edge next_edge = new Edge(); 
			next_edge = edge[i++]; 

			int x = Utility.find(subsets, next_edge.source); 
			int y = Utility.find(subsets, next_edge.destination); 

			// If including this edge does't cause cycle, 
			// include it in result and increment the index 
			// of result for next edge 
			if (x != y) 
			{ 
				result[e++] = next_edge; 
				Utility.Union(subsets, x, y); 
				
				res+=next_edge.weight;
			} 
			// Else discard the next_edge 
		} 

		// print the contents of result[] to display 
		// the built MST 
	
	    System.out.println("Weight of MST is: "+res); 
	}

	public static AdjGraph getGraph(int vertices, Direction dir) {

		AdjGraph graph = new AdjGraph(vertices);

		graph.addEgde(0, 1, 10, dir);
		graph.addEgde(0, 2, 8, dir);
		graph.addEgde(1, 2, 5, dir);
		graph.addEgde(1, 3, 3, dir);
		graph.addEgde(2, 3, 4, dir);
		graph.addEgde(2, 4, 12, dir);
		graph.addEgde(3, 4, 15, dir);

		return graph;
	}
}
