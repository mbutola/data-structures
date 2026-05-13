package com.msb.lrg.problems.design.practice;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class WebCrawler {
	
	Set<String> visited = ConcurrentHashMap.newKeySet();
	BlockingQueue<String> queue = new LinkedBlockingDeque<>();
	AtomicBoolean running = new AtomicBoolean(true);
	ExecutorService service;
	
	private static final int MAX_THREADS = 5;
	
	WebCrawler(int maxThreads){
		service = Executors.newFixedThreadPool(maxThreads);
	}
	
	public void startCrawling(List<String> seeds) {
		/*
		 * Add seeds to queue
		 * for loop and start crawl
		 * method reference converts crawl to runnable as both signature matches
		 * sumbit(this::crawl)
		 * 
		 */
	}
	
	public void crawl(String url) {
		
		while(running.get()) {
			
			/*
			 * poll with timeout or blocking take()
			 * if null continue
			 * else
			 * 	call fetchContect()
			 * 	extractLinks()
			 * 	loops through links
			 * 	if not visited (call add()) then add to queue
			 */
		}
		
	}
	
	public String fetchContent(String url) {
		return null;
	}
	
	public Set<String> extractLinks(String url) {
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Create List of String as seed
		 * Call WebCraler startCrawling
		 * 
		 * Set running.set(false)
		 * service.shutdown()
		 *
		 */

	}

}
