package com.github.hborders.rockpaperscissors;

public class DefaultGameFactoryFactory {

	private final DefaultGameFactory.Provider defaultFactoryProvider;
	private final Round round;
	private final WonRoundCount.Provider wonRoundCountProvider;
	private final Game.Provider gameProvider;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	public DefaultGameFactoryFactory() {
		this(new DefaultGameFactory.Provider(), new Round(),
				new WonRoundCount.Provider(), new Game.Provider(),
				new ToGameFactoryFactory(), new BestofGameFactoryFactory());
	}

	DefaultGameFactoryFactory(
			DefaultGameFactory.Provider defaultFactoryProvider, Round round,
			WonRoundCount.Provider wonRoundCountProvider,
			Game.Provider gameProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		this.defaultFactoryProvider = defaultFactoryProvider;
		this.round = round;
		this.wonRoundCountProvider = wonRoundCountProvider;
		this.gameProvider = gameProvider;
		this.toGameFactoryFactory = toGameFactoryFactory;
		this.bestofGameFactoryFactory = bestofGameFactoryFactory;
	}

	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return defaultFactoryProvider.provide(round, wonRoundCountProvider,
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
