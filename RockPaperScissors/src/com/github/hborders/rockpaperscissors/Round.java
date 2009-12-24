package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Round {
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final AttemptReader attemptReader;

	public Round(Player firstPlayer, Player secondPlayer) {
		this(firstPlayer, secondPlayer, new AttemptReader());
	}

	public Round(Player firstPlayer, Player secondPlayer,
			AttemptReader attemptReader) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.attemptReader = attemptReader;
	}

	public void play() throws IOException {
		Attempt firstPlayerAttempt;
		Attempt secondPlayerAttempt;
		do {
			firstPlayerAttempt = attemptReader.createAttempt(firstPlayer);
			secondPlayerAttempt = attemptReader.createAttempt(secondPlayer);
		} while (!firstPlayerAttempt.beats(secondPlayerAttempt)
				&& !secondPlayerAttempt.beats(firstPlayerAttempt));

		if (firstPlayerAttempt.beats(secondPlayerAttempt)) {
			firstPlayer.wonGame();
		} else {
			secondPlayer.wonGame();
		}
	}

	static class Provider {
		public Round provide(Player firstPlayer, Player secondPlayer,
				AttemptReader attemptReader) {
			return new Round(firstPlayer, secondPlayer, attemptReader);
		}
	}
}
