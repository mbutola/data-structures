package com.msb.lrg.problems.gof;

/*
	Facade Pattern (Structural Pattern)
		The Facade Pattern provides a simple, unified interface to a complex system of classes, APIs, or subsystems.
		Think of it as a front desk in a hotel—clients don’t deal with all departments individually.
	Purpose
		To hide complexity of multiple subsystems.
		To expose a clean, easy-to-use interface.
		To decouple client code from internal details.
	Problem It Solves
		Without Facade → client must interact with many classes
		Imagine a Home Theater system:
			DVDPlayer
			Projector
			Amplifier
			Lights
			Sound System
		To watch a movie, the client must do:
			dvd.on();
			projector.on();
			soundSystem.setSurround();
			lights.dim();
		This tightly couples the client code to multiple subsystem classes.
	Problems:
		Too many method calls
		High coupling
		Changes in subsystems break client code
		Complex for new developers
	How Facade Solves It
		Facade introduces one simple class that internally calls many subsystems.
		Client only interacts with:
			homeTheater.watchMovie("Avatar");
			Internally:
			lights.dim();
			projector.on();
			dvd.on();
			soundSystem.setSurround();
		This hides all complexity.
Advantages
	| Benefit                              | Explanation                         |
	| ------------------------------------ | ----------------------------------- |
	| **Simplifies client code**           | Client uses only 1 high-level API   |
	| **Reduces coupling**                 | Client is decoupled from subsystems |
	| **Easier to use & maintain**         | Clean abstraction                   |
	| **Encapsulates internal complexity** | Subsystems can change freely        |
Disadvantages
	| Drawback                                | Explanation                                     |
	| --------------------------------------- | ----------------------------------------------- |
	| **Facade may grow into a “God object”** | If it tries to cover too much functionality     |
	| **Too much abstraction hides features** | Some advanced capabilities may be inaccessible  |
	| **Not flexible if poorly designed**     | Clients may bypass facade if it lacks functions |
Common real-world examples:
	JDBC → hides driver complexity
	Spring’s JdbcTemplate
	REST client libraries
	Payment SDKs
	Logging facade (SLF4J)

 */
public class FacadeDemo {

	public static void main(String[] args) {
        // Create subsystems
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        SoundSystem sound = new SoundSystem();

        // Create facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, projector, lights, sound);

        // Use facade
        homeTheater.watchMovie("Avatar");
        System.out.println("--- Movie Finished ---");
        homeTheater.endMovie();

	}

}

class DVDPlayer {
	public void on() {System.out.println("DVDPlayer ON");};
	public void play(String movie) {System.out.println("Playing movie :: " + movie);};
	public void off() {System.out.println("DVDPlayer OFF");};
}

class Projector {
	public void on() {System.out.println("Projector ON");};
	public void off() {System.out.println("Projector OFF");};
}

class Lights {
	public void dim() {System.out.println("Lights DIM");};
	public void on() {System.out.println("Lights ON");};
}

class SoundSystem {
	public void on() {System.out.println("SoundSystem ON");};
	public void setSurround() {System.out.println("Surround sound ENABLED");};
	public void off() {System.out.println("SoundSystem OFF");};
}

class HomeTheaterFacade {
	
	private DVDPlayer dvd;
	private Projector projector;
	private Lights lights;
	private SoundSystem sound;
	
	HomeTheaterFacade(DVDPlayer dvd, Projector projector, Lights lights, SoundSystem sound){
		this.dvd = dvd;
		this.projector = projector;
		this.lights = lights;
		this.sound = sound;
	}
	
	public void watchMovie(String movie) {
		System.out.println("Get ready for a movie...");
		projector.on();
		lights.dim();
		sound.on();
		sound.setSurround();
		dvd.on();
		dvd.play(movie);
	}
	
	public void endMovie() {
		System.out.println("Shutting movie theater down...");
		dvd.off();
		sound.off();
		projector.off();
		lights.on();
	}
}

