package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToByWonRoundCountFactory toByWonRoundCountFactory;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;
	private final ToByAfterPlayHook.Provider toByAfterPlayHookProvider;
	private final GameFactory.Provider gameFactoryProvider;
	private final Game.Provider gameProvider;

	ToByGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToByWonRoundCountFactory toByWonRoundCountFactory,
			Round.Provider roundProvider, AttemptReader attemptReader,
			ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
			GameFactory.Provider gameFactoryProvider, Game.Provider gameProvider) {
		this.roundCountFactory = roundCountFactory;
		this.toByWonRoundCountFactory = toByWonRoundCountFactory;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
		this.toByAfterPlayHookProvider = toByAfterPlayHookProvider;
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
				ToByAfterPlayHook toByAfterPlayHook = toByAfterPlayHookProvider
						.provide(winningWonRoundCount);
				Round round = roundProvider.provide(attemptReader,
						toByAfterPlayHook);
				return gameFactoryProvider.provide(winningWonRoundCount, round,
						gameProvider);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
