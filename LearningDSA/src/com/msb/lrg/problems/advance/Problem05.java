package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

⭐ Problem 5 — Async Dependency DAG
	🧩 Problem
		Workflow:
			user → orders → shipment → invoice
			orders → payments
		Return invoice.
		Graph dependencies + parallel branches.


 */
public class Problem05 {
	
	static ExecutorService pool = Executors.newFixedThreadPool(3);

	public static void main(String[] args) {
        System.out.println(run());
        pool.shutdown();
	}

	static Invoice run() {
		
		CompletableFuture<User> uF = CompletableFuture.supplyAsync(Problem05::getUser, pool);
		
		CompletableFuture<List<Order>> oF = uF.thenCompose( u -> 
												CompletableFuture.supplyAsync(() -> getOrders(u), pool));
		
		CompletableFuture<Shipment> sF = oF.thenCompose( o -> 
												CompletableFuture.supplyAsync(() -> getShipment(o), pool));

		CompletableFuture<Payment> pF = oF.thenCompose( o -> 
												CompletableFuture.supplyAsync(() -> getPayment(o), pool));
		
		return sF.thenCombine(pF, (a,b) -> new Invoice(a,b)).join();
}
	
	static User getUser() { sleep(200); return new User("U"); }
	static List<Order> getOrders(User u) { sleep(300); return List.of(new Order(1)); }
	static Shipment getShipment(List<Order> orders) { sleep(400); return new Shipment(1); }
	static Payment getPayment(List<Order> orders) { sleep(500); return new Payment(1); }
	
	static class User { String id; User(String id) { this.id = id; } }
	static class Order { int id; Order(int id) { this.id = id; } }
	static class Shipment { int id; Shipment(int id) { this.id = id; } }
	static class Payment { int id; Payment(int id) { this.id = id; } }
	static class Invoice {
		Shipment s;
		Payment p;
		Invoice(Shipment s, Payment p) {
			this.s = s;
			this.p = p;
		}
		public String toString(){return "Invoice";}
	}

    static void sleep(long ms){ try{Thread.sleep(ms);}catch(Exception ignored){} }
	
}
