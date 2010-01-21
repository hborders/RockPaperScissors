package com.github.hborders.rockpaperscissors;

import java.io.IOException;
import java.io.Writer;

public class Player {
	private final String rawPlayer;
	private final int playerNumber;
	private final WonRoundCount wonRoundCount;

	public Player(String rawPlayer, int playerNumber,
			WonRoundCount wonRoundCount) {
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

	@Override
	public String toString() {
		return "Player[" + rawPlayer + " (Player " + playerNumber + ")]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerNumber;
		result = prime * result
				+ ((rawPlayer == null) ? 0 : rawPlayer.hashCode());
		result = prime * result
				+ ((wonRoundCount == null) ? 0 : wonRoundCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerNumber != other.playerNumber)
			return false;
		if (rawPlayer == null) {
			if (other.rawPlayer != null)
				return false;
		} else if (!rawPlayer.equals(other.rawPlayer))
			return false;
		if (wonRoundCount == null) {
			if (other.wonRoundCount != null)
				return false;
		} else if (!wonRoundCount.equals(other.wonRoundCount))
			return false;
		return true;
	}
}
