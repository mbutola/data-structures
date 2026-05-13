package com.msb.lrg.problems.design.practice;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DistributedKVStore {
	
	ConsistentHashMap map;
	
	/*
	 * addShard adds shard to map
	 * 
	 * get remove and put
	 * get the shard and call similar action on the shard
	 */

	
	static class ConsistentHashMap{
		
		TreeMap<Integer, Shard> map;
		
		/*
		 * hash
		 * addShard
		 * getShard
		 * removeShard
		 */
		
		public Integer hash(String key) {
			return key.hashCode() & 0x7fffffff;
		}
		
		public void addShard(String name) {
			/*
			 * hash and put in map
			 */
		}
		
		public void removeShard(String name) {
			/*
			 * hash and remove from map
			 */
		}

		public void getShard(String name) {
			/*
			 * hash
			 * if key not exists call tailmap to get SortedMap
			 * and then get shard as firstEntry()
			 */
		}
		
	}
	
	static class Shard {
	
		Node primary;
		List<Node> backUps;
		
		/*
		 * get put and remove
		 * put and remove add to primary and backUps
		 * get from primary
		 */
	}
	
	static class Node {
		Map<String, String> map;
		
		/*
		 * get remove and put
		 */
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
