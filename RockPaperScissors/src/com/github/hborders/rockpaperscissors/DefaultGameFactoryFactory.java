package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;

public class DefaultGameFactoryFactory {

	private final WonRoundCount defaultWinningWonRoundCount;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	public DefaultGameFactoryFactory(WonRoundCount defaultWinningWonRoundCount,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		this.defaultWinningWonRoundCount = defaultWinningWonRoundCount;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
		this.toGameFactoryFactory = toGameFactoryFactory;
		this.bestofGameFactoryFactory = bestofGameFactoryFactory;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return new GameFactory(defaultWinningWonRoundCount,
					noOpAfterPlayHookFactory, attemptReader);
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
