package com.github.hborders.rockpaperscissors;

public class AttemptFactory {
	public Attempt createAttempt(String rawAttempt)
			throws InvalidRawAttemptException {
		return null;
	}

	public static class InvalidRawAttemptException extends Exception {
		private static final long serialVersionUID = 1L;

	}
}
