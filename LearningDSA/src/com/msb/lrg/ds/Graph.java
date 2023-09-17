package com.msb.lrg.ds;

import java.util.ArrayList;

public class Graph {

    int vertices;
    ArrayList<ArrayList<Edge>> adjacencylist;

	public Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new ArrayList<ArrayList<Edge>>();
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist.add(new ArrayList<>());
        }
    }

    public void addEgde(int source, int destination, int weight, Direction dir) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist.get(source).add(edge); //for directed graph
        if(dir == Direction.BI) {
            edge = new Edge(destination, source, weight);
            adjacencylist.get(destination).add(edge);
        }
    }

    public void printGraph(){
        for (int i = 0; i <vertices ; i++) {
        	ArrayList<Edge> list = adjacencylist.get(i);
            System.out.printf(" " + i + " :: ");
            for (int j = 0; j <list.size() ; j++) {
                System.out.printf(list.get(j).destination + "(" +  list.get(j).weight + ")" + "%3s", "-> ");
            }
            System.out.println("");
        }
        
    }
    
    public ArrayList<ArrayList<Edge>> getList(){
    	return adjacencylist;
    }

    public ArrayList<Edge> get(int s){
    	return adjacencylist.get(s);
    }

	public enum Direction{
		UNI,
		BI;
	}

}
