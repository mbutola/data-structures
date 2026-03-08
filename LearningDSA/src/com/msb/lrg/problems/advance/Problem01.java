package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*

Problem 1 — Parallel Aggregator with Timeout + Fallback
	🧩 Problem
		Build backend method:
			getAccount(id)
		It must call 3 microservices in parallel:
			User service
			Orders service
			Payments service
		Constraints:
			Each call may fail
			Each call has timeout 1s
			If fails/timeout → fallback empty
			Return combined AccountView
			Total latency ≈ slowest successful call
		This is classic microservice fan-out/fan-in.
	🧠 Step-by-Step Explanation
		Start 3 async calls using supplyAsync → parallel fan-out
		orTimeout ensures max 1s per call
		exceptionally converts failure → fallback value
		thenCombine merges results (fan-in)
		join() waits only once at end
		Latency = max(service times) ≈ optimal.

 */
public class Problem01 {

	static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) {
		System.out.println(getAccount("42"));
		pool.shutdown();
	}
	
	static AccountView getAccount(String id) {
		CompletableFuture<User> userF = 
						CompletableFuture.supplyAsync(() -> userSvc(id), pool)
								.orTimeout(1, TimeUnit.SECONDS)
								.exceptionally(e -> User.EMPTY);
		
		CompletableFuture<List<Order>> orderF = 
				CompletableFuture.supplyAsync(() -> orderSvc(id), pool)
						.orTimeout(1, TimeUnit.SECONDS)
						.exceptionally(e -> List.of());

		CompletableFuture<List<Payment>> payF = 
				CompletableFuture.supplyAsync(() -> paymentSvc(id), pool)
						.orTimeout(1, TimeUnit.SECONDS)
						.exceptionally(e -> List.of());
		
		return userF
				.thenCombine(orderF, Pair::new)
				.thenCombine(payF, 
						(uo,p) -> new AccountView(uo.first, uo.second, p))
				.join();
		
	}
	
	static class Pair<A,B>{
		A first;
		B second;
		Pair(A f, B s){
			this.first = f;
			this.second = s;
		}
	}
	
	static class AccountView {
		User user;
		List<Order> orders;
		List<Payment> payments;
		
		AccountView(User u, List<Order> o, List<Payment> p){
			this.user = u;
			this.orders = o;
			this.payments = p;
		}
		
		public String toString() {
			return user.name + " orders=" + orders.size() + " payments=" + payments.size();
		}
	}
	
	static User userSvc(String id) {
		sleep(500);
		if(Math.random() < 0.3) 
			throw new RuntimeException();
		return new User("User-" + id);
	}
	
	static List<Order> orderSvc(String id) {
		sleep(800);
		return List.of(new Order(1), new Order(2));
	}
	
	static List<Payment> paymentSvc(String id) {
		sleep(1200);
		return List.of(new Payment(10));
	}
	
	static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	static class User {
		String name;
		User(String name){
			this.name = name;
		}
		
		static final User EMPTY = new User("NA");
	}
	
	static class Order {
		int id;
		Order(int id){
			this.id = id;
		}
	}
	
	static class Payment {
		int id;
		Payment(int id){
			this.id = id;
		}
	}
	
}
