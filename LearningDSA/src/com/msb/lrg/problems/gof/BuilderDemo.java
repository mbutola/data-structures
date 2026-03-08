package com.msb.lrg.problems.gof;

/*
	What is the Builder Pattern?
		The Builder Pattern is a creational design pattern that lets you construct complex objects step-by-step,
		separating the construction process from the representation.
		In other words:
			Instead of passing 10 arguments into a constructor, you build the object in a readable, 
			staged way — like assembling a car part-by-part.

	Purpose (Intent)
		To separate the construction of a complex object from its representation.
		Allow creating different representations of the same type of object using the same building process.
		Make object creation more readable and maintainable, especially when there are many optional fields.

	Problem It Solves
		Let’s say we have a User class:
			User user = new User("John", "Doe", 30, "New York", "12345", "john@email.com", true, false, "Engineer");
		Problems:
			Hard to read (which parameter is which?)
			Hard to maintain (adding/removing parameters breaks client code)
			Constructor overloading explosion (User(...), User(...), User(...) for different combinations)

	How Builder Solves It
		The Builder pattern introduces:
		A separate Builder class that constructs the object step-by-step.
		The final build() method returns the completed object.
 */
public class BuilderDemo {

	public static void main(String[] args) {
		User user = new User.Builder()
								.setFirstName("John")
								.setLastName("Doe")
								.setAge(30)
								.setCity("New York")
								.setEmail("john.doe.email.com")
								.build();
		System.out.println(user);

	}

}

class User {
	
	String firstName;
	String lastName;
	int age;
	String city;
	String email;

	User(Builder builder){
		firstName = builder.firstName;
		lastName = builder.lastName;
		age = builder.age;
		city = builder.city;
		email = builder.email;
	}
	
	@Override
    public String toString() {
        return String.format("%s %s (%d) from %s [%s]", firstName, lastName, age, city, email);
    }
	
	static class Builder {
		
		String firstName;
		String lastName;
		int age;
		String city;
		String email;

		Builder setAge(int age) {
			this.age = age;
			return this;
		}

		Builder setCity(String city) {
			this.city = city;
			return this;
		}

		Builder setEmail(String email) {
			this.email = email;
			return this;
		}
	
		Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		User build() {
			return new User(this); 
		}
		
	}
}
