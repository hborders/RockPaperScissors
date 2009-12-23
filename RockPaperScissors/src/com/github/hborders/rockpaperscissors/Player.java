package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

public class Player {
	private final String rawPlayer;

	public Player(String rawPlayer) throws InvalidPlayerException {
		if (rawPlayer == null) {
			throw new InvalidPlayerException();
		}

		this.rawPlayer = rawPlayer.trim();
		if (this.rawPlayer.length() == 0) {
			throw new InvalidPlayerException();
		}
	}

	public void write(Writer writer) throws IOException {
		writer.write(rawPlayer);
	}

	static class Provider {
		public Player provide(String player) throws InvalidPlayerException {
			return new Player(player);
		}
	}

	public static class InvalidPlayerException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
