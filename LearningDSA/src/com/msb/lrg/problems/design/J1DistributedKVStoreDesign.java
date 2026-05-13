package com.msb.lrg.problems.design;

import java.util.*;

public class J1DistributedKVStoreDesign {
	
	static class DistributedKVStore {
		ConsistentHashMap map;
		
		DistributedKVStore(){
			map = new ConsistentHashMap();
		}
		
		public void addShard(String name, Shard value) {
			map.addShard(name, value);
		}
		
		public void put(String key, String value) {
			Shard shard = map.getShard(key);
			shard.put(key, value);
		}
		
		public void delete(String key) {
			Shard shard = map.getShard(key);
			shard.delete(key);
		}
		
		public String get(String key) {
			Shard shard = map.getShard(key);
			return shard.get(key);
		}
		
		static class ConsistentHashMap {
			TreeMap<Integer, Shard> map;
			
			ConsistentHashMap(){
				map = new TreeMap<>();
			}
			
			public Integer hash(String key) {
				return key.hashCode() & 0x7fffffff;
			}
			
			public void addShard(String key, Shard value) {
				map.put(hash(key), value);
			}
			
			public void removeShard(String key) {
				map.remove(hash(key));
			}
			
			public Shard getShard(String key) {
				
				if(map.isEmpty()) return null;
				
				Integer hash = hash(key);
				
				if(!map.containsKey(hash)) {
					SortedMap<Integer, Shard> tail = map.tailMap(hash);
					hash = tail.isEmpty() ? map.firstKey() : tail.firstKey();
				}
				
				return map.get(hash);
				
			}
		}
	}
    
    static class Shard {
    	Node primary;
    	List<Node> replicas;
    	
    	Shard(Node primary, List<Node> replicas){
    		this.primary = primary;
    		this.replicas = replicas;
    	}

    	public void put(String key, String value) {
    		primary.put(key, value);
    		for(Node replica : replicas) {
    			replica.put(key, value);
    		}
    	}
    	
    	public void delete(String key) {
    		primary.delete(key);
    		for(Node replica : replicas) {
    			replica.delete(key);
    		}
    	}

    	public String get(String key) {
    		return primary.get(key);
    	}

    }
    
    static class Node {
    	String name;
    	Map<String, String> map;
    	
    	Node(String name){
    		this.name = name;
    		map = new HashMap<>();
    	}
    	
    	public void put(String key, String value) {
    		map.put(key, value);
    	}
    	
    	public void delete(String key) {
    		map.remove(key);
    	}

    	public String get(String key) {
    		return map.get(key);
    	}
    	
    }

	public static void main(String[] args) {

        DistributedKVStore store = new DistributedKVStore();

        // Create nodes
        Node n1 = new Node("N1");
        Node n2 = new Node("N2");
        Node n3 = new Node("N3");
        Node n4 = new Node("N4");

        // Create shards
        Shard shard1 = new Shard(n1, Arrays.asList(n2));
        Shard shard2 = new Shard(n3, Arrays.asList(n4));

        store.addShard("shard1", shard1);
        store.addShard("shard2", shard2);

        // Operations
        store.put("user1", "Alice");
        store.put("user2", "Bob");

        System.out.println(store.get("user1"));
        System.out.println(store.get("user2"));
    }
}
