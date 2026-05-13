package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.DelayQueue;

/*

🧠 Problem — Design a Hit Counter
	📌 Problem Statement
		Design a hit counter that:
			Records number of hits at timestamp (seconds)
			Returns hits in last 5 minutes (300 seconds)
		Example:
			hit(1)
			hit(2)
			hit(3)
			getHits(4) → 3
			hit(300)
			getHits(300) → 4
			getHits(301) → 3
🎯 What Interviewer Tests
	Sliding window design
	Queue vs bucket vs distributed storage
	Time-series optimization
	Memory vs latency tradeoff
📊 ALL Possible Approaches
	❌ Approach-1 Brute Force List
		Store all timestamps.
		Query → count timestamps within window.
	Complexity
		Insert:
			O(1)
		Query:
			O(N)
		Space:
			O(N)
	Problem
		❌ Very slow at scale
		❌ Memory explosion
	Solution
		class HitCounter {
		    List<Integer> hits;
		    public HitCounter() {
		        hits = new ArrayList<>();
		    }
		    public void hit(int timestamp) {
		        hits.add(timestamp);
		    }
		    public int getHits(int timestamp) {
		        int count = 0;
		        for (int t : hits) {
		            if (timestamp - t < 300) {
		                count++;
		            }
		        }
		        return count;
		    }
		}
	🧠 Slight Improvement (Still Simple)
		We can clean old hits during query:
			public int getHits(int timestamp) {
			    Iterator<Integer> it = hits.iterator();
			
			    while (it.hasNext()) {
			        if (timestamp - it.next() >= 300)
			            it.remove();
			    }
			    return hits.size();
			}
⚠️ Approach-2 Queue Sliding Window
	Store timestamps in queue.
	Remove expired timestamps during query.
	Complexity
		Insert:
			O(1)
		Query:
			O(k)
			k = expired timestamps
		Amortized:
			O(1)
		Space:
			O(hits in window)
	Good for
		✔ Coding interview optimal
	Solution
		class HitCounter {
		    Queue<Integer> q;
		    public HitCounter() {
		        q = new LinkedList<>();
		    }
		    public void hit(int timestamp) {
		        q.offer(timestamp);
		    }
		    public int getHits(int timestamp) {
		
		        while (!q.isEmpty() && timestamp - q.peek() >= 300) {
		            q.poll();
		        }
		        return q.size();
		    }
		}
⚠️ Approach-3 Sorted Map / TreeMap
	Store:
		timestamp → count
	Query:
		range sum
		Complexity
	Insert:
		O(log N)
	Query:
		O(log N + K)
	Space:
		O(unique timestamps)
	Better but still heavy.
	Solution
		class HitCounter {
		    TreeMap<Integer, Integer> map;
		    int totalHits;
		    public HitCounter() {
		        map = new TreeMap<>();
		        totalHits = 0;
		    }
		    public void hit(int timestamp) {
		        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
		        totalHits++;
		    }
		    public int getHits(int timestamp) {
		        int start = timestamp - 300;
		        while (!map.isEmpty() && map.firstKey() <= start) {
		            totalHits -= map.pollFirstEntry().getValue();
		        }
		        return totalHits;
		    }
		}
✅ Approach-4 Circular Array (Optimal Production Coding)
	Observation:
		Window fixed = 300 sec.
	So:
		👉 Only need 300 buckets.
	Each bucket:
		timestamp + hit count
		Complexity
	Insert:
		O(1)
	Query:
		O(300) → O(1)
	Space:
		O(300)
	This is FAANG expected optimal coding answer
🚀 Approach-5 Distributed Time-Series (Real Production)
	At scale:
	Use Redis sorted sets
	Use Kafka stream aggregation
	Use time-series DB (InfluxDB)
🧠 Deep Explanation of Optimal Circular Bucket Solution
	Key idea:
		👉 Window size fixed → use circular array
	We create:
		int[] hits = new int[300]
		int[] times = new int[300]
	When hit comes:
		index = timestamp % 300
	If:
		times[index] != timestamp
	Means:
		Old data → reset bucket.
	So:
		times[index] = timestamp
		hits[index] = 1
	Else:
		hits[index]++
	Query:
		Loop all buckets:
		If:
			currentTime - times[i] < 300
			Add hits.
	This gives:
		👉 Constant memory
		👉 Constant query time
	This is real optimized time-series windowing technique
⏱ Time Complexity
	Insert:
		O(1)
	Query:
		O(300) → O(1)
	Space:
		O(300)

 */
public class F1HitCounterDesign {

	public static void main(String[] args) {
//		brute();
//		hitCounterQueue();
//		hitCounterMap();
//		hitCounterCyclic();
		hitCounterQueueBucket();
	}
	
