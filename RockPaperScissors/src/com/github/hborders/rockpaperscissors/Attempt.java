package com.github.hborders.rockpaperscissors;

public abstract class Attempt {
	Attempt() {
	}

	public abstract boolean beats(Attempt otherAttempt);

	protected abstract String getName();

	@Override
	public String toString() {
		return "Attempt[" + getName() + "]";
	}

	public static final Attempt ROCK = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == SCISSORS);
		}

		@Override
		public String getName() {
			return "ROCK";
		}
	};

	public static final Attempt PAPER = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == ROCK);
		}

		@Override
		public String getName() {
			return "PAPER";
		}
	};

	public static final Attempt SCISSORS = new Attempt() {
		@Override
		public boolean beats(Attempt otherAttempt) {
			return (otherAttempt == PAPER);
		}

		@Override
		public String getName() {
			return "SCISSORS";
		}
	};
}
