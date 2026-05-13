package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

Approach 1 — Coarse-grained Lock (Simplest, Interview Safe)

 */
public class B1GetRandomDesign {

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
		Lock lock = new ReentrantLock();

		GetRandom(){
			list = new ArrayList<>();
			map = new HashMap<>();
			ran = new Random();
		}
		
		public boolean insert(int val){
			lock.lock();
			try {
				if(map.containsKey(val))
					return false;
				
				map.put(val, list.size());
				list.add(val);
				return true;
			} finally {
				lock.unlock();
			}
		}
		
		public boolean remove(int val) {
			lock.lock();
			try {
				if(!map.containsKey(val))
					return false;
				
				int index = map.get(val);
				int lastElement = list.getLast();
				
				list.set(index, lastElement);
				map.put(lastElement, index);
				
				list.remove(list.size() - 1);
				map.remove(val);
				
				return true;
			} finally {
				lock.unlock();
			}
		}
		
		public int getRandom() {
			lock.lock();
			try {
				int random = ran.nextInt(list.size());
				return list.get(random);			
			} finally {
				lock.unlock();
			}
		}
	}


}
