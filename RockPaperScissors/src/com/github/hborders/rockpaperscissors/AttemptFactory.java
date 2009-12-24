package com.github.hborders.rockpaperscissors;

public class AttemptFactory {
	public Attempt createAttempt(String rawAttempt)
			throws InvalidRawAttemptException {
		if ("R".equalsIgnoreCase(rawAttempt)) {
			return Attempt.ROCK;
		} else if ("P".equalsIgnoreCase(rawAttempt)) {
			return Attempt.PAPER;
		} else if ("S".equalsIgnoreCase(rawAttempt)) {
			return Attempt.SCISSORS;
		} else {
			throw new InvalidRawAttemptException();
		}
	}

	public static class InvalidRawAttemptException extends Exception {
		private static final long serialVersionUID = 1L;

	}
}
