package com.msb.lrg.problems.gof;

/*
	State Pattern
	What is the State Pattern?
		The State Pattern allows an object to change its behavior when its internal state changes.
		It appears as if the object has changed its class.
	Purpose
		To handle state-based behavior cleanly.
		To avoid complex if/else or switch statements scattered across the code.
		To allow each state to encapsulate its own behavior.
	Problem It Solves
		Without State Pattern:
			You often write code like:
				if (state == PLAYING) {
				    // do play actions
				} else if (state == PAUSED) {
				    // do pause actions
				} else if (state == STOPPED) {
				    // do stop actions
				}
	Problems:
		Hard to maintain
		Adding new states is difficult
		Breaks Open/Closed principle
		Behavior is mixed with state-check logic
	How the State Pattern Solves It
		Each state is a separate class.
		Behavior is delegated to the current state object.
		Context switches states dynamically.
		This removes all big if-else blocks and makes behavior dynamic and clean.
	Advantages
		Removes large conditional logic
		State-specific behavior goes inside state classes.
		Makes the system scalable
		Adding a new state = create a new class.
		Clean separation of concerns
		Each state has its own logic.
		Follows Open/Closed Principle
		New behavior does not modify existing logic.
	Disadvantages
		More classes
		Each state becomes a class → increases code files.
		Can be overkill
		For simple states, using enums + switch may be enough.

 */
public class StateDemo {

	public static void main(String[] args) {
        Player player = new Player();

        player.play();
        player.pause();
        player.play();
        player.stop();
        player.pause();
	}

}

class Player {
	
	private State state;
	
	Player(){
		state = new StopState();
	}
	
	void setState(State state) {
		this.state = state;
	}
	
	void play() {
		state.play(this);
	}
	
	void pause() {
		state.pause(this);
	}
	
	void stop() {
		state.stop(this);
	}
}

interface State {
	void play(Player player);
	void pause(Player player);
	void stop(Player player);
}

class PlayState implements State {

	public void play(Player player) {
		System.out.println("Already playing ...");
	}
	
	public void pause(Player player) {
		System.out.println("Pausing the player ...");
		player.setState(new PausedState());
	}
	
	public void stop(Player player) {
		System.out.println("Stopping playback ...\"");
		player.setState(new StopState());
	}	

}

class PausedState implements State {

	public void play(Player player) {
		System.out.println("Resuming playback ...");
		player.setState(new PlayState());
	}
	
	public void pause(Player player) {
		System.out.println("Already puased ...");
	}
	
	public void stop(Player player) {
		System.out.println("Stopping from paused ...");
		player.setState(new StopState());
	}	

}

class StopState implements State {

	public void play(Player player) {
		System.out.println("Starting playback ...");
		player.setState(new PlayState());
	}
	
	public void pause(Player player) {
		System.out.println("Cannot pause when stopped ...");
		player.setState(new PausedState());
	}
	
	public void stop(Player player) {
		System.out.println("Already stopped ...");
	}	

}
