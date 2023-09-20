package com.msb.lrg.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;

import com.msb.lrg.ds.Edge;

public class DirGraphBellFordSSSP {

	public static void main(String[] args) {
		ArrayList<Edge> edgeList = DirGraphBellFordSSSP.getEdges();
		
		int vertices = 4;
		int[] dist = new int[vertices];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[0] = 0;
		System.out.println(Arrays.toString(dist));
		
		for (int count = 0; count < vertices-1; count++) {
			for(Edge e : edgeList) {
				if(dist[e.source] != Integer.MAX_VALUE && dist[e.destination] > dist[e.source] + e.weight)
					dist[e.destination] = dist[e.source] + e.weight;
			}
			System.out.println(Arrays.toString(dist));
		}
		for(Edge e : edgeList) {
			if(dist[e.destination] > dist[e.source] + e.weight)
				System.out.println("Negative weight cycle ...");;
		}
		System.out.printf("O/P : ");
		System.out.println(Arrays.toString(dist));
	}

	public static ArrayList<Edge> getEdges(){
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		edgeList.add(new Edge(1,2,-3));
		edgeList.add(new Edge(1,3,2));
		edgeList.add(new Edge(2,3,3));
		edgeList.add(new Edge(0,1,1));
		edgeList.add(new Edge(0,2,4));
		
		return edgeList;
	}
}
