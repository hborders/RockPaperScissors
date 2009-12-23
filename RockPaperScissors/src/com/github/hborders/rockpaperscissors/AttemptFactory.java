package com.github.hborders.rockpaperscissors;

public class AttemptFactory {
	private final Console console;

	public AttemptFactory() {
		this(new Console());
	}

	AttemptFactory(Console console) {
		this.console = console;
	}

	public Attempt createAttempt(Player player, int number) {
		return null;
	}

	static class Provider {
		public AttemptFactory provide(Console console) {
			return new AttemptFactory(console);
		}
	}
}
