package com.msb.lrg.ds;

public class Edge implements Comparable<Edge> {

    public int source;
    public int destination;
    public int weight;

    public Edge() {};
    
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

	@Override
	public int compareTo(Edge edge) {
		return this.weight - edge.weight;
	}
    
}
