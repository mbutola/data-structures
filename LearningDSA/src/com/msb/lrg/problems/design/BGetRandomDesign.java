package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*

✅ Problem Statement
	Design a data structure that supports:
		insert(val)      → insert if not present
		remove(val)      → remove if present
		getRandom()      → return random element
	Constraint
		All operations must be O(1)
	❗ Why this is FAANG Level
		This tests:
			Hashing
			Array indexing trick
			Constant time delete
			Real system DS thinking
			Cache-like structures
			Memory vs speed tradeoff
	💡 Key Idea (Core Insight)
		Normal structures:
		| DS    | insert | delete | random |
		| ----- | ------ | ------ | ------ |
		| List  | O(1)   | O(n)   | O(1)   |
		| Set   | O(1)   | O(1)   | ❌      |
		| Array | O(1)   | ❌      | O(1)   |
	So combine:
		HashMap + ArrayList
✅ Approach
	Maintain:
		List<Integer> list → store values
		Map<Integer, Integer> map → value → index
	Insert
		add to list
		store index in map
	Remove (IMPORTANT TRICK)
		To delete in O(1):
		swap with last element
		remove last
		update index in map
	GetRandom
		random index from list


 */
public class BGetRandomDesign {

	public static void main(String[] args) {
		GetRandom set = new GetRandom();

        System.out.println(set.insert(10));
        System.out.println(set.insert(20));
        System.out.println(set.insert(30));
        System.out.println(set.remove(20));

        System.out.println("Random: " + set.getRandom());
        System.out.println("Random: " + set.getRandom());
	}
	
	static class GetRandom {
		
		List<Integer> list;
		Map<Integer, Integer> map;
		Random ran;

		GetRandom(){
			list = new ArrayList<>();
			map = new HashMap<>();
			ran = new Random();
		}
		
		public boolean insert(int val){
			
			if(map.containsKey(val))
				return false;
			
			map.put(val, list.size());
			list.add(val);
			return true;
		}
		
		public boolean remove(int val) {
			if(!map.containsKey(val))
				return false;
			
			int index = map.get(val);
			int lastElement = list.getLast();
			
			list.set(index, lastElement);
			map.put(lastElement, index);
			
			list.remove(list.size() - 1);
			map.remove(val);
			
			return true;
		}
		
		public int getRandom() {
			
			int random = ran.nextInt(list.size());
			return list.get(random);			
		}
	}

}
