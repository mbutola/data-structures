package com.msb.lrg.problems.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*

Problem — Design a Parking Lot
	📌 Problem Statement
		Design a parking lot system that supports:
			Park vehicle
			Remove vehicle
		Different vehicle types:
			Bike
			Car
			Truck
		Parking spot types:
			Small
			Medium
			Large
		Rules:
			Bike → small/medium/large
			Car → medium/large
			Truck → large only
		System must:
			Allocate nearest available spot
			Track occupied spots
			Support multiple floors


 */

public class G1ParkingDesign {

	enum VehicleType {
	    BIKE, CAR, TRUCK
	}

	static class Parking {
    	
        PriorityQueue<Integer> small = new PriorityQueue<>();
        PriorityQueue<Integer> medium = new PriorityQueue<>();
        PriorityQueue<Integer> large = new PriorityQueue<>();

        Map<Integer, String> slotType = new HashMap<>();

        Parking(int s, int m, int l) {

	        for (int i = 1; i <= s; i++) {
	            small.offer(i);
	            slotType.put(i, "SMALL");
	        }
	
	        for (int i = s + 1; i <= s + m; i++) {
	            medium.offer(i);
	            slotType.put(i, "MEDIUM");
	        }
	
	        for (int i = s + m + 1; i <= s + m + l; i++) {
	            large.offer(i);
	            slotType.put(i, "LARGE");
	        }
        }

        public int park(VehicleType type) {

	        if (type == VehicleType.BIKE) {
	            if (!small.isEmpty()) return small.poll();
	            if (!medium.isEmpty()) return medium.poll();
	            if (!large.isEmpty()) return large.poll();
	        }
	
	        if (type == VehicleType.CAR) {
	            if (!medium.isEmpty()) return medium.poll();
	            if (!large.isEmpty()) return large.poll();
	        }
	
	        if (type == VehicleType.TRUCK) {
	            if (!large.isEmpty()) return large.poll();
	        }
	
	        return -1;
	    }

	    public void leave(int slot) {
	
	        String type = slotType.get(slot);
	
	        if (type.equals("SMALL")) small.offer(slot);
	        else if (type.equals("MEDIUM")) medium.offer(slot);
	        else large.offer(slot);
	    }
    }

    public static void main(String[] args) {
		Parking p = new Parking(2, 2, 2);

        System.out.println(p.park(VehicleType.BIKE));   // 1
        System.out.println(p.park(VehicleType.CAR));    // 3
        System.out.println(p.park(VehicleType.TRUCK));  // 5

        p.leave(3);

        System.out.println(p.park(VehicleType.CAR));    // 3
	}

}
