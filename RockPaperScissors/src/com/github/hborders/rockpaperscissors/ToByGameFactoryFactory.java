package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToByWonRoundCountFactory toByWonRoundCountFactory;
	private final AttemptReader attemptReader;

	ToByGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToByWonRoundCountFactory toByWonRoundCountFactory,
			AttemptReader attemptReader) {
		this.roundCountFactory = roundCountFactory;
		this.toByWonRoundCountFactory = toByWonRoundCountFactory;
		this.attemptReader = attemptReader;
	}

	public GameFactory createGameFactory(RoundCount toRoundCount, String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 4) {
			try {
				RoundCount byRoundCount = roundCountFactory
						.createRoundCount(args[3]);
				WonRoundCount winningWonRoundCount = toByWonRoundCountFactory
						.createWinningWonRoundCount(toRoundCount, byRoundCount);
				WonRoundCount extendingWonRoundCount = toByWonRoundCountFactory
						.createExtendingWonRoundCount(toRoundCount,
								byRoundCount);
				ToByAfterPlayHookFactory toByAfterPlayHookFactory = new ToByAfterPlayHookFactory(
						extendingWonRoundCount, winningWonRoundCount);
				return new GameFactory(winningWonRoundCount,
						toByAfterPlayHookFactory, attemptReader);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
