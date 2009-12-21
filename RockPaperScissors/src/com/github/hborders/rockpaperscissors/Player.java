package com.github.hborders.rockpaperscissors;

public class Player {
	private final String player;

	public Player(String player) throws InvalidPlayerException {
		if (player == null) {
			throw new InvalidPlayerException();
		}

		this.player = player.trim();
		if (this.player.length() == 0) {
			throw new InvalidPlayerException();
		}
	}

	public static class InvalidPlayerException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