	static void hitCounterQueueBucket() {
		HitCounterQueueBucket hc = new HitCounterQueueBucket();

        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        System.out.println(hc.getHits(4));   // 3

        hc.hit(300);
        System.out.println(hc.getHits(300)); // 4

        System.out.println(hc.getHits(301)); // 3
	}
	
	static class HitCounterQueueBucket {
		
		static class Node {
			
			int timestamp;
			int count;
			
			Node(int timestamp, int count) {
				this.timestamp = timestamp;
				this.count = count;
			}
			
		}
		
		Deque<Node> queue;
		int total;
		
		HitCounterQueueBucket() {
			queue = new LinkedList<>();
			total = 0;
		}
		
		public void cleanUp(int timestamp) {
			while(!queue.isEmpty() && queue.peek().timestamp <= timestamp - 300) {
				Node node = queue.poll();
				total -= node.count;
			}
		}
		
		public void hit(int timestamp) {
			cleanUp(timestamp);
			if(!queue.isEmpty() && 
						queue.peekLast().timestamp == timestamp) {
				queue.peekLast().count++;
			} else {
				queue.offer(new Node(timestamp, 1));
			}
			total++;
		}
		
		public int getHits(int timestamp) {
			cleanUp(timestamp);
			return total;
		}
	}
	
	static void hitCounterCyclic() {
		HitCounterCyclic cyclic = new HitCounterCyclic();
		cyclic.hit(0);
		cyclic.hit(1);
		cyclic.hit(1);
		cyclic.hit(2);
		cyclic.hit(3);
		cyclic.hit(4);
		cyclic.hit(5);
		System.out.println("" + cyclic.getHits(300));
        System.out.println(cyclic.getHits(603));   // 3
        System.out.println(cyclic.getHits(605));   // 3
	}
	
	static class HitCounterCyclic {
		
		int[] hits = new int[300];
		int[] time = new int[300];
		int count;

		HitCounterCyclic(){
			hits = new int[300];
			time = new int[300];
			count = 0;
		}
		
		public void hit(int timestamp) {
			int index = timestamp % 300;
			if(time[index] != timestamp) {
				count -= hits[index];
				hits[index] = 1;
				time[index] = timestamp;		
			} else {
				hits[index]++;
			}
			count++;
		}

		public int getHits(int timestamp) {
			int index = timestamp % 300;
			for(int i = 0; i < 300; i++) {
				if(time[i] <= timestamp - 300) {
					count -= hits[i];
					hits[i] = 0;
					time[i] = 0;
				}
			}
			return count;
		}
	}

	static void hitCounterMap() {
		HitCounterMap map = new HitCounterMap();
		map.hit(0);
		map.hit(1);
		map.hit(1);
		map.hit(2);
		map.hit(300);
		System.out.println("" + map.getHits(300));
	}
	
	static class HitCounterMap {
		
		TreeMap<Integer, Integer> map;
		int count;
		
		HitCounterMap(){
			map = new TreeMap<>();
			count = 0;
		}
		
		public void hit(int timestamp) {
			map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
			count++;
		}

		public int getHits(int timestamp) {
			
			while(!map.isEmpty() && timestamp - map.firstKey() >= 300) {
				count -= map.pollFirstEntry().getValue();
			}
			return count;
		}
	}

	static void hitCounterQueue() {
		HitCounterQueue queue = new HitCounterQueue();
		queue.hit(0);
		queue.hit(1);
		queue.hit(1);
		queue.hit(2);
		queue.hit(300);
		System.out.println("" + queue.getHits(300));
	}
	
	static class HitCounterQueue {
		
		Queue<Integer> queue;
		
		HitCounterQueue(){
			queue = new LinkedList<>();
		}
		
		public void hit(int timestamp) {
			queue.offer(timestamp);
		}

		public int getHits(int timestamp) {
			
			while(!queue.isEmpty() && queue.peek() <= timestamp - 300) {
				queue.poll();
			}
			return queue.size();
		}
	}

	static void brute() {
		Brute brute = new Brute();
		brute.hit(0);
		brute.hit(1);
		brute.hit(1);
		brute.hit(2);
		brute.hit(300);
		System.out.println("" + brute.getHits(300));
	}
	
	static class Brute {
		
		List<Integer> list;
		
		Brute(){
			list = new ArrayList<>();
		}
		
		public void hit(int timestamp) {
			list.add(timestamp);
		}

		public int getHits(int timestamp) {
			
			int count = 0;
			
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) > timestamp - 300)
					count++;
			}
			return count;
		}
	}

}
