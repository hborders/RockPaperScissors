package com.github.hborders.rockpaperscissors;


public class DefaultGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	public DefaultGameFactoryFactory() {
		this(new GameFactory.Provider(), new GameCount.Provider(),
				new ToGameFactoryFactory(), new BestofGameFactoryFactory());
	}

	DefaultGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			GameCount.Provider gameCountProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		super(gameFactoryProvider, gameCountProvider);
		this.toGameFactoryFactory = toGameFactoryFactory;
		this.bestofGameFactoryFactory = bestofGameFactoryFactory;
	}

	@Override
	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return gameFactoryProvider.provide();
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
