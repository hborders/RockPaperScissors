package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

import com.github.hborders.rockpaperscissors.AttemptFactory.InvalidRawAttemptException;

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

	public Attempt createAttempt(Player player) throws IOException {
		Attempt attempt = null;
		do {
			player.write(writer);
			writer.write(" [R]ock, [P]aper, or [S]cissors? ");
			String input = console.readLine();
			try {
				attempt = attemptFactory.createAttempt(input);
			} catch (InvalidRawAttemptException invalidRawAttemptException) {
			}
		} while (attempt == null);

		return attempt;
	}

	static class Provider {
		public AttemptReader provide(Writer writer, Console console,
				AttemptFactory attemptFactory) {
			return new AttemptReader(writer, console, attemptFactory);
		}
	}
}
