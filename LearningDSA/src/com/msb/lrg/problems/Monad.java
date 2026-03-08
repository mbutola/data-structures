package com.msb.lrg.problems;

import java.util.function.Consumer;
import java.util.function.Function;

public class Monad {

	public static void main(String[] args) {
			
		User user = new User(new Address("Delhi"));
//		User user = new User(null);
//		User user = new User(new Address(null));
		
		System.out.println(MayBe.of(user)
							.map(User::getAddress)
							.peek(a -> System.out.println("Address :: " + a))
							.map(Address::getCity)
							.orElse("Unknown"));
	}

}

class MayBe<T> {
	
	private final T value;
	
	private MayBe(T value){
		this.value = value;
	}
	
	public static <T> MayBe<T> of(T value){
		return new MayBe<>(value);
	}
	
	/*
		Function<T,R> requires:
			R apply(T t);
		For String::toUpperCase:
			String toUpperCase()
		Java rewrites this automatically as:
			(s) -> s.toUpperCase()
		So internally:
			mapper.apply(value)
		Becomes:
			value.toUpperCase()
	 */
	public <R> MayBe<R> map(Function<T,R> mapper){
		if(value == null) 
			return new MayBe<>(null);
		
		return new MayBe<>(mapper.apply(value));
	}

	/*
		static MayBe<Address> getAddress(User u) {
    		return u.address == null ? MayBe.of(null) : MayBe.of(u.address);
		}

		static MayBe<String> getCity(Address a) {
    	return a.city == null ? MayBe.of(null) : MayBe.of(a.city);
		}
		
		❌ Using map (bad)
				MayBe.of(user)
     					.map(BT::getAddress)
    					.map(BT::getCity);
			Result:
				MayBe<MayBe<MayBe<String>>> ❌
				
		✅ Using flatMap (good)
				MayBe.of(user)
     					.flatMap(BT::getAddress)
     					.flatMap(BT::getCity)
     					.orElse("Unknown");
				Clean and safe ✔️
	 */
	public <R> MayBe<R> flatMap(Function<T,MayBe<R>> mapper){
		if(value == null) 
			return new MayBe<>(null);
		
		return mapper.apply(value);
	}
	
	public T orElse(T defaultValue){
		return value != null ? value : defaultValue;
	}
	
	@Override
	public String toString() {
		return value == null ? "Nothing" : "Just(" + value +")";
	}
	
	public MayBe<T> peek(Consumer<T> action) {
		if(value != null)
			action.accept(value);
		return this;
	}
}

class User {
	Address address;
	
	User(Address address){
		this.address = address;
	}
	
	Address getAddress() {
		return this.address;
	}
}

class Address {
	String city;
	
	Address(String city){
		this.city = city;
	}
	
	String getCity() {
		return this.city;
	}
}
