package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToByWonRoundCountFactory toByWonRoundCountFactory;
	private final ToByAfterPlayHookFactory.Provider toByAfterPlayHookFactoryProvider;
	private final ToByAfterPlayHook.Provider toByAfterPlayHookProvider;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;
	private final GameFactory.Provider gameFactoryProvider;
	private final Game.Provider gameProvider;

	ToByGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToByWonRoundCountFactory toByWonRoundCountFactory,
			ToByAfterPlayHookFactory.Provider toByAfterPlayHookFactoryProvider,
			ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
			Round.Provider roundProvider, AttemptReader attemptReader,
			GameFactory.Provider gameFactoryProvider, Game.Provider gameProvider) {
		this.roundCountFactory = roundCountFactory;
		this.toByWonRoundCountFactory = toByWonRoundCountFactory;
		this.toByAfterPlayHookFactoryProvider = toByAfterPlayHookFactoryProvider;
		this.toByAfterPlayHookProvider = toByAfterPlayHookProvider;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
		this.gameFactoryProvider = gameFactoryProvider;
		this.gameProvider = gameProvider;
	}

	public GameFactory createGameFactory(RoundCount toRoundCount, String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 4) {
			try {
				RoundCount byRoundCount = roundCountFactory
						.createRoundCount(args[3]);
				WonRoundCount winningWonRoundCount = toByWonRoundCountFactory
						.createWonRoundCount(toRoundCount, byRoundCount);
				ToByAfterPlayHookFactory toByAfterPlayHookFactory = toByAfterPlayHookFactoryProvider
						.provide(toByAfterPlayHookProvider,
								winningWonRoundCount);
				return gameFactoryProvider.provide(winningWonRoundCount,
						toByAfterPlayHookFactory, attemptReader, roundProvider,
						gameProvider);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
