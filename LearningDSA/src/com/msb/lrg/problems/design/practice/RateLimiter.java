package com.msb.lrg.problems.design.practice;

import java.util.Map;

public class RateLimiter {
	
	Map<String, TokenBucket> map;
    private final int capacity;
    private final int refillRate; // tokens per second
	
    public RateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }
	
	public boolean allow(String id) {
		/*
		 * check and put Token Bucket entry in map
		 * Get and check allow on Token Bucket
		 */
		return false;
	}
	
	static class TokenBucket {
		public int capacity;
		public int refillRate;
		long lastAccess;
		int token;
		
		public TokenBucket(int capacity, int refillRate) {
			
		}
		
		public boolean allow() {
			/*
			 * call refill()
			 * 
			 * check if token is > 0
			 * yes decreade and allow 
			 * otherwise false
			 * 
			 */
			return false;
		}
		
		public void refill() {
			/*
			 * current tie  -  last access time
			 * convert to seconds fro nanoSeconds
			 * update token count
			 */
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
