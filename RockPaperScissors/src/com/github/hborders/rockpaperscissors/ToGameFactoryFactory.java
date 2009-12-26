package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToWonRoundCountFactory toWonRoundCountFactory;
	private final ToByGameFactoryFactory toByGameFactoryFactory;
	private final Game.Provider gameProvider;
	private final Round toRound;
	private final GameFactory.Provider gameFactoryProvider;

	ToGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToWonRoundCountFactory toWonRoundCountFactory,
			ToByGameFactoryFactory toByGameFactoryFactory,
			Game.Provider gameProvider, Round toRound,
			GameFactory.Provider gameFactoryProvider) {
		this.roundCountFactory = roundCountFactory;
		this.toWonRoundCountFactory = toWonRoundCountFactory;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
		this.gameProvider = gameProvider;
		this.toRound = toRound;
		this.gameFactoryProvider = gameFactoryProvider;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (2 <= args.length) {
			try {
				RoundCount toRoundCount = roundCountFactory
						.createRoundCount(args[1]);
				if (2 < args.length) {
					return toByGameFactoryFactory.createGameFactory(
							toRoundCount, args);
				}

				WonRoundCount winningWonRoundCount = toWonRoundCountFactory
						.createWonRoundCount(toRoundCount);

				return gameFactoryProvider.provide(winningWonRoundCount,
						toRound, gameProvider);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
