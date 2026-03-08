package com.msb.lrg.problems.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * Problem Scenario:
 * 		You are building a stock price retriever service.
 * 		Each stock price API call is slow and may hang.
 * 		You want to:
 * 			Run it asynchronously.
 * 			Timeout if it takes too long.
 * 			Cancel the task if the timeout expires.
 * 			Handle exceptions properly.
 * 			Collect results for multiple stocks in parallel.
 */
public class FutureAdvancedExample {

	public static void main(String[] args) throws InterruptedException {
		
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> stocks = List.of("AAPL", "GOOG", "TSLA", "MSFT", "AMZN");
        
        List<Future<Double>> futures = new ArrayList<>();
        for(String symbol : stocks) {
        	Future<Double> future = executor.submit(() -> fetchStockPrice(symbol));
        	futures.add(future);
        }
        
        for(int i=0; i<stocks.size(); i++) {
        	String symbol = stocks.get(i);
        	Future<Double> future = futures.get(i);
        	
        	try {
        		double price = future.get(2, TimeUnit.SECONDS);
        		System.out.println(symbol + ":: $" + price);
        	} catch(TimeoutException e) {
        		System.out.println("Timeout for :: " + symbol + ":: Cancelling");
        		future.cancel(true);
        	}catch(ExecutionException e) {
        		System.out.println("Failes for :: " + symbol + ":" + e.getCause());
        	} 	
        }
        
        executor.shutdown();

	}
	
	static double fetchStockPrice(String symbol) throws InterruptedException {
		long delay = (long) (Math.random() * 4000 + 1000);
		System.out.println("Fetching " + symbol + " (delay " + delay + " ms)");
		Thread.sleep(delay);
		return Math.random()*1000;
	}

}
