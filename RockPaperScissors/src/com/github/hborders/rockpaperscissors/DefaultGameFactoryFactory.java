package com.github.hborders.rockpaperscissors;

public class DefaultGameFactoryFactory {

	private final GameFactory.Provider gameFactoryProvider;
	private final Round defaultRound;
	private final WonRoundCount defaultWinningWonRoundCount;
	private final Game.Provider gameProvider;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	DefaultGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			Round defaultRound, WonRoundCount defaultWinningWonRoundCount,
			Game.Provider gameProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		this.gameFactoryProvider = gameFactoryProvider;
		this.defaultRound = defaultRound;
		this.defaultWinningWonRoundCount = defaultWinningWonRoundCount;
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
					defaultRound, gameProvider);
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
