package com.github.hborders.rockpaperscissors;

public class Attempt {
	private final String attempt;

	public Attempt(String attempt) {
		this.attempt = attempt;
	}

	public boolean beats(Attempt otherAttempt) {
		return false;
	}
}
