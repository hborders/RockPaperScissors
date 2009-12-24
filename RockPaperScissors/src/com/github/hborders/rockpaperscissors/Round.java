package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Round {
	private final AttemptReader attemptReader;

	public Round() {
		this(new AttemptReader());
	}

	public Round(AttemptReader attemptReader) {
		this.attemptReader = attemptReader;
	}

	public void play(Player firstPlayer, Player secondPlayer)
			throws IOException {
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
}
