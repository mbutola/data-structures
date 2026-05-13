package com.msb.lrg.problems.design.practice;

import java.util.Map;

public class ShoppingCart {

	Map<Integer, CartItem> cart;
	PricingStrategy strategy;
	
	ShoppingCart(PricingStrategy strategy){
		/*
		 * Create HashMap
		 * 
		 */
	}
	
	public void addItem(int id, int quantity) {
		/*
		 * Product id and quantity
		 * If CartItem does not exist
		 * 	create new CartItem entry
		 * Else
		 * 	update quantity
		 */
	}
	
	public void removeItem(int id) {
		/*
		 * Product id
		 * If CartItem exist
		 * 	remove
		 */
	}

	public void updateQuantity(int id, int quantity) {
		/*
		 * Product id and quantity
		 * If CartItem exist
		 * 	replace quantity
		 */
	}

	public double getTotal() {
		/*
		 * loop through all items in the cart and call getSubTotal
		 * and then total
		 * Apply strategy
		 */
		return 0;
	}
		
	static class CartItem {
		Product product;
		int quantity;
		
		public double getSubTotal() {
			return 0;
		}
	}
	
	static interface PricingStrategy {
		public double apply(double amount);
	}
	
	static class PercentageStrategy implements PricingStrategy{
		public double apply(double amount) {
			return 0;
		}
	}
	
	static class Product {
		int id;
		String name;
		double price;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
