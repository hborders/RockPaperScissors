package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class BestofGame implements IGame {
	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round round;

	public BestofGame(WonRoundCount winningWonRoundCount, Player firstPlayer,
			Player secondPlayer) {
		this(winningWonRoundCount, firstPlayer, secondPlayer, new Round());
	}

	BestofGame(WonRoundCount winningWonRoundCount, Player firstPlayer,
			Player secondPlayer, Round round) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.round = round;
	}

	@Override
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

	static class Provider {
		public BestofGame provide(WonRoundCount winningWonRoundCount,
				Player firstPlayer, Player secondPlayer, Round round) {
			return new BestofGame(winningWonRoundCount, firstPlayer,
					secondPlayer, round);
		}
	}
}
