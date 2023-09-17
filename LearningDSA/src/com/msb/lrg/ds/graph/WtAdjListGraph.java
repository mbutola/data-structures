package com.msb.lrg.ds.graph;

import java.util.ArrayList;

import com.msb.lrg.ds.Graph.Direction;

public class WtAdjListGraph {
	
//	static class Edge {
//        int source;
//        int destination;
//        int weight;
//
//        public Edge(int source, int destination, int weight) {
//            this.source = source;
//            this.destination = destination;
//            this.weight = weight;
//        }
//    }
//
//    static class Graph {
//        int vertices;
//        ArrayList<ArrayList<Edge>> adjacencylist;
//
//        Graph(int vertices) {
//            this.vertices = vertices;
//            adjacencylist = new ArrayList<ArrayList<Edge>>();
//            //initialize adjacency lists for all the vertices
//            for (int i = 0; i <vertices ; i++) {
//                adjacencylist.add(new ArrayList<>());
//            }
//        }
//
//        public void addEgde(int source, int destination, int weight, Direction direction) {
//            Edge edge = new Edge(source, destination, weight);
//            adjacencylist.get(source).add(edge); //for directed graph
//            if(direction == direction.BI) {
//                edge = new Edge(destination, source, weight);
//                adjacencylist.get(destination).add(edge);
//            }
//        }
//
//        public void printGraph(){
//            for (int i = 0; i <vertices ; i++) {
//            	ArrayList<Edge> list = adjacencylist.get(i);
//                System.out.printf(" " + i + " :: ");
//                for (int j = 0; j <list.size() ; j++) {
//                    System.out.printf(list.get(j).destination + "(" +  list.get(j).weight + ")" + "%3s", "-> ");
//                }
//                System.out.println("");
//            }
//            
//            for (int i = 0; i < vertices; i++) {
//            	ArrayList<Edge> list = adjacencylist.get(i);
//				for (int j = 0; j < list.size(); j++) {
//					System.out.printf("%4s", list.get(j).weight);
//				}
//				System.out.println("");
//			}
//            
//        }
//    }
//      public static void main(String[] args) {
//            Graph graph = WtAdjListGraph.getGraph(Direction.UNI);
//            graph.printGraph();
//        }
//      
//      public static Graph getGraph(Direction dir) {
//
//    	  int vertices = 6;
//          Graph graph = new Graph(vertices);
//          graph.addEgde(0, 1, 4, dir);
//          graph.addEgde(0, 2, 3, dir);
//          graph.addEgde(1, 3, 2, dir);
//          graph.addEgde(1, 2, 5, dir);
//          graph.addEgde(2, 3, 7, dir);
//          graph.addEgde(3, 4, 2, dir);
//          graph.addEgde(4, 0, 4, dir);
//          graph.addEgde(4, 1, 4, dir);
//          graph.addEgde(4, 5, 6, dir);
//          
//          return graph;
//      }
}