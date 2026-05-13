package com.msb.lrg.problems.design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class H1RateLimiterDesign {
	
	static class RateLimiter {

	    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();
	
	    private final int capacity;
	    private final int refillRate; // tokens per second
	
	    public RateLimiter(int capacity, int refillRate) {
	        this.capacity = capacity;
	        this.refillRate = refillRate;
	    }
	
	    public boolean allowRequest(String userId) {
	        TokenBucket bucket = buckets.computeIfAbsent(
	            userId,
	            id -> new TokenBucket(capacity, refillRate)
	        );
	
	        return bucket.allow();
	    }
	}

    static class TokenBucket {
        private final int capacity;
        private final int refillRate;

        /*
         * token should be double for accurate calculations otherwise with time it 
         * will become inaccurate as token can be fractions. 
         */
        private double tokens;
        private long lastRefillTime;

        public TokenBucket(int capacity, int refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.tokens = capacity;
            this.lastRefillTime = System.nanoTime();
        }

        public synchronized boolean allow() {
            refill();

            if (tokens >= 1) {
                tokens -= 1;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.nanoTime();
            double seconds = (now - lastRefillTime) / 1_000_000_000.0;

            double newTokens = seconds * refillRate;
            tokens = Math.min(capacity, tokens + newTokens);

            lastRefillTime = now;
        }
    }

    // Test
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5, 2); // 5 burst, 2/sec

        for (int i = 0; i < 10; i++) {
            System.out.println(limiter.allowRequest("user1"));
            Thread.sleep(200);
        }
    }
}