package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import com.github.hborders.rockpaperscissors.AttemptFactory.InvalidRawAttemptException;
import com.google.inject.Inject;

public class AttemptReader {
	private final BufferedReader bufferedReader;
	private final Writer writer;
	private final AttemptFactory attemptFactory;

	@Inject
	public AttemptReader(BufferedReader bufferedReader, Writer writer,
			AttemptFactory attemptFactory) {
		this.bufferedReader = bufferedReader;
		this.writer = writer;
		this.attemptFactory = attemptFactory;
	}

	public Attempt createAttempt(Player player) throws IOException {
		Attempt attempt = null;
		do {
			player.write(writer);
			writer.write(" [R]ock, [P]aper, or [S]cissors? ");
			writer.flush();
			String input = bufferedReader.readLine();
			try {
				attempt = attemptFactory.createAttempt(input);
			} catch (InvalidRawAttemptException invalidRawAttemptException) {
			}
		} while (attempt == null);

		return attempt;
	}
}
