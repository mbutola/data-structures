package com.msb.lrg.problems.gof;

/*
 	Command Pattern (GoF Behavioral Pattern)
	What is the Command Pattern?
		Command Pattern turns a request (an action) into a standalone object.
		This object contains:
			What action to perform
			Who should perform it
			Required parameters
		In simple words:
			It encapsulates a request as an object so it can be stored, queued, logged, or undone.
		Purpose
			Separate the object that invokes an action from the object that performs the action
			Represent operations as first-class objects
			Support features like:
				Undo / Redo
				Queuing commands
				Logging commands
				Macro commands (batch operations)
		Problem It Solves
			Without Command pattern:
				Button click → directly calls Light.on()
				Menu item → directly calls Document.save()
			Client and receiver are tightly coupled
			Hard to add “undo”, “redo”, “queue”, “log” functionality
			Hard to parameterize requests
			Command pattern removes this tight coupling.
		How It Solves the Problem
			It introduces 4 components:
				Command Interface
					Defines execute() (and optional undo()).
				Concrete Command
					Implements the command — calls the actual receiver.
				Receiver
					Actual business logic (Light, TV, DB, File, etc.)
				Invoker
					UI button, menu, job scheduler — calls command.execute().
			So the invoker doesn't know the receiver details.
		Advantages
			Decouples UI (invokers) from business logic (receivers)
			Easy to add Undo/Redo
			Commands can be stored, queued, batched
			Open/Closed principle
			Add a new command without touching existing code.
		Disadvantages
			More classes → more boilerplate
			Simple operations become more complex
		Real-World Examples
			GUI Buttons → every button action is a command
			Queue of jobs (batch processors)
			Undo/Redo in editors (VSCode, Word, Photoshop)
			Scheduler commands

 */
public class CommandDemo {

	public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        CommandControl remote = new CommandControl();

        remote.setCommand(lightOn);
        remote.pressButton();  // ON
        remote.pressUndo();    // OFF

        remote.setCommand(lightOff);
        remote.pressButton();  // OFF
        remote.pressUndo();    // ON
	}

}

interface Command {
	void execute();
	void undo();
}

class Light {
	void turnOn() {
		System.out.println("Light is ON");
	}

	void turnOff() {
		System.out.println("Light is OFF");
	}
}

class LightOnCommand implements Command {
	
	private Light light;
	
	LightOnCommand(Light light){
		this.light = light;
	}
	
	public void execute() {
		light.turnOn();
	}

	public void undo() {
		light.turnOff();
	}
}

class LightOffCommand implements Command {
	
	private Light light;
	
	LightOffCommand(Light light){
		this.light = light;
	}

	public void execute() {
		light.turnOff();
	}

	public void undo() {
		light.turnOn();
	}
}

class CommandControl {
	
	private Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void pressButton() {
		command.execute();
	}
	
	public void pressUndo() {
		command.undo();
	}
}