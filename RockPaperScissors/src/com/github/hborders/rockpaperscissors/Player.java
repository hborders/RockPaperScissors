package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

public class Player {
	private final String rawPlayer;
	private final int playerNumber;
	private final WonRoundCount wonRoundCount;

	public Player(String rawPlayer, int playerNumber) {
		this(rawPlayer, playerNumber, new WonRoundCount());
	}

	Player(String rawPlayer, int playerNumber, WonRoundCount wonRoundCount) {
		this.rawPlayer = rawPlayer;
		this.playerNumber = playerNumber;
		this.wonRoundCount = wonRoundCount;
	}

	public void write(Writer writer) throws IOException {
		writer.write(rawPlayer + " (Player " + playerNumber + ")");
	}

	public WonRoundCount getWonRoundCount() {
		return wonRoundCount;
	}

	public void wonRound() {
		wonRoundCount.increment();
	}

	static class Provider {
		public Player provide(String player, int playerNumber,
				WonRoundCount wonRoundCount) {
			return new Player(player, playerNumber);
		}
	}
}
