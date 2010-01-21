package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;
import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class GameFactory {
	private final WonRoundCount winningWonRoundCount;
	private final IAfterPlayHookFactory afterPlayHookFactory;
	private final AttemptReader attemptReader;

	public GameFactory(WonRoundCount winningWonRoundCount,
			IAfterPlayHookFactory afterPlayHookFactory,
			AttemptReader attemptReader) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.afterPlayHookFactory = afterPlayHookFactory;
		this.attemptReader = attemptReader;
	}

	public Game createGame(Player firstPlayer, Player secondPlayer) {
		IAfterPlayHook afterPlayHook = afterPlayHookFactory
				.createAfterPlayHook(firstPlayer, secondPlayer);
		Round round = new Round(attemptReader, afterPlayHook);
		return new Game(winningWonRoundCount, firstPlayer, secondPlayer, round);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((afterPlayHookFactory == null) ? 0 : afterPlayHookFactory
						.hashCode());
		result = prime * result
				+ ((attemptReader == null) ? 0 : attemptReader.hashCode());
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
		GameFactory other = (GameFactory) obj;
		if (afterPlayHookFactory == null) {
			if (other.afterPlayHookFactory != null)
				return false;
		} else if (!afterPlayHookFactory.equals(other.afterPlayHookFactory))
			return false;
		if (attemptReader == null) {
			if (other.attemptReader != null)
				return false;
		} else if (!attemptReader.equals(other.attemptReader))
			return false;
		if (winningWonRoundCount == null) {
			if (other.winningWonRoundCount != null)
				return false;
		} else if (!winningWonRoundCount.equals(other.winningWonRoundCount))
			return false;
		return true;
	}
}
