package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Round {
	private final AttemptReader attemptReader;
	private final IAfterPlayHook afterPlayHook;

	public Round() {
		this(new AttemptReader(), new NoOpAfterPlayHook());
	}

	public Round(AttemptReader attemptReader, IAfterPlayHook afterPlayHook) {
		this.attemptReader = attemptReader;
		this.afterPlayHook = afterPlayHook;
	}

	public Player play(Player firstPlayer, Player secondPlayer)
			throws IOException {
		Attempt firstPlayerAttempt;
		Attempt secondPlayerAttempt;
		do {
			firstPlayerAttempt = attemptReader.createAttempt(firstPlayer);
			secondPlayerAttempt = attemptReader.createAttempt(secondPlayer);
		} while (!firstPlayerAttempt.beats(secondPlayerAttempt)
				&& !secondPlayerAttempt.beats(firstPlayerAttempt));

		Player winningPlayer;
		if (firstPlayerAttempt.beats(secondPlayerAttempt)) {
			winningPlayer = firstPlayer;
		} else {
			winningPlayer = secondPlayer;
		}

		winningPlayer.wonRound();
		afterPlayHook.afterPlay(winningPlayer);
		return winningPlayer;
	}

	protected void postRoundHook(Player winningPlayer) {

	}

	public interface IAfterPlayHook {
		void afterPlay(Player winningPlayer);
	}

	static class Provider {
		public Round provide(AttemptReader attemptReader,
				IAfterPlayHook afterPlayHook) {
			return new Round(attemptReader, afterPlayHook);
		}
	}
}
