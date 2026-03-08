package com.msb.lrg.problems.gof;

/*
 	What Is the Prototype Pattern?
 	Prototype Pattern = "Make new objects by copying existing ones instead of building from scratch."
	The Prototype Pattern is a creational design pattern that lets you create new objects by cloning existing ones, instead of instantiating new ones from scratch.
	So rather than calling new, you say:
		“Give me another object like this one.”
	It’s useful when:
		Object creation is expensive (deep structures, DB calls, etc.)
		You want to avoid complex initialization.
		You want to clone an existing object’s configuration but change a few fields.
	Disadvantages :
		Cloning complex object graphs can be tricky (deep vs shallow)
		Hard to manage objects with circular references
		Requires proper Cloneable implementation or copy constructors
 */
public class PrototypeDemo {

	public static void main(String[] args) {
		Employee original = new Employee(1, "Alice", "HR", new Address("Delhi"));
        Employee copy = (Employee) original.clone(); // clone instead of new
        
        System.out.println(original == copy);
        System.out.println(original.address == copy.address);
        
        copy.showDetails(); // 1 - Alice - HR
	}

}

class Address implements Cloneable {
	String city;
	Address(String city){
		this.city = city;
	}

	public Address clone() {
    	try {
    		return (Address)super.clone();
    	} catch(CloneNotSupportedException e) {
    		throw new AssertionError();
    	}
    }
}

class Employee implements Cloneable {
    private int id;
    private String name;
    private String department;
    Address address;
    
    Employee(int id, String name, String department, Address address){
    	this.id = id;
    	this.name = name;
    	this.department = department;
    	this.address = address;
    }
    
    public Employee clone() {
    	try {
    		Employee copy = (Employee)super.clone();
    		copy.address = address.clone();
    		return copy;
    	} catch(CloneNotSupportedException e) {
    		throw new AssertionError();
    	}
    }
    
    public void showDetails() {
        System.out.println(id + " - " + name + " - " + department);
    }
}
