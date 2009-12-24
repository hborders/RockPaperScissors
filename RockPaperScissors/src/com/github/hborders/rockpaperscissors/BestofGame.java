package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class BestofGame implements IGame {
	private final int gameCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;

	public BestofGame(int gameCount, Player firstPlayer, Player secondPlayer) {
		this(gameCount, firstPlayer, secondPlayer, new Round.Provider(),
				new AttemptReader());
	}

	BestofGame(int gameCount, Player firstPlayer, Player secondPlayer,
			Round.Provider roundProvider, AttemptReader attemptReader) {
		this.gameCount = gameCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
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
				Round round = roundProvider.provide(firstPlayer, secondPlayer,
						attemptReader);
				round.play();
			}
		}
	}

	static class Provider {
		public BestofGame provide(int gameCount, Player firstPlayer,
				Player secondPlayer, Round.Provider roundProvider,
				AttemptReader attemptReader) {
			return new BestofGame(gameCount, firstPlayer, secondPlayer,
					roundProvider, attemptReader);
		}
	}
}
