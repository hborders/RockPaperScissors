package com.github.hborders.rockpaperscissors;

import java.io.IOException;

public class Round {
	private final AttemptReader attemptReader;
	private final IAfterPlayHook afterPlayHook;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afterPlayHook == null) ? 0 : afterPlayHook.hashCode());
		result = prime * result
				+ ((attemptReader == null) ? 0 : attemptReader.hashCode());
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
		Round other = (Round) obj;
		if (afterPlayHook == null) {
			if (other.afterPlayHook != null)
				return false;
		} else if (!afterPlayHook.equals(other.afterPlayHook))
			return false;
		if (attemptReader == null) {
			if (other.attemptReader != null)
				return false;
		} else if (!attemptReader.equals(other.attemptReader))
			return false;
		return true;
	}

	public interface IAfterPlayHook {
		void afterPlay(Player winningPlayer);
	}

	public interface IAfterPlayHookFactory {
		IAfterPlayHook createAfterPlayHook(Player firstPlayer,
				Player secondPlayer);
	}
}
