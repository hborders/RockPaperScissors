package com.github.hborders.rockpaperscissors;

public class DefaultGameFactoryFactory {

	private final GameFactory.Provider gameFactoryProvider;
	private final Round round;
	private final WonRoundCount defaultWinningWonRoundCount;
	private final Game.Provider gameProvider;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	public DefaultGameFactoryFactory() {
		this(new GameFactory.Provider(), new Round(), new WonRoundCount(1),
				new Game.Provider(), new ToGameFactoryFactory(),
				new BestofGameFactoryFactory());
	}

	DefaultGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			Round round, WonRoundCount defaultWinningWonRoundCount,
			Game.Provider gameProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		this.gameFactoryProvider = gameFactoryProvider;
		this.round = round;
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
					round, gameProvider);
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
