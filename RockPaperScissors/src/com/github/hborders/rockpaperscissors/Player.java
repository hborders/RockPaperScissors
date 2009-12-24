package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

public class Player {
	private final String rawPlayer;
	private final int playerNumber;
	private int wins;

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

	public int getWins() {
		return wins;
	}

	public void wonGame() {
		wins++;
	}

	public static class InvalidPlayerException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class Provider {
		public Player provide(String player, Integer playerNumber)
				throws InvalidPlayerException {
			return new Player(player, playerNumber);
		}
	}
}
