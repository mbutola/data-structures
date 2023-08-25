package com.msb.lrg.ds.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintMatrix {

	public static void main(String[] args) {

		int[][] mazeArray = PrintMatrix.getMazeArray();
		PrintMatrix.printArray(mazeArray);
		
		System.out.println("----------------------");
		
		List<List<Integer>> mazeList = PrintMatrix.getMazeList();
		PrintMatrix.printList(mazeList);
		
	}

	public static int[][] getMazeArray(){
		int[][] maze = new int[4][4];
		maze[0][0] = 1;
		maze[0][1] = 0;
		maze[0][2] = 0;
		maze[0][3] = 0;
		maze[1][0] = 1;
		maze[1][1] = 1;
		maze[1][2] = 0;
		maze[1][3] = 1;
		maze[2][0] = 0;
		maze[2][1] = 1;
		maze[2][2] = 0;
		maze[2][3] = 0;
		maze[3][0] = 1;
		maze[3][1] = 1;
		maze[3][2] = 1;
		maze[3][3] = 1;
		
		return maze;
	}

	
	public static List<List<Integer>> getMazeList(){
		List<List<Integer>> mazeList = 
					new ArrayList<List<Integer>>(
							Arrays.asList(
									Arrays.asList(new Integer[]{1,0,0,0}),
									Arrays.asList(new Integer[]{1,1,0,1}),
									Arrays.asList(new Integer[]{0,1,0,0}),
									Arrays.asList(new Integer[]{1,1,1,1})
									)
							);
		return mazeList;
	}
	
	public static void printArray(int[][] in) {
		for(int i=0; i<in.length; i++) {
			System.out.print(" | ");
			for(int j=0; j<in[i].length; j++) {
				System.out.print(in[i][j]);
				System.out.print(" ");
			}
			System.out.println("|");
			System.out.println("");
		}
	}

	public static void printList(List<List<Integer>> in) {
	in.stream()
		.forEach(PrintMatrix::printRow);
	}

	public static void printRow(List<Integer> row) {
		System.out.print(" | ");
		row.stream()
			.forEach(col -> System.out.print(col + " "));
		System.out.println("|\n");
	}
	
}
