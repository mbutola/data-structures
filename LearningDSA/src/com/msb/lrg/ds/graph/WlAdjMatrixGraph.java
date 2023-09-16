package com.msb.lrg.ds.graph;

public class WlAdjMatrixGraph {

	private int adjMatrix[][];
	private int numVertices;

	public static enum direction{
		UNI,
		BI;
	}
	
	// Initialize the matrix
	public WlAdjMatrixGraph(int numVertices) {
		this.numVertices = numVertices;
		adjMatrix = new int[numVertices][numVertices];
	}

// Add edges
	public void addEdge(int i, int j, int weight, WlAdjMatrixGraph.direction dir) {
		adjMatrix[i][j] = weight;
		if(dir == dir.BI)
			adjMatrix[j][i] = weight;
	}

// Remove edges
	public void removeEdge(int i, int j) {
		adjMatrix[i][j] = 0;
		adjMatrix[j][i] = 0;
	}

// Print the matrix
	public void printGraph() {
		System.out.printf(" ");
		for (int i = 0; i < numVertices; i++) {
			System.out.printf("%4s", i);
		}
		System.out.println("");
		for (int i = 0; i < numVertices; i++) {
			System.out.printf("%1s", i);
			for (int j : adjMatrix[i]) {
				System.out.printf("%4s", j);
			}
			System.out.println("");
		}
	}

	public static void main(String args[]) {
		WlAdjMatrixGraph graph = WlAdjMatrixGraph.getGraph(direction.BI);
		graph.printGraph();
	}
	
	public static WlAdjMatrixGraph getGraph(WlAdjMatrixGraph.direction dir) {
		WlAdjMatrixGraph g = new WlAdjMatrixGraph(4);

		g.addEdge(0, 1, 2, dir);
		g.addEdge(0, 2, 3, dir);
		g.addEdge(0, 3, 4, dir);
		g.addEdge(1, 2, 5, dir);
		g.addEdge(1, 3, 6, dir);
		g.addEdge(2, 3, 7, dir);

		return g;
	}
}