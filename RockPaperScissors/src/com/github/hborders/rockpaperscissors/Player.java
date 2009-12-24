package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

public class Player {
	private final String rawPlayer;
	private final int playerNumber;

	public Player(String rawPlayer, int playerNumber)
			throws InvalidPlayerException {
		if (rawPlayer == null) {
			throw new InvalidPlayerException();
		}

		this.rawPlayer = rawPlayer.trim();
		if (this.rawPlayer.length() == 0) {
			throw new InvalidPlayerException();
		}
		this.playerNumber = playerNumber;
	}

	public void write(Writer writer) throws IOException {
		writer.write(rawPlayer + " (Player " + playerNumber + ")");
	}

	static class Provider {
		public Player provide(String player, Integer playerNumber)
				throws InvalidPlayerException {
			return new Player(player, playerNumber);
		}
	}

	public static class InvalidPlayerException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
