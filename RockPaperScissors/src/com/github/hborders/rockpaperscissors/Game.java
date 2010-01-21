package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Game {
	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round round;

	public Game(WonRoundCount winningWonRoundCount, Player firstPlayer,
			Player secondPlayer, Round round) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.round = round;
	}

	public Player play() throws IOException {
		while (true) {
			if (winningWonRoundCount.matches(firstPlayer.getWonRoundCount())) {
				return firstPlayer;
			} else if (winningWonRoundCount.matches(secondPlayer
					.getWonRoundCount())) {
				return secondPlayer;
			} else {
				round.play(firstPlayer, secondPlayer);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstPlayer == null) ? 0 : firstPlayer.hashCode());
		result = prime * result + ((round == null) ? 0 : round.hashCode());
		result = prime * result
				+ ((secondPlayer == null) ? 0 : secondPlayer.hashCode());
		result = prime
				* result
				+ ((winningWonRoundCount == null) ? 0 : winningWonRoundCount
						.hashCode());
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
		Game other = (Game) obj;
		if (firstPlayer == null) {
			if (other.firstPlayer != null)
				return false;
		} else if (!firstPlayer.equals(other.firstPlayer))
			return false;
		if (round == null) {
			if (other.round != null)
				return false;
		} else if (!round.equals(other.round))
			return false;
		if (secondPlayer == null) {
			if (other.secondPlayer != null)
				return false;
		} else if (!secondPlayer.equals(other.secondPlayer))
			return false;
		if (winningWonRoundCount == null) {
			if (other.winningWonRoundCount != null)
				return false;
		} else if (!winningWonRoundCount.equals(other.winningWonRoundCount))
			return false;
		return true;
	}
}
