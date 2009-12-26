package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Game {
	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round round;

	Game(WonRoundCount winningWonRoundCount, Player firstPlayer,
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

	public static class Provider {
		public Game provide(WonRoundCount winningWonRoundCount,
				Player firstPlayer, Player secondPlayer, Round round) {
			return new Game(winningWonRoundCount, firstPlayer, secondPlayer,
					round);
		}
	}
}
