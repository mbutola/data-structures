package com.msb.lrg.problems.design.practice;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwitterDesign {
	
	Map<Integer, Set<Integer>> follow;
	Map<Integer, List<Tweet>> tweet;
	
	public void postTweet(int userId, int tweetId) {
		/*
		 * Check if absent create and add
		 */
	}
		
	public void follow(int followeeId, int followerId) {
		/*
		 * Check if absent create and add
		 */
	}
	
	public void unfollow(int followeeId, int followerId) {
		/*
		 * Check if present remove
		 * if empty return null which deletes
		 */
	}
	
	public List<Tweet> getNewsFeed(int userId){
		/*
		 * Create PQ with decreasing time
		 * First add user feed 
		 * then iterate all follers and add tweets
		 * tweetMap.getOrDefault(userId, Collections.emptyList()))
		 * Loop and take 5
		 */
		
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	class Tweet{
		int id;
		int time;
	}

}
