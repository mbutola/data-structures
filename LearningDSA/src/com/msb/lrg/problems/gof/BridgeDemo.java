package com.msb.lrg.problems.gof;

/*
	What is the Bridge Pattern?
		The Bridge Pattern is a GoF Structural pattern that decouples an abstraction from its implementation, so 
		both can vary independently.
		In simple words:
			Bridge lets you split a big class into two independent hierarchies—Abstraction and 
			Implementation—so you avoid subclass explosion.
	Purpose
		To avoid class explosion caused by combining multiple dimensions of variation.
		To allow independent extensibility of:
			What the object does → Abstraction
			How the object does it → Implementation
	Problem It Solves
		Imagine you want to create different Remote Controls (TV, Radio) with different types (BasicRemote, AdvancedRemote).
		Naive approach → subclass everything:
			BasicTvRemote
			AdvancedTvRemote
			BasicRadioRemote
			AdvancedRadioRemote
			SuperAdvancedTvRemote
			... endless classes
		This leads to:
			Too many subclasses
			Rigid structure
			Hard to extend or modify
	How Bridge Solves the Problem
		Bridge splits the system into two separate class hierarchies:
			1. Abstraction (Remote control API)
				RemoteControl
					- BasicRemote
					- AdvancedRemote
			2. Implementation (Device API)
				Device
					- Tv
					- Radio
		The abstraction keeps a reference to the implementation:
			protected Device device;
			Now you can mix & match:
				BasicRemote + TV
				AdvancedRemote + Radio
			No subclass explosion.
	Advantages
		| Advantage                                          | Why it matters                              |
		| -------------------------------------------------- | ------------------------------------------- |
		| **Avoids subclass explosion**                      | Clean hierarchy even with many combinations |
		| **Abstraction and Implementation are independent** | Change one without touching the other       |
		| **Improves testability**                           | Mock implementations easily                 |
		| **Great for multi-platform designs**               | GUI, device drivers, cloud APIs             |
	Disadvantages
		| Disadvantage         | Explanation                                               |
		| -------------------- | --------------------------------------------------------- |
		| **More classes**     | Needs interfaces + classes for abstraction/implementation |
		| **More indirection** | Calls go through two layers                               |
	Example Code — Bridge Pattern in Java
		Scenario:
			We have Devices (TV, Radio) and Remote Controls (BasicRemote, AdvancedRemote).
			We combine them flexibly using Bridge.
 */
public class BridgeDemo {

	public static void main(String[] args) {
        Device tv = new Tv();
        Device radio = new Radio();

        RemoteControl basicTvRemote = new BasicRemote(tv);
        RemoteControl advancedRadioRemote = new AdvancedRemote(radio);

        basicTvRemote.powerOn();
        basicTvRemote.setVolume(30);
        basicTvRemote.powerOff();

        System.out.println("-----");

        advancedRadioRemote.powerOn();
        advancedRadioRemote.setVolume(50);
        ((AdvancedRemote) advancedRadioRemote).mute();
        advancedRadioRemote.powerOff();	}

}

abstract class RemoteControl {
	protected Device device;
	
	RemoteControl(Device device){
		this.device = device;
	}

	abstract void powerOn();
	abstract void powerOff();
	abstract void setVolume(int percentage);
}

class BasicRemote extends RemoteControl {
	
	BasicRemote(Device device){
		super(device);
	}
	
	public void powerOn() {
		device.turnOn();
	}

	public void powerOff() {
		device.turnOff();
	}

	public void setVolume(int percent) {
		device.setVolume(percent);
	}

}

class AdvancedRemote extends RemoteControl {
	
	AdvancedRemote(Device device){
		super(device);
	}

	public void powerOn() {
		System.out.println("Advanced Power ON");
		device.turnOn();
	}

	public void powerOff() {
		System.out.println("Advanced Power OFF");
		device.turnOff();
	}

	public void setVolume(int percent) {
		System.out.println("Advanced Volume Control");
		device.setVolume(percent);
	}

	public void mute() {
		System.out.println("Device muted");
		device.setVolume(0);
	}
}

interface Device {
	void turnOn();
	void turnOff();
	void setVolume(int percentage);
}

class Tv implements Device {
	
	public void turnOn() {
		System.out.println("TV turned ON");
	}
	
	public void turnOff() {
		System.out.println("TV turned OFF");
	}
	
	public void setVolume(int percentage) {
		System.out.println("TV volume set to :: " + percentage );
	}
}

class Radio implements Device {
	
	public void turnOn() {
		System.out.println("Radio turned ON");
	}
	
	public void turnOff() {
		System.out.println("Radio turned OFF");
	}
	
	public void setVolume(int percentage) {
		System.out.println("Radio volume set to :: " + percentage );
	}
}
