package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameFactory.Provider;
import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToByGameFactoryFactory toByGameFactoryFactory;
	private final ToWonRoundCountFactory toWonRoundCountFactory;
	private final GameFactory.Provider gameFactoryProvider;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;
	private final Round.Provider roundProvider;
	private final Game.Provider gameProvider;

	ToGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToByGameFactoryFactory toByGameFactoryFactory,
			ToWonRoundCountFactory toWonRoundCountFactory,
			Provider gameFactoryProvider,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader,
			com.github.hborders.rockpaperscissors.Round.Provider roundProvider,
			com.github.hborders.rockpaperscissors.Game.Provider gameProvider) {
		this.roundCountFactory = roundCountFactory;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
		this.toWonRoundCountFactory = toWonRoundCountFactory;
		this.gameFactoryProvider = gameFactoryProvider;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
		this.roundProvider = roundProvider;
		this.gameProvider = gameProvider;
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
						noOpAfterPlayHookFactory, attemptReader, roundProvider,
						gameProvider);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
