package com.github.hborders.rockpaperscissors;

public abstract class Attempt {
	Attempt() {
	}

	public abstract boolean beats(Attempt otherAttempt);

	public static final Attempt ROCK = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == SCISSORS);
		}
	};

	public static final Attempt PAPER = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == ROCK);
		}
	};

	public static final Attempt SCISSORS = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == PAPER);
		}
	};
}
