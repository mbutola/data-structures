package com.msb.lrg.problems;

import lombok.Builder;
import lombok.Data;

/*
	Lombok generates getters, setters, equals, hashCode, toString, and builder code 
	at compile time, reducing boilerplate and improving readability.
 */
public class LombokDemo {

	public static void main(String[] args) {
	System.out.println(UserLombark.builder()
							.name("Manmohan")
							.age(50)
							.build());
	}

}

@Data
@Builder
class UserLombark {
	private String name;
	private int age;
}

