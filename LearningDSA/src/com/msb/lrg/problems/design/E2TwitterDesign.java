package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*

🧠 Why This is Merge-K Sorted
	Each user tweets list:
		👉 Already sorted by timestamp
	Heap:
		👉 Maintains best candidate across users
	Poll:
		👉 Add next tweet from same user
		This is exactly Merge K Sorted Lists.
	📈 Complexity
		Let:
			f = number of followees
			k = feed size (10)
		Then:
			👉 O(k log f)
		Memory:
			👉 O(f)

 */
public class E2TwitterDesign {
	
	public static int time = 1;

	public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // User 1 posts tweets
        twitter.postTweet(1, 101);
        twitter.postTweet(1, 102);

        // User 2 posts tweets
        twitter.postTweet(2, 201);
        twitter.postTweet(2, 202);

        twitter.postTweet(1, 103);
        twitter.postTweet(2, 203);
        twitter.postTweet(1, 104);
        twitter.postTweet(2, 204);
        twitter.postTweet(1, 105);
        twitter.postTweet(2, 205);

        // User 1 follows user 2
        twitter.follow(1, 2);

        System.out.println("News Feed for User 1:");
        System.out.println(twitter.getNewsFeed(1));

        // User 1 unfollows user 2
        twitter.unfollow(1, 2);
        twitter.postTweet(2, 203);

        System.out.println("After Unfollow:");
        System.out.println(twitter.getNewsFeed(1));
	}
	
	static class Twitter {
		Map<Integer, Tweet> tweetMap = new HashMap<>();
		Map<Integer, Set<Integer>> followMap = new HashMap<>();

		public void postTweet(int userId, int tweetId) {
			Tweet t = new Tweet(tweetId, time++);
			t.next = tweetMap.get(userId);
			tweetMap.put(userId, t);
		}
	
		public void follow(int followeeId, int followerId) {
			followMap.computeIfAbsent(followeeId, k -> new HashSet<>()).add(followerId);
		}
	
		public void unfollow(int followeeId, int followerId) {
			followMap.computeIfPresent(followeeId, (k,v) -> {
											v.remove(Integer.valueOf(followerId));
											return v.isEmpty() ? null : v;
										});
		}
	
		public List<Tweet> getNewsFeed(int userId){
			
			PriorityQueue<Tweet> pq = new PriorityQueue<>((o1,o2) -> o2.time - o1.time);
			
			/*
			 * Add itself to the followMap as one has to follow itself
			 */
			followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
			
			for(int followerId : followMap.get(userId)) {
				Tweet t = tweetMap.get(followerId);
				if(t != null)
					pq.add(t);
			}
			
			/*
			 * Meaning:
			 * 		Take best tweet
			 * 		Add its next tweet to heap
			 * So heap always maintains:
			 * 		👉 Next best candidates
			 */
			int count = 10;
			List<Tweet> res = new LinkedList<>();
			while(!pq.isEmpty() && count-- > 0) {
				Tweet t = pq.poll();
				res.add(t);
				
				if(t.next != null)
					pq.add(t.next);
			}
			return res;
		}
	}

	static class Tweet {
		int id;
		int time;
		Tweet next;
		
		Tweet(int id, int time){
			this.id = id;
			this.time = time;
		}
		
		public String toString() {
			return id + "::" + time;
		}
	}
}

