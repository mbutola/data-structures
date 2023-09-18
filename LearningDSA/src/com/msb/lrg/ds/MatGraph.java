package com.msb.lrg.ds;

public class MatGraph {

	private int[][] adjMatrix;
	private int numVertices;

// Initialize the matrix
	public MatGraph(int numVertices) {
		this.numVertices = numVertices;
		adjMatrix = new int[numVertices][numVertices];
	}

// Add edges
	public void addEdge(int i, int j, int weight, Direction dir) {
		adjMatrix[i][j] = weight;
		if(dir == dir.BI)
			adjMatrix[j][i] = weight;
	}
	
// Remove edges
	public void removeEdge(int i, int j) {
		adjMatrix[i][j] = 0;
		adjMatrix[j][i] = 0;
	}

	public int[][] getGraph(){
		return adjMatrix;
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

	public enum Direction{
		UNI,
		BI;
	}
}
