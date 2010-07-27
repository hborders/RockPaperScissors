package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameFactory.Provider;
import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.google.inject.Inject;

public class DefaultGameFactoryFactory {

	private final GameFactory.Provider gameFactoryProvider;
	private final WonRoundCount defaultWinningWonRoundCount;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;
	private final Round.Provider roundProvider;
	private final Game.Provider gameProvider;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	@Inject
	public DefaultGameFactoryFactory(Provider gameFactoryProvider,
			WonRoundCount defaultWinningWonRoundCount,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader, Round.Provider roundProvider,
			Game.Provider gameProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		this.gameFactoryProvider = gameFactoryProvider;
		this.defaultWinningWonRoundCount = defaultWinningWonRoundCount;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
		this.roundProvider = roundProvider;
		this.gameProvider = gameProvider;
		this.toGameFactoryFactory = toGameFactoryFactory;
		this.bestofGameFactoryFactory = bestofGameFactoryFactory;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return gameFactoryProvider.provide(defaultWinningWonRoundCount,
					noOpAfterPlayHookFactory, attemptReader, roundProvider,
					gameProvider);
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
