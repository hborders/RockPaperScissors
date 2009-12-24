package com.github.hborders.rockpaperscissors;

import java.io.Writer;

public class AttemptReader {
	private final Writer writer;
	private final Console console;
	private final AttemptFactory attemptFactory;

	public AttemptReader() {
		this(new Console(), new AttemptFactory());
	}

	private AttemptReader(Console console, AttemptFactory attemptFactory) {
		this(console.writer(), console, attemptFactory);
	}

	AttemptReader(Writer writer, Console console, AttemptFactory attemptFactory) {
		this.writer = writer;
		this.console = console;
		this.attemptFactory = attemptFactory;
	}

	public Attempt createAttempt(Player player) {
		return null;
	}

	static class Provider {
		public AttemptReader provide(Writer writer, Console console,
				AttemptFactory attemptFactory) {
			return new AttemptReader(writer, console, attemptFactory);
		}
	}
}