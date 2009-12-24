package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class BestofGame implements IGame {
	private final int gameCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round round;

	public BestofGame(int gameCount, Player firstPlayer, Player secondPlayer) {
		this(gameCount, firstPlayer, secondPlayer, new Round());
	}

	BestofGame(int gameCount, Player firstPlayer, Player secondPlayer,
			Round round) {
		this.gameCount = gameCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.round = round;
	}

	@Override
	public Player play() throws IOException {
		int winsNeeded = (gameCount / 2) + 1;
		while (true) {
			int firstPlayerWins = firstPlayer.getWins();
			int secondPlayerWins = secondPlayer.getWins();
			if (firstPlayerWins == winsNeeded) {
				return firstPlayer;
			} else if (secondPlayerWins == winsNeeded) {
				return secondPlayer;
			} else {
				round.play(firstPlayer, secondPlayer);
			}
		}
	}

	static class Provider {
		public BestofGame provide(int gameCount, Player firstPlayer,
				Player secondPlayer, Round round) {
			return new BestofGame(gameCount, firstPlayer, secondPlayer, round);
		}
	}
}
