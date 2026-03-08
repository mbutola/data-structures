package com.msb.lrg.problems.gof;

import java.util.HashMap;
import java.util.Map;

/*
 	Flyweight Pattern
	1. What is Flyweight Pattern?
		Flyweight Pattern is a structural design pattern used to minimize memory usage by sharing common, reusable 
		objects instead of creating new ones repeatedly.
		It separates object state into:
			Intrinsic State → shared and immutable (common across objects)
			Extrinsic State → provided externally (changes every use)
	2. Purpose
		Reduce memory consumption
		Improve performance when creating many similar objects
		Share objects that have common, reusable data
		In simple words:
			Store common data once and reuse it for many objects.
	3. Problem It Solves
		When you have huge numbers of objects that have duplicated data.
		Examples:
			Thousands of characters in a document editor (font, size → same)
			Thousands of game trees, bullets, particles
			Millions of map icons (same icon, different location)
		Problem:
			Creating full objects for each instance wastes memory.
	4. How it Solves the Problem
		Break objects into:
			Intrinsic (shared) properties
			Extrinsic (external) properties
			Store intrinsic objects in a Flyweight Factory
			Reuse existing instances instead of creating new ones
	5. Advantages
		Memory Efficient
			Drastically reduces RAM usage by reusing heavy shared objects.
		Performance Boost
			Less object creation → faster runtime.
		Centralized State Management
			Common state is stored in one place.
	6. Disadvantages
		Complexity Increases
			Understanding intrinsic vs extrinsic state splitting is harder.
		Thread Safety Challenges
			Shared objects need careful handling.
		Less Useful for Small Object Counts
			Overkill if you're not creating thousands of objects.
	Real-World Analogies
		| Context                 | Flyweight Concept                          |
		| ----------------------- | ------------------------------------------ |
		| Font objects in MS Word | Same “Arial 12” object reused              |
		| Cars in a rental system | Same model shared, plate no. external      |
		| Game bullets            | Same bullet type reused, position external |

 */
public class FlyWeightDemo {

	public static void main(String[] args) {
		
		for(int i=0; i<5; i++) {
			CircleFlyWeight circle = CircleFlyWeightFactory.getCircleFlyWeight("Red");
			circle.draw(i * 10, i * 15, 20);
		}
	}

}

interface CircleFlyWeight {
	void draw(int x, int y, int radius);
}

class CircleColor implements CircleFlyWeight {
	
	private final String color;
	
	public CircleColor(String color) {
		this.color = color;
	}
	
	public void draw(int x, int y, int radius) {
		System.out.println("Color :: " + color +
					", x :: " + x +
					", y :: " + y +
					", radius :: " + radius);
	}
}

class CircleFlyWeightFactory {

	private static final Map<String, CircleFlyWeight> map = new HashMap<>();
	
	public static CircleFlyWeight getCircleFlyWeight(String color) {
		if(!map.containsKey(color)) {
			map.put(color, new CircleColor(color));
		}
		return map.get(color);
	}
	
}