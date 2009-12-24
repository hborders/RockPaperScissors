package com.github.hborders.rockpaperscissors;

import java.io.Writer;

public class AttemptFactory {
	private final Writer writer;
	private final Console console;

	public AttemptFactory() {
		this(new Console());
	}

	private AttemptFactory(Console console) {
		this(console.writer(), console);
	}

	AttemptFactory(Writer writer, Console console) {
		this.writer = writer;
		this.console = console;
	}

	public Attempt createAttempt(Player player) {
		return null;
	}

	static class Provider {
		public AttemptFactory provide(Writer writer, Console console) {
			return new AttemptFactory(writer, console);
		}
	}
}
