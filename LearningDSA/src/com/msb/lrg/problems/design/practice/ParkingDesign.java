package com.msb.lrg.problems.design.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingDesign {

    PriorityQueue<Integer> small = new PriorityQueue<>();
    PriorityQueue<Integer> medium = new PriorityQueue<>();
    PriorityQueue<Integer> large = new PriorityQueue<>();

    Map<Integer, String> slotType = new HashMap<>();
 
	public ParkingDesign(int s, int m, int l) {
		/*
		 * Create all entries in PQ
		 * and also updatr slotType
		 */
	}
	
	public int park(VehicleType type) {
		/*
		 * If vehical type small try all three slots
		 * if medium then 2
		 * and large only one
		 */
		return 0;
	}
	
	public void leave(int slot) {
		/*
		 * check slot type
		 * and add toi that PQ
		 */
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static enum VehicleType {
	    BIKE, CAR, TRUCK
	}
	
}
