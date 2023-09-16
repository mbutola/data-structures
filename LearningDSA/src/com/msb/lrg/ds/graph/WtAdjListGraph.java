package com.msb.lrg.ds.graph;

import java.util.LinkedList;

public class WtAdjListGraph {
	
	public static enum direction{
		UNI,
		BI;
	}
    
	static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<LinkedList<Edge>> adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList<LinkedList<Edge>>();
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist.add(new LinkedList<>());
            }
        }

        public void addEgde(int source, int destination, int weight, WtAdjListGraph.direction direction) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist.get(source).add(edge); //for directed graph
            if(direction == direction.BI) {
                edge = new Edge(destination, source, weight);
                adjacencylist.get(destination).add(edge);
            }
        }

        public void printGraph(){
            for (int i = 0; i <vertices ; i++) {
                LinkedList<Edge> list = adjacencylist.get(i);
                System.out.printf("vertex-" + i + " is connected :: ");
                for (int j = 0; j <list.size() ; j++) {
                    System.out.printf(list.get(j).destination + " with weight " +  list.get(j).weight + "%5s", "");
                }
                System.out.println("");
            }
            
            for (int i = 0; i < vertices; i++) {
                LinkedList<Edge> list = adjacencylist.get(i);
				for (int j = 0; j < list.size(); j++) {
					System.out.printf("%4s", list.get(j).weight);
				}
				System.out.println("");
			}
            
        }
    }
      public static void main(String[] args) {
            Graph graph = WtAdjListGraph.getGraph(direction.UNI);
            graph.printGraph();
        }
      
      public static Graph getGraph(WtAdjListGraph.direction direction) {

    	  int vertices = 6;
          Graph graph = new Graph(vertices);
          graph.addEgde(0, 1, 4, direction);
          graph.addEgde(0, 2, 3, direction);
          graph.addEgde(1, 3, 2, direction);
          graph.addEgde(1, 2, 5, direction);
          graph.addEgde(2, 3, 7, direction);
          graph.addEgde(3, 4, 2, direction);
          graph.addEgde(4, 0, 4, direction);
          graph.addEgde(4, 1, 4, direction);
          graph.addEgde(4, 5, 6, direction);
          
          return graph;
      }
}