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

✅ Problem Explanation (Simple Words)

	We need to design a mini Twitter system supporting:
	
		1️⃣ postTweet(userId, tweetId)
			→ User posts a tweet
		
		2️⃣ follow(followerId, followeeId)
			→ User follows another user
		
		3️⃣ unfollow(followerId, followeeId)
			→ User unfollows
		
		4️⃣ getNewsFeed(userId)
			→ Return 10 most recent tweets
			→ Tweets must be from
		
		user himself
		
		users he follows
			→ Order: most recent first

✅ Approach
	postTweet
		Add tweet to user's list
		Assign increasing timestamp
	follow
		Add followee in set
	unfollow
		Remove followee
	getNewsFeed
		Steps:
			Collect tweets from:
				user
				followees
			Put all into max heap based on time
			Extract top 10

✅ Time Complexity
	postTweet
		O(1)
	follow / unfollow
		O(1)
	getNewsFeed
		Worst:
			O(N log N)
		Where:
			N = total tweets of user + followees
		Acceptable for coding round.
✅ Why Timestamp Needed
	To maintain tweet ordering
	We use:
		Logical counter
	Instead of system time because:
		No collision
		Deterministic ordering
		Faster comparison
✅ Interview Explanation Strategy
	When interviewer asks:
		Say:
			I will store tweets per user and follow relationships.
			For feed generation, I will collect tweets and use a max heap to get latest 10.
		This shows:
			✔ clarity
			✔ simplicity
			✔ correctness
	⭐ If Interviewer Asks Optimization
		Then say:
			Instead of pushing all tweets, we can use k-way merge heap.
			This gives:
				O(10 log k)
		This is strong signal answer.

 */
public class E1TwitterDesign {
	
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
		Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
		Map<Integer, Set<Integer>> followMap = new HashMap<>();

		public void postTweet(int userId, int tweetId) {
			tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Tweet(tweetId, time++));
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
			pq.addAll(tweetMap.getOrDefault(userId, Collections.emptyList()));
			
			for(int followerId : followMap.getOrDefault(Integer.valueOf(userId), Collections.emptySet())) {
				pq.addAll(tweetMap.getOrDefault(followerId, Collections.emptyList()));
			}
			
			int count = 5;
			List<Tweet> res = new LinkedList<>();
			while(!pq.isEmpty() && count-- > 0) {
				res.add(pq.poll());
			}
			return res;
		}
	}

	static class Tweet {
		int id;
		int time;
		
		Tweet(int id, int time){
			this.id = id;
			this.time = time;
		}
		
		public String toString() {
			return id + "::" + time;
		}
	}
}

