package com.msb.lrg.problems.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ParallelApiExampleOne {

	static class User{
		int userId;
		String userName;
		
		User(int userId, String userName){
			this.userId = userId;
			this.userName = userName;
		}
	}

	static class Order{
		int orderId;
		int userId;
		String itemName;
		
		Order(int userId, int orderId, String itemName){
			this.userId = userId;
			this.orderId = orderId;
			this.itemName = itemName;
		}
		
		public String toString() {
			return "UserID::"+ userId + ",OrderID::" + orderId + ",Item::" + itemName;
		}
	}

	public static void main(String[] args) {
		CompletableFuture.supplyAsync(() -> getUserProfile(1))
						.thenCompose(user -> CompletableFuture.supplyAsync(() -> getUserOrders(user)))
						.thenAccept(System.out::println)
						.join();
	
//		// Separate futures
//		CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> getUserProfile(1));
//		CompletableFuture<List<Order>> orderFuture = userFuture.thenCompose(
//														(user) -> CompletableFuture.supplyAsync(() -> getUserOrders(user)));
//		
//		CompletableFuture<Void> results = orderFuture.thenAccept(System.out::println);
//		
//		results.join();
	}
	
	static User getUserProfile(int id) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return new User(id, "Tom");
	}

	static List<Order> getUserOrders(User user) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	
		return Arrays.asList(new Order(user.userId,1,"Apple"), new Order(user.userId,2,"Orange"));
	}
}
